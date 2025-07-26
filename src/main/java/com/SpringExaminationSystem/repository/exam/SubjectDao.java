package com.SpringExaminationSystem.repository.exam;

import com.SpringExaminationSystem.model.exam.Subject;
import com.SpringExaminationSystem.repository.SoftDeleteRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDao extends SoftDeleteRepository<Subject, Integer> {

}
