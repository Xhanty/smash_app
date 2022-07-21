package com.sssas.smash.Models;

public class Ayuda {

    public Integer ID;
    public String NOMBRE_AYUDA;
    public Integer ID_SECTOR;
    public Integer ID_CLASIFICACION_ACTIVIDAD;
    public Integer ID_IMPACTO;
    public Integer ESTADO;
    public String SEXO;
    public String RANGO_EDAD;
    public String DESCRIPCION;
    public String AYUDA_CUSTOM_ADD;

    public Ayuda() {
    }

    public Ayuda(Integer ID, String NOMBRE_AYUDA, Integer ID_SECTOR, Integer ID_CLASIFICACION_ACTIVIDAD, Integer ID_IMPACTO, Integer ESTADO, String SEXO, String RANGO_EDAD, String DESCRIPCION) {
        this.ID = ID;
        this.NOMBRE_AYUDA = NOMBRE_AYUDA;
        this.ID_SECTOR = ID_SECTOR;
        this.ID_CLASIFICACION_ACTIVIDAD = ID_CLASIFICACION_ACTIVIDAD;
        this.ID_IMPACTO = ID_IMPACTO;
        this.ESTADO = ESTADO;
        this.SEXO = SEXO;
        this.RANGO_EDAD = RANGO_EDAD;
        this.DESCRIPCION = DESCRIPCION;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNOMBRE_AYUDA() {
        return NOMBRE_AYUDA;
    }

    public void setNOMBRE_AYUDA(String NOMBRE_AYUDA) {
        this.NOMBRE_AYUDA = NOMBRE_AYUDA;
    }

    public Integer getID_SECTOR() {
        return ID_SECTOR;
    }

    public void setID_SECTOR(Integer ID_SECTOR) {
        this.ID_SECTOR = ID_SECTOR;
    }

    public Integer getID_CLASIFICACION_ACTIVIDAD() {
        return ID_CLASIFICACION_ACTIVIDAD;
    }

    public void setID_CLASIFICACION_ACTIVIDAD(Integer ID_CLASIFICACION_ACTIVIDAD) {
        this.ID_CLASIFICACION_ACTIVIDAD = ID_CLASIFICACION_ACTIVIDAD;
    }

    public Integer getID_IMPACTO() {
        return ID_IMPACTO;
    }

    public void setID_IMPACTO(Integer ID_IMPACTO) {
        this.ID_IMPACTO = ID_IMPACTO;
    }

    public Integer getESTADO() {
        return ESTADO;
    }

    public void setESTADO(Integer ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getSEXO() {
        return SEXO;
    }

    public void setSEXO(String SEXO) {
        this.SEXO = SEXO;
    }

    public String getRANGO_EDAD() {
        return RANGO_EDAD;
    }

    public void setRANGO_EDAD(String RANGO_EDAD) {
        this.RANGO_EDAD = RANGO_EDAD;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    @Override
    public String toString() {
        this.AYUDA_CUSTOM_ADD = this.AYUDA_CUSTOM_ADD = NOMBRE_AYUDA;
        return AYUDA_CUSTOM_ADD;
    }
}
