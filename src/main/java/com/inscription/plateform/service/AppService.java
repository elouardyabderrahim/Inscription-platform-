package com.inscription.plateform.service;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AppService <T>{

    public void save(T obj);

    public void update(T obj);

    public void delete(Long id);

    public T findById(Long id);

    public List<T> getAll();
}
