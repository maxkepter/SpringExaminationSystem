package com.SpringExaminationSystem.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DeleteableRepo<E, K> extends ObjectRepo<E, K>, UpdateablebleRepo<E> {

}
