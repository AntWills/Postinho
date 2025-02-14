package com.project.model;

import java.util.List;
import com.project.entity.Cpf;
import java.util.ArrayList;

public class Patient {
    Cpf cpf;
    String name;
    List<MedicalConsultation> consultations;

    public Patient() {
    }

    public Patient(Cpf cpf, String name) {
        this.cpf = cpf;
        this.name = name;
        this.consultations = new ArrayList<>();
    }

    public Patient(Cpf cpf, String name, List<MedicalConsultation> cultateList) {
        this.cpf = cpf;
        this.name = name;
        this.consultations = cultateList;
    }

    @Override
    public String toString() {
        return "cpf: " + cpf + " - Nome: " + name
                + "\nPossui um total de " + consultations.size()
                + " consultas realizadas.";
    }

    public Cpf geCpft() {
        return cpf;
    }

    public void setCpfId(Cpf cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MedicalConsultation> getConsultations() {
        return consultations;
    }

    public MedicalConsultation getMedicalAppointmentID(int id) {
        List<MedicalConsultation> list = this.consultations;

        for (MedicalConsultation mAppointment : list) {
            if (mAppointment.getID() == id)
                return mAppointment;
        }
        return null;
    }

}
