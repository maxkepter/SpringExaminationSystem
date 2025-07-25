package com.SpringExaminationSystem.model.exam;

import org.springframework.context.annotation.Scope;

import com.SpringExaminationSystem.model.BaseEntity;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
@Entity
@Table(name = "Chapter")
public class Chapter extends BaseEntity {
    @Id
    private Integer chapterID;

    @Column(nullable = false)
    private int chapterNo;

    @Column(nullable = false)
    private boolean isDisable;

    @ManyToOne
    @JoinColumn(name = "subID", nullable = false)
    private Subject subject;

}