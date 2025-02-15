package com.project.service;

import java.util.List;

import com.project.dao.MedicalConsultationDAO;
import com.project.entity.Cpf;
import com.project.entity.Date;
import com.project.model.MedicalConsultation;

public class MedicalConsultationService {
    private static MedicalConsultationDAO mcDao = new MedicalConsultationDAO();

    public static void save(int typeService,
            Cpf cpf, Date dateService,
            String reasonForService) {
        MedicalConsultation mc = new MedicalConsultation(typeService, cpf, dateService, reasonForService);

        mcDao.save(mc);
    }

    public static void update(int id, int typeService,
            Cpf cpf, Date dateService,
            String reasonForService) {
        MedicalConsultation mc = new MedicalConsultation(id, typeService, cpf, dateService, reasonForService);
        mcDao.update(mc);
    }

    public static void delete(int id) {
        mcDao.delete(id);
    }

    public static MedicalConsultation findById(int id) {
        MedicalConsultation mc = mcDao.findById(id);
        if (mc == null) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }

        return mc;
    }

    public static List<MedicalConsultation> findAll() {
        List<MedicalConsultation> listMc = mcDao.findAll();
        if (listMc == null) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }

        return listMc;
    }

    public static List<MedicalConsultation> findByCpf(Cpf cpf) {
        return mcDao.findByCpf(cpf);
    }

    public static MedicalConsultation findByIdAndCpf(Integer id, Cpf cpf) {
        return mcDao.findByIdAndCpf(id, cpf);
    }

    public static List<MedicalConsultation> findByDate(Date date) {
        return mcDao.findByDate(date);
    }

}
