package com.project.model;

import java.time.LocalDate;

// import com.project.entity.Cpf;
import com.project.entity.Date;
// import com.project.model.Doctor;
// import com.project.service.PatientService;

public class Consultation {
    private int id;
    /**
     * 0 -> Agendado;
     * 1 -> Realizado;
     * 2 -> Cancelado;
     * 3 -> Não realizado.
     */
    private int status;
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private String reasonForService;

    public Consultation() {
    }

    public Consultation(
            int id,
            int status,
            Patient patient,
            Doctor doctor,
            LocalDate dateService,
            String reasonForService) {
        this.id = id;
        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
        this.date = dateService;
        this.reasonForService = reasonForService;
    }

    public Consultation(
            int status,
            Patient patient,
            Doctor doctor,
            LocalDate dateService,
            String reasonForService) {
        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
        this.date = dateService;
        this.reasonForService = reasonForService;
    }

    @Override
    public String toString() {
        // return ColorOut.getText(typeServiceString(typeService),
        // colorType(typeService))
        // + " Data: " + dateConsultation.toString() + "\n"
        // + "CPF do paciente: " + idPatient + " ID: " + id + "\n"
        // + "Motivação: " + reasonForService + ".";
        return "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate dateService) {
        this.date = dateService;
    }

    public String getReasonForService() {
        return reasonForService;
    }

    public void setReasonForService(String reasonForService) {
        this.reasonForService = reasonForService;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
