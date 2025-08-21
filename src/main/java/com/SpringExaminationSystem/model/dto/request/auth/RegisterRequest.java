package com.SpringExaminationSystem.model.dto.request.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
