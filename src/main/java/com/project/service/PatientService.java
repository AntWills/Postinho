package com.project.service;

import com.project.dao.PatientDAO;
import com.project.exception.ExistEntityException;
import com.project.exception.InvalidCpfException;
// import com.project.exception.InvalidCpfException;
import com.project.model.Patient;
import com.project.util.UtilCpf;

import java.sql.SQLException;
import java.util.List;

public class PatientService {

    private static PatientDAO patientDAO = new PatientDAO();

    public static void start() throws SQLException {
        patientDAO.start();
    }

    public static void save(String cpf, String name) throws InvalidCpfException, SQLException, ExistEntityException {
        if (PatientService.findById(cpf) != null) {
            throw new ExistEntityException("O paciente já existe.");
        }
        Patient patient = new Patient(cpf, name);
        patientDAO.save(patient);
    }

    public static void update(String cpf, String newName) throws Exception {
        Patient patient = new Patient(cpf, newName);
        patientDAO.update(patient);
    }

    public static void delete(String cpf) throws SQLException {
        patientDAO.delete(cpf);
    }

    public static Patient findById(String cpf) throws SQLException {
        try {
            UtilCpf.validator(cpf);
        } catch (InvalidCpfException e) {
            return null;
        }
        Patient patient = patientDAO.findById("cpf_id", cpf);

        return patient;
    }

    public static List<Patient> findAll() throws SQLException {
        return patientDAO.findAll();
    }

    public static int cauntPatients() {
        try {
            return patientDAO.numberPatient();
        } catch (SQLException e) {
            return -1;
        }
    }
}
