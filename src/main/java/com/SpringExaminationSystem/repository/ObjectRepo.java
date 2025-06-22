package com.SpringExaminationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ObjectRepo<E, K> extends SearchableRepo<E>, JpaRepository<E, K> {

}
