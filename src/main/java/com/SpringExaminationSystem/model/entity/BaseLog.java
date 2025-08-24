package com.SpringExaminationSystem.model.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class BaseLog {
    public static final String FIELD_INFOMATION = "infomation";
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt;
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    @Column(updatable = false, length = 100)
    private String infomation;
}
