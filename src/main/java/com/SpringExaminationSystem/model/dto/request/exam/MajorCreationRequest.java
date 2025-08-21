package com.SpringExaminationSystem.model.dto.request.exam;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class MajorCreationRequest {
    String majorCode;
    String majorName;
}
