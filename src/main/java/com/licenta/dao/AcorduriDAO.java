package com.licenta.dao;

import com.licenta.form.FormAdaugareAcorduri;
import com.licenta.form.FormOptiuniTable;
import com.licenta.model.Acorduri;
import com.licenta.model.Parteneri;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class AcorduriDAO {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public void insert(FormAdaugareAcorduri acorduri) throws ParseException {
        String sql = "SELECT idPartener FROM parteneri WHERE denumirePartener = ?";
        Integer idPartener = jdbcTemplate.queryForObject(sql, new Object[]{acorduri.getDenumirePartener()}, Integer.class);

        String sqlInsert = "insert into acorduri "
                + "(dataAcordului, valoareaAcordului,dataInitiala, dataFinala, nrLocuriOcupate, nrLocuriDisponibile, idPartener)"
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        java.util.Date dataAcordului = format.parse(acorduri.getDataAcordului());
        java.sql.Date sqlDateAcord = new java.sql.Date(dataAcordului.getTime());
        java.util.Date dataInitiala = format.parse(acorduri.getDataInitiala());
        java.sql.Date sqlDataInitiala = new java.sql.Date(dataInitiala.getTime());
        java.util.Date dataFinala = format.parse(acorduri.getDataFinala());
        java.sql.Date sqlDataFinala = new java.sql.Date(dataFinala.getTime());

        java.sql.Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlInsert);
            ps.setDate(1, sqlDateAcord);
            ps.setInt(2, acorduri.getValoareaAcordului());
            ps.setDate(3, sqlDataInitiala);
            ps.setDate(4, sqlDataFinala);
            ps.setInt(5, 0);
            ps.setInt(6, acorduri.getNrLocuriDisponibile());
            ps.setInt(7, idPartener);
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




}
