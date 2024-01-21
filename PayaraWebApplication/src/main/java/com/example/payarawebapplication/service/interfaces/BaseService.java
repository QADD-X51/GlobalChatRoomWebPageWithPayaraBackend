package com.example.payarawebapplication.service.interfaces;
import jakarta.ejb.Remote;

import java.util.List;

public interface BaseService<T> {
    public List<T> getAll();

    public T getById(Long targetId);

    public void insert(T newEntity);

    public void update(T updatedEntity);

    public void delete(Long messageId);
}
