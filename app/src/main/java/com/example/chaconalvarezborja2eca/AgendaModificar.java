package com.example.chaconalvarezborja2eca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AgendaModificar extends AppCompatActivity {
    ImageButton volverAtras;
    ImageButton Modificar;
    EditText numero;
    EditText nombre;
    EditText email;
    EditText alias;

    EditText direccion;
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_modificar);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("agenda");
        dbRef = userRef.child("AGENDA DE:  " + userId);

        volverAtras = findViewById(R.id.VolverAtras1);
        volverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AgendaModificar.this, MenuPrincipal.class);
                startActivity(intent1);
            }
        });

        Modificar = findViewById(R.id.modificarContacto);
        Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = findViewById(R.id.editTextPhone);
                nombre = findViewById(R.id.EditNombre);
                direccion = findViewById(R.id.editDireccion);
                email = findViewById(R.id.EditMail);
                alias = findViewById(R.id.editAlias);
                Query q = dbRef.orderByChild("movil").equalTo(numero.getText().toString());
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()){
                            String clave = ds.getKey();
                            dbRef.child(clave).child("nombre").setValue(nombre.getText().toString());
                            dbRef.child(clave).child("direccion").setValue(direccion.getText().toString());
                            dbRef.child(clave).child("email").setValue(email.getText().toString());
                            dbRef.child(clave).child("alias").setValue(alias.getText().toString());

                        }
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}
