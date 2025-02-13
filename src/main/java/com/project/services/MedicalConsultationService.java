package com.project.services;

import com.project.model.MedicalConsultation;
// import com.project.util.ReadData;

public class MedicalConsultationService {
    private static void inTerminal(MedicalConsultation mAppointment) {
        System.out.println("Tipos de atendimento: ");
        System.out.println("(0) : [NOT URGENT] : Blue");
        System.out.println("(1) : [LITTLE URGENT] : Green");
        System.out.println("(2) : [URGENT] : Yellow");
        System.out.println("(3) : [EMERGING] : Red\n");

        System.out.print("Digite o tipo: ");
        // mAppointment.setTypeService(ReadData.INT());

        // mAppointment.setCpfPatient(CPF.inTerminal("Digite os dados do CPF: "));
        // mAppointment.setDateService(Date.inTerminal("Digite a data:"));

        System.out.print("\nDigite a razão da consulta: ");
        // mAppointment.setReasonForService(ReadData.STRING());
    }

    public static MedicalConsultation inTerminal(String msg) {
        MedicalConsultation mAppointment = new MedicalConsultation();

        System.out.println(msg);
        // MedicalConsultation.inTerminal(mAppointment);

        return mAppointment;
    }

    // public static MedicalConsultation inTerminal(boolean activateLoop, String
    // msg) {
    // if (!activateLoop)
    // // return MedicalConsultation.inTerminal(msg);

    // MedicalConsultation mAppointment = new MedicalConsultation();

    // char confirmation = '0';
    // do {
    // Terminal.clear();
    // System.out.println(msg);
    // MedicalConsultation.inTerminal(mAppointment);
    // Terminal.clear();

    // System.out.print("\nOs dados da consulta::\n\n" + mAppointment.toString() +
    // "\n\n::estão corretos? [y][n]");
    // confirmation = ReadData.CHAR();
    // } while (confirmation != 'y');

    // return mAppointment;
    // }
}
