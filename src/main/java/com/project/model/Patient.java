package com.project.model;

import java.util.List;
import com.project.entity.Cpf;
import java.util.ArrayList;

public class Patient {
    Cpf cpf;
    String name;
    List<Consultation> consultations;

    public Patient() {
    }

    public Patient(Cpf cpf, String name) {
        this.cpf = cpf;
        this.name = name;
        this.consultations = new ArrayList<>();
    }

    public Patient(Cpf cpf, String name, List<Consultation> cultateList) {
        this.cpf = cpf;
        this.name = name;
        this.consultations = cultateList;
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

    public List<Consultation> getConsultations() {
        return consultations;
    }
}
