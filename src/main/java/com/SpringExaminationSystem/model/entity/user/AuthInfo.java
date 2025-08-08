package com.SpringExaminationSystem.model.entity.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthInfo {
    public static final String ROLE_FIELD = "role";
    public static final String[] AUTHORIES = new String[] { "ADMIN", "USER" };

    @Id
    public Integer userId;
    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    @JoinColumn(name = "userID")
    private User user;
    @Column(name = "UserName", unique = true, nullable = false, length = 255)
    public String userName;
    @Column(nullable = false)
    public String password;
    @Column(nullable = false)
    private Integer role;

}