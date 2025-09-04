package com.SpringExaminationSystem.model.dto.request.exam;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamCreateRequest {
    @NotBlank(message = "Exam title is required")
    private String title;

    private String description;

    @NotNull(message = "Subject code is required")
    private String subjectCode;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer durationMinutes;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @NotEmpty(message = "Questions list cannot be empty")
    private List<Integer> questionIds;

    @Min(value = 1, message = "Max attempts must be at least 1")
    private Integer maxAttempts;

    private Boolean showResultImmediately;

    private Boolean shuffleQuestions;

    private Boolean shuffleOptions;
}
