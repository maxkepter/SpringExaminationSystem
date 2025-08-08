package com.SpringExaminationSystem.repository.exam.student;

import com.SpringExaminationSystem.model.entity.exam.student.StudentExam;
import com.SpringExaminationSystem.repository.SoftDeleteRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface StudentExamDao extends SoftDeleteRepository<StudentExam, Integer> {

}
