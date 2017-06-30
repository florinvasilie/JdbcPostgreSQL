package com.licenta.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class SpecializariDAO {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }
    public List<String> findAllSpecilizari(){
        String sql = "SELECT denumireSpecializare FROM specializari";
        List<String> specializari = jdbcTemplate.queryForList(sql, String.class);
        return specializari;
    }
}
