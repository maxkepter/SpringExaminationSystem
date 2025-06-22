package com.SpringExaminationSystem.repository.custom;

import java.util.Map;

import com.SpringExaminationSystem.repository.UpdateablebleRepo;

public abstract class DeleteableOjectCustomRepo<E, K> extends ObjectCustomRepo<E, K> implements UpdateablebleRepo<E> {

    public DeleteableOjectCustomRepo(Class<E> enityClass) {
        super(enityClass);
    }

    @Override
    public void update(E object) {
        entityManager.getTransaction().begin();

        entityManager.merge(object);

        entityManager.getTransaction().commit();
    }

    @Override
    public void updatePartial(int id, Map<String, Object> fields) {
        // TODO Auto-generated method stub

    }

}
