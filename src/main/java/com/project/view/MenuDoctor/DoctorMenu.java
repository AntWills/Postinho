package com.project.view.MenuDoctor;

import com.project.model.Doctor;
import com.project.util.EntityUtil;
import com.project.util.ReadDataFromTerminal;
import com.project.util.Terminal;

public class DoctorMenu {
    private int op;
    private Doctor doctor;

    DoctorMenu(Doctor doctor) {
        this.doctor = doctor;
    }

    public static void runDoctorMenu(Doctor doctor) {
        DoctorMenu doctorMenu = new DoctorMenu(doctor);

        do {
            doctorMenu.menu();
            doctorMenu.options();
        } while (doctorMenu.getOp() != 0);
    }

    public void menu() {
        Terminal.clear();
        System.out.println("-- Doutor : " + doctor.getName() + " --\n");

        System.out.println("Dados do doutor:\n");
        EntityUtil.printInTerminal(doctor);
        System.out.println("");

        // System.out.println("\n[1] : Imprimir ultimas consultas.");
        // System.out.println("[2] : Imprimir consultas futuras.");
        // System.out.println("[3] : Atualizar ultimas consulta.");
        // System.out.println("[4] : Remarcar consultas futuras.");
        System.out.println("[#] Não sei o que colocar aqui.");
        System.out.println("[0] : Voltar.\n");

        System.out.print("Digite uma das opções: ");
        this.op = ReadDataFromTerminal.INT();
    }

    public void options() {

    }

    public int getOp() {
        return this.op;
    }
}
