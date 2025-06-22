package com.SpringExaminationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.SpringExaminationSystem.model.ObjectIdentify;

import jakarta.transaction.Transactional;

@NoRepositoryBean
public interface DeactivatableObjectRepo<E extends ObjectIdentify<K>, K> extends JpaRepository<E, K> {

    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} e SET e.isDisable = true WHERE e.id = :id")
    void softDeleteById(@Param("id") K id);

    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} e SET e.isDisable = true WHERE e.id IN :ids")
    void softDeleteAllById(@Param("ids") Iterable<K> ids);

    @Override
    @Transactional
    default void deleteById(K id) {
        softDeleteById(id);
    }

    @Override
    @Transactional
    default void delete(E entity) {
        if (entity != null) {
            softDeleteById(entity.getId());
        }
    }

    @Override
    @Transactional
    default void deleteAll(Iterable<? extends E> entities) {
        for (E entity : entities) {
            delete(entity);
        }
    }

    @Override
    @Transactional
    default void deleteAll() {
        findAll().forEach(this::delete);
    }

    @Override
    @Transactional
    default void deleteAllById(Iterable<? extends K> ids) {
        for (K id : ids) {
            deleteById(id);
        }
    }

    @Override
    @Transactional
    default void deleteAllInBatch() {
        deleteAll(findAll());
    }

    @Override
    @Transactional
    default void deleteAllInBatch(Iterable<E> entities) {
        deleteAll(entities);
    }

    @Override
    @Transactional
    default void deleteInBatch(Iterable<E> entities) {
        deleteAll(entities);
    }

    @Override
    @Transactional
    default void deleteAllByIdInBatch(Iterable<K> ids) {
        softDeleteAllById(ids);
    }

    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} e SET e.isDisable = false WHERE e.id = :id")
    void enableById(@Param("id") K id);

    @Query("SELECT e.isDisable FROM #{#entityName} e WHERE e.id = :id")
    Boolean isDisable(@Param("id") K id);

}
