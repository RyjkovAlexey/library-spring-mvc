package ru.alexey.library.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    public List<T> findAll();

    public Optional<T> findById(int id);

    public void save(T t);

    public void update(int id, T updated);

    public void delete(int id);
}
