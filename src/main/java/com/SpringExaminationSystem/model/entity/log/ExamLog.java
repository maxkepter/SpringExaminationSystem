package com.SpringExaminationSystem.model.entity.log;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Scope;

import com.SpringExaminationSystem.model.entity.BaseEntity;
import com.SpringExaminationSystem.model.entity.BaseLog;
import com.SpringExaminationSystem.model.entity.exam.student.StudentExam;

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

import lombok.Builder;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
@Entity
@Table(name = "ExamLog")
public class ExamLog extends BaseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer examLogId;

    @ManyToOne
    @JoinColumn(name = "studentExamId", nullable = false)
    private StudentExam studentExam;

}