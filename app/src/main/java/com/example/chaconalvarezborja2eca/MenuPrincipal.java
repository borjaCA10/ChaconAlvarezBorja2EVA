package com.example.chaconalvarezborja2eca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {
Button guardarContacto;
Button eliminarContacto;
Button mostrarContacto;
Button modificarContacto;
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
    }
}