package test;

import entities.MedicalCare.*;
import dbEntities.*;
import entities.CPF;

public class TestDAO {
    public static void main(String[] args) {
        CPF cpf = new CPF();
        MedicalCare care = new MedicalCare(0, new Date(), "Dor de cabeça");

        MedicalCareDAO dao = new MedicalCareDAO();

        // dao.add(care, cpf);
        dao.delete(1);
    }
}
