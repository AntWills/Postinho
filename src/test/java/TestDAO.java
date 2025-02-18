import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.project.dao.DbConnect;

public class TestDAO {
    @Test
    public void testCreateTableDoctor() {
        final String query = "CREATE TABLE IF NOT EXISTS doctor (" +
                "doctor_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL" +
                ");";

        assertDoesNotThrow(() -> DbConnect.execQuery(query), "");
    }

    @Test
    public void testCreateTablePatient() {
        final String query = "CREATE TABLE IF NOT EXISTS " + "patient" + " (" +
                "cpf_id TEXT PRIMARY KEY, " +
                "name TEXT" +
                ");";

        assertDoesNotThrow(() -> DbConnect.execQuery(query), "");
    }

    @Test
    public void testCreateTableConsultation() {
        final String query = "CREATE TABLE IF NOT EXISTS " + "consultation" + " (" +
                "consultation_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "patient_id TEXT NOT NULL, " +
                "doctor_id INTEGER NOT NULL, " +
                "status INTEGER NOT NULL, " +
                "date_consultation TEXT, " +
                "reason TEXT, " + // Adicionando a vírgula que estava faltando
                "FOREIGN KEY (patient_id) REFERENCES Patient(cpf_id), " +
                "FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id)" +
                ");";

        assertDoesNotThrow(() -> DbConnect.execQuery(query), "");
    }
    // MedicalAppointmentDAO.initi();

    // MedicalCareDAO.add(new MedicalCare(0,
    // new CPF("11122233300"),
    // new Date(), "Dor de cabeça"));

    /*
     * 
     * MedicalCare mCareVector[] = {
     * new MedicalCare(0,
     * new CPF("000.000.000-01"),
     * new Date(), "Dor de cabeça"),
     * new MedicalCare(0,
     * new CPF("000.000.000-01"),
     * new Date(), "Dor de cabeça"),
     * new MedicalCare(1,
     * new CPF("000.000.000-01"),
     * new Date(), "Vomito"),
     * new MedicalCare(2,
     * new CPF("000.000.000-01"),
     * new Date(), "Cortes no joelho"),
     * new MedicalCare(3,
     * new CPF("000.000.000-01"),
     * new Date(), "Perna quebrada")
     * };
     */

    // List<MedicalAppointment> mcList = MedicalAppointmentDAO.search(new
    // CPF("000.000.000-01"));

    // for (int i = 0; i < mcList.size(); i++) {
    // System.out.println(mcList.get(i) + "\n");
    // }

}
