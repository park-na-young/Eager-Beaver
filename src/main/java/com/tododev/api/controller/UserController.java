package com.tododev.api.controller;

import com.tododev.api.dto.*;
import com.tododev.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    /**
     * 일반 로그인 API
     *
     * @param webRequest
     * @param requestBody
     * @return
     */
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


