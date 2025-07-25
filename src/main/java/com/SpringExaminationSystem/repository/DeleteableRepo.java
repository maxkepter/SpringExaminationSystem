package com.SpringExaminationSystem.repository;

import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

public abstract class DeleteableRepo<E, K> extends SimpleJpaRepository<E, K> {

    private EntityManager entityManager;

    public DeleteableRepo(Class<E> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    public void update(E entity) {
        entityManager.getTransaction().begin();

        entityManager.merge(entity);

        entityManager.getTransaction().commit();
    }

}
