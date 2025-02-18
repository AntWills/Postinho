package com.project.model;

import java.time.LocalDate;

public class Consultation {
    private int id;
    /**
     * 0 -> Agendado;
     * 1 -> Realizado;
     * 2 -> Cancelado;
     * 3 -> Não realizado.
     */
    private int status;
    private String patientId;
    private int doctorId;
    private LocalDate date;
    private String reasonForService;

    public Consultation() {
    }

    public Consultation(
            int id,
            int status,
            String patientId,
            int doctorId,
            LocalDate dateService,
            String reasonForService) {
        this.id = id;
        this.status = status;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = dateService;
        this.reasonForService = reasonForService;
    }

    public Consultation(
            int status,
            String patientId,
            int doctorId,
            LocalDate dateService,
            String reasonForService) {
        this.status = status;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = dateService;
        this.reasonForService = reasonForService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientId() {
        return this.patientId;
    }

    public void setPatient(String patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return this.doctorId;
    }

    public void setDoctor(int doctorId) {
        this.doctorId = doctorId;
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
