package com.SpringExaminationSystem.repository.user;

import com.SpringExaminationSystem.model.entity.user.User;
import com.SpringExaminationSystem.repository.SoftDeleteRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends SoftDeleteRepository<User, Integer> {

}
