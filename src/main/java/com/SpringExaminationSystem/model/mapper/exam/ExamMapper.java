package com.SpringExaminationSystem.model.mapper.exam;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.SpringExaminationSystem.model.dto.response.ExamResponse;
import com.SpringExaminationSystem.model.entity.exam.Exam;

@Mapper(componentModel = "spring")
public interface ExamMapper {

    @Mapping(source = "examCode", target = "examCode")
    @Mapping(source = "examName", target = "title") // examName -> title
    @Mapping(expression = "java(\"Generated exam\")", target = "description") // default description
    @Mapping(expression = "java(getSubjectNameFromQuestions(exam.getQuestions()))", target = "subjectName") // extract
                                                                                                            // from
                                                                                                            // questions
    @Mapping(source = "duration", target = "durationMinutes")
    @Mapping(source = "examDate", target = "startTime") // examDate -> startTime
    @Mapping(expression = "java(exam.getDeadline().atTime(23, 59, 59))", target = "endTime") // deadline -> endTime
    @Mapping(target = "questions", ignore = true) // ignore questions, handle manually
    @Mapping(expression = "java(exam.getQuestions() != null ? exam.getQuestions().size() : 0)", target = "totalQuestions")
    @Mapping(expression = "java(1)", target = "maxAttempts") // default maxAttempts
    @Mapping(expression = "java(false)", target = "showResultImmediately") // default false
    @Mapping(expression = "java(\"ACTIVE\")", target = "status") // default status
    @Mapping(expression = "java(exam.getUser() != null ? exam.getUser().getFirstName() + \" \" + exam.getUser().getLastName() : \"Unknown\")", target = "createdBy")
    @Mapping(expression = "java(exam.getCreatedAt() != null ? exam.getCreatedAt().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime() : null)", target = "createdAt")
    ExamResponse toExamResponse(Exam exam);

    /**
     * Helper method to extract subject name from questions
     */
    default String getSubjectNameFromQuestions(
            java.util.List<com.SpringExaminationSystem.model.entity.exam.Question> questions) {
        if (questions != null && !questions.isEmpty()) {
            com.SpringExaminationSystem.model.entity.exam.Question firstQuestion = questions.get(0);
            if (firstQuestion.getChapter() != null && firstQuestion.getChapter().getSubject() != null) {
                return firstQuestion.getChapter().getSubject().getSubjectName();
            }
        }
        return "Unknown";
    }
}
