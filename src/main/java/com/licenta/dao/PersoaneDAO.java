package com.licenta.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.*;


public class PersoaneDAO {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }
    //select pentru droptdow - nume studenti
    public List<String> findAllStudenti() {
        List<String> studenti = new ArrayList<>();
        String sql = "SELECT nume, prenume FROM persoane";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            String nume = (String) row.get("nume");
            String prenume = (String) row.get("prenume");
            studenti.add(nume + " " + prenume);
        }
        return studenti;
    }
}
