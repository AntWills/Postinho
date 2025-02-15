package com.project.service;

import com.project.dao.PatientDAO;
import com.project.entity.Cpf;
// import com.project.exception.InvalidCpfException;
import com.project.model.Patient;
import java.util.List;

public class PatientService {

    private static PatientDAO patientDAO = new PatientDAO();

    public static void save(Cpf cpf, String name) {
        if (patientDAO.existPatient(cpf)) {
            throw new IllegalArgumentException("Paciente com este CPF já existe");
        }

        Patient patient = new Patient(cpf, name);
        patientDAO.save(patient);
    }

    public static void update(Cpf cpf, String newName) {
        Patient patient = new Patient(cpf, newName);
        patientDAO.update(patient);
    }

    public static void delete(Cpf cpf) {
        // Patient patient = patientDAO.findById(cpf);
        // if (patient == null) {
        // throw new IllegalArgumentException("Paciente não encontrado");
        // }

        patientDAO.delete(cpf);
    }

    public static Patient findById(Cpf cpf) {
        Patient patient = patientDAO.findById(cpf);
        if (patient == null) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }

        return patient;
    }

    public static List<Patient> findAll() {
        return patientDAO.findAll();
    }

    public static boolean exist(Cpf cpf) {
        return patientDAO.existPatient(cpf);
    }

    public static int cauntPatients() {
        return patientDAO.numberPatient();
    }
}
