package com.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
// import java.sql.SQLException;
import java.util.List;

// import com.project.exception.InvalidCpfException;

public interface Idao<T, K> {
    void save(T obj) throws SQLException;

    void update(T obj) throws SQLException;

    void delete(K id) throws SQLException;

    T findById(String nameId, K id) throws SQLException;

    List<T> findAll() throws SQLException;

    T mapResultSetToEntity(ResultSet rs) throws SQLException;

    String getTableName();
}
