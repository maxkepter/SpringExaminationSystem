package com.SpringExaminationSystem.repository;

import com.SpringExaminationSystem.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    default void softDelete(User entity) {
        entity.setIsActive(false);
        save(entity);
    }
}
