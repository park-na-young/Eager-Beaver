package com.tododev.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tododev.api.data.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.StringJoiner;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder
public class ContentListDto {
    private String id;
    private String content;
    private boolean completed;
    private String color;

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("content=" + content)
                .add("completed=" + completed)
                .add("color=" + color)
                .toString();
    }
}
