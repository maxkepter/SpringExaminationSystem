package com.SpringExaminationSystem.repository.custom;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.SpringExaminationSystem.repository.DeactivatableObjectRepo;
import com.SpringExaminationSystem.repository.UpdateablebleRepo;

public abstract class DeactiveableObjectCustomRepo<E, K> extends ObjectCustomRepo<E, K>
        implements UpdateablebleRepo<E> {

    public DeactiveableObjectCustomRepo(Class<E> enityClass) {
        super(enityClass);
    }

    @Autowired
    private DeactivatableObjectRepo deactivatableObjectRepo;

    @Override
    public void update(E object) {

    }

    @Override
    public void updatePartial(int id, Map<String, Object> fields) {
        // TODO Auto-generated method stub

    }

}
