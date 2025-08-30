package com.SpringExaminationSystem.repository.exam;

import com.SpringExaminationSystem.model.entity.exam.Exam;
import com.SpringExaminationSystem.repository.SoftDeleteRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ExamDao extends SoftDeleteRepository<Exam, String> {

}
