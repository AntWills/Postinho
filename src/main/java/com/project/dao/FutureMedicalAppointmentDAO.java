package com.project.dao;

public class FutureMedicalAppointmentDAO {

}

// import java.util.List;
// import java.util.ArrayList;

// import com.project.entity.Cpf;
// import com.project.entity.Date;
// import com.project.model.Consultation;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// public class FutureMedicalAppointmentDAO {
// public static void initi() {
// DbConnect.openBank();
// String query = "CREATE TABLE IF NOT EXISTS "
// + "FutureMedicalAppointment ("
// + "id_FutureMedicalAppointment INTEGER PRIMARY KEY AUTOINCREMENT, "
// + "type_FutureMedicalAppointment INTEGER NOT NULL, "
// + "cpf_patient_FutureMedicalAppointment CHAR(11) NOT NULL, "
// + "date_care_FutureMedicalAppointment CHAR(10), "
// + "reason_service_FutureMedicalAppointment TEXT"
// + ");";
// try {
// DbConnect.execQuery(query);
// } catch (Exception e) {
// System.err.println("Error FutureMedicalCareDAO: " + e.getMessage());
// }
// DbConnect.closeBank();
// }

// public static void add(Consultation mc) {
// String query = "INSERT INTO FutureMedicalAppointment"
// + "(type_FutureMedicalAppointment, cpf_patient_FutureMedicalAppointment,"
// + "date_care_FutureMedicalAppointment,
// reason_service_FutureMedicalAppointment)"
// + "VALUES (?, ?, ?, ?)";

// try {
// PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

// pstmt.setInt(1, mc.getTypeService());
// pstmt.setString(2, mc.getPatient().getStringCpf());
// pstmt.setString(3, mc.getDateConsultation().toString());
// pstmt.setString(4, mc.getReasonForService());

// pstmt.executeUpdate();
// } catch (SQLException e) {
// System.err.println("Error FutureMedicalAppointmentDAO.add: " +
// e.getMessage());
// } catch (Exception e) {
// System.err.println("Error FutureMedicalAppointmentDAO.add: " +
// e.getMessage());
// }
// DbConnect.closeBank();
// }

// public static void delete(int id) {
// String query = "DELETE FROM FutureMedicalAppointment Where
// id_FutureMedicalAppointment = ?";

// try {
// PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

// pstmt.setInt(1, id);

// pstmt.executeUpdate();
// } catch (SQLException e) {
// System.err.println("Error FutureMedicalAppointmentDAO.delete(int): " +
// e.getMessage());
// } catch (Exception e) {
// System.err.println("Error FutureMedicalAppointmentDAO.delete(int): " +
// e.getMessage());
// }
// DbConnect.closeBank();
// }

// public static void updade(int id, Consultation mAppointment) {
// String query = "UPDATE FutureMedicalAppointment SET "
// + "type_FutureMedicalAppointment = ?, "
// + "cpf_patient_FutureMedicalAppointment = ?, "
// + "date_care_FutureMedicalAppointment = ?, "
// + "reason_service_FutureMedicalAppointment = ? "
// + "WHERE id_FutureMedicalAppointment = " + id;
// try {
// PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

// pstmt.setInt(1, mAppointment.getTypeService());
// pstmt.setString(2, mAppointment.getPatient().getStringCpf());
// pstmt.setString(3, mAppointment.getDateConsultation().toString());
// pstmt.setString(4, mAppointment.getReasonForService());

// pstmt.executeUpdate();
// } catch (SQLException e) {
// System.err.println("Error FutureMedicalAppointmentDAO.update: " +
// e.getMessage());
// } catch (Exception e) {
// System.err.println("Error FutureMedicalAppointmentDAO.update: " +
// e.getMessage());
// }
// DbConnect.closeBank();
// }

// public static Consultation search(int id) {
// Consultation mAppointment = null;

// String query = "SELECT * FROM FutureMedicalAppointment "
// + "WHERE id_FutureMedicalAppointment = ?";
// try {
// PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

// pstmt.setInt(1, id);

// ResultSet rSet = pstmt.executeQuery();

