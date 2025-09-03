package com.SpringExaminationSystem.controller.admin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringExaminationSystem.model.dto.request.exam.SubjectCreationRequest;
import com.SpringExaminationSystem.service.exam.SubjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping
    public void addSubject(@RequestBody SubjectCreationRequest request) {
        System.out.println(request);
        subjectService.addSubject(request);
    }

    // @GetMapping("/subject")

}
