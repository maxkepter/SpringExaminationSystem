package com.SpringExaminationSystem.repository.log;

import com.SpringExaminationSystem.model.log.ExamLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamLogDao extends JpaRepository<ExamLog, Integer> {

}
