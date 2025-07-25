package com.SpringExaminationSystem.model.log;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Scope;

import com.SpringExaminationSystem.model.BaseEntity;
import com.SpringExaminationSystem.model.exam.student.StudentExam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
@Entity
@Table(name = "ExamLog")
public class ExamLog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer examLogId;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false, length = 255)
    private String information;

    @ManyToOne
    @JoinColumn(name = "studentExamID", nullable = false)
    private StudentExam studentExam;

}