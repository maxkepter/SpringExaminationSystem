SET NOCOUNT ON;
SET XACT_ABORT ON;
BEGIN TRAN;

-- ============================
-- USERS & AUTHINFO
-- ============================
DECLARE @user_admin INT, @user_student INT;

INSERT INTO [User] (
    createdAt, createdBy, isActive, updatedAt, updatedBy,
    firstName, lastName, email
)
VALUES (
    GETDATE(), N'system', 1, GETDATE(), N'system',
    N'Admin', N'Root', N'admin@example.com'
);
SET @user_admin = SCOPE_IDENTITY();

INSERT INTO AuthInfo (userID, UserName, [password], role)
VALUES (@user_admin, N'admin', N'P@ssw0rd!', 0); -- 0 = ADMIN

INSERT INTO [User] (
    createdAt, createdBy, isActive, updatedAt, updatedBy,
    firstName, lastName, email
)
VALUES (
    GETDATE(), N'system', 1, GETDATE(), N'system',
    N'Nguyễn', N'Văn A', N'student1@example.com'
);
SET @user_student = SCOPE_IDENTITY();

INSERT INTO AuthInfo (userID, UserName, [password], role)
VALUES (@user_student, N'student1', N'P@ssw0rd!', 1); -- 1 = USER

-- ============================
-- MAJOR, SUBJECT, subject_major
-- ============================
INSERT INTO Major (majorCode, createdAt, createdBy, isActive, updatedAt, updatedBy, majorName)
VALUES (N'CS', GETDATE(), N'system', 1, GETDATE(), N'system', N'Computer Science');

INSERT INTO Major (majorCode, createdAt, createdBy, isActive, updatedAt, updatedBy, majorName)
VALUES (N'MATH', GETDATE(), N'system', 1, GETDATE(), N'system', N'Mathematics');

INSERT INTO Subject (subjectCode, createdAt, createdBy, isActive, updatedAt, updatedBy, subjectName)
VALUES (N'CS101', GETDATE(), N'system', 1, GETDATE(), N'system', N'Intro to Programming');

INSERT INTO Subject (subjectCode, createdAt, createdBy, isActive, updatedAt, updatedBy, subjectName)
VALUES (N'MTH101', GETDATE(), N'system', 1, GETDATE(), N'system', N'Calculus I');

-- mapping Subject ↔ Major (bảng subject_major)
INSERT INTO subject_major (subjectCode, majorCode) VALUES (N'CS101', N'CS');
INSERT INTO subject_major (subjectCode, majorCode) VALUES (N'MTH101', N'MATH');

-- ============================
-- CHAPTER (thuộc SUBJECT)
-- ============================
DECLARE @chap_alg INT, @chap_deriv INT, @chap_java INT;

INSERT INTO Chapter (createdAt, createdBy, isActive, updatedAt, updatedBy, chapterName, subjectCode)
VALUES (GETDATE(), N'teacher1', 1, GETDATE(), N'teacher1', N'Algebra Refresher', N'MTH101');
SET @chap_alg = SCOPE_IDENTITY();

INSERT INTO Chapter (createdAt, createdBy, isActive, updatedAt, updatedBy, chapterName, subjectCode)
VALUES (GETDATE(), N'teacher1', 1, GETDATE(), N'teacher1', N'Derivatives Basics', N'MTH101');
SET @chap_deriv = SCOPE_IDENTITY();

INSERT INTO Chapter (createdAt, createdBy, isActive, updatedAt, updatedBy, chapterName, subjectCode)
VALUES (GETDATE(), N'teacher1', 1, GETDATE(), N'teacher1', N'Java Syntax', N'CS101');
SET @chap_java = SCOPE_IDENTITY();

-- ============================
-- QUESTION
-- ============================
DECLARE @q1 INT, @q2 INT, @q3 INT;

INSERT INTO Question (createdAt, createdBy, isActive, updatedAt, updatedBy, difficulty, questionContent, chapterId)
VALUES (GETDATE(), N'teacher1', 1, GETDATE(), N'teacher1', 1, N'2 + 2 = ?', @chap_alg);
SET @q1 = SCOPE_IDENTITY();

INSERT INTO Question (createdAt, createdBy, isActive, updatedAt, updatedBy, difficulty, questionContent, chapterId)
VALUES (GETDATE(), N'teacher1', 1, GETDATE(), N'teacher1', 2, N'Đạo hàm của x^2 là gì?', @chap_deriv);
SET @q2 = SCOPE_IDENTITY();

