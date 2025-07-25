package com.SpringExaminationSystem.repository;

import com.SpringExaminationSystem.model.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {
    default void softDelete(Admin entity) {
        entity.setIsActive(false);
        save(entity);
    }
}
