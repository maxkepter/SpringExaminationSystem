package com.SpringExaminationSystem.service.exam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SpringExaminationSystem.model.entity.exam.Exam;
import com.SpringExaminationSystem.model.entity.exam.Question;
import com.SpringExaminationSystem.model.entity.exam.student.QuestionWithOptions;
import com.SpringExaminationSystem.model.entity.exam.student.StudentExam;
import com.SpringExaminationSystem.model.entity.user.User;
import com.SpringExaminationSystem.repository.exam.ExamDao;
import com.SpringExaminationSystem.repository.exam.student.StudentExamDao;
import com.SpringExaminationSystem.repository.user.AuthInfoDao;

@Service
public class StudentExamService {
    @Autowired
    ExamDao examDao;
    @Autowired
    AuthInfoDao authInfoDao;
    @Autowired
    StudentExamDao studentExamDao;

    @Transactional
    public StudentExam createStudentExam(String userName, Integer examId) {
        User user = authInfoDao.findByUserName(userName).getUser();
        Exam exam = examDao.findActiveByIdWithQuestions(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));
        List<Question> questions = exam.getQuestions();
        StudentExam studentExam = StudentExam.builder()
                .examStatus(StudentExam.EXAM_DOING)
                .score(0)
                .submitTime(LocalDateTime.now())
                .startTime(LocalDateTime.now())
                .examDetail(QuestionWithOptions.convertFromEntities(questions))
                .studentChoice(new HashMap<>())
                .exam(exam)
                .user(user)
                .build();

        studentExamDao.save(studentExam);
        return studentExam;
    }

    @Transactional(readOnly = true)
    public StudentExam getStudentExamById(Integer studentExamId) {
        return studentExamDao.findById(studentExamId)
                .orElseThrow(() -> new RuntimeException("Student exam not found with id: " + studentExamId));
    }
}
