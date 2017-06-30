package com.licenta.dao;

import com.licenta.form.FormSelectie;
import com.licenta.form.FormSelectieTable;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


public class FormSelectieDAO {
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
    public  Integer findidStudentBy(String codPersoana, String nivelStudii, java.sql.Date dataSesiune){
        String sql = "SELECT st.idStudent FROM studenti st, specializari s, dosare d, sesiuni se " +
                "WHERE st.codPersoana = ? and st.idSpecializare = s.idSpecializare and s.nivelStudii = ? " +
                "and d.idStudent = st.idStudent and d.codDosar = se.codDosar and se.dataSesiune = ?";
        List<Integer> student = jdbcTemplate.queryForList(sql, Integer.class, codPersoana, nivelStudii, dataSesiune);
        System.out.println("studentsize " + student.size());
        Integer idStudent = null;
        if (!student.isEmpty())
            idStudent = student.get(0);
        System.out.println("idStunde = " + idStudent);
        return idStudent;
    }

    public List<Integer> findidOptiuniDosarBy(Integer idStudent){
        String sql = "SELECT o.idOptiuniDosar FROM optiuni_dosare o, dosare d, studenti s " +
                "WHERE s.idStudent = ? and s.idStudent = d.idStudent and d.codDosar = o.codDosar";
        List<Integer> idOptiuniDosar = jdbcTemplate.queryForList(sql, Integer.class, idStudent);
        for (Integer string : idOptiuniDosar)
            System.out.println("optiuni dosar " + string);
        return idOptiuniDosar;
    }

    public Set<Integer> findidAcordBy(List<Integer> idOptiuniDosar){
        String sql = "SELECT a.idAcord FROM acorduri a, optiuni_dosare o " +
                "WHERE a.idAcord = o.idAcord and o.idOptiuniDosar = ?";
        Set<Integer> idAcordAll = new HashSet<>();
        for (Integer integer : idOptiuniDosar){
            List<Integer> idAcord = jdbcTemplate.queryForList(sql, Integer.class, integer);
            idAcordAll.addAll(idAcord);
        }
        for (Integer integer : idAcordAll)
            System.out.println("id acord = " + integer);
        return idAcordAll;
    }
    public List<FormSelectieTable> showSelectieFinala(Set<Integer> idAcord){
        List<FormSelectieTable> formSelectieTableList = new ArrayList<>();
        String sql = "SELECT p.denumirePartener as denumirePartener, p.domeniuActivitate as domeniuActivitate, m.dataPlecarii as dataPlecarii, m.dataSosirii as dataSosirii, " +
                    "m.durataAcordului as durataAcordului, cs.denumireComisie as denumireComisie, a.nrLocuriOcupate as nrLocuriOcupate, a.nrLocuriDisponibile as nrLocuriDisponibile, sf.punctaj as punctaj " +
                    "FROM acorduri a, parteneri p, mobilitati m, selectie_finala sf, sesiuni se, comisie cs " +
                    "WHERE a.idAcord = ? and a.idPartener = p.idPartener and a.idAcord = m.idAcord and m.idSelectieFinala = sf.idSelectieFinala " +
                    "and sf.idSesiuni = se.idSesiuni and se.idComisie = cs.idComisie";
        for (Integer integer : idAcord){
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{ integer });
            for (Map row : rows) {
                FormSelectieTable formSelectieTable = new FormSelectieTable();
                formSelectieTable.setDenumirePartener((String) row.get("denumirePartener"));
                formSelectieTable.setDomeniuActivitate((String) row.get("domeniuActivitate"));

                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                java.sql.Date dataPlecariiSQL = (java.sql.Date) row.get("dataPlecarii");
                java.sql.Date dataSosiriiSQL = (java.sql.Date) row.get("dataSosirii");
                String dataPlecarii = format.format(dataPlecariiSQL);
                String dataSosirii = format.format(dataSosiriiSQL);
                formSelectieTable.setDataPlecare(dataPlecarii);
                formSelectieTable.setDataSosire(dataSosirii);

                formSelectieTable.setDurataAcordului((String) row.get("durataAcordului"));
                formSelectieTable.setDenumireComisie((String) row.get("denumireComisie"));

                Integer nrLocuriOcupate = (Integer) row.get("nrLocuriOcupate");
                Integer nrLocuriDisponibile = (Integer) row.get("nrLocuriDisponibile");
                Integer punctaj = (Integer) row.get("punctaj");
                System.out.println(punctaj);
                if (nrLocuriOcupate >= nrLocuriDisponibile || punctaj <= 7)
                    formSelectieTable.setStatut("Respins");
                if (nrLocuriOcupate < nrLocuriDisponibile && punctaj > 7)
                     formSelectieTable.setStatut("Admis");
                if (nrLocuriDisponibile == nrLocuriOcupate && punctaj >= 7)
                    formSelectieTable.setStatut("Rezerva");
                formSelectieTableList.add(formSelectieTable);
            }
        }
        for (FormSelectieTable formSelectieTable : formSelectieTableList){
            System.out.println(formSelectieTable.getDenumirePartener());
            System.out.println(formSelectieTable.getDomeniuActivitate());
            System.out.println(formSelectieTable.getDataPlecare());
            System.out.println(formSelectieTable.getDataSosire());
            System.out.println(formSelectieTable.getDurataAcordului());
            System.out.println(formSelectieTable.getDenumireComisie());
            System.out.println(formSelectieTable.getStatut());
        }
        return formSelectieTableList;
    }


}
