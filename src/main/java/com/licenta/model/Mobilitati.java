package com.licenta.model;

import java.util.Date;


public class Mobilitati {
    private String codMobilitatea;
    private Date dataPlecarii;
    private Date dataSosirii;
    private String durataAcordului;
    private int idAcord;
    private int idActivitate;
    private int idSelectieFinala;

    public String getCodMobilitatea() {
        return codMobilitatea;
    }

    public void setCodMobilitatea(String codMobilitatea) {
        this.codMobilitatea = codMobilitatea;
    }

    public Date getDataPlecarii() {
        return dataPlecarii;
    }

    public void setDataPlecarii(Date dataPlecarii) {
        this.dataPlecarii = dataPlecarii;
    }

    public Date getDataSosirii() {
        return dataSosirii;
    }

    public void setDataSosirii(Date dataSosirii) {
        this.dataSosirii = dataSosirii;
    }

    public String getDurataAcordului() {
        return durataAcordului;
    }

    public void setDurataAcordului(String durataAcordului) {
        this.durataAcordului = durataAcordului;
    }

    public int getIdAcord() {
        return idAcord;
    }

    public void setIdAcord(int idAcord) {
        this.idAcord = idAcord;
    }

    public int getIdActivitate() {
        return idActivitate;
    }

    public void setIdActivitate(int idActivitate) {
        this.idActivitate = idActivitate;
    }

    public int getIdSelectieFinala() {
        return idSelectieFinala;
    }

    public void setIdSelectieFinala(int idSelectieFinala) {
        this.idSelectieFinala = idSelectieFinala;
    }
}
