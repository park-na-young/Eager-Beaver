package com.tododev.api.service;

import com.tododev.api.dto.*;
import com.tododev.api.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoJpaRepository todoJpaRepository;

    @Autowired
    public TodoService(
            TodoRepository todoRepository,
            TodoJpaRepository todoJpaRepository
    ) {
        this.todoRepository = todoRepository;
        this.todoJpaRepository = todoJpaRepository;
    }

    /**
     * TODOLIST 저장 API
     *
     * @param userid
     * @param requestBody
     * @return
     */
    public ApiResponseDto saveTodoList(String userid, TodoContentDto requestBody) {
        try {
//            Optional<TodoContent> existingTodoList = todoRepository.findByUserid(userid);
            todoJpaRepository.insertTodoContent(userid, requestBody.getTodoContent());

        } catch (Exception e) {
            log.error("투두 리스트 저장 실패 = {}", requestBody.getTodoContent());
            return ApiResponseDto.error("E009", "투두 리스트 저장 중 오류가 발생했습니다.");
        }
        log.info("<< TodoService (saveTodoList) >> =  id ={}, requestBody = {}", userid, requestBody.getTodoContent());
        return ApiResponseDto.success("정상적으로 TodoList 저장을 완료했습니다.", HttpStatus.OK);
    }
}
