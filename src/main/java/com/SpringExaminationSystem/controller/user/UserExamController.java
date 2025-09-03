package com.SpringExaminationSystem.controller.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringExaminationSystem.model.dto.common.StudentExamDTO;
import com.SpringExaminationSystem.service.exam.ExamHistoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/student/exam")
@RequiredArgsConstructor
public class UserExamController {
    private final ExamHistoryService examHistoryService;

    @GetMapping(path = "/history")
    public ResponseEntity<List<StudentExamDTO>> getExamHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return ResponseEntity.ok(examHistoryService.getByUsername(username));
    }
}