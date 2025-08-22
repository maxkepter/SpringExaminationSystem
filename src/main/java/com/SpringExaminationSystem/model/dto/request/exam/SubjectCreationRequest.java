package com.SpringExaminationSystem.model.dto.request.exam;

import java.util.List;

import com.SpringExaminationSystem.model.dto.common.MajorDTO;
import com.SpringExaminationSystem.model.entity.exam.Major;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class SubjectCreationRequest {
    String subjectCode;
    String subjectName;
    List<MajorDTO> majors;
    List<String> chapters;
}