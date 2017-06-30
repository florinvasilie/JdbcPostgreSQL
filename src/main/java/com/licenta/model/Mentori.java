package com.licenta.model;


public class Mentori {
    private int idMentor;
    private String codPersoana;
    private String codMobilitatea;
    private  int idProblema;
    private  String idStudent;

    public int getIdMentor() {
        return idMentor;
    }

    public void setIdMentor(int idMentor) {
        this.idMentor = idMentor;
    }

    public String getCodPersoana() {
        return codPersoana;
    }

    public void setCodPersoana(String codPersoana) {
        this.codPersoana = codPersoana;
    }

    public String getCodMobilitatea() {
        return codMobilitatea;
    }

    public void setCodMobilitatea(String codMobilitatea) {
        this.codMobilitatea = codMobilitatea;
    }

    public int getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(int idProblema) {
        this.idProblema = idProblema;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }
}
