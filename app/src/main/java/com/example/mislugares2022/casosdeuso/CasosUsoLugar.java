package com.example.mislugares2022.casosdeuso;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import com.example.mislugares2022.modelo.Lugar;
import com.example.mislugares2022.modelo.RepositorioLugares;
import com.example.mislugares2022.presentacion.VistaLugarActivity;

import java.util.OptionalInt;

public class CasosUsoLugar {
    private Activity actividad;
    private RepositorioLugares lugares;

    public final static int RESULTADO_EDITAR = 1;

    public final static int RESULTADO_GALERIA = 2;
    public final static int RESULTADO_FOTO = 3;

    private static Uri uriUltimaFoto;

    public CasosUsoLugar() {

    }

    public CasosUsoLugar(Activity actividad, RepositorioLugares lugares) {
        this.actividad = actividad;
        this.lugares = lugares;
    }

    //OPERACIONES BÁSICAS

    public static void mostrar(Activity actividad, int id) {
        Intent i = new Intent(actividad,
                VistaLugarActivity.class);
        i.putExtra("id", id);
        actividad.startActivity(i);
    }


    public static void guardar(Activity actividad, RepositorioLugares lugares, int id, Lugar nuevoLugar) {

        lugares.actualiza(id, nuevoLugar);

        CasoDeUsoNavegacion.navegarA(actividad, VistaLugarActivity.class, OptionalInt.of(id), "id");
        actividad.finish();
    }

    public void borrar(final int id) {
        lugares.borrar(RepositorioLugares.elemento(id));
        actividad.finish();
    }

    public void ponerDeGaleria(int codigoSolicitud) {
        String action;
        if (Build.VERSION.SDK_INT >= 23) {
            action = Intent.ACTION_OPEN_DOCUMENT;
        } else {
            action = Intent.ACTION_PICK;
        }
        Intent intent = new Intent(action, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        actividad.startActivityForResult(intent, codigoSolicitud);
    }
}
