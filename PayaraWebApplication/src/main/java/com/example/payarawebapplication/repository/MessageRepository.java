package com.example.payarawebapplication.repository;

import com.example.payarawebapplication.model.MessageEntity;
import com.example.payarawebapplication.model.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;

public class MessageRepository {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public MessageRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("MSUnit");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void insert(MessageEntity message) {
        entityManager.getTransaction().begin();
        entityManager.persist(message);
        entityManager.getTransaction().commit();
    }

    public void update(MessageEntity message) {
        entityManager.getTransaction().begin();
        entityManager.merge(message);
        entityManager.getTransaction().commit();
    }

    public void delete(Long Id) {
        MessageEntity toDelete = entityManager.find(MessageEntity.class, Id);
        if (toDelete != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(toDelete);
            entityManager.getTransaction().commit();
        }
    }

    public MessageEntity getById(Long Id) {
        return entityManager.find(MessageEntity.class, Id);
    }

    public List<MessageEntity> getAll() {
        TypedQuery<MessageEntity> query = entityManager.createQuery("SELECT u FROM MessageEntity u", MessageEntity.class);
        return query.getResultList();
    }

    public List<MessageEntity> getAllAfterDate(LocalDateTime targetDate) {
        TypedQuery<MessageEntity> query = entityManager.createQuery("SELECT u FROM MessageEntity u " +
                                                                       "WHERE u.date > :targetDate", MessageEntity.class);
        query.setParameter("targetDate", targetDate);
        return query.getResultList();
    }

    public void closeConnection() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public List<MessageEntity> getAllAfterDate() {
        TypedQuery<MessageEntity> query = entityManager.createQuery("SELECT u FROM MessageEntity u", MessageEntity.class);
        return query.getResultList();
    }
}
