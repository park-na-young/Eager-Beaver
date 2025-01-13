package com.tododev.api.service;

import com.tododev.api.data.User;
import com.tododev.api.dto.*;
import com.tododev.api.jwt.*;
import com.tododev.api.redis.*;
import com.tododev.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * 일반 로그인
     *
     * @param requestBody
     * @return
     */
    @Transactional
    public ApiResponseDto signIn(SignInDto requestBody) {
        try {
            Optional<User> optionalUser = userRepository.findByUserid(requestBody.getUserid());

            if (optionalUser.isEmpty()) {
                return ApiResponseDto.error("E005", "아이디(로그인 전화번호, 로그인 전용 아이디) 또는 비밀번호가 잘못 되었습니다.");
            }
            // 1. email + password 를 기반으로 Authentication 객체 생성
            // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(requestBody.getUserid(), requestBody.getPassword());

            // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
            // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

            String accessToken = jwtToken.getAccessToken();
            String refreshToken = jwtToken.getRefreshToken();

            // refreshToken redis 저장
            RefreshToken redis = new RefreshToken(refreshToken,requestBody.getUserid());
            refreshTokenRepository.save(redis);
            return ApiResponseDto.success("JWT 토큰이 성공적으로 발급되었습니다",jwtToken);


        } catch (BadCredentialsException e) {
            log.error("사용자 로그인 실패 = {}", requestBody, e);
            return ApiResponseDto.error("E001", "유효하지 않은 자격 증명입니다.");
        } catch (Exception e) {
            log.error("로그인 중 오류 발생 : {}", requestBody, e);
            return ApiResponseDto.error("E002", "로그인 중 오류가 발생했습니다");
        }
    }

    /**
     * 회원가입 API
     * @param requestBody
     * @return
     */
    @Transactional
    public ApiResponseDto signUp(SignUpDto requestBody) {

        if(userRepository.existsByUserid(requestBody.getUserid())){
            return ApiResponseDto.error("E004","이미 사용중인 이메일입니다.");
        }
        //Password 암호화
        String encodedPassword = passwordEncoder.encode(requestBody.getPassword());
        userRepository.save(requestBody.toEntity(encodedPassword));
        return ApiResponseDto.success("회원가입이 성공적으로 완료되었습니다.", HttpStatus.OK);
    }


}
