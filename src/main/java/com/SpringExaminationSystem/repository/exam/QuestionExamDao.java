package com.SpringExaminationSystem.repository.exam;

import com.SpringExaminationSystem.model.exam.QuestionExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionExamDao extends JpaRepository<QuestionExam, Integer> {
    default void softDelete(QuestionExam entity) {
        entity.setIsActive(false);
        save(entity);
    }
}
