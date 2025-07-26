package com.SpringExaminationSystem.model.exam;

import org.hibernate.annotations.SQLDelete;
import org.springframework.context.annotation.Scope;

import com.SpringExaminationSystem.model.BaseEntity;

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
@Table(name = "Major")
@SQLDelete(sql = "update Major set isActive=0 where majorId=?")
public class Major extends BaseEntity {
    @Id
    private Integer majorId;

    @Column(nullable = false, length = 100)
    private String majorName;

}