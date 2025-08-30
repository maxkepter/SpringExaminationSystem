package com.SpringExaminationSystem.model.entity.exam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.context.annotation.Scope;

import com.SpringExaminationSystem.model.entity.BaseEntity;
import com.SpringExaminationSystem.model.entity.user.User;
import com.SpringExaminationSystem.model.enums.ExamStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Scope("prototype")
@Entity
@Table(name = "Exam")
@SQLDelete(sql = "update Exam set isActive=0 where examCode=?")
public class Exam extends BaseEntity {
    public static final String EXAM_DURATION = "duration";
    public static final String EXAM_CODE = "examCode";
    public static final String EXAM_NAME = "examName";

    @Id
    @Column(nullable = false, unique = true, length = 50)
    private String examCode;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private LocalDateTime examDate;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = false, length = 100)
    private String examName;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "duration_minutes")
    private int durationMinutes;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "max_attempts")
    private Integer maxAttempts;

    @Column(name = "show_result_immediately")
    private Boolean showResultImmediately;

    @Column(name = "shuffle_questions")
    private Boolean shuffleQuestions;

    @Column(name = "shuffle_options")
    private Boolean shuffleOptions;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ExamStatus status;

    @ManyToOne
    @JoinColumn(name = "subjectId", nullable = false)
    private Subject subject;

    @ManyToMany(targetEntity = Question.class)
    @JoinTable(name = "Exam_Question", joinColumns = @JoinColumn(name = "examCode"), inverseJoinColumns = @JoinColumn(name = "questionId"))
    private List<Question> questions;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

}