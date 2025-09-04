package com.SpringExaminationSystem.service.exam;

import java.util.ArrayList;
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
import com.SpringExaminationSystem.model.entity.exam.Chapter;
import com.SpringExaminationSystem.model.entity.exam.Exam;
import com.SpringExaminationSystem.model.entity.exam.Question;
import com.SpringExaminationSystem.model.entity.exam.QuestionTemplate;
import com.SpringExaminationSystem.model.entity.exam.Subject;
import com.SpringExaminationSystem.model.mapper.exam.ExamMapper;
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
    private final ExamMapper examMapper;

    public ExamResponse createExam(ExamCreateRequest request) {
        Subject subject = subjectDao.findActiveById(request.getSubjectCode())
                .orElseThrow(() -> new IllegalArgumentException("Subject not found"));

        List<Question> questions = questionDao.findAllById(request.getQuestionIds());
        if (questions.size() != request.getQuestionIds().size()) {
            throw new IllegalArgumentException("Some questions not found");
        }
        validateExamRequest(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalArgumentException("User not authenticated");
        }
        String username = auth.getName();
        AuthInfo authInfo = authInfoDao.findByUserName(username);
        if (authInfo == null) {
            throw new IllegalArgumentException("User not found");
        }
        User currentUser = authInfo.getUser();
        String examCode = generateExamCode(subject.getSubjectCode());
        Exam exam = Exam.builder()
                .examCode(examCode)
                .examName(request.getTitle()) // map title -> examName
                .duration(request.getDurationMinutes())
                .examDate(request.getStartTime()) // map startTime -> examDate
                .deadline(request.getEndTime().toLocalDate()) // map endTime -> deadline
                .questions(questions)
                .user(currentUser)
                .build();

        Exam savedExam = examDao.save(exam);
        ExamResponse examResponse = examMapper.toExamResponse(savedExam);

        // Manual mapping for questions since ExamMapper ignores them
        if (savedExam.getQuestions() != null && !savedExam.getQuestions().isEmpty()) {
            examResponse.setQuestions(mapQuestionsToResponse(savedExam.getQuestions()));
        }

        return examResponse;
    }

    public ExamResponse createExamFromTemplates(ExamCreateRequest request, List<QuestionTemplate> questionTemplates) {
        Subject subject = subjectDao.findActiveById(request.getSubjectCode())
                .orElseThrow(() -> new IllegalArgumentException("Subject not found"));

        List<Question> questions = generateQuestionsFromTemplates(questionTemplates, subject);

        if (questions.isEmpty()) {
            throw new IllegalArgumentException("No questions could be generated from templates");
        }

        validateExamRequestForTemplate(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalArgumentException("User not authenticated");
        }
        String username = auth.getName();
        AuthInfo authInfo = authInfoDao.findByUserName(username);
        if (authInfo == null) {
            throw new IllegalArgumentException("User not found");
        }
        User currentUser = authInfo.getUser();

        String examCode = generateExamCode(subject.getSubjectCode());
        Exam exam = Exam.builder()
                .examCode(examCode)
                .examName(request.getTitle()) // map title -> examName
                .duration(request.getDurationMinutes())
                .examDate(request.getStartTime()) // map startTime -> examDate
                .deadline(request.getEndTime().toLocalDate()) // map endTime -> deadline
                .questions(questions)
                .user(currentUser)
                .build();

        Exam savedExam = examDao.save(exam);
        ExamResponse examResponse = examMapper.toExamResponse(savedExam);

        // Manual mapping for questions since ExamMapper ignores them
        if (savedExam.getQuestions() != null && !savedExam.getQuestions().isEmpty()) {
            examResponse.setQuestions(mapQuestionsToResponse(savedExam.getQuestions()));
        }

        return examResponse;
    }

    public ExamResponse getExamById(String examCode) {
        Exam exam = examDao.findActiveById(examCode)
                .orElseThrow(() -> new IllegalArgumentException("Exam not found"));
        ExamResponse examResponse = examMapper.toExamResponse(exam);

        // Manual mapping for questions since ExamMapper ignores them
        if (exam.getQuestions() != null && !exam.getQuestions().isEmpty()) {
            examResponse.setQuestions(mapQuestionsToResponse(exam.getQuestions()));
        }

        return examResponse;
    }

    public List<ExamResponse> getAllActiveExams() {
        return examDao.findAllActive()
                .stream()
                .map(exam -> {
                    ExamResponse examResponse = examMapper.toExamResponse(exam);
                    // Manual mapping for questions since ExamMapper ignores them
                    if (exam.getQuestions() != null && !exam.getQuestions().isEmpty()) {
                        examResponse.setQuestions(mapQuestionsToResponse(exam.getQuestions()));
                    }
                    return examResponse;
                })
                .collect(Collectors.toList());
    }

    private List<Question> generateQuestionsFromTemplates(List<QuestionTemplate> questionTemplates, Subject subject) {
        List<Question> generatedQuestions = new ArrayList<>();

        for (QuestionTemplate template : questionTemplates) {
            List<Chapter> chapters = subject.getChapters().stream()
                    .filter(chapter -> chapter.getChapterName().contains(template.getChapterNo()))
                    .collect(Collectors.toList());

            if (chapters.isEmpty()) {
                try {
                    Integer chapterId = Integer.parseInt(template.getChapterNo());
                    chapters = subject.getChapters().stream()
                            .filter(chapter -> chapter.getChapterId().equals(chapterId))
                            .collect(Collectors.toList());
                } catch (NumberFormatException e) {
                    continue;
                }
            }

            for (Chapter chapter : chapters) {
                List<Question> availableQuestions = findQuestionsByChapterAndDifficulty(chapter,
                        template.getDifficulty());

                int requestedAmount = Math.min(template.getAmount(), availableQuestions.size());

                for (int i = 0; i < requestedAmount; i++) {
                    if (!availableQuestions.isEmpty()) {
                        int randomIndex = (int) (Math.random() * availableQuestions.size());
                        Question selectedQuestion = availableQuestions.remove(randomIndex);
                        generatedQuestions.add(selectedQuestion);
                    }
                }
            }
        }

        return generatedQuestions;
    }

    private List<Question> findQuestionsByChapterAndDifficulty(Chapter chapter, String difficulty) {
        final int difficultyLevel = parseDifficultyLevel(difficulty);

        return questionDao.findAllActive().stream()
                .filter(question -> question.getChapter().getChapterId().equals(chapter.getChapterId()))
                .filter(question -> question.getDifficulty() == difficultyLevel)
                .collect(Collectors.toList());
    }

    private int parseDifficultyLevel(String difficulty) {
        try {
            return Integer.parseInt(difficulty);
        } catch (NumberFormatException e) {
            switch (difficulty.toLowerCase()) {
                case "easy":
                case "dễ":
                    return 1;
                case "medium":
                case "trung bình":
                    return 2;
                case "hard":
                case "khó":
                    return 3;
                default:
                    return 1;
            }
        }
    }

    private void validateExamRequestForTemplate(ExamCreateRequest request) {
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Exam title is required");
        }

        if (request.getSubjectCode() == null) {
            throw new IllegalArgumentException("Subject Code is required");
        }

        if (request.getDurationMinutes() == null || request.getDurationMinutes() < 1) {
            throw new IllegalArgumentException("Duration must be at least 1 minute");
        }

        if (request.getMaxAttempts() == null || request.getMaxAttempts() < 1) {
            throw new IllegalArgumentException("Max attempts must be at least 1");
        }
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

    private String generateExamCode(String subjectCode) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return subjectCode + "-EXAM-" + timestamp.substring(timestamp.length() - 6);
    }

    /**
     * Manual mapping for questions since ExamMapper ignores them
     */
    private java.util.List<com.SpringExaminationSystem.model.dto.response.QuestionResponse> mapQuestionsToResponse(
            List<Question> questions) {
        if (questions == null) {
            return null;
        }

        return questions.stream()
                .map(this::mapQuestionToResponse)
                .collect(Collectors.toList());
    }

    private com.SpringExaminationSystem.model.dto.response.QuestionResponse mapQuestionToResponse(Question question) {
        com.SpringExaminationSystem.model.dto.response.QuestionResponse.QuestionResponseBuilder builder = com.SpringExaminationSystem.model.dto.response.QuestionResponse
                .builder()
                .questionId(question.getQuestionId())
                .content(question.getQuestionContent())
                .type("MULTIPLE_CHOICE") // default type
                .difficulty(question.getDifficulty())
                .points(1); // default points

        if (question.getChapter() != null) {
            builder.chapterName(question.getChapter().getChapterName());
        } else {
            builder.chapterName("Unknown");
        }

        if (question.getOptions() != null) {
            java.util.List<com.SpringExaminationSystem.model.dto.response.OptionResponse> options = question
                    .getOptions().stream()
                    .map(option -> com.SpringExaminationSystem.model.dto.response.OptionResponse.builder()
                            .optionId(option.getOptionId())
                            .content(option.getOptionContent())
                            .build())
                    .collect(Collectors.toList());
            builder.options(options);
        }

        return builder.build();
    }

    // Chapter 1: "Introduction to Java"
    // Easy level (1 = Easy, 2 = Medium, 3 = Hard)
    // Lấy 2 câu hỏi random từ chapter này
}
