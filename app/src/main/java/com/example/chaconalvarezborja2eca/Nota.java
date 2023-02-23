package com.example.chaconalvarezborja2eca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Nota extends AppCompatActivity {
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
EditText titulo ;
EditText Nota;
Button guardar;
Button ver;
ListView lista;
ClaseNota claseNota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("nota");
        dbRef = userRef.child("NOTA DE " + userId);
        titulo = findViewById(R.id.textView5);
        Nota = findViewById(R.id.Contenido);
        guardar = findViewById(R.id.Guardar);
        lista = findViewById(R.id.listaConte);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                claseNota = new ClaseNota(titulo.getText().toString(),Nota.getText().toString());
                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                    dbRef.push().setValue(claseNota);
                    titulo.setText("");
                    Nota.setText("");
                    finish();
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
                finish();
            }

        });

        ver = findViewById(R.id.VerNota);
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayAdapter<String> adapter;
                        ArrayList<String> listadoContactos = new ArrayList<String>();
                        for (DataSnapshot dp : snapshot.getChildren()) {
                            claseNota = dp.getValue(ClaseNota.class);
                            listadoContactos.add(claseNota.getTitulo());
                        }
                        adapter = new ArrayAdapter<>(Nota.this, android.R.layout.simple_list_item_1, listadoContactos);
                        lista.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    }


}