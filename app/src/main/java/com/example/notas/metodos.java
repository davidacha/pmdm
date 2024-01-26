package com.example.notas;

import android.content.Context;
import android.content.Intent;

public class metodos {
    public static void cambiarActividad(Context context, Class<?> claseDestino) {
        Intent intent = new Intent(context, claseDestino);
        context.startActivity(intent);
    }
}
