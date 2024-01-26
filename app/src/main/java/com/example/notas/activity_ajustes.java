package com.example.notas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class activity_ajustes extends AppCompatActivity {
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        Chip chipHome = findViewById(R.id.chip2);
        chipHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodos.cambiarActividad(activity_ajustes.this, activity_agenda.class);
            }
        });

        Button btnCambiarIdiomaEsp = findViewById(R.id.buttonEspanhol);
        btnCambiarIdiomaEsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarIdioma("es");
            }
        });

        Button btnCambiarIdiomaIng = findViewById(R.id.buttonIngles);
        btnCambiarIdiomaIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarIdioma("en");
            }
        });

        Button btnCambiarIdiomaFr = findViewById(R.id.buttonFrances);
        btnCambiarIdiomaFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarIdioma("fr");
            }
        });

        Button btnCambiarIdiomaIt = findViewById(R.id.buttonItaliano);
        btnCambiarIdiomaIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarIdioma("it");
            }
        });

        Button botonClaro = findViewById(R.id.buttonClaro);
        botonClaro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarTema(true);
            }
        });

        Button botonDark = findViewById(R.id.buttonDark);
        botonDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarTema(false);
            }
        });

        Chip buttonCalendar = findViewById(R.id.chip);
        buttonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodos.cambiarActividad(activity_ajustes.this, activity_calendario.class);
            }
        });

        Button buttonUser = findViewById(R.id.buttonCambiarUser);
        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeEmailDialog();
            }
        });

        Button buttonCambiarPass = findViewById(R.id.buttonCambiarPass);
        buttonCambiarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });
    }

    private void showChangeEmailDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        final EditText editText = dialogView.findViewById(R.id.editText);

        builder.setView(dialogView)
                .setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newEmail = editText.getText().toString();
                        updateEmail(newEmail);
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

    private void updateEmail(String newEmail) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            user.updateEmail(newEmail)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(activity_ajustes.this, "Email cambiado correctamente", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(activity_ajustes.this, "Error al cambiar Email", Toast.LENGTH_SHORT).show();
                                Exception exception = task.getException();
                            }
                        }
                    });
        }
    }
    private void changePassword(){
        String userEmail = firebaseAuth.getCurrentUser().getEmail();
        firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Solicitud enviada correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Fallo al enviar la solicitud", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cambiarIdioma(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.setLocale(locale);

        getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }
    private void cambiarTema(Boolean tema) {
        recreate();
    }
    private void showToast(String string) {
        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
    }

}
