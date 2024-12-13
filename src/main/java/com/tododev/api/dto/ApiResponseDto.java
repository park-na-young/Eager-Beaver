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

    public static ApiResponseDto success(Object result) {
        return new ApiResponseDto(true, null, null, result);
    }

    public static ApiResponseDto success(String message, Object result) {
        message = "success response bro";
        return new ApiResponseDto(true, null, message, result);
    }

    public Object getResult() {
        return result;
    }

    public static ApiResponseDto error(String errorCode, String message) {
        return new ApiResponseDto(false, errorCode, message, null);
    }
}