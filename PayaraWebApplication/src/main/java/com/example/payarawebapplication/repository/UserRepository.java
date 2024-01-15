package com.example.payarawebapplication.repository;

import com.example.payarawebapplication.model.UserEntity;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserRepository {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public UserRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("MSUnit");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void insert(UserEntity user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public void update(UserEntity user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    public void delete(Long Id) {
        UserEntity toDelete = entityManager.find(UserEntity.class, Id);
        if (toDelete != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(toDelete);
            entityManager.getTransaction().commit();
        }
    }

    public UserEntity getById(Long Id) {
        return entityManager.find(UserEntity.class, Id);
    }

    public List<UserEntity> getAll() {
        TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class);
        return query.getResultList();
    }

    public void closeConnection() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public UserEntity getByUsername(String username) {
        TypedQuery<UserEntity> query =  entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = ?1", UserEntity.class);
        query.setParameter(1, username);
        List<UserEntity> result = query.getResultList();
        if(result.isEmpty())
        {
            return null;
        }
        return result.get(0);
    }

    public UserEntity getByUserCredentials(UserEntity user) {
        TypedQuery<UserEntity> query =  entityManager.createQuery("SELECT u FROM UserEntity u " +
                                                                     "WHERE u.username = ?1 " +
                                                                     "AND u.password = ?2", UserEntity.class);
        query.setParameter(1, user.getUsername());
        query.setParameter(2, user.getPassword());
        List<UserEntity> result = query.getResultList();
        if(result.isEmpty())
        {
            return null;
        }
        return result.get(0);
    }
}
