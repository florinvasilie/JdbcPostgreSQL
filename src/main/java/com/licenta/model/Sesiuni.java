package com.licenta.model;

import java.util.Date;


public class Sesiuni {
    private int idSesiune;
    private Date dataSesiune;
    private String comentarii;
    private String codDosar;
    private int idComisie;

    public int getIdSesiune() {
        return idSesiune;
    }

    public void setIdSesiune(int idSesiune) {
        this.idSesiune = idSesiune;
    }

    public Date getDataSesiune() {
        return dataSesiune;
    }

    public void setDataSesiune(Date dataSesiune) {
        this.dataSesiune = dataSesiune;
    }

    public String getComentarii() {
        return comentarii;
    }

    public void setComentarii(String comentarii) {
        this.comentarii = comentarii;
    }

    public String getCodDosar() {
        return codDosar;
    }

    public void setCodDosar(String codDosar) {
        this.codDosar = codDosar;
    }

    public int getIdComisie() {
        return idComisie;
    }

    public void setIdComisie(int idComisie) {
        this.idComisie = idComisie;
    }
}
