package com.SpringExaminationSystem.controller.exam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringExaminationSystem.model.dto.common.SubjectDTO;
import com.SpringExaminationSystem.model.dto.request.exam.SubjectCreationRequest;
import com.SpringExaminationSystem.model.entity.exam.Chapter;
import com.SpringExaminationSystem.model.entity.exam.Subject;
import com.SpringExaminationSystem.model.mapper.exam.SubjectMapper;
import com.SpringExaminationSystem.repository.exam.ChapterDao;
import com.SpringExaminationSystem.repository.exam.SubjectDao;
import com.SpringExaminationSystem.service.exam.SubjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;

    @PostMapping("/subject")
    public void addSubject(@RequestBody SubjectCreationRequest request) {
        System.out.println(request);
        subjectService.addSubject(request);
    }

//    @GetMapping("/subject")
    
}
