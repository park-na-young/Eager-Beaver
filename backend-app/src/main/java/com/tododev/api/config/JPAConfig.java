package com.tododev.api.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.*;
import org.springframework.context.annotation.*;

@Configuration
public class JPAConfig {

    @PersistenceContext
    private EntityManager entityManager;

//    public JPAQueryFactory queryFactory() {
//        return new JPAQueryFactory(entityManager);
//    }

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }

}
