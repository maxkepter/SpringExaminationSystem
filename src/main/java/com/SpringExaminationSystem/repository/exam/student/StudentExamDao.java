package com.SpringExaminationSystem.repository.exam.student;

import com.SpringExaminationSystem.model.exam.student.StudentExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentExamDao extends JpaRepository<StudentExam, Integer> {
    default void softDelete(StudentExam entity) {
        entity.setIsActive(false);
        save(entity);
    }
}
