package com.example.chaconalvarezborja2eca;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

public class LlamarContacto extends AppCompatActivity {
    TextView numero;
    TextView nombre;
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamar_contacto);
        String content = getIntent().getStringExtra("nombre");

        nombre = findViewById(R.id.textNombre1);
        numero = findViewById(R.id.textTlf);
        nombre.setText(content);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("agenda");
        dbRef = userRef.child("AGENDA DE:  " + userId);

        Query q = dbRef.orderByChild("nombre").equalTo(nombre.getText().toString());
        q.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Contacto contacto = new Contacto();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    contacto = ds.getValue(Contacto.class);
                }
                nombre.setText(contacto.getNombre());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
