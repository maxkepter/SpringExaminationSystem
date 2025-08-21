package com.SpringExaminationSystem.model.dto.request.exam;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class OptionCreationRequest {
    String optionContent;
    boolean isCorrect;

}
