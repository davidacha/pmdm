package com.example.notas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        TextInputEditText textEmail = findViewById(R.id.textEmail);
        TextInputEditText textPass = findViewById(R.id.textPass);

        Button buttonAcceso = findViewById(R.id.buttonAcceso);
        buttonAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inicioSesion(textEmail,textPass);
            }
        });

        Button buttonReg = findViewById(R.id.buttonReg);
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodos.cambiarActividad(MainActivity.this, activity_registro.class);
            }
        });

        Button buttonPass = findViewById(R.id.buttonPass);
        buttonPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangePassDialog();
            }
        });
    }

    public void inicioSesion(TextInputEditText textEmail, TextInputEditText textPass){
        String email= String.valueOf(textEmail.getText());
        String pass= String.valueOf(textPass.getText());
        if(email.isEmpty()){
            Toast.makeText(MainActivity.this, R.string.introducirEmail, Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.isEmpty()){
            Toast.makeText(MainActivity.this, R.string.meterContrasenha, Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, R.string.iniciosesionCorrecto, Toast.LENGTH_SHORT).show();
                    metodos.cambiarActividad(MainActivity.this, activity_agenda.class);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this, R.string.falloiniciarsesion, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showChangePassDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.contrasenha_olvidada, null);
        final EditText editText = dialogView.findViewById(R.id.editTextContra);

        builder.setView(dialogView)
                .setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Email = editText.getText().toString();
                        changePassword(Email);
                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
    private void changePassword(String email){
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Solicitud enviada correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Fallo al enviar la solicitud", Toast.LENGTH_SHORT).show();
            }
        });
    }
}