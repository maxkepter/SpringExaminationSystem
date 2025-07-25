package com.SpringExaminationSystem.repository.exam;

import com.SpringExaminationSystem.model.exam.SubjectMajor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectMajorDao extends JpaRepository<SubjectMajor, Integer> {
    default void softDelete(SubjectMajor entity) {
        entity.setIsActive(false);
        save(entity);
    }
}
