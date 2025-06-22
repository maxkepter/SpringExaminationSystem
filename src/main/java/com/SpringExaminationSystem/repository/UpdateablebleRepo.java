package com.SpringExaminationSystem.repository;

import java.util.Map;

public interface UpdateablebleRepo<E> {
    void update(E object);

    void updatePartial(int id, Map<String, Object> fields);
}
