package com.SpringExaminationSystem.controller.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringExaminationSystem.model.dto.request.exam.MajorCreationRequest;
import com.SpringExaminationSystem.model.mapper.exam.MajorMapper;
import com.SpringExaminationSystem.repository.exam.MajorDao;

@RestController
@RequestMapping("/exam")
public class MajorController {
    // This controller will handle requests related to majors in the examination
    // system.
    // You can define methods here to handle various endpoints related to majors.
    @Autowired
    private MajorDao majorDao;
    @Autowired
    private MajorMapper majorMapper;

    @PostMapping("/major")
    public void addMajor(MajorCreationRequest request) {
        majorDao.save(majorMapper.toEntity(request));
    }

}
