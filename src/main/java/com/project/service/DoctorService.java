package com.project.service;

import java.sql.SQLException;
import java.util.List;

import com.project.dao.DoctorDAO;
import com.project.model.Doctor;

public class DoctorService {
    private static DoctorDAO doctorDAO = new DoctorDAO();

    public static void start() throws SQLException {
        doctorDAO.start();
    }

    public static void save(String name) throws SQLException {
        Doctor doctor = new Doctor(name);

        doctorDAO.save(doctor);
    }

    public static void update(int id, String name) throws SQLException {
        Doctor doctor = new Doctor(id, name);
        doctorDAO.update(doctor);
    }

    public static void delete(int id) throws SQLException {
        doctorDAO.delete(id);
    }

    public static Doctor findById(int id) throws SQLException {
        return doctorDAO.findById("doctor_id", id);
    }

    public static List<Doctor> findAll() throws SQLException {
        return doctorDAO.findAll();
    }
}
