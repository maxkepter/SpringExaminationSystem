package com.SpringExaminationSystem.service.exam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SpringExaminationSystem.model.entity.user.AuthInfo;
import com.SpringExaminationSystem.model.entity.user.User;
import com.SpringExaminationSystem.repository.user.AuthInfoDao;
import com.SpringExaminationSystem.model.dto.request.exam.ExamCreateRequest;
import com.SpringExaminationSystem.model.dto.response.ExamResponse;
import com.SpringExaminationSystem.model.dto.response.OptionResponse;
import com.SpringExaminationSystem.model.dto.response.QuestionResponse;
import com.SpringExaminationSystem.model.entity.exam.Exam;
import com.SpringExaminationSystem.model.entity.exam.Question;
import com.SpringExaminationSystem.model.entity.exam.QuestionOption;
import com.SpringExaminationSystem.model.entity.exam.Subject;
import com.SpringExaminationSystem.model.enums.ExamStatus;
import com.SpringExaminationSystem.repository.exam.ExamDao;
import com.SpringExaminationSystem.repository.exam.QuestionDao;
import com.SpringExaminationSystem.repository.exam.SubjectDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamService {
    private final ExamDao examDao;
    private final QuestionDao questionDao;
    private final SubjectDao subjectDao;
    private final AuthInfoDao authInfoDao;

    public ExamResponse createExam(ExamCreateRequest request) {
        // 1. Validate subject exists
        Subject subject = subjectDao.findActiveById(request.getSubjectCode())
                .orElseThrow(() -> new IllegalArgumentException("Subject not found"));

        // 2. Validate questions exist
        List<Question> questions = questionDao.findAllById(request.getQuestionIds());
        if (questions.size() != request.getQuestionIds().size()) {
            throw new IllegalArgumentException("Some questions not found");
        }

        // 3. Basic validation
        validateExamRequest(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalArgumentException("User not authenticated");
        }

        // Get current user from JWT token
        String username = auth.getName();
        AuthInfo authInfo = authInfoDao.findByUserName(username);
        if (authInfo == null) {
            throw new IllegalArgumentException("User not found");
        }
        User currentUser = authInfo.getUser();

        // 4. Determine exam status
        ExamStatus status = determineExamStatus(request.getStartTime(), request.getEndTime());

        // 5. Generate examCode
        String examCode = generateExamCode(subject.getSubjectCode());

        // 6. Create exam entity
        Exam exam = Exam.builder()
                .examCode(examCode)
                .title(request.getTitle())
                .description(request.getDescription())
                .subject(subject)
                .duration(request.getDurationMinutes())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .deadline(request.getEndTime().toLocalDate())
                .examDate(request.getStartTime())
                .examName(request.getTitle())
                .maxAttempts(request.getMaxAttempts())
                .showResultImmediately(request.getShowResultImmediately())
                .shuffleQuestions(request.getShuffleQuestions())
                .shuffleOptions(request.getShuffleOptions())
                .status(status)
                .questions(questions)
                .user(currentUser)
                .build();

        // 6. Save exam
        Exam savedExam = examDao.save(exam);

        // 7. Convert to response
        return convertToExamResponse(savedExam);
    }

    public ExamResponse getExamById(String examCode) {
        Exam exam = examDao.findActiveById(examCode)
                .orElseThrow(() -> new IllegalArgumentException("Exam not found"));
        return convertToExamResponse(exam);
    }

    public List<ExamResponse> getAllActiveExams() {
        return examDao.findAllActive()
                .stream()
                .map(this::convertToExamResponse)
                .collect(Collectors.toList());
    }

    private void validateExamRequest(ExamCreateRequest request) {
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Exam title is required");
        }

        if (request.getSubjectCode() == null) {
            throw new IllegalArgumentException("Subject Code is required");
        }

        if (request.getQuestionIds() == null || request.getQuestionIds().isEmpty()) {
            throw new IllegalArgumentException("Questions list cannot be empty");
        }

        if (request.getDurationMinutes() == null || request.getDurationMinutes() < 1) {
            throw new IllegalArgumentException("Duration must be at least 1 minute");
        }

        if (request.getMaxAttempts() == null || request.getMaxAttempts() < 1) {
            throw new IllegalArgumentException("Max attempts must be at least 1");
        }
    }

    private ExamStatus determineExamStatus(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null) {
            return ExamStatus.DRAFT;
        }

        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(startTime)) {
            return ExamStatus.PENDING;
        }

        if (endTime != null && now.isAfter(endTime)) {
            return ExamStatus.EXPIRED;
        }

        return ExamStatus.ACTIVE;
    }

    private String generateExamCode(String subjectCode) {
        // Generate unique exam code based on subject and timestamp
        String timestamp = String.valueOf(System.currentTimeMillis());
        return subjectCode + "-EXAM-" + timestamp.substring(timestamp.length() - 6);
    }

    private ExamResponse convertToExamResponse(Exam exam) {
        List<QuestionResponse> questionResponses = exam.getQuestions().stream()
                .map(this::convertToQuestionResponse)
                .collect(Collectors.toList());

        return ExamResponse.builder()
                .examCode(exam.getExamCode())
                .title(exam.getTitle())
                .description(exam.getDescription())
                .subjectName(exam.getSubject().getSubjectName())
                .durationMinutes(exam.getDuration())
                .startTime(exam.getStartTime())
                .endTime(exam.getEndTime())
                .totalQuestions(exam.getQuestions().size())
                .maxAttempts(exam.getMaxAttempts())
                .showResultImmediately(exam.getShowResultImmediately())
                .status(exam.getStatus().name())
                .createdBy(exam.getCreatedBy())
                .createdAt(exam.getCreatedAt() != null ? exam.getCreatedAt().toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDateTime() : null)
                .questions(questionResponses)
                .build();
    }

    private QuestionResponse convertToQuestionResponse(Question question) {
        List<OptionResponse> optionResponses = question.getOptions().stream()
                .map(this::convertToOptionResponse)
                .collect(Collectors.toList());

        return QuestionResponse.builder()
                .questionId(question.getQuestionId())
                .content(question.getQuestionContent())
                .type("MULTIPLE_CHOICE")
                .difficulty(question.getDifficulty())
                .points(1)
                .chapterName(question.getChapter() != null ? question.getChapter().getChapterName() : null)
                .options(optionResponses)
                .build();
    }

    private OptionResponse convertToOptionResponse(QuestionOption option) {
        return OptionResponse.builder()
                .optionId(option.getOptionId())
                .content(option.getOptionContent())
                .build();
    }
}
