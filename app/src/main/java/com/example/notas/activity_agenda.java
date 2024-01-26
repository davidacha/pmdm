package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.chip.Chip;

public class activity_agenda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        Button buttonAjustes = findViewById(R.id.buttonAjustes);
        buttonAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodos.cambiarActividad(activity_agenda.this, activity_ajustes.class);
            }
        });

        Chip chipAjustes = findViewById(R.id.chipAj);
        chipAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodos.cambiarActividad(activity_agenda.this, activity_ajustes.class);
            }
        });

        Chip chipCalendar = findViewById(R.id.chip);
        chipCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodos.cambiarActividad(activity_agenda.this, activity_calendario.class);
            }
        });

        Button buttonCalendar = findViewById(R.id.button6);
        buttonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodos.cambiarActividad(activity_agenda.this, activity_calendario.class);
            }
        });
    }
}