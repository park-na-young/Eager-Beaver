package com.tododev.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tododev.api.data.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder
public class TodoContentDto {
    private List<ContentListDto> todoContent;

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("todoContent=" + todoContent)
                .toString();
    }
}
