package com.SpringExaminationSystem.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringExaminationSystem.model.dto.common.AuthInfoDTO;
import com.SpringExaminationSystem.service.exam.AuthInfoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/admin/authinfo")
public class AuthInfoController {

    @Autowired
    AuthInfoService authInfoService;

    @PutMapping("/{oldPassword}")
    public String changePassword(@PathVariable String oldPassword, @RequestBody AuthInfoDTO authInfoDTO) {
        authInfoService.changePassword(authInfoDTO, oldPassword);
        return "Change password successfully";        
    }

    @PutMapping("/reset")
    public String resetPassword(@RequestBody AuthInfoDTO authInfoDTO) {
        authInfoService.resetPassword(authInfoDTO);
        return "Reset password successfully";        
    }
}
