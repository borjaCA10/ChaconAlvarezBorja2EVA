package com.example.chaconalvarezborja2eca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class AgendaGuardar extends AppCompatActivity {
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    ImageButton image;
    ImageButton VolverAtras;
    EditText nombre;
    EditText direccion;
    EditText email;
    EditText numero;
    String nuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("agenda");
        dbRef = userRef.child("AGENDA DE:  " + userId);
        image = findViewById(R.id.GuardadContacto);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = findViewById(R.id.textNombre);
                direccion = findViewById(R.id.textDireccion);
                email = findViewById(R.id.textEmail);
                numero = findViewById(R.id.textMovil);

                Contacto contacto = new Contacto(nombre.getText().toString(),direccion.getText().toString(),email.getText().toString(),numero.getText().toString());
                dbRef.push().setValue(contacto);
                Toast.makeText(AgendaGuardar.this, "SE HA REGISTRADO EL CONTACTO " + userId, Toast.LENGTH_SHORT).show();

                nombre.setText(nuevo);
                direccion.setText(nuevo);
                email.setText(nuevo);
                numero.setText(nuevo);

            }
        });

        VolverAtras = findViewById(R.id.VolverAtras);
        VolverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AgendaGuardar.this, MenuPrincipal.class);
                startActivity(intent1);
            }
        });
    }
}