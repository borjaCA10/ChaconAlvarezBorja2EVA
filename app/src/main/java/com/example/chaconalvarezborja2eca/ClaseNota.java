package com.example.chaconalvarezborja2eca;

import java.io.Serializable;

public class ClaseNota implements Serializable {

    public String titulo;
    public String nota;

    ClaseNota(String titulo, String Nota) {
        this.titulo = titulo;
        this.nota = nota;

    }

    public ClaseNota() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
