package com.SpringExaminationSystem.controller.exam;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringExaminationSystem.model.dto.request.exam.StudentExamCreationRequest;
import com.SpringExaminationSystem.model.dto.response.exam.StudentExamResponse;
import com.SpringExaminationSystem.model.entity.exam.student.StudentExam;
import com.SpringExaminationSystem.service.exam.StudentExamService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class StudentExamController {

    private final StudentExamService studentExamService;

    @PostMapping("/student-exam")
    public ResponseEntity<StudentExamResponse> createStudentExam(
            @Valid @RequestBody StudentExamCreationRequest request) {
        // Get username from security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Create student exam using the service
        StudentExam studentExam = studentExamService.getStudentExam(username, request.getExamId());

        // Convert to response DTO and return
        StudentExamResponse response = StudentExamResponse.fromEntity(studentExam);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student-exam/{studentExamId}")
    public ResponseEntity<StudentExamResponse> getStudentExam(@PathVariable Integer studentExamId) {
        // Get the student exam
        StudentExam studentExam = studentExamService.getStudentExamById(studentExamId);

        // Convert to response DTO and return
        StudentExamResponse response = StudentExamResponse.fromEntity(studentExam);
        return ResponseEntity.ok(response);
    }
}
