package com.tododev.api.repository;

import com.tododev.api.data.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoContent, Integer> {
    Optional<TodoContent> findByUserid(String userid);

}
