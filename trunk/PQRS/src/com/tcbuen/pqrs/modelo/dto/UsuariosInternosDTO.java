package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.UsuariosInternos;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class UsuariosInternosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String apellidos;
    private String contrasena;
    private String correoElectronico;
    private String estadoRegistro;
    private Date fechaCreacion;
    private Long idUsuInterno;
    private String login;
    private String nombres;
    private String numeroIdentificacion;
    private Long idAreaInvolucrada_AreasInvolucradas;
    private Long idRol_Roles;

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getEstadoRegistro() {
        if(estadoRegistro.equals("A")){
        	estadoRegistro = "Activo";
        }else{
        	estadoRegistro ="Inactivo";
        }
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdUsuInterno() {
        return idUsuInterno;
    }

    public void setIdUsuInterno(Long idUsuInterno) {
        this.idUsuInterno = idUsuInterno;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Long getIdAreaInvolucrada_AreasInvolucradas() {
        return idAreaInvolucrada_AreasInvolucradas;
    }

    public void setIdAreaInvolucrada_AreasInvolucradas(
        Long idAreaInvolucrada_AreasInvolucradas) {
        this.idAreaInvolucrada_AreasInvolucradas = idAreaInvolucrada_AreasInvolucradas;
    }

    public Long getIdRol_Roles() {
        return idRol_Roles;
    }

    public void setIdRol_Roles(Long idRol_Roles) {
        this.idRol_Roles = idRol_Roles;
    }
}
