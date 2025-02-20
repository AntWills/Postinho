package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T, K> implements Idao<T, K> {

    @Override
    public void save(T obj) throws SQLException {
        String query = this.getSaveQuery();

        Connection con = DbConnect.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);

        this.setPstmtForSave(pstmt, obj);
        pstmt.executeUpdate();

        DbConnect.closeBank();
    }

    @Override
    public void update(T obj) throws SQLException {
        String query = this.getUpdateQuery();

        PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
        this.setPstmtForUdate(pstmt, obj);
        pstmt.executeUpdate();

        DbConnect.closeBank();
    }

    @Override
    public void delete(K id) throws SQLException {
        String query = "DELETE FROM " + this.getTableName() + " WHERE consultation_id = ?";

        PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
        pstmt.setObject(1, id);
        pstmt.executeUpdate();
        DbConnect.closeBank();
    }

    @Override
    public T findById(String nameId, K id) throws SQLException {
        T obj = null;

        String query = "";
        query += "SELECT * FROM  " + this.getTableName();
        query += " WHERE " + nameId + " = ?;";

        PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
        pstmt.setObject(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            obj = this.mapResultSetToEntity(rs);
        }

        DbConnect.closeBank();
        return obj;
    }

    @Override
    public List<T> findAll() throws SQLException {
        List<T> listObj = new ArrayList<>();

        String query = "SELECT * FROM " + this.getTableName();

        PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            listObj.add(this.mapResultSetToEntity(rs));
        }

        DbConnect.closeBank();
        return listObj;
    }

    public abstract void start() throws SQLException;

    protected abstract String getSaveQuery();

    protected abstract void setPstmtForSave(PreparedStatement pstmt, T obj) throws SQLException;

    protected abstract String getUpdateQuery();

    protected abstract void setPstmtForUdate(PreparedStatement pstmt, T obj) throws SQLException;
}
