package com.SpringExaminationSystem.repository.exam;

import com.SpringExaminationSystem.model.exam.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterDao extends JpaRepository<Chapter, Integer> {
    default void softDelete(Chapter entity) {
        entity.setIsActive(false);
        save(entity);
    }
}
