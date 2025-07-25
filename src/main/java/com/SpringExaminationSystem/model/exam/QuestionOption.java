package com.SpringExaminationSystem.model.exam;

import org.springframework.context.annotation.Scope;

import com.SpringExaminationSystem.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
@Scope("prototype")
@Entity
@Table(name = "QuestionOption")
public class QuestionOption extends BaseEntity {
    @Id
    private Integer optionId;

    @Column(nullable = false)
    private boolean isCorrect;

    @Lob
    @Column(nullable = false, columnDefinition = "VARCHAR(MAX)")
    private String optionContent;

    @ManyToOne
    @JoinColumn(name = "questionId", nullable = false)
    private Question question;

}