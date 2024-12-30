package com.tododev.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder
public class DdayRequestDto {
    public String ddayDate;
    public String ddayContent;

    @Override
    public String toString() {
        return new StringJoiner(",", SignInDto.class.getSimpleName() + "[", "]")
                .add("ddayDate=" + ddayDate)
                .add("ddayContent=" + ddayContent)
                .toString();

    }
    public LocalDateTime getParsedOrderDate() {
        return getLocalDateTime(ddayDate);
    }
    public static LocalDateTime getLocalDateTime(String time) {
        if (!org.springframework.util.StringUtils.hasLength(time))
            return null;
        if (!time.matches("\\d{12}"))  // 12자리 문자열 체크
            return null;

        // DateTimeFormatter를 사용하여 문자열을 LocalDateTime으로 변환
        DateTimeFormatter formatter_yyMMddHHmmss = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        return LocalDateTime.parse(time, formatter_yyMMddHHmmss);
    }
}
