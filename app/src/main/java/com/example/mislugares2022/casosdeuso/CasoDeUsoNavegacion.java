package com.example.mislugares2022.casosdeuso;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import java.util.OptionalInt;

public class CasoDeUsoNavegacion {


    public static void navegarA(Activity actividad, Class destino, OptionalInt
            extra, String extraTexto) {

        Intent iLugar = new Intent(actividad, destino);

        if (extra.isPresent())
            iLugar.putExtra(extraTexto, extra.getAsInt());


        actividad.startActivity(iLugar);
    }


}