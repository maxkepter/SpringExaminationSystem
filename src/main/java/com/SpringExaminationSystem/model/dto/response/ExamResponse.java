package com.SpringExaminationSystem.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponse {
    private String examCode;
    private String title;
    private String description;
    private String subjectName;
    private Integer durationMinutes;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer totalQuestions;
    private Integer maxAttempts;
    private Boolean showResultImmediately;
    private String status; // PENDING, ACTIVE, EXPIRED
    private String createdBy;
    private LocalDateTime createdAt;
    private List<QuestionResponse> questions;
}
