package com.tododev.api.controller;


import com.tododev.api.data.User;
import com.tododev.api.dto.*;
import com.tododev.api.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/banner")
@Tag(name = "Custom User Banner", description = "사용자 배너")
public class BannerController {

    private final BannerService bannerService;

    /**
     * 사용자 배너 업로드 API
     * @param webRequest
     * @param userDetails
     * @param path
     * @param image
     * @return
     */
    @Operation(
            summary = "사용자 배너 업로드",
            description = "사용자가 선택한 이미지를 업로드하고 배너 정보를 저장합니다."
    )
    @PostMapping("/upload/{path}")
    public ResponseEntity<ApiResponseDto> uploadBanner(
            WebRequest webRequest,
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable(name ="path") String path,
            @RequestParam(name = "image") MultipartFile image
    ) {
        if (userDetails == null) {
            // 로그인되지 않은 사용자 처리 (예: 401 Unauthorized)
            return new ResponseEntity<>(ApiResponseDto.error("E007", "User not authenticated"), HttpStatus.UNAUTHORIZED);
        }

        // 요청 데이터 생성
        UserBannersDto requestBody = new UserBannersDto();
        requestBody.setUserid(userDetails.getUsername());
        requestBody.setImageUrl(path);
        webRequest.setAttribute("requestBody", requestBody, RequestAttributes.SCOPE_REQUEST);
        return new ResponseEntity<>(bannerService.uploadImage(userDetails.getUsername(), requestBody, image), HttpStatus.OK);
    }

    /**
     * 디데이 업로드 API
     * @param webRequest
     * @param userDetails
     * @param requestBody
     * @return
     */
    @Operation(
            summary = "사용자 배너 - Dday 설정 업로드",
            description = "사용자가 설정한 디데이 배너 정보를 저장합니다."
    )
    @PostMapping("/dday")
    public ResponseEntity<ApiResponseDto> uploadDate(
            WebRequest webRequest,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody DdayRequestDto requestBody
    ) {
        if (userDetails == null) {
            // 로그인되지 않은 사용자 처리 (예: 401 Unauthorized)
            return new ResponseEntity<>(ApiResponseDto.error("E007", "User not authenticated"), HttpStatus.UNAUTHORIZED);
        }
        webRequest.setAttribute("requestBody", requestBody, RequestAttributes.SCOPE_REQUEST);
        return new ResponseEntity<>(bannerService.saveDdayContent(userDetails.getUsername(), requestBody), HttpStatus.OK);
    }

    @GetMapping("/test")
    public String testScreen() {
        return "Hello World! It works!!";
    }


}
