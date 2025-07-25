package com.SpringExaminationSystem.repository.exam;

import com.SpringExaminationSystem.model.exam.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    default void softDelete(Question entity) {
        entity.setIsActive(false);
        save(entity);
    }
}
