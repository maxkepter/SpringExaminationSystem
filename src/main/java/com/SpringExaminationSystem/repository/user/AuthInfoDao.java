package com.SpringExaminationSystem.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringExaminationSystem.model.entity.user.AuthInfo;

public interface AuthInfoDao extends JpaRepository<AuthInfo, Integer> {
    public AuthInfo findByUserName(String userName);

    public AuthInfo findByUserId(Integer userId);
}
