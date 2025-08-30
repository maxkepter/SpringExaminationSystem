package com.SpringExaminationSystem.model.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminQuestionResponse {
    private Integer questionId;
    private String content;
    private String type;
    private Integer difficulty;
    private Integer points;
    private String chapterName;
    private List<AdminOptionResponse> options;
}
