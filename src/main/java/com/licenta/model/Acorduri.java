package com.licenta.model;

import com.sun.javafx.beans.IDProperty;

import javax.annotation.Generated;
import java.util.Date;


public class Acorduri {

    private int idAcord;
    private Date dataAcordului;
    private int valoareaAcordului;
    private Date dataInitiala;
    private Date dataFinala;
    private int nrLocuriLicenta;
    private int nrLocuriDoctorat;
    private int nrLocuriMaster;
    private int nrLocuriOcupate;
    private int nrLocuriDisponibile;

    public int getNrLocuriMaster() {
        return nrLocuriMaster;
    }

    public void setNrLocuriMaster(int nrLocuriMaster) {
        this.nrLocuriMaster = nrLocuriMaster;
    }

    public int getNrLocuriOcupate() {
        return nrLocuriOcupate;
    }

    public void setNrLocuriOcupate(int nrLocuriOcupate) {
        this.nrLocuriOcupate = nrLocuriOcupate;
    }

    public int getNrLocuriDisponibile() {
        return nrLocuriDisponibile;
    }

    public void setNrLocuriDisponibile(int nrLocuriDisponibile) {
        this.nrLocuriDisponibile = nrLocuriDisponibile;
    }

    private int idPartener;

    public int getIdAcord() {
        return idAcord;
    }

    public void setIdAcord(int idAcord) {
        this.idAcord = idAcord;
    }

    public Date getDataAcordului() {
        return dataAcordului;
    }

    public void setDataAcordului(Date dataAcordului) {
        this.dataAcordului = dataAcordului;
    }

    public int getValoareaAcordului() {
        return valoareaAcordului;
    }

    public void setValoareaAcordului(int valoareaAcordului) {
        this.valoareaAcordului = valoareaAcordului;
    }

    public Date getDataInitiala() {
        return dataInitiala;
    }

    public void setDataInitiala(Date dataInitiala) {
        this.dataInitiala = dataInitiala;
    }

    public Date getDataFinala() {
        return dataFinala;
    }

    public void setDataFinala(Date dataFinala) {
        this.dataFinala = dataFinala;
    }

    public int getNrLocuriLicenta() {
        return nrLocuriLicenta;
    }

    public void setNrLocuriLicenta(int nrLocuriLicenta) {
        this.nrLocuriLicenta = nrLocuriLicenta;
    }

    public int getNrLocuriDoctorat() {
        return nrLocuriDoctorat;
    }

    public void setNrLocuriDoctorat(int nrLocuriDoctorat) {
        this.nrLocuriDoctorat = nrLocuriDoctorat;
    }

    public int getIdPartener() {
        return idPartener;
    }

    public void setIdPartener(int idPartener) {
        this.idPartener = idPartener;
    }
}
