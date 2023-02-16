package com.example.chaconalvarezborja2eca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

public class AgendaGuardar extends AppCompatActivity {
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    ImageButton image;
    ImageButton VolverAtras;
    EditText nombre;
    EditText direccion;
    EditText email;
    EditText numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("agenda");
        dbRef = userRef.child("AGENDA DE:  " + userId);
        nombre = findViewById(R.id.textNombre);
        direccion = findViewById(R.id.textDireccion);
        email = findViewById(R.id.textEmail);
        numero = findViewById(R.id.textMovil);
        image = findViewById(R.id.GuardadContacto);
        String nuevo = "";
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contacto nuevoContacto = new Contacto(nombre.getText().toString(), direccion.getText().toString(), email.getText().toString(), numero.getText().toString());

                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean numero1 = false;
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Contacto contacto = ds.getValue(Contacto.class);
                            if (contacto.getMovil().equals(nuevoContacto.getMovil())) {
                                numero1 = true;
                                break;

                            }
                        }
                        if (numero1) {
                            Toast.makeText(AgendaGuardar.this, "YA EXISTE UN NUMERO IGUAL", Toast.LENGTH_SHORT).show();
                        } else {
                            dbRef.push().setValue(nuevoContacto);
                            Toast.makeText(AgendaGuardar.this, "SE HA REGISTRADO EL CONTACTO " + userId, Toast.LENGTH_SHORT).show();

                            nombre.setText(nuevo);
                            direccion.setText(nuevo);
                            email.setText(nuevo);
                            numero.setText(nuevo);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
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