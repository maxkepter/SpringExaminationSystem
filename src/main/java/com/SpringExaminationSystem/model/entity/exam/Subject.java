package com.SpringExaminationSystem.model.entity.exam;

import org.hibernate.annotations.SQLDelete;
import org.springframework.context.annotation.Scope;

import com.SpringExaminationSystem.model.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Scope("prototype")
@Entity
@Table(name = "Subject")
@SQLDelete(sql = "update Subject set isActive=0 where subId=?")
public class Subject extends BaseEntity {
    @Id
    private String subjectCode;

    @Column(nullable = false, length = 100)
    private String subjectName;

}