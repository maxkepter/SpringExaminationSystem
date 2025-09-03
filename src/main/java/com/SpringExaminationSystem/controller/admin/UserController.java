package com.SpringExaminationSystem.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringExaminationSystem.model.dto.common.AuthInfoDTO;
import com.SpringExaminationSystem.model.dto.common.UserDTO;
import com.SpringExaminationSystem.service.exam.UserService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/admin/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/{userId}")
    public UserDTO getUser(@PathVariable Integer userId) {       
         return userService.getUser(userId);
        
        
    }
    
    

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return "Delete user successfully";
    }

    @PutMapping("/role")
    public String editUserRole(@RequestBody AuthInfoDTO authInfoDTO) {
        Integer userId = authInfoDTO.getUserId();
        Integer role = authInfoDTO.getRole();
        userService.editUserRole(userId, role);
        return "Update user role successfully";
    }

    @PutMapping()
    public String editUser(@RequestBody UserDTO userDTO) {
        userService.editUser(userDTO);

        return "Update user successfully";
    }

}
