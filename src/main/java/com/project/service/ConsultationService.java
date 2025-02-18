package com.project.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.project.dao.ConsultationDAO;
import com.project.model.Consultation;
import com.project.model.Doctor;
import com.project.model.Patient;

public class ConsultationService {
    private static ConsultationDAO consultationDao = new ConsultationDAO();

    public static void start() throws SQLException {
        consultationDao.start();
    }

    public static void save(
            int status,
            Patient patient,
            Doctor doctor,
            LocalDate dateService,
            String reasonForService) throws SQLException {
        Consultation mc = new Consultation(
                status,
                patient.geCpf(),
                doctor.getId(),
                dateService,
                reasonForService);

        consultationDao.save(mc);
    }

    public static void update(
            int id,
            int status,
            Patient patient,
            Doctor doctor,
            LocalDate dateService,
            String reasonForService) throws SQLException {
        Consultation consultation = new Consultation(
                id,
                status,
                patient.geCpf(),
                doctor.getId(),
                dateService, reasonForService);
        consultationDao.update(consultation);
    }

    public static void update(Consultation consultation) throws SQLException {
        consultationDao.update(consultation);
    }

    public static void delete(int id) throws SQLException {
        consultationDao.delete(id);
    }

    public static Consultation findById(int id) throws SQLException {
        Consultation mc = consultationDao.findById("consultation_id", id);
        if (mc == null) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }

        return mc;
    }

    public static List<Consultation> findAll() throws SQLException {
        List<Consultation> listMc = consultationDao.findAll();
        if (listMc == null) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }

        return listMc;
    }

    public static List<Consultation> findByPatient(Patient patient) throws SQLException {
        return consultationDao.findByPatient(patient);
    }

    public static Consultation findByIdAndPatient(Integer id, Patient patient) throws SQLException {
        return consultationDao.findByIdAndPatient(id, patient);
    }

    public static List<Consultation> findByDate(LocalDate date) throws SQLException {
        return consultationDao.findByDate(date);
    }

    public static Consultation findByPatientAndDate(Patient patient, LocalDate date) throws SQLException {
        return consultationDao.findByPatientAndDate(patient, date);
    }

    public static List<Consultation> findByPatientAndDateAfter(Patient patient, LocalDate date) throws SQLException {
        return consultationDao.findByPatientAndDateAfter(patient, date);
    }

}
