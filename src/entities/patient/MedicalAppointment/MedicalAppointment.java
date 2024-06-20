package entities.patient.MedicalAppointment;

import entities.terminal.*;
import entities.patient.CPF;

public class MedicalAppointment {
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
     * <p>
     * (3) : [EMERGING] : RED
     */
    private int typeService;
    private int id;
    private CPF cpfPatient;
    private Date dateService;
    private String reasonForService;

    public MedicalAppointment() {
        this.id = 0;
        this.typeService = 0;
        this.cpfPatient = new CPF();
        this.dateService = new Date();
        this.reasonForService = "";
    }

    public MedicalAppointment(int id, int typeService,
            CPF cpf, Date dateService,
            String reasonForService) {
        this.id = id;
        this.typeService = typeService;
        this.cpfPatient = cpf;
        this.dateService = dateService;
        this.reasonForService = reasonForService;
    }

    public MedicalAppointment(int typeService,
            CPF cpf, Date dateService,
            String reasonForService) {
        this.id = 0;
        this.typeService = typeService;
        this.cpfPatient = cpf;
        this.dateService = dateService;
        this.reasonForService = reasonForService;
    }

    private static void inTerminal(MedicalAppointment mAppointment) {
        System.out.println("Tipos de atendimento: ");
        System.out.println("(0) : [NOT URGENT] : Blue");
        System.out.println("(1) : [LITTLE URGENT] : Green");
        System.out.println("(2) : [URGENT] : Yellow");
        System.out.println("(3) : [EMERGING] : Red\n");

        System.out.print("Digite o tipo: ");
        mAppointment.setTypeService(ReadData.INT());

        mAppointment.setCpfPatient(CPF.inTerminal("Digite os dados do CPF: "));
        mAppointment.setDateService(Date.inTerminal("Digite a data:"));

        System.out.print("\nDigite a razão da consulta: ");
        mAppointment.setReasonForService(ReadData.STRING());
    }

    public static MedicalAppointment inTerminal(String msg) {
        MedicalAppointment mAppointment = new MedicalAppointment();

        System.out.println(msg);
        MedicalAppointment.inTerminal(mAppointment);

        return mAppointment;
    }

    public static MedicalAppointment inTerminal(boolean activateLoop, String msg) {
        if (!activateLoop)
            return MedicalAppointment.inTerminal(msg);

        MedicalAppointment mAppointment = new MedicalAppointment();

        char confirmation = '0';
        do {
            Terminal.clear();
            System.out.println(msg);
            MedicalAppointment.inTerminal(mAppointment);
            Terminal.clear();

            System.out.print("\nOs dados da consulta::\n\n" + mAppointment.toString() + "\n\n::estão corretos? [y][n]");
            confirmation = ReadData.CHAR();
        } while (confirmation != 'y');

        return mAppointment;
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
