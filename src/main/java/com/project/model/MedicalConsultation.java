package com.project.model;

import com.project.entity.CPF;
import com.project.entity.Date;
import com.project.util.ColorOut;

public class MedicalConsultation {
    private int typeService, id;
    private CPF cpfPatient;
    private Date dateService;
    private String reasonForService;

    public MedicalConsultation() {
    }

    public MedicalConsultation(int id, int typeService,
            CPF cpf, Date dateService,
            String reasonForService) {
        this.id = id;
        this.typeService = typeService;
        this.cpfPatient = cpf;
        this.dateService = dateService;
        this.reasonForService = reasonForService;
    }

    public MedicalConsultation(int typeService,
            CPF cpf, Date dateService,
            String reasonForService) {
        this.typeService = typeService;
        this.cpfPatient = cpf;
        this.dateService = dateService;
        this.reasonForService = reasonForService;
    }

    @Override
    public String toString() {
        return ColorOut.getText(typeServiceString(typeService), colorType(typeService))
                + " Data: " + dateService.toString() + "\n"
                + "CPF do paciente: " + cpfPatient + " ID: " + id + "\n"
                + "Motivação: " + reasonForService + ".";
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

    public int getTypeService() {
        return typeService;
    }

    public void setTypeService(int typeService) {
        this.typeService = typeService;
    }

    public CPF getCpfPatient() {
        return cpfPatient;
    }

    public void setCpfPatient(CPF cpf) {
        this.cpfPatient = cpf;
    }

    public Date getDateService() {
        return dateService;
    }

    public void setDateService(Date dateService) {
        this.dateService = dateService;
    }

    public String getReasonForService() {
        return reasonForService;
    }

    public void setReasonForService(String reasonForService) {
        this.reasonForService = reasonForService;
    }
}
