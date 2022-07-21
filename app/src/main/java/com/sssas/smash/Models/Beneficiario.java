package com.sssas.smash.Models;

import java.util.Date;

public class Beneficiario {

    public Integer ID; //NOT NULL
    public Integer ID_MUNICIPIO_REGISTRO;

    public String IMAGEN;
    public String NOMBRES; //NOT NULL
    public String APELLIDOS; //NOT NULL

    public Date FECHA_NACIMIENTO;
    public String TELEFONO_REDES_SOCIALES;
    public Integer ID_BARRIO;
    public String DIRECCION;
    public Integer ID_SITUACION_MIGRATORIA;
    public Integer ID_TIPO_BENEFICIARIO;
    public Integer ID_SEXO;
    public Integer ID_GENERO;
    public Integer ID_ESTADO_CIVIL;
    public Integer ID_ETNIA;
    public Integer ID_DISCAPACIDAD;
    public Integer ID_ENFERMEDAD;
    public Integer ID_ESCOLARIDAD;
    public Integer RETORNO_PAIS_ORIGEN;
    public Integer MEJORA_RETORNO_PAIS_ORIGEN;
    public Integer ID_VIVIENDA;
    public Integer AYUDA_SOSTENIMIENTO;
    public Integer ID_OCUPACION;
    public Integer ESTUDIA_ACTUALMENTE;
    public Integer ESTA_EMBARAZADA;
    public Integer ESTADO_PROCEDENCIA;
    public Integer ID_ADMINISTRADOR; //NOT NULL
    public Date FECHA_REGISTRO;
    public String OBSERVACIONES;
    public Integer ID_ORGANIZACION;
    public Integer ESTADO;
    public Integer TIPO_FORMULARIO;
    //NEW
    public Integer ID_PROYECTO;
    public Integer ID_TIPO_DOCUMENTO;
    public String DOCUMENTO;
    public Integer CABEZA_HOGAR; //POR DEFECTO ES 0 QUE ES NO
    public String COORDENADA;

    public Beneficiario() {
    }

