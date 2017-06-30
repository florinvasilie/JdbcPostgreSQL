package com.licenta.dao;

import com.licenta.form.FormAcorduriPrecedente;
import com.licenta.form.FormAcorduriPrecedenteTable;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FormAcorduriPrecDAO {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private FormOptiuniDAO formOptiuniDAO = new FormOptiuniDAO();


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public String findCodPersoanaFromPersoaneBy(String nume){
        formOptiuniDAO.setDataSource(dataSource);
        return  formOptiuniDAO.findCodPersoanaFromPersoaneBy(nume);
    }
    public Integer findidStudentBy(String codPersoana, String nivelStudii){
        String sql = "SELECT st.idStudent FROM studenti st, specializari s " +
                "WHERE st.codPersoana = ? and st.idSpecializare = s.idSpecializare and s.nivelStudii = ? ";

        List<Integer> student = jdbcTemplate.queryForList(sql, Integer.class, codPersoana, nivelStudii);
        System.out.println("studentsize " + student.size());
        Integer idStudent = null;
        if (!student.isEmpty())
            idStudent = student.get(0);
        System.out.println("idStunde = " + idStudent);
        return idStudent;
    }

    public List<Integer> findidTraseuStudentBy(Integer idStudent){
        String sql = "SELECT idTraseuStudent FROM traseu_studenti WHERE idStudent = ?";
        List<Integer> idTraseuStudentList = jdbcTemplate.queryForList(sql, Integer.class, idStudent);
        for (Integer string : idTraseuStudentList)
            System.out.println(string);
        return idTraseuStudentList;
    }

    public List<FormAcorduriPrecedenteTable> showAcorduriPrecendete(List<Integer> idTraseuStudentList){
        List<FormAcorduriPrecedenteTable> formAcorduriPrecedenteTableList = new ArrayList<>();
        String sql = "SELECT  s.anUniversitar as anUnivStud, ts.anUniversitar as anUnivTraseu, p.denumirePartener as denumirePartener, " +
                              "m.durataAcordului as durataAcordului, a.valoareaAcordului as valoareaAcordului, sp.denumireSpecializare as denumireSpecializare " +
                    "FROM traseu_studenti ts, acorduri a, parteneri p, mobilitati m, studenti s, specializari sp " +
                    "WHERE ts.idTraseuStudent = ? and ts.idStudent = s.idStudent and ts.idAcord = a.idAcord and a.idPartener = p.idPartener and s.idSpecializare = sp.idSpecializare " +
                           "and a.idAcord = m.idAcord";
        for (Integer string : idTraseuStudentList){
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{ string });
            for (Map row : rows){
                String[] anUnivStudParts = ((String) row.get("anUnivStud")).split("-");
                int anUnivStud = Integer.valueOf(anUnivStudParts[1]);
                String[] anUnivTraseuParts = ((String) row.get("anUnivTraseu")).split("-");
                int anUnivTraseu = Integer.valueOf(anUnivTraseuParts[1]);
                if (anUnivTraseu < anUnivStud){
                    FormAcorduriPrecedenteTable formAcorduriPrecedenteTable = new FormAcorduriPrecedenteTable();
                    formAcorduriPrecedenteTable.setDenumirePartener((String) row.get("denumirePartener"));
                    formAcorduriPrecedenteTable.setDurataAcordului((String) row.get("durataAcordului"));
                    formAcorduriPrecedenteTable.setValoareaAcordului((Integer) row.get("valoareaAcordului"));
                    formAcorduriPrecedenteTable.setAnUniversitar((String) row.get("anUnivTraseu"));
                    formAcorduriPrecedenteTable.setDenumireSpecializare((String) row.get("denumireSpecializare"));
                    formAcorduriPrecedenteTableList.add(formAcorduriPrecedenteTable);
                }
            }

        }
        for (FormAcorduriPrecedenteTable formAcorduriPrecedenteTable : formAcorduriPrecedenteTableList){
            System.out.println(formAcorduriPrecedenteTable.getAnUniversitar());
            System.out.println(formAcorduriPrecedenteTable.getDenumirePartener());
            System.out.println(formAcorduriPrecedenteTable.getDurataAcordului());
            System.out.println(formAcorduriPrecedenteTable.getValoareaAcordului());
            System.out.println(formAcorduriPrecedenteTable.getDenumireSpecializare());
        }
        return formAcorduriPrecedenteTableList;
    }
}