// while (rSet.next()) {
// int idMedicalCare = rSet.getInt(1);
// int type = rSet.getInt(2);
// Cpf cpfMedicalCare = new Cpf(rSet.getString(3));
// Date dateMedicalCare = new Date(rSet.getString(4));
// String reazon = rSet.getString(5);

// mAppointment = new Consultation(
// idMedicalCare, type,
// cpfMedicalCare, dateMedicalCare, reazon);

// }
// } catch (SQLException e) {
// System.err.println("Error FutureMedicalAppointmentDAO.seek(int): " +
// e.getMessage());
// } catch (Exception e) {
// System.err.println("Error FutureMedicalAppointmentDAO.seek(int): " +
// e.getMessage());
// }
// DbConnect.closeBank();
// return mAppointment;
// }

// public static List<Consultation> search(Cpf cpf) {
// List<Consultation> list = new ArrayList<>();

// String query = "SELECT * FROM FutureMedicalAppointment "
// + "WHERE cpf_patient_FutureMedicalAppointment = ?";
// try {
// PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

// pstmt.setString(1, cpf.getStringCpf());

// ResultSet rSet = pstmt.executeQuery();

// while (rSet.next()) {
// int idMedicalCare = rSet.getInt(1);
// int type = rSet.getInt(2);
// Cpf cpfMedicalCare = new Cpf(rSet.getString(3));
// Date dateMedicalCare = new Date(rSet.getString(4));
// String reazon = rSet.getString(5);

// Consultation mc = new Consultation(
// idMedicalCare, type,
// cpfMedicalCare, dateMedicalCare, reazon);
// list.add(mc);
// }
// } catch (SQLException e) {
// System.err.println("Error FutureMedicalAppointmentDAO.seek(CPF): " +
// e.getMessage());
// } catch (Exception e) {
// System.err.println("Error FutureMedicalAppointmentDAO.seek(CPF): " +
// e.getMessage());
// }
// return list;
// }

// public static Consultation search(int id, Cpf cpf) {
// Consultation mAppointment = null;

// String query = "SELECT * FROM FutureMedicalAppointment "
// + "WHERE id_FutureMedicalAppointment = ? AND " +
// "cpf_patient_FutureMedicalAppointment ?";
// try {
// PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

// pstmt.setInt(1, id);
// pstmt.setString(2, cpf.getStringCpf());

// ResultSet rSet = pstmt.executeQuery();

// while (rSet.next()) {
// int idMedicalCare = rSet.getInt(1);
// int type = rSet.getInt(2);
// Cpf cpfMedicalCare = new Cpf(rSet.getString(3));
// Date dateMedicalCare = new Date(rSet.getString(4));
// String reazon = rSet.getString(5);

// mAppointment = new Consultation(
// idMedicalCare, type,
// cpfMedicalCare, dateMedicalCare, reazon);

// }
// } catch (SQLException e) {
// System.err.println("Error FutureMedicalAppointmentDAO.seek(int): " +
// e.getMessage());
// } catch (Exception e) {
// System.err.println("Error FutureMedicalAppointmentDAO.seek(int): " +
// e.getMessage());
// }
// DbConnect.closeBank();
// return mAppointment;
// }

// public static List<Consultation> search(Date date) {
// List<Consultation> list = new ArrayList<>();

// String query = "SELECT * FROM FutureMedicalAppointment "
// + "WHERE date_care_FutureMedicalAppointment = ?";
// try {
// PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

// pstmt.setString(1, date.toString());

// ResultSet rSet = pstmt.executeQuery();

// while (rSet.next()) {
// int idMedicalCare = rSet.getInt(1);
// int type = rSet.getInt(2);
// Cpf cpfMedicalCare = new Cpf(rSet.getString(3));
// Date dateMedicalCare = new Date(rSet.getString(4));
// String reazon = rSet.getString(5);

// Consultation mc = new Consultation(
// idMedicalCare, type,
// cpfMedicalCare, dateMedicalCare, reazon);
// list.add(mc);
// }
// } catch (SQLException e) {
// System.err.println("Error FutureMedicalAppointmentDAO.seek(Date): " +
// e.getMessage());
// } catch (Exception e) {
// System.err.println("Error FutureMedicalAppointmentDAO.seek(Date): " +
// e.getMessage());
// }
// DbConnect.closeBank();
// return list;
// }
// }
