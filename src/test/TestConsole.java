package test;

import entities.patient.MedicalAppointment.*;;

public class TestConsole {
    public static void main(String[] args) {
        MedicalAppointment mAppointment = MedicalAppointment.inTerminal(true, "## Insira os dados da consulta ##\n");

        System.out.println(mAppointment);
    }
}
