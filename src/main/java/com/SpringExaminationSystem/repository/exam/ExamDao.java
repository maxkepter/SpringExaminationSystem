package com.SpringExaminationSystem.repository.exam;

import com.SpringExaminationSystem.model.exam.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamDao extends JpaRepository<Exam, Integer> {
    default void softDelete(Exam entity) {
        entity.setIsActive(false);
        save(entity);
    }
}
