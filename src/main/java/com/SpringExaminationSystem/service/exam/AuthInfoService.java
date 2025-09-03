package com.SpringExaminationSystem.service.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SpringExaminationSystem.model.dto.common.AuthInfoDTO;
import com.SpringExaminationSystem.model.entity.user.AuthInfo;
import com.SpringExaminationSystem.model.mapper.exam.AuthInfoMapper;
import com.SpringExaminationSystem.model.mapper.exam.UserMapper;
import com.SpringExaminationSystem.repository.user.AuthInfoDao;

@Service
public class AuthInfoService {

    @Autowired
    AuthInfoDao authInfoDao;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AuthInfoMapper authInfoMapper;

    public void changePassword(AuthInfoDTO authInfoDTO, String oldPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        AuthInfo authInfo;

        authInfo = authInfoDao.findByUserId(authInfoDTO.getUserId());

        if (authInfo == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(oldPassword, authInfo.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }
        authInfo.setPassword(passwordEncoder.encode(authInfoDTO.getPassword()));
        authInfoDao.save(authInfo);
    }

    public void resetPassword(AuthInfoDTO authInfoDTO) {        
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        AuthInfo authInfo = authInfoDao.findByUserId(authInfoDTO.getUserId());
        if (authInfo == null) {
            throw new RuntimeException("User not found");
        }
        authInfo.setPassword(passwordEncoder.encode(authInfoDTO.getPassword()));
        authInfoDao.save(authInfo);
    }
}
