package com.SpringExaminationSystem.model.log;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Scope;

import com.SpringExaminationSystem.model.ObjectIdentify;
import com.SpringExaminationSystem.model.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class BanLog extends ObjectIdentify<Integer> {
    public static final String FIELD_USER = "user";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_START_TIME = "startTime";
    public static final String FIELD_END_TIME = "endTime";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime startTime;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "statusId", nullable = false)
    private LogStatus status;

    @Override
    public Integer getId() {
        return logId;
    }
}