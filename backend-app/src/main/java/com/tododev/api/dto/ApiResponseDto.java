package com.tododev.api.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto {
    private boolean response;
    private String errorCode;
    private String message;
    private Object result;

    public static ApiResponseDto success(String message, Object result) {
        return new ApiResponseDto(true, null, message, result);
    }

    public static ApiResponseDto error(String errorCode, String message) {
        return new ApiResponseDto(false, errorCode, message, null);
    }

    public Object getResult() {
        return result;
    }
}