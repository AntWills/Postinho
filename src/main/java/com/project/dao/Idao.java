package com.project.dao;

import java.sql.ResultSet;
// import java.sql.SQLException;
import java.util.List;

// import com.project.exception.InvalidCpfException;

public interface Idao<T, K> {
    void save(T obj);

    void update(T obj);

    void delete(K id);

    T findById(String nameId, K id);

    List<T> findAll();

    T mapResultSetToEntity(ResultSet rs) throws Exception;

    String getTableName();
}
