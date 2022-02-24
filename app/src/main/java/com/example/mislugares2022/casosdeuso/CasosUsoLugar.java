package com.example.mislugares2022.casosdeuso;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import com.example.mislugares2022.aplicacion.Aplicacion;
import com.example.mislugares2022.modelo.GeoPunto;
import com.example.mislugares2022.modelo.Lugar;
import com.example.mislugares2022.modelo.LugaresBD;
import com.example.mislugares2022.modelo.RepositorioLugares;
import com.example.mislugares2022.presentacion.EdicionLugarActivity;
import com.example.mislugares2022.presentacion.VistaLugarActivity;

import java.util.OptionalInt;

public class CasosUsoLugar {
    private Activity actividad;
    private LugaresBD lugares;

    public final static int RESULTADO_EDITAR = 1;

    public final static int RESULTADO_GALERIA = 2;
    public final static int RESULTADO_FOTO = 3;

    private static Uri uriUltimaFoto;

    public CasosUsoLugar() {

    }

    public CasosUsoLugar(Activity actividad, LugaresBD lugares) {
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

    public void compartir(Lugar lugar) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT,
                lugar.getNombre() + " - " + lugar.getUrl());
        actividad.startActivity(i);
    }

    public void llamarTelefono(Lugar lugar) {
        actividad.startActivity(new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:" + lugar.getTelefono())));
    }

    public void verPgWeb(Lugar lugar) {
        actividad.startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(lugar.getUrl())));
    }

    public final void verMapa(Lugar lugar) {
        double lat = lugar.getPosicion().getLatitud();
        double lon = lugar.getPosicion().getLongitud();
        Uri uri = lugar.getPosicion() != GeoPunto.SIN_POSICION
                ? Uri.parse("geo:" + lat + ',' + lon)
                : Uri.parse("geo:0,0?q=" + lugar.getDireccion());
        actividad.startActivity(new Intent("android.intent.action.VIEW", uri));
    }
    public void nuevo() {
        int id = lugares.nuevo();
        GeoPunto posicion = ((Aplicacion) actividad.getApplication())
                .posicionActual;
        if (!posicion.equals(GeoPunto.SIN_POSICION)) {
            Lugar lugar = lugares.elemento(id);
            lugar.setPosicion(posicion);
            lugares.actualiza(id, lugar);
            lugar.setUrl("");
            lugar.setValoracion(0);
            lugar.setNombre("");
            lugar.setComentario("");
            lugar.setTelefono(0);
        }
        Intent i = new Intent(actividad, EdicionLugarActivity.class);
        i.putExtra("id", id);
        actividad.startActivity(i);
    }

    public void editar(int pos) {
        Intent i = new Intent(actividad, EdicionLugarActivity.class);
        i.putExtra("id", pos);
        actividad.startActivity(i);

    }

}
