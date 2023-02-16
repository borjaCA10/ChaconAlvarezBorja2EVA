package com.example.chaconalvarezborja2eca;

import java.io.Serializable;

public class Contacto implements Serializable {
    public String nombre;
    public String direccion;
    public String email;
    public String movil;


    Contacto(String nombre, String direccion, String email, String movil) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.movil = movil;
    }

    public Contacto() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", movil=" + movil +
                '}';
    }
}
