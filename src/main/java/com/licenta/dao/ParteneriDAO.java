package com.licenta.dao;

import com.licenta.form.FormAdaugarePartener;
import com.licenta.model.Parteneri;
import org.springframework.jdbc.core.JdbcTemplate;


import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ParteneriDAO {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public void insert(FormAdaugarePartener partener){
        String sql = "insert into parteneri "
                + "( domeniuActivitate, denumirePartener,tara, oras)"
                + "VALUES ( ?, ?, ?, ?)";
        java.sql.Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, partener.getDomeniuActivitateAdaugarePartener());
            ps.setString(2, partener.getDenumirePartenerAdaugarePartener());
            ps.setString(3, partener.getTaraAdaugarePartener());
            ps.setString(4, partener.getOrasAdaugarePartener());
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
    public List<String> findAllParteneri() {
        String sql = "SELECT denumirePartener FROM parteneri";
        List<String> parteneri = jdbcTemplate.queryForList(sql, String.class);
        return parteneri;
    }
}
