package com.tododev.api.data;

import com.tododev.api.dto.ContentListDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class TodoContent {

    @Id
    private String userid;

    @Column(name = "todo_content")
    private String todoContent;

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("userid=" + userid)
                .add("todoContent=" + todoContent)
                .toString();
    }

}
