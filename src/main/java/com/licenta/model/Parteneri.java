package com.licenta.model;


public class Parteneri {
    private int idPartener;
    private String domeniuActivitate;
    private String denumirePartener;
    private String tara;
    private String oras;

    public int getIdPartener() {
        return idPartener;
    }

    public void setIdPartener(int idPartener) {
        this.idPartener = idPartener;
    }

    public String getDomeniuActivitate() {
        return domeniuActivitate;
    }

    public void setDomeniuActivitate(String domeniuActivitate) {
        this.domeniuActivitate = domeniuActivitate;
    }

    public String getDenumirePartener() {
        return denumirePartener;
    }

    public void setDenumirePartener(String denumirePartener) {
        this.denumirePartener = denumirePartener;
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }
}
