package com.example.payarawebapplication.repository;

import com.example.payarawebapplication.model.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserRepository {
    @PersistenceContext(unitName = "MSUnit")
    private final EntityManager entityManager;

    public UserRepository() {
        this.entityManager = Persistence.createEntityManagerFactory("MSUnit").createEntityManager();
    }

    public void insert(UserEntity user) {
        entityManager.persist(user);
    }

    public void update(UserEntity user) {
        entityManager.merge(user);
    }

    public void delete(Long Id) {
        UserEntity toDelete = entityManager.find(UserEntity.class, Id);
        if (toDelete != null) {
            entityManager.remove(toDelete);
        }
    }

    public UserEntity getById(Long Id) {
        return entityManager.find(UserEntity.class, Id);
    }

    public List<UserEntity> getAll() {
        TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class);
        return query.getResultList();
    }

    public UserEntity getByUsername(String username) {
        TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = ?1", UserEntity.class);
        query.setParameter(1, username);
        List<UserEntity> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public UserEntity getByUserCredentials(UserEntity user) {
        TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u " +
                "WHERE u.username = ?1 " +
                "AND u.password = ?2", UserEntity.class);
        query.setParameter(1, user.getUsername());
        query.setParameter(2, user.getPassword());
        List<UserEntity> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
