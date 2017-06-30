package com.licenta.dao;

import com.licenta.form.FormStergePartener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class FormStergerePartenerDAO {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }
    public void deletePartener(FormStergePartener formStergePartener){
        String sql = "DELETE FROM parteneri WHERE  denumirePartener = ?";
        jdbcTemplate.update(sql, new Object[]{formStergePartener.getDenumirePartenerStergere()});
    }
}
