package test;

import entities.MedicalCare.Date;
import entities.MedicalCare.MedicalCare;
import dbEntities.*;
import entities.CPF;

import java.util.*;

public class TestDAO {
    public static void main(String[] args) {
        CPF cpf = new CPF();
        MedicalCare care = new MedicalCare(0, new Date(), "Dor de cabeça");

        MedicalCareDAO dao = new MedicalCareDAO();

        // dao.add(care, cpf);
        // dao.add(care, cpf);
        // dao.add(care, cpf);
        // dao.add(care, cpf);
        // dao.add(care, cpf);
        // dao.delete(1);
        List<MedicalCare> list = dao.seek(new CPF("00000000001"));
        System.out.println("Teste: " + list.size() + "\n\n");

        for (MedicalCare mc : list) {
            System.out.println(mc.toString() + "\n");
        }
    }
}
