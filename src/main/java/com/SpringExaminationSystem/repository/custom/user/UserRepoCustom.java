package com.SpringExaminationSystem.repository.custom.user;

import com.SpringExaminationSystem.model.user.User;
import com.SpringExaminationSystem.repository.custom.DeleteableOjectCustomRepo;

public class UserRepoCustom extends DeleteableOjectCustomRepo<User, Integer> {

    public UserRepoCustom(Class<User> enityClass) {
        super(enityClass);

    }

}
