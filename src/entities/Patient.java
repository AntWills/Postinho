package entities;

public class Patient {
    int id;
    String name;

    public Patient() {
    }

    public Patient(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void in() {
        System.err.println("\nDigite os seguintes dados do paciente:\n");

        System.err.println("Digite o id: ");
        this.id = ReadData.INT();
        System.err.println("Digite o nome: ");
        this.name = ReadData.STRING();
    }

}
