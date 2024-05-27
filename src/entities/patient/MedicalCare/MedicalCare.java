package entities.patient.MedicalCare;

import entities.terminal.*;
import entities.patient.CPF;

public class MedicalCare {
    /**
     * # data reading #
     * <p>
     * number form : type servive : color form
     * <p>
     * (0) : [NOT URGENT] : Blue
     * <P>
     * (1) : [LITTLE URGENT] : Green
     * <p>
     * (2) : [URGENT] : Yellow
     */
    private int typeService;
    private int id;
    private CPF cpfPatient;
    private Date dateService;
    private String reasonForService;

    public MedicalCare() {
        this.id = 0;
        this.typeService = 0;
        this.cpfPatient = new CPF();
        this.dateService = new Date();
        this.reasonForService = "";
    }

    public MedicalCare(int id, int typeService,
            CPF cpf, Date dateService,
            String reasonForService) {
        this.id = id;
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
                + "Motivação: " + reasonForService;
    }

    private String colorType(int i) {
        switch (i) {
            case 0:
                return ColorOut.black + ColorOut.bgBlue + ColorOut.bold;
            case 1:
                return ColorOut.black + ColorOut.bgGreen + ColorOut.bold;
            case 2:
                return ColorOut.black + ColorOut.bgYellow + ColorOut.bold;
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
            default:
                return "";
        }
    }

    public int getTypeService() {
        return typeService;
    }

    public void setTypeService(int typeService) {
        this.typeService = typeService;
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
