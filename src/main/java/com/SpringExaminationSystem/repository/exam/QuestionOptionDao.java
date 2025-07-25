package com.SpringExaminationSystem.repository.exam;

import com.SpringExaminationSystem.model.exam.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionOptionDao extends JpaRepository<QuestionOption, Integer> {
    default void softDelete(QuestionOption entity) {
        entity.setIsActive(false);
        save(entity);
    }
}
