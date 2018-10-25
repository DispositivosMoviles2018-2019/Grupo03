package com.optativa.pojos;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private String usuario;
    private String clave;
    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private String genero;
    private String fecha;
    private List<String> asignaturas;
    private Boolean becado;
    private byte [] foto;

    public Usuario() {
        asignaturas = new ArrayList();
    }

    public Usuario(String usuario, String clave, String nombre, String apellido, String email, String celular, String genero, String fecha, List<String> asignaturas, Boolean becado, byte []  foto) {
        asignaturas = new ArrayList();
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.celular = celular;
        this.genero = genero;
        this.fecha = fecha;
        this.asignaturas = asignaturas;
        this.becado = becado;
        this.foto = foto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<String> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<String> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Boolean getBecado() {
        return becado;
    }

    public void setBecado(Boolean becado) {
        this.becado = becado;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return usuario +"/t";
    }
}
