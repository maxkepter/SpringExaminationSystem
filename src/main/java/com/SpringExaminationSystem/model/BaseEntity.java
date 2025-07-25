package com.SpringExaminationSystem.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class BaseEntity {
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    @CreatedDate
    private Date createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private Date updatedAt;
    @LastModifiedBy
    private String updatedBy;
    private boolean isActive;

}
