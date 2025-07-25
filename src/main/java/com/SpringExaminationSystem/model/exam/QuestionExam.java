package com.SpringExaminationSystem.model.exam;

import com.SpringExaminationSystem.model.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@Entity
@Table(name = "QuestionExam")
@IdClass(QuestionExam.class)
public class QuestionExam extends BaseEntity {
    @Id
    private Integer examID;

    @Id
    private int questionId;

    @ManyToOne
    @JoinColumn(name = "examID", insertable = false, updatable = false)
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "questionId", insertable = false, updatable = false)
    private Question question;

}
