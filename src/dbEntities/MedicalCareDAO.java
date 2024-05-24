package dbEntities;

import entities.MedicalCare.MedicalCare;

public class MedicalCareDAO {
    public MedicalCareDAO() {
        String query = "CREATE TABLE IF NOT EXISTS"
                + "MedicalCare ("
                + "id_MedicalCare INTEREGER PRIMARY KEY AUTOINCREMENT,"
                + "tipy_MedicalCare INTEREGER NOT NULL,"
                + "cpf_patient_MedicalCare char[13] NOT NULL,"
                + "date_care_MedicalCare char[10],"
                + "reason_service TEXT)";
        try {
            UtilDB.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error MedicalCareDAO: " + e.getMessage());
        }
    }

    public void add(MedicalCare mc) {
        // String query = "INSERT INTO MedicalCare"
        // + "VALUES(";
    }
}
