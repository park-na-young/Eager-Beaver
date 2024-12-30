package com.tododev.api.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
public class UserBannersDto {
    private String userid;
    private String imageUrl; // 이미지 경로
}
