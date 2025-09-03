package com.SpringExaminationSystem.model.mapper.exam;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.SpringExaminationSystem.model.dto.common.StudentExamDTO;
import com.SpringExaminationSystem.model.dto.response.exam.StudentExamResponse;
import com.SpringExaminationSystem.model.entity.exam.student.StudentExam;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface StudentExamMapper {

    @Mapping(source = "studentExamId", target = "studentExamId")
    @Mapping(source = "examStatus", target = "examStatus")
    @Mapping(source = "examStatus", target = "examStatusInfo", qualifiedByName = "getExamStatusInfo")
    @Mapping(source = "score", target = "score")
    @Mapping(source = "submitTime", target = "submitTime")
    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "examDetail", target = "examDetail")
    @Mapping(source = "studentChoice", target = "studentChoice")
    @Mapping(source = "exam.examId", target = "examId")
    @Mapping(source = "exam.examName", target = "examName")
    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "user.authInfo.userName", target = "userName")
    StudentExamResponse toResponse(StudentExam studentExam);

    @Named("getExamStatusInfo")
    default String getExamStatusInfo(int examStatus) {
        return StudentExam.EXAM_STATUS_INFO[examStatus];
    }

    @Mapping(source = "studentExamId", target = "studentExamId")
    @Mapping(source = "examStatus", target = "examStatus")
    @Mapping(source = "examStatus", target = "examStatusInfo", qualifiedByName = "getExamStatusInfo")
    @Mapping(source = "score", target = "score")
    @Mapping(source = "submitTime", target = "submitTime")
    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "exam.examId", target = "examId")
    @Mapping(source = "exam.examName", target = "examName")
    StudentExamDTO toDto(StudentExam studentExam);
}
