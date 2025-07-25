package com.SpringExaminationSystem.repository;

import com.SpringExaminationSystem.model.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {
    default void softDelete(Student entity) {
        entity.setIsActive(false);
        save(entity);
    }
}
