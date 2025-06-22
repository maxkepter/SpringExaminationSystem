package com.SpringExaminationSystem.repository.custom;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.SpringExaminationSystem.model.exam.student.StudentExam;
import com.SpringExaminationSystem.repository.SearchableRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@NoRepositoryBean
public abstract class ObjectCustomRepo<E, K> implements SearchableRepo<E> {

    protected final Class<E> entityClass;
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    protected JpaRepository<E, K> jpaRepository;

    public ObjectCustomRepo(Class<E> enityClass) {
        this.entityClass = enityClass;
    }

    @Override
    public List<E> findByField(Map<String, Object> fieldValues, String[] attributeName) {
        if (fieldValues == null || fieldValues.isEmpty()) {
            return jpaRepository.findAll();
        }

        String queryString = buildQuery(fieldValues, attributeName);

        try {
            TypedQuery<E> query = entityManager.createQuery(queryString, entityClass);
            for (String field : StudentExam.ATTRIBUTE_NAME) {
                if (fieldValues.containsKey(field)) {
                    query.setParameter(field, fieldValues.get(field));
                }
            }
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error when querying StudentExam", e);
        }
    }

    @Override
    public List<E> findPage(Map<String, Object> fieldValues, String[] attributeName, int pageIndex, int pageSize) {
        if (fieldValues == null || fieldValues.isEmpty()) {
            return jpaRepository.findAll();
        }

        String queryString = buildQuery(fieldValues, attributeName);

        try {
            TypedQuery<E> query = entityManager.createQuery(queryString, entityClass);
            for (String field : StudentExam.ATTRIBUTE_NAME) {
                if (fieldValues.containsKey(field)) {
                    query.setParameter(field, fieldValues.get(field));
                }
            }
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error when querying StudentExam", e);
        }
    }

    private String buildQuery(Map<String, Object> fieldValues, String[] attributeName) {
        StringBuilder queryBuilder = new StringBuilder("SELECT e FROM " + entityClass.getSimpleName() + " e WHERE ");
        for (String field : StudentExam.ATTRIBUTE_NAME) {
            if (fieldValues.containsKey(field)) {
                queryBuilder.append("e." + field + " = :" + field + " AND ");
            }
        }
        queryBuilder.setLength(queryBuilder.length() - 5); // remove last ' AND '
        return queryBuilder.toString();
    }

}
