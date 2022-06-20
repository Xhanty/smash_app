package com.sssas.smash.Models;

import java.util.Date;

public class Usuario {

    public Integer ID;
    public String USUARIO;
    public String NOMBRE_USUARIO;
    public Integer ID_INSTITUCION;
    public Integer ID_PERFIL;
    public String TELEFONO;
    public String EMAIL;
    public String CLAVE;
    public Integer ID_ESTATUS;
    public Date FECHA_REGISTRO;
    public Date FECHA_UPDATE_CLAVE;
    public Integer ESTADO_CLAVE;
    public Integer PAIS;

    public Usuario(Integer ID, String USUARIO, String NOMBRE_USUARIO, Integer ID_INSTITUCION, Integer ID_PERFIL, String TELEFONO, String EMAIL, String CLAVE, Integer ID_ESTATUS, Date FECHA_REGISTRO, Date FECHA_UPDATE_CLAVE, Integer ESTADO_CLAVE, Integer PAIS) {
        this.ID = ID;
        this.USUARIO = USUARIO;
        this.NOMBRE_USUARIO = NOMBRE_USUARIO;
        this.ID_INSTITUCION = ID_INSTITUCION;
        this.ID_PERFIL = ID_PERFIL;
        this.TELEFONO = TELEFONO;
        this.EMAIL = EMAIL;
        this.CLAVE = CLAVE;
        this.ID_ESTATUS = ID_ESTATUS;
        this.FECHA_REGISTRO = FECHA_REGISTRO;
        this.FECHA_UPDATE_CLAVE = FECHA_UPDATE_CLAVE;
        this.ESTADO_CLAVE = ESTADO_CLAVE;
        this.PAIS = PAIS;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getNOMBRE_USUARIO() {
        return NOMBRE_USUARIO;
    }

    public void setNOMBRE_USUARIO(String NOMBRE_USUARIO) {
        this.NOMBRE_USUARIO = NOMBRE_USUARIO;
    }

    public Integer getID_INSTITUCION() {
        return ID_INSTITUCION;
    }

    public void setID_INSTITUCION(Integer ID_INSTITUCION) {
        this.ID_INSTITUCION = ID_INSTITUCION;
    }

    public Integer getID_PERFIL() {
        return ID_PERFIL;
    }

    public void setID_PERFIL(Integer ID_PERFIL) {
        this.ID_PERFIL = ID_PERFIL;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getCLAVE() {
        return CLAVE;
    }

    public void setCLAVE(String CLAVE) {
        this.CLAVE = CLAVE;
    }

    public Integer getID_ESTATUS() {
        return ID_ESTATUS;
    }

    public void setID_ESTATUS(Integer ID_ESTATUS) {
        this.ID_ESTATUS = ID_ESTATUS;
    }

    public Date getFECHA_REGISTRO() {
        return FECHA_REGISTRO;
    }

    public void setFECHA_REGISTRO(Date FECHA_REGISTRO) {
        this.FECHA_REGISTRO = FECHA_REGISTRO;
    }

    public Date getFECHA_UPDATE_CLAVE() {
        return FECHA_UPDATE_CLAVE;
    }

    public void setFECHA_UPDATE_CLAVE(Date FECHA_UPDATE_CLAVE) {
        this.FECHA_UPDATE_CLAVE = FECHA_UPDATE_CLAVE;
    }

    public Integer getESTADO_CLAVE() {
        return ESTADO_CLAVE;
    }

    public void setESTADO_CLAVE(Integer ESTADO_CLAVE) {
        this.ESTADO_CLAVE = ESTADO_CLAVE;
    }

    public Integer getPAIS() {
        return PAIS;
    }

    public void setPAIS(Integer PAIS) {
        this.PAIS = PAIS;
    }
}
