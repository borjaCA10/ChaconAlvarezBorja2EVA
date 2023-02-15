package com.example.chaconalvarezborja2eca;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgendaBorrar extends AppCompatActivity {
private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
ImageButton borrarContacto;
EditText textoNumero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_borrar);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("agenda");
        dbRef = userRef.child("AGENDA DE: " + userId);

        borrarContacto = findViewById(R.id.BorrarCon);
        borrarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoNumero = findViewById(R.id.textTelefono);

            }
        });
    }
}