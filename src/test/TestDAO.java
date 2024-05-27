package test;

import dbEntities.*;
import entities.patient.CPF;
import entities.patient.MedicalCare.Date;
import entities.patient.MedicalCare.MedicalCare;

import java.util.*;

public class TestDAO {
    public static void main(String[] args) {
        // CPF cpf = new CPF();
        // MedicalCare care = new MedicalCare(0, new Date(), "Dor de cabeça");

        MedicalCareDAO.initi();

        // dao.add(care, cpf);
        // dao.add(care, cpf);
        // dao.add(care, cpf);
        // dao.add(care, cpf);
        // dao.add(care, cpf);
        // dao.delete(1);
        List<MedicalCare> list = MedicalCareDAO.seek(new CPF("00000000000"));
        System.out.println("Teste: " + list.size() + "\n\n");

        for (MedicalCare mc : list) {
            System.out.println(mc.toString() + "\n");
        }
    }
}
