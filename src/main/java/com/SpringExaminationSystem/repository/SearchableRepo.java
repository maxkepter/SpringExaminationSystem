package com.SpringExaminationSystem.repository;

import java.util.List;
import java.util.Map;

public interface SearchableRepo<E> {
    List<E> findPage(Map<String, Object> fieldValues, String[] attributeName, int pageIndex, int pageSize);

    List<E> findByField(Map<String, Object> fieldValues, String[] attributeName);
}
