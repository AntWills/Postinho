package com.project.service;

import com.project.dao.PatientDAO;
import com.project.exception.InvalidCpfException;
import com.project.exception.UtilCpf;
// import com.project.exception.InvalidCpfException;
import com.project.model.Patient;

import java.util.List;

public class PatientService {

    private static PatientDAO patientDAO = new PatientDAO();

    public static void start() {
        patientDAO.start();
    }

    public static void save(String cpf, String name) throws InvalidCpfException, IllegalArgumentException {
        if (patientDAO.existPatient(cpf)) {
            throw new IllegalArgumentException("Paciente com este CPF já existe");
        }

        Patient patient = new Patient(cpf, name);
        patientDAO.save(patient);
    }

    public static void update(String cpf, String newName) throws Exception {
        Patient patient = new Patient(cpf, newName);
        patientDAO.update(patient);
    }

    public static void delete(String cpf) {
        patientDAO.delete(cpf);
    }

    public static Patient findById(String cpf) {
        try {
            UtilCpf.validator(cpf);
        } catch (InvalidCpfException e) {
            return null;
        }
        Patient patient = patientDAO.findById("cpf_id", cpf);

        return patient;
    }

    public static List<Patient> findAll() {
        return patientDAO.findAll();
    }

    public static boolean exist(String cpf) {
        return patientDAO.existPatient(cpf);
    }

    public static int cauntPatients() {
        return patientDAO.numberPatient();
    }
}
