package com.licenta.model;

public class ProblemeSemnalate {
    private int idProblema;
    private String detaliiProblema;
    private String metodaRezolvareProblema;

    public int getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(int idProblema) {
        this.idProblema = idProblema;
    }

    public String getDetaliiProblema() {
        return detaliiProblema;
    }

    public void setDetaliiProblema(String detaliiProblema) {
        this.detaliiProblema = detaliiProblema;
    }

    public String getMetodaRezolvareProblema() {
        return metodaRezolvareProblema;
    }

    public void setMetodaRezolvareProblema(String metodaRezolvareProblema) {
        this.metodaRezolvareProblema = metodaRezolvareProblema;
    }
}
