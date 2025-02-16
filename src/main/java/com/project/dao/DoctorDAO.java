package com.project.dao;

import java.sql.ResultSet;
import java.util.List;
import com.project.model.Doctor;

public class DoctorDAO implements Idao<Doctor, Integer> {
    public static void start() {
        DbConnect.openBank();
        String query = "";
        query += "CREATE TABLE IF NOT EXISTS Doctor (";
        query += "doctor_id INTEGER PRIMARY KEY,";
        query += "name TEXT NOT NULL";
        query += ");";
    }

    @Override
    public void save(Doctor doctor) {
        String query = "";
    }

    @Override
    public void update(Doctor doctor) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Doctor findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Doctor> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Doctor mapResultSetToEntity(ResultSet rs) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mapResultSetToEntity'");
    }

}
