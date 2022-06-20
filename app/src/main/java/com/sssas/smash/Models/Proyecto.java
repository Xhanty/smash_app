package com.sssas.smash.Models;

import java.util.Date;

public class Proyecto {

    public Integer ID;
    public String NOMBRE_PROYECTO;
    public Date FECHA_INICIAL;
    public Date FECHA_FINAL;
    public String VALOR;
    public Integer ESTADO;
    public String NOMBRE_LARGO;
    public String CODIGO;
    public String CODIGO_CARITAS;
    public String CODIGO_DONANTE;
    public String FOTO;
    public String DESCRIPCION;
    public Integer DASHBOARD;
    public Date FECHA_INFORME_FINAL;
    public String URL;
    public String NOMBRE_LOGO;
    public String PROYECTO_CUSTOM_LOGIN;

    public Proyecto() {
    }

    public Proyecto(Integer ID, String NOMBRE_PROYECTO) {
        this.ID = ID;
        this.NOMBRE_PROYECTO = NOMBRE_PROYECTO;
    }

    public Proyecto(Integer ID, String NOMBRE_PROYECTO, Date FECHA_INICIAL, Date FECHA_FINAL, String VALOR, Integer ESTADO, String NOMBRE_LARGO, String CODIGO, String CODIGO_CARITAS, String CODIGO_DONANTE, String FOTO, String DESCRIPCION, Integer DASHBOARD, Date FECHA_INFORME_FINAL, String URL, String NOMBRE_LOGO) {
        this.ID = ID;
        this.NOMBRE_PROYECTO = NOMBRE_PROYECTO;
        this.FECHA_INICIAL = FECHA_INICIAL;
        this.FECHA_FINAL = FECHA_FINAL;
        this.VALOR = VALOR;
        this.ESTADO = ESTADO;
        this.NOMBRE_LARGO = NOMBRE_LARGO;
        this.CODIGO = CODIGO;
        this.CODIGO_CARITAS = CODIGO_CARITAS;
        this.CODIGO_DONANTE = CODIGO_DONANTE;
        this.FOTO = FOTO;
        this.DESCRIPCION = DESCRIPCION;
        this.DASHBOARD = DASHBOARD;
        this.FECHA_INFORME_FINAL = FECHA_INFORME_FINAL;
        this.URL = URL;
        this.NOMBRE_LOGO = NOMBRE_LOGO;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNOMBRE_PROYECTO() {
        return NOMBRE_PROYECTO;
    }

    public void setNOMBRE_PROYECTO(String NOMBRE_PROYECTO) {
        this.NOMBRE_PROYECTO = NOMBRE_PROYECTO;
    }

    public Date getFECHA_INICIAL() {
        return FECHA_INICIAL;
    }

    public void setFECHA_INICIAL(Date FECHA_INICIAL) {
        this.FECHA_INICIAL = FECHA_INICIAL;
    }

    public Date getFECHA_FINAL() {
        return FECHA_FINAL;
    }

    public void setFECHA_FINAL(Date FECHA_FINAL) {
        this.FECHA_FINAL = FECHA_FINAL;
    }

    public String getVALOR() {
        return VALOR;
    }

    public void setVALOR(String VALOR) {
        this.VALOR = VALOR;
    }

    public Integer getESTADO() {
        return ESTADO;
    }

    public void setESTADO(Integer ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getNOMBRE_LARGO() {
        return NOMBRE_LARGO;
    }

    public void setNOMBRE_LARGO(String NOMBRE_LARGO) {
        this.NOMBRE_LARGO = NOMBRE_LARGO;
    }

    public String getCODIGO() {
        return CODIGO;
    }

    public void setCODIGO(String CODIGO) {
        this.CODIGO = CODIGO;
    }

    public String getCODIGO_CARITAS() {
        return CODIGO_CARITAS;
    }

    public void setCODIGO_CARITAS(String CODIGO_CARITAS) {
        this.CODIGO_CARITAS = CODIGO_CARITAS;
    }

    public String getCODIGO_DONANTE() {
        return CODIGO_DONANTE;
    }

    public void setCODIGO_DONANTE(String CODIGO_DONANTE) {
        this.CODIGO_DONANTE = CODIGO_DONANTE;
    }

    public String getFOTO() {
        return FOTO;
    }

    public void setFOTO(String FOTO) {
        this.FOTO = FOTO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public Integer getDASHBOARD() {
        return DASHBOARD;
    }

    public void setDASHBOARD(Integer DASHBOARD) {
        this.DASHBOARD = DASHBOARD;
    }

    public Date getFECHA_INFORME_FINAL() {
        return FECHA_INFORME_FINAL;
    }

    public void setFECHA_INFORME_FINAL(Date FECHA_INFORME_FINAL) {
        this.FECHA_INFORME_FINAL = FECHA_INFORME_FINAL;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getNOMBRE_LOGO() {
        return NOMBRE_LOGO;
    }

    public void setNOMBRE_LOGO(String NOMBRE_LOGO) {
        this.NOMBRE_LOGO = NOMBRE_LOGO;
    }

    @Override
    public String toString() {
        this.PROYECTO_CUSTOM_LOGIN = this.PROYECTO_CUSTOM_LOGIN = NOMBRE_PROYECTO;
        return PROYECTO_CUSTOM_LOGIN;
    }
}
