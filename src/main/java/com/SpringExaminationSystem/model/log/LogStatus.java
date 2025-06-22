package com.SpringExaminationSystem.model.log;

import org.springframework.context.annotation.Scope;

import com.SpringExaminationSystem.model.ObjectIdentify;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class LogStatus extends ObjectIdentify<Integer> {
    @Id
    private Integer statusId;

    @Column(nullable = false)
    private String status;

    @Override
    public Integer getId() {
        return statusId;
    }
}