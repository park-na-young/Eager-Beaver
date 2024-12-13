package com.tododev.api.dto;

import lombok.*;

import java.util.StringJoiner;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SignInDto {
    private String userid;
    private String password;

    @Builder
    public SignInDto(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", SignInDto.class.getSimpleName() + "[", "]")
                .add("userid=" + userid)
                .add("password=" + password)
                .toString();

    }
}