INSERT INTO Question (createdAt, createdBy, isActive, updatedAt, updatedBy, difficulty, questionContent, chapterId)
VALUES (GETDATE(), N'teacher1', 1, GETDATE(), N'teacher1', 1, N'Giá trị của 5 % 2 trong Java?', @chap_java);
SET @q3 = SCOPE_IDENTITY();

-- ============================
-- QUESTION OPTION
-- ============================
-- Cho @q1
INSERT INTO QuestionOption (createdAt, createdBy, isActive, updatedAt, updatedBy, isCorrect, optionContent, questionId)
VALUES (GETDATE(), N'teacher1', 1, GETDATE(), N'teacher1', 0, N'3', @q1);
INSERT INTO QuestionOption (createdAt, createdBy, isActive, updatedAt, updatedBy, isCorrect, optionContent, questionId)
VALUES (GETDATE(), N'teacher1', 1, GETDATE(), N'teacher1', 1, N'4', @q1);
INSERT INTO QuestionOption (createdAt, createdBy, isActive, updatedAt, updatedBy, isCorrect, optionContent, questionId)
VALUES (GETDATE(), N'teacher1', 1, GETDATE(), N'teacher1', 0, N'5', @q1);

-- Cho @q2
INSERT INTO QuestionOption (createdAt, createdBy, isActive, updatedAt, updatedBy, isCorrect, optionContent, questionId)
VALUES (GETDATE(), N'teacher1', 1, GETDATE(), N'teacher1', 1, N'2x', @q2);
INSERT INTO QuestionOption (createdAt, createdBy, isActive, updatedAt, updatedBy, isCorrect, optionContent, questionId)
VALUES (GETDATE(), N'teacher1', 1, GETDATE(), N'teacher1', 0, N'x^3', @q2);

-- Cho @q3
INSERT INTO QuestionOption (createdAt, createdBy, isActive, updatedAt, updatedBy, isCorrect, optionContent, questionId)
VALUES (GETDATE(), N'teacher1', 1, GETDATE(), N'teacher1', 0, N'0', @q3);
INSERT INTO QuestionOption (createdAt, createdBy, isActive, updatedAt, updatedBy, isCorrect, optionContent, questionId)
VALUES (GETDATE(), N'teacher1', 1, GETDATE(), N'teacher1', 1, N'1', @q3);

-- ============================
-- EXAM
-- ============================
DECLARE @exam_math INT;

INSERT INTO Exam (
    createdAt, createdBy, isActive, updatedAt, updatedBy,
    deadline, duration, examCode, examDate, examName, userId
)
VALUES (
    GETDATE(), N'admin', 1, GETDATE(), N'admin',
    CONVERT(date, DATEADD(day, 7, GETDATE())), -- deadline (DATE)
    60,
    N'EXAM-MTH-001',
    DATEADD(day, 1, GETDATE()),                -- examDate (DATETIME)
    N'Calculus I - Midterm',
    @user_admin
);
SET @exam_math = SCOPE_IDENTITY();

-- EXAM_QUESTION (nối đề ↔ câu hỏi)
INSERT INTO Exam_Question (examID, questionID) VALUES (@exam_math, @q1);
INSERT INTO Exam_Question (examID, questionID) VALUES (@exam_math, @q2);
INSERT INTO Exam_Question (examID, questionID) VALUES (@exam_math, @q3);

-- ============================
-- STUDENT EXAM
-- ============================
DECLARE @stud_exam INT;

INSERT INTO StudentExam (
    createdAt, createdBy, isActive, updatedAt, updatedBy,
    examDetail, examStatus, score, startTime, studentChoice, submitTime, ExamId, UserId
)
VALUES (
    GETDATE(), N'system', 1, GETDATE(), N'system',
    N'[]',   -- examDetail (NVARCHAR(MAX) JSON)
    2,       -- EXAM_DOING
    0.0,     -- score (float)
    DATEADD(minute, -5, GETDATE()), -- startTime
    N'{}',   -- studentChoice (NVARCHAR(MAX) JSON map)
    GETDATE(), -- submitTime (NOT NULL) <- set to now to avoid NULL error
    @exam_math,
    @user_student
);
SET @stud_exam = SCOPE_IDENTITY();

-- ============================
-- LOGS
-- ============================
-- ExamLog
INSERT INTO ExamLog (createdAt, createdBy, infomation, studentExamId)
VALUES (GETDATE(), N'student1', N'Start exam', @stud_exam);

-- BanLog (ví dụ khoá user_student 30 phút)
INSERT INTO BanLog (createdAt, createdBy, infomation, endTime, userId)
VALUES (GETDATE(), N'admin', N'Cheat detected', DATEADD(minute, 30, GETDATE()), @user_student);

COMMIT;
GO


select * from [User]