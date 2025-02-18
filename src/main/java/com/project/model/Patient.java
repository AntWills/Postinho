package com.project.model;

import java.util.List;

import com.project.exception.InvalidCpfException;
import com.project.exception.UtilCpf;

import java.util.ArrayList;

public class Patient {
    String cpf;
    String name;
    List<Consultation> consultations;

    public Patient() {
    }

    public Patient(String cpf, String name) throws InvalidCpfException {
        UtilCpf.validator(cpf);
        this.cpf = cpf;
        this.name = name;
    }

    public Patient(String cpf, String name, List<Consultation> consultationList) throws InvalidCpfException {
        UtilCpf.validator(cpf);

        this.cpf = cpf;
        this.name = name;
        this.consultations = consultationList;
    }

    public String geCpf() {
        return cpf;
    }

    public void setCpfId(String cpf) throws InvalidCpfException {
        UtilCpf.validator(cpf);

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

    public void setConsultations(List<Consultation> consultationList) {
        this.consultations = consultationList;
    }
}
