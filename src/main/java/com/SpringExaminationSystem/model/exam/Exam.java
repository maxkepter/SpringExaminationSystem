package com.SpringExaminationSystem.model.exam;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Scope;

import com.SpringExaminationSystem.model.ObjectIdentify;
import com.SpringExaminationSystem.model.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Scope("prottype")
@Entity
@Table(name = "Exam")
public class Exam extends ObjectIdentify<Integer> {
    public static final String EXAM_ID = "examID";
    public static final String EXAM_DURATION = "duration";
    public static final String EXAM_CODE = "examCode";
    public static final String EXAM_NAME = "examName";

    @Id
    private Integer examID;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private LocalDateTime examDate;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = false, unique = true, length = 50)
    private String examCode;

    @Column(nullable = false, length = 100)
    private String examName;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @Override
    public Integer getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }
}