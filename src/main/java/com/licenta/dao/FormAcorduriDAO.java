package com.licenta.dao;

import com.licenta.form.FormAcorduri;
import com.licenta.form.FormAcorduriTable;
import com.licenta.model.Acorduri;
import com.licenta.model.Sesiuni;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FormAcorduriDAO {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public List<Integer> findcodDosarBy(String nivelStudii, String anUniversitar, java.sql.Date dataSesiune){
        String sql = "SELECT d.codDosar FROM sesiuni s, dosare d, studenti st, specializari sp " +
                "WHERE sp.nivelStudii = ? and sp.idSpecializare = st.idSpecializare and st.anUniversitar = ? and  st.idStudent = d.idStudent " +
                "and s.dataSesiune = ? and s.codDosar = d.codDosar";
        List<Integer> codDosar = jdbcTemplate.queryForList(sql, Integer.class,nivelStudii, anUniversitar, dataSesiune);
        return codDosar;
    }

    public Set<Integer> findidAcordFromOptiuni_DosareBy(List<Integer> codDosar){
        String sql = "SELECT idAcord FROM optiuni_dosare where codDosar = ?";
        Set<Integer> idAcordAll = new HashSet<>();
        for (Integer string : codDosar){
            List<Integer> idAcord = jdbcTemplate.queryForList(sql, Integer.class, string);
            idAcordAll.addAll(idAcord);
        }
        for (Integer integer : idAcordAll)
            System.out.println("idAcord= " + integer);
        return idAcordAll;
    }

    public List<FormAcorduriTable> showFormAcorduriTable(Set<Integer> idAcord){
        List<FormAcorduriTable> formAcorduriTables = new ArrayList<>();
        String sqlGetidPartener = "SELECT idPartener FROM acorduri where idAcord = ?";
        Set<Integer> idPartenerAll = new HashSet<>();
        for (Integer integer : idAcord) {
            List<Integer> idPartener = jdbcTemplate.queryForList(sqlGetidPartener, Integer.class, integer);
            idPartenerAll.addAll(idPartener);
        }
        String sql = "SELECT p.domeniuActivitate, p.denumirePartener, p.tara, p.oras, a.valoareaAcordului, m.durataAcordului " +
                        "FROM parteneri p, acorduri a, mobilitati m where a.idPartener = p.idPartener and a.idAcord = m.idAcord and p.idPartener = ?";
        for (Integer integer : idPartenerAll){
            FormAcorduriTable formAcorduriTable = jdbcTemplate.queryForObject(sql,  new Object[] { integer }, new RowMapper<FormAcorduriTable>() {
                @Override
                public FormAcorduriTable mapRow(ResultSet rs, int rownumber) throws SQLException {
                    FormAcorduriTable f = new FormAcorduriTable();
                    f.setDomeniuActivitate(rs.getString(1));
                    f.setDenumirePartener(rs.getString(2));
                    f.setTara(rs.getString(3));
                    f.setOras(rs.getString(4));
                    f.setValoareaAcordlui(rs.getInt(5));
                    f.setDurataAcordului(rs.getString(6));
                    return f;
                }
            });
            formAcorduriTables.add(formAcorduriTable);
        }

//        for (FormAcorduriTable form : formAcorduriTables){
//            System.out.println("activitate " + form.getDomeniuActivitate());
//            System.out.println("denumire Patener " + form.getDenumirePartener());
//            System.out.println("oras " + form.getOras());
//            System.out.println("tara " + form.getTara());
//            System.out.println("valoarea acord " + form.getValoareaAcordlui());
//            System.out.println("durata acordului " + form.getDurataAcordului());
//        }
        return formAcorduriTables;
    }
}
