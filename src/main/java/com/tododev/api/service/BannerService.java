package com.tododev.api.service;

import com.tododev.api.data.UserBanners;
import com.tododev.api.dto.*;
import com.tododev.api.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Slf4j
@Service
public class BannerService {
    private static final String UPLOAD_DIR = "/Beaver/uploads/image";
    private final UserBannersRepository userBannersRepository;
    private final UserBannerJpaRepository userBannerJpaRepository;

    public BannerService(
            UserBannersRepository userBannersRepository,
            UserBannerJpaRepository userBannerJpaRepository) {
        this.userBannersRepository = userBannersRepository;
        this.userBannerJpaRepository = userBannerJpaRepository;
    }

    /**
     * 이미지 업로드
     *
     * @param userid
     * @param requestBody
     * @param image
     * @return
     */
    @Transactional
    public ApiResponseDto uploadImage(String userid, UserBannersDto requestBody, MultipartFile image) {
        try {
            // 이미지 파일을 지정된 디텍토리에 저장하는 메소드 호출
            String imageUrl = saveImage(image);

            // UserBanners 객체 생성 및 이미지 경로 설정
            UserBanners userBanners = new UserBanners();
            userBanners.setUserid(userid);
            userBanners.setImageUrl(imageUrl);

            // DB에 이미지 경로 저장
            saveImagePath(userBanners);

            return ApiResponseDto.success(HttpStatus.OK);
        } catch (Exception e) {
            log.error("사용자 정의 배너 이미지 업로드 실패 - {}", requestBody, e);
            return ApiResponseDto.error("EOO3", "이미지 업로드 중 오류가 발생하였습니다.");
        }

    }


    /**
     * 이미지 파일을 저장하는 메서드
     */
    public String saveImage(MultipartFile image) {
        try {
            // 사용자가 업로드한 실제 파일명
            String originalFilename = image.getOriginalFilename();
            String storedFileName = UUID.randomUUID().toString() + "_" + originalFilename;

            // 파일을 저장할 디렉토리 경로 설정 (서버의 업로드 디렉토리)
            File uploadDir = new File(UPLOAD_DIR);

            //디렉토리 없으면 생성
            if (!uploadDir.exists()) {
                boolean created = uploadDir.mkdirs();  // 부모 디렉토리도 생성
                log.info("디렉토리 생성 시도 결과: {}", created);
                if (!created) {
                    log.info("<<<<<<<<<<<<>>>>>>>>>>={}",uploadDir);
                    throw new RuntimeException("디렉토리 생성 실패: " + uploadDir.getAbsolutePath());
                }
            }

            String filePath = uploadDir.getAbsolutePath() + "/" + storedFileName;
            File destinationFile = new File(filePath);
            image.transferTo(destinationFile);

            // 저장된 이미지 파일의 상대 경로 반환
            return "/uploads/banner/" + storedFileName;

        } catch (IOException e) {
            throw new RuntimeException("<< BannerService (saveImage) 이미지 저장 실패", e);
        }
    }

    /**
     * 이미지 파일 경로 저장 메서드
     */
    public void saveImagePath(UserBanners userBanners) {
        // userBannersRepository를 통해 userid가 존재하는지 확인
        Optional<UserBanners> existingBanner = userBannersRepository.findByUserid(userBanners.getUserid());

        if (existingBanner.isEmpty()) {
            // 존재하지 않을 경우 새 배너 데이터 삽입
            userBannerJpaRepository.insertUserBanners(
                    userBanners.getUserid(),
                    userBanners.getImageUrl(),
                    "#000000",
                    null
            );
        } else {
            // 존재할 경우 이미지 경로만 업데이트
            userBannerJpaRepository.updateImageUrl(
                    userBanners.getUserid(),
                    userBanners.getImageUrl()
            );
        }
    }

}

