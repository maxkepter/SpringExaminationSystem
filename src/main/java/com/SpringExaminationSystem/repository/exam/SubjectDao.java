package com.SpringExaminationSystem.repository.exam;

import com.SpringExaminationSystem.model.exam.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDao extends JpaRepository<Subject, Integer> {
    default void softDelete(Subject entity) {
        entity.setIsActive(false);
        save(entity);
    }
}
