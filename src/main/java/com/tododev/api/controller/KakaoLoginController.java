package com.tododev.api.controller;


import com.tododev.api.dto.*;
import com.tododev.api.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class KakaoLoginController {

    private final KakaoService kakaoService;

    @GetMapping("/callback")
    public ResponseEntity<ApiResponseDto> callback(
            @RequestParam(required = false) String code
    ) throws IOException {
        // url에 포함된 code를 이용하여 액세스 토큰 발급
        String accessToken = kakaoService.getAccessTokenFromKakao(code);

        // 액세스 토큰을 이용하여 카카오 서버에서 유저 정보(닉네임,이메일) 받아오기
        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);
        //회원가입 처리
        ApiResponseDto response = kakaoService.signUpWithKakao(userInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
