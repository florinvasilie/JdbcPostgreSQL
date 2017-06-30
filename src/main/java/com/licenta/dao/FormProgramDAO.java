package com.licenta.dao;

import com.licenta.form.FormProgramTable;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.*;


public class FormProgramDAO {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private FormOptiuniDAO formOptiuniDAO = new FormOptiuniDAO();

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public String findCodPersoanaFromPersoaneBy(String nume){
        formOptiuniDAO.setDataSource(dataSource);
        return formOptiuniDAO.findCodPersoanaFromPersoaneBy(nume);
    }
    public Set<Integer> findMentoriForStudentBy(String codPersoana){
        String sql = "SELECT idStudent FROM studenti WHERE codPersoana = ?";
        List<Integer> idStudent = jdbcTemplate.queryForList(sql, Integer.class, codPersoana);
        Set<Integer> idMentoriAll = new HashSet<>();
        String sqlForMentori = "SELECT idMentor FROM mentori WHERE idStudent = ?";
        for (Integer string : idStudent) {
            List<Integer> idMentori = jdbcTemplate.queryForList(sqlForMentori, Integer.class, string);
            idMentoriAll.addAll(idMentori);
        }
        for (Integer string : idMentoriAll)
            System.out.println("idMentor =" + string + "pentru studentul " + idStudent);
        return idMentoriAll;

    }
    public List<FormProgramTable> showProgram(Set<Integer> idMentori){
        List<FormProgramTable> formProgramTableList = new ArrayList<>();
        String sql = "SELECT a.denumire as denumireActivitate, a.programul as programActivitate, p.nume as nume, p.prenume as prenume, " +
                "pb.detaliiProblema as detaliiProblema, pb.metodaRezolvareProblema as metodaRezolvareProblema " +
                "FROM mentori m, mobilitati mo, activitati a, probleme_semnalate pb, persoane p " +
                "WHERE  m.idMentor = ? and m.idProblema = pb.idProblema and m.codMobilitatea = mo.codMobilitatea and " +
                "mo.idActivitate =  a.idActivitate and m.codPersoana = p.codPersoana";
        for (Integer integer : idMentori){
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{ integer });
            for (Map row : rows) {
                FormProgramTable formProgramTable = new FormProgramTable();
                formProgramTable.setDenumireActivitate((String) row.get("denumireActivitate"));
                formProgramTable.setProgramActivitate((String) row.get("programActivitate"));
                formProgramTable.setNumeMentor(row.get("nume") + " " + row.get("prenume"));
                formProgramTable.setDetaliiProblema((String) row.get("detaliiProblema"));
                formProgramTable.setMetodaRezolvareProblema((String) row.get("metodaRezolvareProblema"));
                formProgramTableList.add(formProgramTable);
            }
        }

        for (FormProgramTable formProgramTable : formProgramTableList){
            System.out.println("Denumire Act " + formProgramTable.getDenumireActivitate());
            System.out.println("Program act " + formProgramTable.getProgramActivitate());
            System.out.println("Nume mentor " + formProgramTable.getNumeMentor());
            System.out.println("Detalii pb " + formProgramTable.getDetaliiProblema());
            System.out.println("Metoda rez" + formProgramTable.getMetodaRezolvareProblema());
        }
        return formProgramTableList;
    }
}
