package com.tododev.api.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tododev.api.data.TodoContent;
import com.tododev.api.dto.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TodoJpaRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final TodoRepository todoRepository;
    private final ObjectMapper objectMapper;
    public TodoJpaRepository(
            JPAQueryFactory jpaQueryFactory,
            TodoRepository todoRepository,
            ObjectMapper objectMapper
    ) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.todoRepository  = todoRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * DB INSERT : table TodoContent
     * @param userid
     * @param todoContent
     * @return
     */
    public TodoContent insertTodoContent(
            String userid,
            List<ContentListDto> todoContent
    ) throws JsonProcessingException {
        TodoContent entity = TodoContent.builder()
                .userid(userid)
                .todoContent(objectMapper.writeValueAsString(todoContent))
                .build();

        return todoRepository.save(entity);

    }
}
