package com.example.chaconalvarezborja2eca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuPrincipal extends AppCompatActivity {
Button guardarContacto;
Button eliminarContacto;
Button mostrarContacto;
Button modificarContacto;
ImageButton cerrarsesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        guardarContacto = findViewById(R.id.buttonAgregar);

        guardarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenGuardar = new Intent(MenuPrincipal.this, AgendaGuardar.class);
                startActivity(intenGuardar);
            }
        });

        eliminarContacto = findViewById(R.id.buttonBorrar);
        eliminarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEliminar = new Intent(MenuPrincipal.this, AgendaBorrar.class);
                startActivity(intentEliminar);
            }
        });

        mostrarContacto = findViewById(R.id.buttonMostrar);
        mostrarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenMostrar = new Intent(MenuPrincipal.this, AgendaListado.class);
                startActivity(intenMostrar);
            }
        });
        modificarContacto = findViewById(R.id.buttonModificar);
        modificarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten1 = new Intent(MenuPrincipal.this, AgendaModificar.class);
                startActivity(inten1);
            }
        });

        cerrarsesion = findViewById(R.id.cerrarSesion);
        cerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}