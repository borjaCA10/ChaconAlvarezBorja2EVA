package com.example.chaconalvarezborja2eca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class LoginActivity extends AppCompatActivity {
    Button login;
    Button registro;
    private FirebaseAuth contactoBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText usuario = findViewById(R.id.textUsername);
        EditText contraseña = findViewById(R.id.textPassword);
        registro = findViewById(R.id.registroButton);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                registrarse(usuario.getText().toString(), contraseña.getText().toString());
            }
        });

        login = findViewById(R.id.incioButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iniciarSesion(usuario.getText().toString(), contraseña.getText().toString());
            }
        });

    }


    public void registrarse(String usuario, String contraseña) {
        contactoBase = FirebaseAuth.getInstance();

        contactoBase.createUserWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = contactoBase.getCurrentUser();
                    Toast.makeText(LoginActivity.this, "SE HA REGISTRADO A : " + user.getUid() + "EN NUESTRA BASE DE DATOS", Toast.LENGTH_SHORT).show();

                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(LoginActivity.this,"YA EXISTE UN USUARIO CON ESE CORREO", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(LoginActivity.this, "NO SE HA PODIDO REGISTRAR EN NUESTRA BASE DE DATOS, REVISE SUS DATOS ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void iniciarSesion(String usuario, String contraseña) {
        contactoBase = FirebaseAuth.getInstance();
        contactoBase.signInWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = contactoBase.getCurrentUser();
                    Toast.makeText(LoginActivity.this, "BIENVENIDO DE NUEVO : " + user.getUid(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MenuPrincipal.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "HA OCURRIDO UN ERROR AL INCIAR SESION, PRIEMRO DEBE REGISTRARSE ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}