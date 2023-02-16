package com.example.chaconalvarezborja2eca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.collection.LLRBBlackValueNode;

import java.util.ArrayList;

public class AgendaListado extends AppCompatActivity {
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    ListView listado;
    Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_agenda_listado);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("agenda");
        dbRef = userRef.child("AGENDA DE:  " + userId);
        listado = findViewById(R.id.listadoContactos);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayAdapter<String> adapter;
                ArrayList<String> listadoContactos = new ArrayList<String>();
                for (DataSnapshot dp : snapshot.getChildren()) {
                    contacto = dp.getValue(Contacto.class);
                    listadoContactos.add(contacto.getMovil());
                }
                adapter = new ArrayAdapter<>(AgendaListado.this, android.R.layout.simple_list_item_1, listadoContactos);
                listado.setAdapter(adapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
       listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(Intent.ACTION_DIAL);
               intent.setData(Uri.parse("tel:" +listado.getItemAtPosition(position).toString()));
               startActivity(intent);
           }
       });
    }
}