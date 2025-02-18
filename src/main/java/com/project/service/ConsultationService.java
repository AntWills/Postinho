package com.project.service;

import java.time.LocalDate;
import java.util.List;

import com.project.dao.ConsultationDAO;
import com.project.entity.Date;
import com.project.model.Consultation;
import com.project.model.Doctor;
import com.project.model.Patient;

public class ConsultationService {
    private static ConsultationDAO consultationDao = new ConsultationDAO();

    public static void start() {
        consultationDao.start();
    }

    public static void save(
            int status,
            String patient_cpf,
            int idDoctor,
            LocalDate dateService,
            String reasonForService) {
        Patient patient = PatientService.findById(patient_cpf);
        Doctor doctor = DoctorService.findById(idDoctor);
        Consultation mc = new Consultation(status, patient, doctor, dateService, reasonForService);

        consultationDao.save(mc);
    }

    public static void save(
            int status,
            Patient patient,
            Doctor doctor,
            LocalDate dateService,
            String reasonForService) {
        Consultation mc = new Consultation(status, patient, doctor, dateService, reasonForService);

        consultationDao.save(mc);
    }

    public static void update(
            int id,
            int status,
            Patient patient,
            Doctor doctor,
            LocalDate dateService,
            String reasonForService) {
        Consultation consultation = new Consultation(id, status, patient, doctor, dateService, reasonForService);
        consultationDao.update(consultation);
    }

    public static void update(Consultation consultation) {
        consultationDao.update(consultation);
    }

    public static void delete(int id) {
        consultationDao.delete(id);
    }

    public static Consultation findById(int id) {
        Consultation mc = consultationDao.findById("consultation_id", id);
        if (mc == null) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }

        return mc;
    }

    public static List<Consultation> findAll() {
        List<Consultation> listMc = consultationDao.findAll();
        if (listMc == null) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }

        return listMc;
    }

    public static List<Consultation> findByPatient(Patient patient) {
        return consultationDao.findByPatient(patient);
    }

    public static Consultation findByIdAndPatient(Integer id, Patient patient) {
        return consultationDao.findByIdAndPatient(id, patient);
    }

    public static List<Consultation> findByDate(Date date) {
        return consultationDao.findByDate(date);
    }

    public static Consultation findByPatientAndDate(Patient patient, LocalDate date) {
        return consultationDao.findByPatientAndDate(patient, date);
    }

    public static List<Consultation> findByPatientAndDateAfter(Patient patient, Date date) {
        return consultationDao.findByPatientAndDateAfter(patient, date);
    }

}
