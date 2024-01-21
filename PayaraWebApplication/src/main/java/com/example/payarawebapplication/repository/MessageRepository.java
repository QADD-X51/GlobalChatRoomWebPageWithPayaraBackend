package com.example.payarawebapplication.repository;

import com.example.payarawebapplication.model.MessageEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;

public class MessageRepository {
    @PersistenceContext(unitName = "MSUnit")
    private final EntityManager entityManager;

    public MessageRepository() {
        this.entityManager = Persistence.createEntityManagerFactory("MSUnit").createEntityManager();
    }

    public void insert(MessageEntity message) {
        entityManager.persist(message);
    }

    public void update(MessageEntity message) {
        entityManager.merge(message);
    }

    public void delete(Long Id) {
        MessageEntity toDelete = entityManager.find(MessageEntity.class, Id);
        if (toDelete != null) {
            entityManager.remove(toDelete);
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

    public List<MessageEntity> getAllAfterDate() {
        TypedQuery<MessageEntity> query = entityManager.createQuery("SELECT u FROM MessageEntity u", MessageEntity.class);
        return query.getResultList();
    }
}
