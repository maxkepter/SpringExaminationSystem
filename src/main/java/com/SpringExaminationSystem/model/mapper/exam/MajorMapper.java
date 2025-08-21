package com.SpringExaminationSystem.model.mapper.exam;

import org.mapstruct.Mapper;

import com.SpringExaminationSystem.model.dto.request.exam.MajorCreationRequest;
import com.SpringExaminationSystem.model.entity.exam.Major;

@Mapper(componentModel = "spring")
public interface MajorMapper {
    Major toEntity(MajorCreationRequest request);

    MajorCreationRequest toDto(Major major);
}
