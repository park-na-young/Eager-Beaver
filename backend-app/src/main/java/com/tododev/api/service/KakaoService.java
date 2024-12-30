package com.tododev.api.service;

import com.tododev.api.data.User;
import com.tododev.api.dto.*;
import com.tododev.api.repository.UserRepository;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoService {
    private final UserRepository userRepository;
    private final String KAUTH_TOKEN_URL_HOST;
    private final String KAUTH_USER_URL_HOST;
    private final PasswordEncoder passwordEncoder;
    private String clientId;

    @Autowired
    public KakaoService(@Value("${kakao.client_id}") String clientId,
                        UserRepository userRepository,
                        PasswordEncoder passwordEncoder
    ) {
        this.clientId = clientId;
        KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
        KAUTH_USER_URL_HOST = "https://kapi.kakao.com";
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * AccessToken 발급
     *
     * @param code
     * @return
     */
    public String getAccessTokenFromKakao(String code) {

        KakaoTokenResponseDto kakaoTokenResponseDto = WebClient.create(KAUTH_TOKEN_URL_HOST).post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientId)
                        .queryParam("code", code)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                //TODO : Custom Exception
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoTokenResponseDto.class)
                .block();


        log.info(" [Kakao Service] Access Token ------> {}", kakaoTokenResponseDto.getAccessToken());
        log.info(" [Kakao Service] Refresh Token ------> {}", kakaoTokenResponseDto.getRefreshToken());
        //제공 조건: OpenID Connect가 활성화 된 앱의 토큰 발급 요청인 경우 또는 scope에 openid를 포함한 추가 항목 동의 받기 요청을 거친 토큰 발급 요청인 경우
        log.info(" [Kakao Service] Id Token ------> {}", kakaoTokenResponseDto.getIdToken());
        log.info(" [Kakao Service] Scope ------> {}", kakaoTokenResponseDto.getScope());

        return kakaoTokenResponseDto.getAccessToken();
    }

    /**
     * 카카오 getUserInfo
     *
     * @param accessToken
     * @return
     */
    public KakaoUserInfoResponseDto getUserInfo(String accessToken) {

        KakaoUserInfoResponseDto userInfo = WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) // access token 인가
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                //TODO : Custom Exception
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoUserInfoResponseDto.class)
                .block();

        log.info("[ Kakao Service ] Auth ID ---> {} ", userInfo.getId());
        log.info("[ Kakao Service ] NickName ---> {} ", userInfo.getKakaoAccount().getProfile().getNickName());
        log.info("[ Kakao Service ] ProfileImageUrl ---> {} ", userInfo.getKakaoAccount().getProfile().getProfileImageUrl());

        return userInfo;
    }

    /**
     * 카카오 회원가입 (DB insert) API
     *
     * @param userInfo
     * @return
     */
    public ApiResponseDto signUpWithKakao(KakaoUserInfoResponseDto userInfo) {
        log.info("<<< KakaoService >>> signUpWithKakao (카카오 회원가입) : userInfo ={}", userInfo);
        String kakaoId = String.valueOf(userInfo.getId());
        String nickname = userInfo.getKakaoAccount().getProfile().getNickName();

        if (userRepository.existsByUserid(kakaoId)) {
            return ApiResponseDto.error("E006", "이미 등록된 카카오 계정입니다.");
        }

        // 비밀번호 랜덤 생성
        String randomPassword = UUID.randomUUID().toString();
        String encodedPassword = "KAKAO_" + passwordEncoder.encode(randomPassword);

        //User 엔티티 생성 및 저장
        User user = User.builder()
                .userid(kakaoId)
                .username(nickname)
                .password(encodedPassword)
                .role("USER")
                .build();

        userRepository.save(user);

        log.info("<<< KakaoService >>> signUpWithKakao (카카오 회원가입 성공, success!!) : user ={}", user);
        return ApiResponseDto.success("카카오 회원가입이 완료되었습니다.",HttpStatus.OK);
    }

}
