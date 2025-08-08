package com.SpringExaminationSystem.model.entity.exam;

import org.hibernate.annotations.SQLDelete;
import org.springframework.context.annotation.Scope;

import com.SpringExaminationSystem.model.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "Subject")
@SQLDelete(sql = "update Subject set isActive=0 where subId=?")
public class Subject extends BaseEntity {
    @Id
    private Integer subId;

    @Column(nullable = false, length = 100)
    private String subjectName;

}