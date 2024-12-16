package com.tododev.api.controller;

import com.tododev.api.dto.*;
import com.tododev.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "User Authentication", description = "사용자 인증 및 계정 관리 API")
public class UserController {

    private final UserService userService;

    /**
     * 일반 로그인 API
     *
     * @param webRequest
     * @param requestBody
     * @return
     */
    @Operation(
            summary = "일반 로그인",
            description = "사용자의 이메일과 비밀번호를 통해 로그인을 수행합니다."
    )
    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponseDto> signIn(
            WebRequest webRequest,
            @RequestBody SignInDto requestBody) {
        webRequest.setAttribute("requestBody", requestBody, RequestAttributes.SCOPE_REQUEST);
        log.info("requestBody is perfect request = {}", requestBody);
        return new ResponseEntity<>(userService.signIn(requestBody), HttpStatus.OK);
    }

    /**
     * 회원가입 API
     *
     * @param webRequest
     * @param requestBody
     * @return
     */
    @Operation(
            summary = "회원가입",
            description = "사용자의 정보를 입력받아 새로운 계정을 생성합니다."
    )
    @PostMapping(value = "/sign-up")
    public ResponseEntity<ApiResponseDto> signUp(
            WebRequest webRequest
            , @RequestBody SignUpDto requestBody
    ) {
        webRequest.setAttribute("requestBody", requestBody, RequestAttributes.SCOPE_REQUEST);
        log.info("requestBody is perfect request = {}", requestBody);
        return new ResponseEntity<>(userService.signUp(requestBody), HttpStatus.OK);
    }

}


