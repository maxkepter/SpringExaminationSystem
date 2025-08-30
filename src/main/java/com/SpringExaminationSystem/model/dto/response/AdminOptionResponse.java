package com.SpringExaminationSystem.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminOptionResponse {
    private Integer optionId;
    private String content;
    private Boolean isCorrect;
}
