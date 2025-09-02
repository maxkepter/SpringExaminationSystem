package com.SpringExaminationSystem.service.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringExaminationSystem.model.entity.exam.student.StudentExam;
import com.SpringExaminationSystem.model.entity.log.ExamLog;
import com.SpringExaminationSystem.repository.log.ExamLogDao;

@Service
public class ExamLogService {
    @Autowired
    ExamLogDao examLogDao;

    public void createExamLog(String infomation, StudentExam studentExam) {

        ExamLog examLog = new ExamLog(infomation, studentExam);
        examLogDao.save(examLog);
    }
}
