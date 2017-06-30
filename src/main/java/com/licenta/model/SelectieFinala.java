package com.licenta.model;


public class SelectieFinala {
    private int idSelectieFinala;
    private String comentarii;
    private String punctaj;
    private int idOptiuniDosar;
    private int idSesiuni;

    public int getIdSelectieFinala() {
        return idSelectieFinala;
    }

    public void setIdSelectieFinala(int idSelectieFinala) {
        this.idSelectieFinala = idSelectieFinala;
    }

    public String getComentarii() {
        return comentarii;
    }

    public void setComentarii(String comentarii) {
        this.comentarii = comentarii;
    }

    public String getPunctaj() {
        return punctaj;
    }

    public void setPunctaj(String punctaj) {
        this.punctaj = punctaj;
    }

    public int getIdOptiuniDosar() {
        return idOptiuniDosar;
    }

    public void setIdOptiuniDosar(int idOptiuniDosar) {
        this.idOptiuniDosar = idOptiuniDosar;
    }

    public int getIdSesiuni() {
        return idSesiuni;
    }

    public void setIdSesiuni(int idSesiuni) {
        this.idSesiuni = idSesiuni;
    }
}
