package com.example.chaconalvarezborja2eca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AgendaBorrar extends AppCompatActivity {
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    ImageButton borrarContacto;
    ImageButton volverAtras;
    EditText textoNumero;
    String clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_borrar);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("agenda");
        dbRef = userRef.child("AGENDA DE:  " + userId);

        borrarContacto = findViewById(R.id.BorrarCon);
        borrarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoNumero = findViewById(R.id.textTelefono);

                Query q = dbRef.orderByChild("movil").equalTo(textoNumero.getText().toString());
                q.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot ds : snapshot.getChildren()) {
                            clave = ds.getKey();
                            dbRef.child(clave).removeValue();
                        }

                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        volverAtras = findViewById(R.id.volverAtras);
        volverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AgendaBorrar.this, MenuPrincipal.class);
                startActivity(intent1);
            }
        });
    }
}