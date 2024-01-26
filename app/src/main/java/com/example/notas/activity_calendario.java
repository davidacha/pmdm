package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class activity_calendario extends AppCompatActivity {

    DatePicker datePicker;
    TextInputEditText editTextFecha;
    TextInputEditText editTextHora;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        editTextFecha=findViewById(R.id.textFecha);
        datePicker=findViewById(R.id.datePicker);
        Chip chipAjustes = findViewById(R.id.chipAj);
        chipAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodos.cambiarActividad(activity_calendario.this, activity_ajustes.class);
            }
        });

        Chip chipHome = findViewById(R.id.chip2);
        chipHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodos.cambiarActividad(activity_calendario.this, activity_agenda.class);
            }
        });
        setupDatePicker(this, datePicker, editTextFecha);

        editTextHora=findViewById(R.id.textHora);
        editTextHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });

    }

    public static void setupDatePicker(final Context context, final DatePicker datePicker, final TextInputEditText editTextFecha) {
        final Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        // Establecer la fecha actual como valor por defecto en el DatePicker
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);
        // Configurar el OnDateChangedListener para actualizar el EditText
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // Actualizar el valor del TextInputEditText con la nueva fecha
                editTextFecha.setText(dateFormat.format(calendar.getTime()));
            }
        });
    }

    public void showTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        // Actualizar el texto en el EditText con la hora seleccionada
                        editTextHora.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

}