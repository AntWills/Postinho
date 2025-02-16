package com.project.service;

import java.util.List;

import com.project.dao.ConsultationDAO;
import com.project.entity.Date;
import com.project.model.Consultation;
import com.project.model.Doctor;
import com.project.model.Patient;

public class ConsultationService {
    private static ConsultationDAO mcDao = new ConsultationDAO();

    public static void save(int typeService,
            Patient patient,
            Doctor doctor,
            Date dateService,
            String reasonForService) {
        Consultation mc = new Consultation(typeService, patient, doctor, dateService, reasonForService);

        mcDao.save(mc);
    }

    public static void update(int id,
            int typeService,
            Patient patient,
            Doctor doctor,
            Date dateService,
            String reasonForService) {
        Consultation mc = new Consultation(id, typeService, patient, doctor, dateService, reasonForService);
        mcDao.update(mc);
    }

    public static void delete(int id) {
        mcDao.delete(id);
    }

    public static Consultation findById(int id) {
        Consultation mc = mcDao.findById(id);
        if (mc == null) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }

        return mc;
    }

    public static List<Consultation> findAll() {
        List<Consultation> listMc = mcDao.findAll();
        if (listMc == null) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }

        return listMc;
    }

    public static List<Consultation> findByPatient(Patient patient) {
        return mcDao.findByPatient(patient);
    }

    public static Consultation findByIdAndPatient(Integer id, Patient patient) {
        return mcDao.findByIdAndPatient(id, patient);
    }

    public static List<Consultation> findByDate(Date date) {
        return mcDao.findByDate(date);
    }

}
