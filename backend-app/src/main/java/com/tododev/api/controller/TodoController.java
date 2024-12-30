package com.tododev.api.controller;

import com.tododev.api.data.TodoContent;
import com.tododev.api.dto.*;
import com.tododev.api.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    public final TodoService todoService;
    @Operation(
            summary = "TODO 저장",
            description = "todo리스트를 저장할 수 있습니다."
    )
    @PostMapping("/save")
    public ResponseEntity<ApiResponseDto> saveTodoContent(
            WebRequest webRequest,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody TodoContentDto requestBody
    ) {
        webRequest.setAttribute("requestBody", requestBody, RequestAttributes.SCOPE_REQUEST);
        return new ResponseEntity<>(todoService.saveTodoList(userDetails.getUsername(), requestBody), HttpStatus.OK);
    }

}
