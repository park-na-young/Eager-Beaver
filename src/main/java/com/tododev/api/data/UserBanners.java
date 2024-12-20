package com.tododev.api.data;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.StringJoiner;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserBanners {

    @Id
    @Column(name = "user_id", nullable = false)
    private String userid;

    @Column(name = "image_url", nullable = false)
    private String imageUrl = "src/main/resources/image/defaultImage.png";

    @Column(name = "font_color", nullable = false)
    private String fontColor = "#000000";

    @Column(name = "dday_date")
    private String ddayDate;


    @Override
    public String toString() {
        return new StringJoiner(", ", UserBanners.class.getSimpleName() + "[", "]")
                .add("userid=" + userid)
                .add("imageUrl=" + imageUrl)
                .add("fontColor=" + fontColor)
                .add("ddayDate=" + ddayDate)
                .toString();
    }
}
