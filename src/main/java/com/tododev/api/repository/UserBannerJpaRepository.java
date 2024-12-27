package com.tododev.api.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tododev.api.data.UserBanners;
import com.tododev.api.dto.UserBannersDto;
import org.springframework.stereotype.Repository;

import static com.tododev.api.data.QUserBanners.userBanners;
@Repository
public class UserBannerJpaRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final UserBannersRepository userBannersRepository;

    public UserBannerJpaRepository(JPAQueryFactory jpaQueryFactory,
                                   UserBannersRepository userBannersRepository
                                   ) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.userBannersRepository = userBannersRepository;
    }

    /**
     * DB INSERT : table UserBanners
     */
    public UserBanners insertUserBanners(
            String userid,
            String imageUrl,
            String fontColor,
            String ddayDate
    ) {
        UserBanners entity = UserBanners.builder()
                .userid(userid)
                .imageUrl(imageUrl)
                .fontColor(fontColor)
                .ddayDate(ddayDate)
                .build();

        return userBannersRepository.save(entity);
    }

    /**
     * DB UPDATE : table UserBanners - imageUrl
     */
    public void updateImageUrl(
            String userid,
            String imageUrl
            ) {
        jpaQueryFactory.update(userBanners)
                .set(userBanners.imageUrl, imageUrl)
                .where(userBanners.userid.eq(userid))
                .execute();
    }
}
