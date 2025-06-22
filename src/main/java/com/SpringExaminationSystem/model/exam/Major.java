package com.SpringExaminationSystem.model.exam;

import org.springframework.context.annotation.Scope;

import com.SpringExaminationSystem.model.ObjectIdentify;

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
public class Major extends ObjectIdentify<Integer> {
    @Id
    private Integer majorId;

    @Column(nullable = false, length = 100)
    private String majorName;

    @Column(nullable = false)
    private boolean isDisable;

    @Override
    public Integer getId() {
        return majorId;
    }
}