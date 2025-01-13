package com.tododev.api.redis;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value="refreshToken",timeToLive = 14440)
public class RefreshToken {

    @Id
    private String refreshToken;

    private String userid;

    public RefreshToken(String refreshToken, String userid) {
        this.refreshToken = refreshToken;
        this.userid = userid;
    }
}