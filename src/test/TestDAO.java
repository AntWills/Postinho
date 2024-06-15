package test;

import dbEntities.*;
import entities.patient.CPF;
import entities.patient.MedicalCare.Date;
import entities.patient.MedicalCare.MedicalAppointment;

import java.util.*;

public class TestDAO {
    public static void main(String[] args) {
        MedicalCareDAO.initi();

        // MedicalCareDAO.add(new MedicalCare(0,
        // new CPF("11122233300"),
        // new Date(), "Dor de cabeça"));

        /*
         * 
         * MedicalCare mCareVector[] = {
         * new MedicalCare(0,
         * new CPF("000.000.000-01"),
         * new Date(), "Dor de cabeça"),
         * new MedicalCare(0,
         * new CPF("000.000.000-01"),
         * new Date(), "Dor de cabeça"),
         * new MedicalCare(1,
         * new CPF("000.000.000-01"),
         * new Date(), "Vomito"),
         * new MedicalCare(2,
         * new CPF("000.000.000-01"),
         * new Date(), "Cortes no joelho"),
         * new MedicalCare(3,
         * new CPF("000.000.000-01"),
         * new Date(), "Perna quebrada")
         * };
         */

        List<MedicalAppointment> mcList = MedicalCareDAO.seek(new CPF("000.000.000-01"));

        for (int i = 0; i < mcList.size(); i++) {
            System.out.println(mcList.get(i) + "\n");
        }
    }
}
