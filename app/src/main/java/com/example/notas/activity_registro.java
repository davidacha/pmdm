package com.example.notas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import bd.FirebaseBD;

public class activity_registro extends AppCompatActivity {

    static FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        TextInputEditText textEmail = findViewById(R.id.textEmail);
        TextInputEditText textPass = findViewById(R.id.textPass);
        TextInputEditText textPass2 = findViewById(R.id.textPass2);

        Button buttonIS = findViewById(R.id.buttonIS);
        buttonIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodos.cambiarActividad(activity_registro.this, MainActivity.class);
            }
        });

        Button buttonRegistro = findViewById(R.id.buttonAcceso2);
        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro(textEmail,textPass,textPass2);
            }
        });
    }

    public void registro(TextInputEditText textEmail, TextInputEditText textPass, TextInputEditText textPass2){
        String email= String.valueOf(textEmail.getText());
        String pass= String.valueOf(textPass.getText());
        String pass2= String.valueOf(textPass2.getText());
        if(email.isEmpty()){
            Toast.makeText(activity_registro.this, R.string.introducirEmail, Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.isEmpty()){
            Toast.makeText(activity_registro.this, R.string.meterContrasenha, Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass2.isEmpty() && pass2!=pass){
            Toast.makeText(activity_registro.this, R.string.repetirContrasenha, Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(activity_registro.this, R.string.registroCorrecto, Toast.LENGTH_SHORT).show();
                    FirebaseBD.añadirUser();
                    metodos.cambiarActividad(activity_registro.this, activity_agenda.class);
                    finish();
                }
                else{
                    Toast.makeText(activity_registro.this, R.string.falloregistro, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}