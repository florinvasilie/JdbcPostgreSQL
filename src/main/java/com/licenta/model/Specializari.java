package com.licenta.model;


public class Specializari {
    private int idSpecializare;
    private String denumireSpecializare;
    private String nivelStudii;
    private int anulDeStudii;

    public int getIdSpecializare() {
        return idSpecializare;
    }

    public void setIdSpecializare(int idSpecializare) {
        this.idSpecializare = idSpecializare;
    }

    public String getDenumireSpecializare() {
        return denumireSpecializare;
    }

    public void setDenumireSpecializare(String denumireSpecializare) {
        this.denumireSpecializare = denumireSpecializare;
    }

    public String getNivelStudii() {
        return nivelStudii;
    }

    public void setNivelStudii(String nivelStudii) {
        this.nivelStudii = nivelStudii;
    }

    public int getAnulDeStudii() {
        return anulDeStudii;
    }

    public void setAnulDeStudii(int anulDeStudii) {
        this.anulDeStudii = anulDeStudii;
    }
}
