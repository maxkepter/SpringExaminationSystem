package com.SpringExaminationSystem.model.exam.student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.SpringExaminationSystem.adapter.ExamDetailConverter;
import com.SpringExaminationSystem.adapter.StudentChoiceConverter;
import com.SpringExaminationSystem.model.BaseEntity;
import com.SpringExaminationSystem.model.exam.Exam;
import com.SpringExaminationSystem.model.user.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "StudentExam")
public class StudentExam extends BaseEntity {
    public static final int EXAM_CLOSED = 0;
    public static final int EXAM_DONE = 1;
    public static final int EXAM_DOING = 2;
    public static final int EXAM_SUSPENDED = 3;
    public static final String[] EXAM_STATUS_INFO = { "Exam closed", "Exam done", "Exam doing", "Exam suspended" };
    public static final String STUDENT_EXAM_ID = "studentExamID";
    public static final String EXAM_STATUS = "examStatus";
    public static final String SCORE = "score";
    public static final String SUBMIT_TIME = "submitTime";
    public static final String START_TIME = "startTime";
    public static final String EXAM_DETAIL = "examDetail";
    public static final String STUDENT_CHOICE = "studentChoice";
    public static final String EXAM = "exam";
    public static final String STUDENT = "student";
    public static final String[] ATTRIBUTE_NAME = {
            STUDENT_EXAM_ID,
            EXAM_STATUS,
            SCORE,
            SUBMIT_TIME,
            START_TIME,
            EXAM_DETAIL,
            STUDENT_CHOICE,
            EXAM,
            STUDENT
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentExamID;

    @Column(nullable = false)
    private int examStatus;

    @Column(nullable = false)
    private float score;

    @Column(nullable = false)
    private LocalDateTime submitTime;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Lob
    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    @Convert(converter = ExamDetailConverter.class)
    private List<QuestionWithOptions> examDetail;

    @Lob
    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    @Convert(converter = StudentChoiceConverter.class)
    private Map<Integer, Set<Integer>> studentChoice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ExamID", nullable = false)
    private Exam exam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    private Student student;

}