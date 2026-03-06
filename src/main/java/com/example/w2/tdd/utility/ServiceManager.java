package com.example.w2.tdd.utility;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public class ServiceManager<T,ID> implements IService<T, ID>{


    private final CrudRepository<T,ID> repository;

    public ServiceManager(CrudRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T t) {
        return null;
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> t) {
        return null;
    }

    @Override
    public T update(T t) {
        return null;
    }

    @Override
    public void delete(T t) {

    }

    @Override
    public void deleteByID(ID id) {

    }

    @Override
    public List<T> findAll() {
        return (List<T>) repository.findAll();
    }

    @Override
    public Optional<T> findAllById(ID id) {
        return repository.findById(id);
    }
}
