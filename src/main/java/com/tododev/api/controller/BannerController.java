package com.tododev.api.controller;


import com.tododev.api.data.User;
import com.tododev.api.dto.*;
import com.tododev.api.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/banner")
@Tag(name = "Custom User Banner", description = "사용자 배너")
public class BannerController {

    private final BannerService bannerService;

    /**
     * 사용자 배너 업로드 API
     *
     * @param user
     * @param path
     * @param image
     * @return
     */
    @Operation(
            summary = "사용자 배너 업로드",
            description = "사용자가 선택한 이미지를 업로드하고 배너 정보를 저장합니다."
    )
    @PostMapping("upload/{path}")
    public ResponseEntity<ApiResponseDto> uploadBanner(
            WebRequest webRequest,
            @AuthenticationPrincipal User user,
            @PathVariable String path,
            @RequestParam("image") MultipartFile image
    ) {
        // 요청 데이터 생성
        UserBannersDto requestBody = new UserBannersDto();
        requestBody.setUserid(user.getUserid());
        requestBody.setImageUrl(path);
        return new ResponseEntity<>(bannerService.uploadImage(user.getUserid(), requestBody, image), HttpStatus.OK);
    }
}
