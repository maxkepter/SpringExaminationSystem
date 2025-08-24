package com.SpringExaminationSystem.model.entity.exam;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import com.SpringExaminationSystem.model.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Question")
@SQLDelete(sql = "update Question set isActive=0 where questionId=?")
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer questionId;

    @Lob
    @Column(nullable = false, columnDefinition = "VARCHAR(MAX)")
    private String questionContent;

    @Column(nullable = false)
    private int difficulty;

    @ManyToOne
    @JoinColumn(name = "chapterId", nullable = false)
    private Chapter chapter;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionOption> options;

    public List<QuestionOption> getOptions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOptions'");
    }

}