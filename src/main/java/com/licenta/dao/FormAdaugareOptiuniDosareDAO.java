package com.licenta.dao;

import com.licenta.form.FormAdaugareOptiuniDosare;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FormAdaugareOptiuniDosareDAO {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private FormOptiuniDAO formOptiuniDAO = new FormOptiuniDAO();

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public void insert(FormAdaugareOptiuniDosare formAdaugareOptiuniDosare){
        String sql = "SELECT idPartener FROM parteneri WHERE denumirePartener = ?";
        Integer idPartener = jdbcTemplate.queryForObject(sql, new Object[]{formAdaugareOptiuniDosare.getDenumirePartenerAdaugareOptiuniDosare()}, Integer.class);
        String sqlFindidAcord = "SELECT idAcord FROM acorduri WHERE idPartener = ?";
        List<Integer> idAcord = jdbcTemplate.queryForList(sqlFindidAcord, new Object[]{idPartener}, Integer.class);
        formOptiuniDAO.setDataSource(dataSource);
        String codPersoana = formOptiuniDAO.findCodPersoanaFromPersoaneBy(formAdaugareOptiuniDosare.getNumeStudentAdaugareOptiuniDosare());
        String sqlFindidStudent = "SELECT idStudent FROM studenti WHERE codPersoana = ?";
        List<Integer> idStudent = jdbcTemplate.queryForList(sqlFindidStudent, new Object[]{codPersoana}, Integer.class);
        String sqlInsertDosare = "INSERT INTO dosare(idStudent) VALUES(?)";
        java.sql.Connection conn = null;
        String sqlFindCodDosar = "SELECT codDosar from dosare WHERE idStudent = ?";
        List<Integer> codDosar = null;
        for (Integer integer : idStudent){
            codDosar = jdbcTemplate.queryForList(sqlFindCodDosar, new Object[]{integer}, Integer.class);
            try {
                conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlInsertDosare);
                ps.setInt(1, integer);
                ps.executeUpdate();
                ps.close();

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
        String sqlInsertOptiuniDosare = "INSERT INTO optiuni_dosare(numarulOptiunii, codDosar, idAcord) VALUES(?, ?, ?)";
        java.sql.Connection conn1 = null;
        for (Integer integer : idAcord){
            for (Integer codDosarContor : codDosar){
                try {
                    conn1 = dataSource.getConnection();
                    PreparedStatement preparedStatement = conn1.prepareStatement(sqlInsertOptiuniDosare);
                    preparedStatement.setInt(1, formAdaugareOptiuniDosare.getNumarulOptiuniiAdaugareOptiuniDosare());
                    preparedStatement.setInt(2,codDosarContor);
                    preparedStatement.setInt(3, integer);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                } catch (SQLException e) {
                    throw new RuntimeException(e);

                } finally {
                    if (conn != null) {
                        try {
                            conn1.close();
                        } catch (SQLException e) {
                        }
                    }
                }
            }

        }
    }
}
