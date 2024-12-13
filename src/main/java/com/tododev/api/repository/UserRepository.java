package com.tododev.api.repository;

import com.tododev.api.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUserid(String userid);

    //유저 id 중복검사
    boolean existsByUserid(String userid);
}
