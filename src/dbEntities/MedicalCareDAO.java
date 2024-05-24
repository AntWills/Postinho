package dbEntities;

import entities.MedicalCare.MedicalCare;
import entities.CPF;

public class MedicalCareDAO {
    public MedicalCareDAO() {
        String query = "CREATE TABLE IF NOT EXISTS "
                + "MedicalCare ("
                + "id_MedicalCare INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "type_MedicalCare INTEGER NOT NULL, "
                + "cpf_patient_MedicalCare CHAR(13) NOT NULL, "
                + "date_care_MedicalCare CHAR(11), "
                + "reason_service TEXT)";
        try {
            UtilDB.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error MedicalCareDAO: " + e.getMessage());
        }
    }

    public void add(MedicalCare mc, CPF cpf) {
        String query = "INSERT INTO MedicalCare"
                + "(type_MedicalCare,cpf_patient_MedicalCare,date_care_MedicalCare,reason_service)"
                + "VALUES('" + mc.getTypeService() + "',"
                + "'" + cpf.getNumberCPF() + "',"
                + "'" + mc.getDateService().toString() + "',"
                + "'" + mc.getReasonForService() + "'"
                + ")";
        try {
            UtilDB.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error MedicalCareDAO: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM MedicalCare WHERE id_MedicalCare = " + id;
        try {
            UtilDB.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error MedicalCareDAO: " + e.getMessage());
        }
    }
}
