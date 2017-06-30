package com.licenta.dao;


import com.licenta.form.FormAdaugareStudent;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FormAdaugareStudentiDAO {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }
    public void insert(FormAdaugareStudent formAdaugareStudent){
        String sql = "SELECT idSpecializare FROM specializari WHERE denumireSpecializare = ?";
        Integer idSpecializare = jdbcTemplate.queryForObject(sql, new Object[]{formAdaugareStudent.getSpecializareAdaugareStudent()}, Integer.class);
        String sqlInsertPersoane = "insert into persoane(codPersoana, nume, prenume, email) VALUES(?, ?, ?, ?)";
        String sqlInsertStudenti = "insert into studenti(anUniversitar, varstaStudent, codPersoana, idSpecializare) VALUES(?, ?, ?, ?)";
        java.sql.Connection conn = null;
        java.sql.Connection conn1 = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlInsertPersoane);
            ps.setString(1, formAdaugareStudent.getCodPersoanaAdaugareStudent());
            ps.setString(2, formAdaugareStudent.getNumeAdaugareStudent());
            ps.setString(3, formAdaugareStudent.getPrenumeAdaugareStudent());
            ps.setString(4, formAdaugareStudent.getEmailAdaugareStudent());
            ps.executeUpdate();
            ps.close();

            conn1 = dataSource.getConnection();
            PreparedStatement preparedStatement = conn1.prepareStatement(sqlInsertStudenti);
            preparedStatement.setString(1, formAdaugareStudent.getAnUniversitarAdaugareStudent());
            preparedStatement.setInt(2, formAdaugareStudent.getVarstaStudentAdaugareStudent());
            preparedStatement.setString(3, formAdaugareStudent.getCodPersoanaAdaugareStudent());
            preparedStatement.setInt(4, idSpecializare);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