    public Beneficiario(Integer ID, Integer ID_MUNICIPIO_REGISTRO, String IMAGEN, String NOMBRES, String APELLIDOS, Date FECHA_NACIMIENTO, String TELEFONO_REDES_SOCIALES, Integer ID_BARRIO, String DIRECCION, Integer ID_SITUACION_MIGRATORIA, Integer ID_TIPO_BENEFICIARIO, Integer ID_SEXO, Integer ID_GENERO, Integer ID_ESTADO_CIVIL, Integer ID_ETNIA, Integer ID_DISCAPACIDAD, Integer ID_ENFERMEDAD, Integer ID_ESCOLARIDAD, Integer RETORNO_PAIS_ORIGEN, Integer MEJORA_RETORNO_PAIS_ORIGEN, Integer ID_VIVIENDA, Integer AYUDA_SOSTENIMIENTO, Integer ID_OCUPACION, Integer ESTUDIA_ACTUALMENTE, Integer ESTA_EMBARAZADA, Integer ESTADO_PROCEDENCIA, Integer ID_ADMINISTRADOR, Date FECHA_REGISTRO, String OBSERVACIONES, Integer ID_ORGANIZACION, Integer ESTADO, Integer TIPO_FORMULARIO, Integer ID_PROYECTO, Integer ID_TIPO_DOCUMENTO, String DOCUMENTO, Integer CABEZA_HOGAR, String COORDENADA) {
        this.ID = ID;
        this.ID_MUNICIPIO_REGISTRO = ID_MUNICIPIO_REGISTRO;
        this.IMAGEN = IMAGEN;
        this.NOMBRES = NOMBRES;
        this.APELLIDOS = APELLIDOS;
        this.FECHA_NACIMIENTO = FECHA_NACIMIENTO;
        this.TELEFONO_REDES_SOCIALES = TELEFONO_REDES_SOCIALES;
        this.ID_BARRIO = ID_BARRIO;
        this.DIRECCION = DIRECCION;
        this.ID_SITUACION_MIGRATORIA = ID_SITUACION_MIGRATORIA;
        this.ID_TIPO_BENEFICIARIO = ID_TIPO_BENEFICIARIO;
        this.ID_SEXO = ID_SEXO;
        this.ID_GENERO = ID_GENERO;
        this.ID_ESTADO_CIVIL = ID_ESTADO_CIVIL;
        this.ID_ETNIA = ID_ETNIA;
        this.ID_DISCAPACIDAD = ID_DISCAPACIDAD;
        this.ID_ENFERMEDAD = ID_ENFERMEDAD;
        this.ID_ESCOLARIDAD = ID_ESCOLARIDAD;
        this.RETORNO_PAIS_ORIGEN = RETORNO_PAIS_ORIGEN;
        this.MEJORA_RETORNO_PAIS_ORIGEN = MEJORA_RETORNO_PAIS_ORIGEN;
        this.ID_VIVIENDA = ID_VIVIENDA;
        this.AYUDA_SOSTENIMIENTO = AYUDA_SOSTENIMIENTO;
        this.ID_OCUPACION = ID_OCUPACION;
        this.ESTUDIA_ACTUALMENTE = ESTUDIA_ACTUALMENTE;
        this.ESTA_EMBARAZADA = ESTA_EMBARAZADA;
        this.ESTADO_PROCEDENCIA = ESTADO_PROCEDENCIA;
        this.ID_ADMINISTRADOR = ID_ADMINISTRADOR;
        this.FECHA_REGISTRO = FECHA_REGISTRO;
        this.OBSERVACIONES = OBSERVACIONES;
        this.ID_ORGANIZACION = ID_ORGANIZACION;
        this.ESTADO = ESTADO;
        this.TIPO_FORMULARIO = TIPO_FORMULARIO;
        this.ID_PROYECTO = ID_PROYECTO;
        this.ID_TIPO_DOCUMENTO = ID_TIPO_DOCUMENTO;
        this.DOCUMENTO = DOCUMENTO;
        this.CABEZA_HOGAR = CABEZA_HOGAR;
        this.COORDENADA = COORDENADA;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID_MUNICIPIO_REGISTRO() {
        return ID_MUNICIPIO_REGISTRO;
    }

    public void setID_MUNICIPIO_REGISTRO(Integer ID_MUNICIPIO_REGISTRO) {
        this.ID_MUNICIPIO_REGISTRO = ID_MUNICIPIO_REGISTRO;
    }

    public String getIMAGEN() {
        return IMAGEN;
    }

    public void setIMAGEN(String IMAGEN) {
        this.IMAGEN = IMAGEN;
    }

    public String getNOMBRES() {
        return NOMBRES;
    }

    public void setNOMBRES(String NOMBRES) {
        this.NOMBRES = NOMBRES;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }

    public Date getFECHA_NACIMIENTO() {
        return FECHA_NACIMIENTO;
    }

    public void setFECHA_NACIMIENTO(Date FECHA_NACIMIENTO) {
        this.FECHA_NACIMIENTO = FECHA_NACIMIENTO;
    }

    public String getTELEFONO_REDES_SOCIALES() {
        return TELEFONO_REDES_SOCIALES;
    }

    public void setTELEFONO_REDES_SOCIALES(String TELEFONO_REDES_SOCIALES) {
        this.TELEFONO_REDES_SOCIALES = TELEFONO_REDES_SOCIALES;
    }

    public Integer getID_BARRIO() {
        return ID_BARRIO;
    }

    public void setID_BARRIO(Integer ID_BARRIO) {
        this.ID_BARRIO = ID_BARRIO;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public Integer getID_SITUACION_MIGRATORIA() {
        return ID_SITUACION_MIGRATORIA;
    }

    public void setID_SITUACION_MIGRATORIA(Integer ID_SITUACION_MIGRATORIA) {
        this.ID_SITUACION_MIGRATORIA = ID_SITUACION_MIGRATORIA;
    }

    public Integer getID_TIPO_BENEFICIARIO() {
        return ID_TIPO_BENEFICIARIO;
    }

    public void setID_TIPO_BENEFICIARIO(Integer ID_TIPO_BENEFICIARIO) {
        this.ID_TIPO_BENEFICIARIO = ID_TIPO_BENEFICIARIO;
    }

    public Integer getID_SEXO() {
        return ID_SEXO;
    }

    public void setID_SEXO(Integer ID_SEXO) {
        this.ID_SEXO = ID_SEXO;
    }

    public Integer getID_GENERO() {
        return ID_GENERO;
    }

    public void setID_GENERO(Integer ID_GENERO) {
        this.ID_GENERO = ID_GENERO;
    }

    public Integer getID_ESTADO_CIVIL() {
        return ID_ESTADO_CIVIL;
    }

    public void setID_ESTADO_CIVIL(Integer ID_ESTADO_CIVIL) {
        this.ID_ESTADO_CIVIL = ID_ESTADO_CIVIL;
    }

    public Integer getID_ETNIA() {
        return ID_ETNIA;
    }

    public void setID_ETNIA(Integer ID_ETNIA) {
        this.ID_ETNIA = ID_ETNIA;
    }

    public Integer getID_DISCAPACIDAD() {
        return ID_DISCAPACIDAD;
    }

    public void setID_DISCAPACIDAD(Integer ID_DISCAPACIDAD) {
        this.ID_DISCAPACIDAD = ID_DISCAPACIDAD;
    }

    public Integer getID_ENFERMEDAD() {
        return ID_ENFERMEDAD;
    }

    public void setID_ENFERMEDAD(Integer ID_ENFERMEDAD) {
        this.ID_ENFERMEDAD = ID_ENFERMEDAD;
    }

    public Integer getID_ESCOLARIDAD() {
        return ID_ESCOLARIDAD;
    }

    public void setID_ESCOLARIDAD(Integer ID_ESCOLARIDAD) {
        this.ID_ESCOLARIDAD = ID_ESCOLARIDAD;
    }

    public Integer getRETORNO_PAIS_ORIGEN() {
        return RETORNO_PAIS_ORIGEN;
    }

    public void setRETORNO_PAIS_ORIGEN(Integer RETORNO_PAIS_ORIGEN) {
        this.RETORNO_PAIS_ORIGEN = RETORNO_PAIS_ORIGEN;
    }

    public Integer getMEJORA_RETORNO_PAIS_ORIGEN() {
        return MEJORA_RETORNO_PAIS_ORIGEN;
    }

    public void setMEJORA_RETORNO_PAIS_ORIGEN(Integer MEJORA_RETORNO_PAIS_ORIGEN) {
        this.MEJORA_RETORNO_PAIS_ORIGEN = MEJORA_RETORNO_PAIS_ORIGEN;
    }

    public Integer getID_VIVIENDA() {
        return ID_VIVIENDA;
    }

    public void setID_VIVIENDA(Integer ID_VIVIENDA) {
        this.ID_VIVIENDA = ID_VIVIENDA;
    }

    public Integer getAYUDA_SOSTENIMIENTO() {
        return AYUDA_SOSTENIMIENTO;
    }

    public void setAYUDA_SOSTENIMIENTO(Integer AYUDA_SOSTENIMIENTO) {
        this.AYUDA_SOSTENIMIENTO = AYUDA_SOSTENIMIENTO;
    }

    public Integer getID_OCUPACION() {
        return ID_OCUPACION;
    }

    public void setID_OCUPACION(Integer ID_OCUPACION) {
        this.ID_OCUPACION = ID_OCUPACION;
    }

    public Integer getESTUDIA_ACTUALMENTE() {
        return ESTUDIA_ACTUALMENTE;
    }

    public void setESTUDIA_ACTUALMENTE(Integer ESTUDIA_ACTUALMENTE) {
        this.ESTUDIA_ACTUALMENTE = ESTUDIA_ACTUALMENTE;
    }

    public Integer getESTA_EMBARAZADA() {
        return ESTA_EMBARAZADA;
    }

    public void setESTA_EMBARAZADA(Integer ESTA_EMBARAZADA) {
        this.ESTA_EMBARAZADA = ESTA_EMBARAZADA;
    }

    public Integer getESTADO_PROCEDENCIA() {
        return ESTADO_PROCEDENCIA;
    }

    public void setESTADO_PROCEDENCIA(Integer ESTADO_PROCEDENCIA) {
        this.ESTADO_PROCEDENCIA = ESTADO_PROCEDENCIA;
    }

    public Integer getID_ADMINISTRADOR() {
        return ID_ADMINISTRADOR;
    }

    public void setID_ADMINISTRADOR(Integer ID_ADMINISTRADOR) {
        this.ID_ADMINISTRADOR = ID_ADMINISTRADOR;
    }

    public Date getFECHA_REGISTRO() {
        return FECHA_REGISTRO;
    }

    public void setFECHA_REGISTRO(Date FECHA_REGISTRO) {
        this.FECHA_REGISTRO = FECHA_REGISTRO;
    }

    public String getOBSERVACIONES() {
        return OBSERVACIONES;
    }

    public void setOBSERVACIONES(String OBSERVACIONES) {
        this.OBSERVACIONES = OBSERVACIONES;
    }

    public Integer getID_ORGANIZACION() {
        return ID_ORGANIZACION;
    }

    public void setID_ORGANIZACION(Integer ID_ORGANIZACION) {
        this.ID_ORGANIZACION = ID_ORGANIZACION;
    }

    public Integer getESTADO() {
        return ESTADO;
    }

    public void setESTADO(Integer ESTADO) {
        this.ESTADO = ESTADO;
    }

    public Integer getTIPO_FORMULARIO() {
        return TIPO_FORMULARIO;
    }

    public void setTIPO_FORMULARIO(Integer TIPO_FORMULARIO) {
        this.TIPO_FORMULARIO = TIPO_FORMULARIO;
    }

    public Integer getID_PROYECTO() {
        return ID_PROYECTO;
    }

    public void setID_PROYECTO(Integer ID_PROYECTO) {
        this.ID_PROYECTO = ID_PROYECTO;
    }

    public Integer getID_TIPO_DOCUMENTO() {
        return ID_TIPO_DOCUMENTO;
    }

    public void setID_TIPO_DOCUMENTO(Integer ID_TIPO_DOCUMENTO) {
        this.ID_TIPO_DOCUMENTO = ID_TIPO_DOCUMENTO;
    }

    public String getDOCUMENTO() {
        return DOCUMENTO;
    }

    public void setDOCUMENTO(String DOCUMENTO) {
        this.DOCUMENTO = DOCUMENTO;
    }

    public Integer getCABEZA_HOGAR() {
        return CABEZA_HOGAR;
    }

    public void setCABEZA_HOGAR(Integer CABEZA_HOGAR) {
        this.CABEZA_HOGAR = CABEZA_HOGAR;
    }

    public String getCOORDENADA() {
        return COORDENADA;
    }

    public void setCOORDENADA(String COORDENADA) {
        this.COORDENADA = COORDENADA;
    }
}
