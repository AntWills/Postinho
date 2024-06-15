package dbEntities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.patient.*;

public class PatientDAO {
    public static void initi() {
        MedicalAppointmentDAO.initi();
        String query = "CREATE TABLE IF NOT EXISTS "
                + "Patient(cpf_Patient CHAR(11) PRIMARY KEY,"
                + "nome_Patient text"
                + ");";

        try {
            UtilDB.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error PatientDAO: " + e.getMessage());
        }
    }

    public static void add(Patient patient) {
        String query = "INSERT INTO Patient "
                + "(cpf_Patient, nome_Patient)"
                + "VALUES (?, ?)";
        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);

            pstmt.setString(1, patient.getId().getNumberCPF());
            pstmt.setString(2, patient.getName());

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error PatientDAO: " + e.getMessage());
        }
    }

    public static void delete(CPF cpf) {
        String query = "DELETE FROM Patient WHERE cpf_Patient = ?";
        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);
            pstmt.setString(1, cpf.getNumberCPF());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error PatientDAO: " + e.getMessage());
        }
    }

    public static Patient seek(CPF cpf) {
        Patient patient = null;
        String query = "SELECT * FROM Patient WHERE cpf_Patient = ?";

        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);
            pstmt.setString(1, cpf.getNumberCPF());
            ResultSet rSet = pstmt.executeQuery();

            if (rSet.next()) {
                CPF cpfPatient = new CPF(rSet.getString(1));
                String name = rSet.getString(2);

                patient = new Patient(cpfPatient, name, MedicalAppointmentDAO.seek(cpf));
            }
        } catch (Exception e) {
            System.err.println("Error PatientDAO: " + e.getMessage());
        }
        return patient;
    }
}
