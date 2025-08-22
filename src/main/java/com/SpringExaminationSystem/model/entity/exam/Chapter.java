package com.SpringExaminationSystem.model.entity.exam;

import org.hibernate.annotations.SQLDelete;
import org.springframework.context.annotation.Scope;

import com.SpringExaminationSystem.model.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
@Entity
@Table(name = "Chapter")
@SQLDelete(sql = "update Chapter set isActive=0 where chapterId=?")
public class Chapter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer chapterId;

    @Column(nullable = false, length = 100)
    private String chapterName;

    @ManyToOne
    @JoinColumn(name = "subId", nullable = false)
    private Subject subject;

}