package com.SpringExaminationSystem.repository.exam;

import com.SpringExaminationSystem.model.exam.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorDao extends JpaRepository<Major, Integer> {
    default void softDelete(Major entity) {
        entity.setIsActive(false);
        save(entity);
    }
}
