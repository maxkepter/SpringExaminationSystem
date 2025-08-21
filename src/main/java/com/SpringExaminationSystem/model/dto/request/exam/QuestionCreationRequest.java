package com.SpringExaminationSystem.model.dto.request.exam;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class QuestionCreationRequest {
    String questionContent;
    int difficulty;
    Integer chapterId;
    OptionCreationRequest[] options;
}
