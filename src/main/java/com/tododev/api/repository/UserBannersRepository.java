package com.tododev.api.repository;

import com.tododev.api.data.UserBanners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBannersRepository extends JpaRepository<UserBanners, String> {

    //특정 userid의 배너 정보 조회
    Optional<UserBanners> findByUserid(String userid);

    // save banner


    // update banner

}