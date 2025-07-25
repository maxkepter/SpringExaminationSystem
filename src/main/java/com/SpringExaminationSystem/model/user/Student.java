package com.SpringExaminationSystem.model.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.SpringExaminationSystem.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Component
@Table(name = "Student")
public class Student extends BaseEntity {
    @Id
    private Integer userID;

    @Column(nullable = false, unique = true, length = 50)
    private String studentCode;

    @OneToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID", insertable = false, updatable = false)
    private User user;

}