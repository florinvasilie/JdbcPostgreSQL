package com.licenta.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class FormAcorduri {
    private String anUniversitar;
    private String dataSesiune;
    private String nivelStudii;

    public String getAnUniversitar() {
        return anUniversitar;
    }

    public void setAnUniversitar(String anUniversitar) {
        this.anUniversitar = anUniversitar;
    }

    public String getDataSesiune() {
        return dataSesiune;
    }

    public void setDataSesiune(String dataSesiune) {
        this.dataSesiune = dataSesiune;
    }

    public String getNivelStudii() {
        return nivelStudii;
    }

    public void setNivelStudii(String nivelStudii) {
        this.nivelStudii = nivelStudii;
    }
}
