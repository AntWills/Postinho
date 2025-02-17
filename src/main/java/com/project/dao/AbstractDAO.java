package com.project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T, K> implements Idao<T, K> {

    @Override
    public void save(T obj) {
        String query = this.getSaveQuery();
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            this.setPstmtForSave(pstmt, obj);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error." + this.getTableName() + ".save: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error." + this.getTableName() + ".save: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    @Override
    public void update(T obj) {
        String query = this.getUpdateQuery();
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            this.setPstmtForUdate(pstmt, obj);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error." + this.getTableName() + ".update: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error." + this.getTableName() + ".update: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    @Override
    public void delete(K id) {
        String query = "DELETE FROM " + this.getTableName() + " WHERE consultation_id = ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setObject(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error." + this.getTableName() + ".delete: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error." + this.getTableName() + ".delete: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    @Override
    public T findById(String nameId, K id) {
        T obj = null;

        String query = "";
        query += "SELECT * FROM  " + this.getTableName();
        query += " WHERE " + nameId + " = ?;";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                obj = this.mapResultSetToEntity(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error." + this.getTableName() + ".findById: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error." + this.getTableName() + ".findById: " + e.getMessage());
        }
        DbConnect.closeBank();

        return obj;
    }

    @Override
    public List<T> findAll() {
        List<T> listObj = new ArrayList<>();

        String query = "SELECT * FROM " + this.getTableName();

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                listObj.add(this.mapResultSetToEntity(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error." + this.getTableName() + ".findAll: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error." + this.getTableName() + ".findAll: " + e.getMessage());
        }
        DbConnect.closeBank();

        return listObj;
    }

    public abstract void start();

    protected abstract String getSaveQuery();

    protected abstract void setPstmtForSave(PreparedStatement pstmt, T obj) throws SQLException;

    protected abstract String getUpdateQuery();

    protected abstract void setPstmtForUdate(PreparedStatement pstmt, T obj) throws SQLException;
}
