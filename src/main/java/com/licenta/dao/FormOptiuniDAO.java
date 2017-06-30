package com.licenta.dao;

import com.licenta.form.FormOptiuniTable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class FormOptiuniDAO {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private FormAcorduriDAO formAcorduriDAO = new FormAcorduriDAO();

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public String findCodPersoanaFromPersoaneBy(String numeStudent){
        String nume = numeStudent.substring(0, numeStudent.indexOf(" "));
        System.out.println("Nume = " + nume);
        String sql = "SELECT codPersoana FROM persoane WHERE nume = ?";
        String codPersoana = jdbcTemplate.queryForObject(sql, new Object[] {nume}, String.class);
        System.out.println("CodPersoana pentru " + numeStudent + " este " + codPersoana);
        return codPersoana;
    }

    public Integer findidStudentBy(String codPersoana, String specializare, String anUniversitar){
        String sql = "SELECT st.idStudent FROM specializari s, studenti st WHERE st.codPersoana = ? and s.idSpecializare = st.idSpecializare" +
                " and s.denumireSpecializare = ? and st.anUniversitar = ?";
        List<Integer> student = jdbcTemplate.queryForList(sql, Integer.class, codPersoana, specializare, anUniversitar);
        Integer idStudent = null;
        if (!student.isEmpty())
             idStudent = student.get(0);
        System.out.println("idStunde = " + idStudent);
        return idStudent;
    }

    public List<Integer> findcodDosarBy(Integer idStudent, java.sql.Date dataSesiune){
        String sql = "SELECT d.codDosar FROM dosare d, sesiuni s, studenti st " +
                "WHERE s.codDosar = d.codDosar and s.dataSesiune = ? and st.idStudent = d.idStudent and st.idStudent = ?";
        List<Integer> codDosar = jdbcTemplate.queryForList(sql, Integer.class, dataSesiune, idStudent);
        for (Integer string : codDosar)
            System.out.println("cod dosar " + string);
        return  codDosar;
    }

    public Set<Integer> findidAcordFromOptiuni_DosareBy(List<Integer> codDosar){
        formAcorduriDAO.setDataSource(dataSource);
        return formAcorduriDAO.findidAcordFromOptiuni_DosareBy(codDosar);
    }
    public List<FormOptiuniTable> showFormOptiuni(Set<Integer> idAcord){
        List<FormOptiuniTable> formOptiuniTableList = new ArrayList<>();
        String sqlGetidPartener = "SELECT idPartener FROM acorduri where idAcord = ?";
        Set<Integer> idPartenerAll = new HashSet<>();
        for (Integer integer : idAcord) {
            List<Integer> idPartener = jdbcTemplate.queryForList(sqlGetidPartener, Integer.class, integer);
            idPartenerAll.addAll(idPartener);
        }
        String sql = "SELECT p.domeniuActivitate as domeniu, p.denumirePartener as partener, o.numarulOptiunii as optiune" +
                " FROM parteneri p, acorduri a, optiuni_dosare o where a.idPartener = p.idPartener and p.idPartener = ? and o.idAcord = a.idAcord";
        for (Integer integer : idPartenerAll){
            System.out.println("id partener " + integer);

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{ integer });
            for (Map row : rows) {
                FormOptiuniTable formOptiuniTable = new FormOptiuniTable();
                formOptiuniTable.setDenumirePartener((String) row.get("partener"));
                formOptiuniTable.setDomeniuActivitate((String) row.get("domeniu"));
                formOptiuniTable.setNumarulOptiunii((Integer) row.get("optiune"));
                formOptiuniTableList.add(formOptiuniTable);
            }
        }
        for (FormOptiuniTable formOptiuniTable : formOptiuniTableList){
            System.out.println("Domeniu activitate " + formOptiuniTable.getDomeniuActivitate());
            System.out.println("Denumire partener " + formOptiuniTable.getDenumirePartener());
            System.out.println("Numarul otpiunii " + formOptiuniTable.getNumarulOptiunii());
        }
        return formOptiuniTableList;
    }
}
