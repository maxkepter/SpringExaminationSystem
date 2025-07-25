package com.SpringExaminationSystem.model.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.SpringExaminationSystem.model.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
public class LoginInfo extends BaseEntity {
    @Id
    private Integer userID;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    @JoinColumn(name = "userID")
    private User user;

    @Column(name = "UserName", unique = true, nullable = false, length = 255)
    private String userName;

    @Column(nullable = false)
    private String password;

}