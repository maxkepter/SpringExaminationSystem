package com.SpringExaminationSystem.model.dto.response.exam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.SpringExaminationSystem.model.entity.exam.student.QuestionWithOptions;
import com.SpringExaminationSystem.model.entity.exam.student.StudentExam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentExamResponse {

    private Integer studentExamId;
    private Integer examStatus;
    private String examStatusInfo;
    private float score;
    private LocalDateTime submitTime;
    private LocalDateTime startTime;
    private List<QuestionWithOptions> examDetail;
    private Map<Integer, Set<Integer>> studentChoice;
    private Integer examId;
    private String examName;
    private Integer userId;
    private String userName;

    public static StudentExamResponse fromEntity(StudentExam studentExam) {
        return StudentExamResponse.builder()
                .studentExamId(studentExam.getStudentExamId())
                .examStatus(studentExam.getExamStatus())
                .examStatusInfo(StudentExam.EXAM_STATUS_INFO[studentExam.getExamStatus()])
                .score(studentExam.getScore())
                .submitTime(studentExam.getSubmitTime())
                .startTime(studentExam.getStartTime())
                .examDetail(studentExam.getExamDetail())
                .studentChoice(studentExam.getStudentChoice())
                .examId(studentExam.getExam().getExamId())
                .examName(studentExam.getExam().getExamName())
                .userId(studentExam.getUser().getUserId())
                .userName(studentExam.getUser().getAuthInfo().getUserName())
                .build();
    }
}
