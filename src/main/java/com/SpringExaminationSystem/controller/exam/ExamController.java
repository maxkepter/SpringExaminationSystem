package com.SpringExaminationSystem.controller.exam;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringExaminationSystem.model.dto.request.exam.ExamCreateRequest;
import com.SpringExaminationSystem.model.dto.response.ExamResponse;
import com.SpringExaminationSystem.service.exam.ExamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;

    @GetMapping
    public ResponseEntity<List<ExamResponse>> getAllExams() {
        List<ExamResponse> responses = examService.getAllActiveExams();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{examCode}")
    public ResponseEntity<ExamResponse> getExamById(@PathVariable String examCode) {
        ExamResponse response = examService.getExamById(examCode);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExamResponse> createExam(@RequestBody ExamCreateRequest request) {
        ExamResponse response = examService.createExam(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
