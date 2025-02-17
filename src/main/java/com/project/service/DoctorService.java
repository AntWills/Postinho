package com.project.service;

import java.util.List;

import com.project.dao.DoctorDAO;
import com.project.model.Doctor;

public class DoctorService {
    private static DoctorDAO doctorDAO = new DoctorDAO();

    public static void start() {
        doctorDAO.start();
    }

    public static void save(String name) {
        Doctor doctor = new Doctor();
        doctor.setName(name);

        doctorDAO.save(doctor);
    }

    public static void update(int id, String name) {
        Doctor doctor = new Doctor(id, name);
        doctorDAO.update(doctor);
    }

    public static void delete(int id) {
        doctorDAO.delete(id);
    }

    public static Doctor findById(int id) {
        return doctorDAO.findById("doctor_id", id);
    }

    public static List<Doctor> findAll() {
        return doctorDAO.findAll();
    }
}
