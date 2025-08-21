package com.SpringExaminationSystem.model.dto.request.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Data
public class LoginRequest {
    private String userName;
    private String password;
}
