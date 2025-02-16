package com.project.model;

// import com.project.entity.Cpf;
import com.project.entity.Date;
// import com.project.model.Doctor;
// import com.project.service.PatientService;
import com.project.util.ColorOut;

public class Consultation {
    private int id;
    // private int typeService;
    private Patient patient;
    private Doctor doctor;
    private Date dateConsultation;
    private String reasonForService;

    public Consultation() {
    }

    public Consultation(int id,
            int typeService,
            Patient patient,
            Doctor doctor,
            Date dateService,
            String reasonForService) {
        this.id = id;
        // this.typeService = typeService;
        this.patient = patient;
        this.doctor = doctor;
        this.dateConsultation = dateService;
        this.reasonForService = reasonForService;
    }

    public Consultation(int typeService,
            Patient patient,
            Doctor doctor,
            Date dateService,
            String reasonForService) {
        // this.typeService = typeService;
        this.patient = patient;
        this.doctor = doctor;
        this.dateConsultation = dateService;
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

    private String colorType(int i) {
        switch (i) {
            case 0:
                return ColorOut.black + ColorOut.bgBlue + ColorOut.bold;
            case 1:
                return ColorOut.black + ColorOut.bgGreen + ColorOut.bold;
            case 2:
                return ColorOut.black + ColorOut.bgYellow + ColorOut.bold;
            case 3:
                return ColorOut.black + ColorOut.bgRed + ColorOut.bold;
            default:
                return "";
        }
    }

    private String typeServiceString(int i) {
        switch (i) {
            case 0:
                return "[NOT URGENT]";
            case 1:
                return "[LITTLE URGENT]";
            case 2:
                return "[URGENT]";
            case 3:
                return "[EMERGING]";
            default:
                return "";
        }
    }

    public int getID() {
        return id;
    }

    // public int getTypeService() {
    // return typeService;
    // }

    // public void setTypeService(int typeService) {
    // this.typeService = typeService;
    // }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor geDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(Date dateService) {
        this.dateConsultation = dateService;
    }

    public String getReasonForService() {
        return reasonForService;
    }

    public void setReasonForService(String reasonForService) {
        this.reasonForService = reasonForService;
    }
}
