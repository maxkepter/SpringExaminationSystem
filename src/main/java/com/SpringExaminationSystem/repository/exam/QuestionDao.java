package com.SpringExaminationSystem.repository.exam;

import com.SpringExaminationSystem.model.exam.Question;
import com.SpringExaminationSystem.repository.SoftDeleteRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends SoftDeleteRepository<Question, Integer> {

}
