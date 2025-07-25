package com.SpringExaminationSystem.repository.log;

import com.SpringExaminationSystem.model.log.BanLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanLogDao extends JpaRepository<BanLog, Integer> {

}
