package com.example.mislugares2022.casosdeuso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.example.mislugares2022.adaptadores.AdaptadorLugares;
import com.example.mislugares2022.aplicacion.Aplicacion;
import com.example.mislugares2022.modelo.GeoPunto;
import com.example.mislugares2022.modelo.Lugar;
import com.example.mislugares2022.modelo.LugaresBD;
import com.example.mislugares2022.presentacion.EdicionLugarActivity;
import com.example.mislugares2022.presentacion.VistaLugarActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.OptionalInt;

public class CasosUsoLugar {
    private Activity actividad;
    private LugaresBD lugares;
    private AdaptadorLugares adaptador;

    public final static int RESULTADO_EDITAR = 1;

    public final static int RESULTADO_GALERIA = 2;
    public final static int RESULTADO_FOTO = 3;

    private static Uri uriUltimaFoto;

    public CasosUsoLugar() {

    }

    public CasosUsoLugar(Activity actividad, LugaresBD lugares, AdaptadorLugares adaptador) {
        this.actividad = actividad;
        this.lugares = lugares;
        this.adaptador = adaptador;
    }

    //OPERACIONES BÁSICAS

    public static void mostrar(Activity actividad, int id) {
        Intent i = new Intent(actividad,
                VistaLugarActivity.class);
        i.putExtra("id", id);
        actividad.startActivity(i);
    }

    public AdaptadorLugares getAdaptador() {
        return adaptador;
    }

    public void actualizaPosLugar(int id, Lugar lugar) {
        // int id = adaptador.idPosicion(pos);
        lugares.actualiza(id, lugar);
        adaptador.setCursor(lugares.extraeCursor());
        adaptador.notifyDataSetChanged();
        //guardar(actividad, lugares, id, lugar);
    }

    public void guardar(Activity actividad, LugaresBD lugares, int id, Lugar nuevoLugar) {
        AdaptadorLugares adaptador = getAdaptador();
        lugares.actualiza(id, nuevoLugar);
        adaptador.setCursor(lugares.extraeCursor());
        CasoDeUsoNavegacion.navegarA(actividad, VistaLugarActivity.class, OptionalInt.of(id), "id");
        adaptador.notifyDataSetChanged();
        actividad.finish();
    }

    public void borrar(final int id) {
        AdaptadorLugares adaptador = getAdaptador();
        lugares.borrar(id);
        adaptador.setCursor(lugares.extraeCursor());
        adaptador.notifyDataSetChanged();
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
            //lugar.setTelefono(0);
        }
        Intent i = new Intent(actividad, EdicionLugarActivity.class);
        i.putExtra("id", id);
        actividad.startActivity(i);
    }

    public void editar(int id) {
        Intent i = new Intent(actividad, EdicionLugarActivity.class);
        i.putExtra("id", id);
        actividad.startActivity(i);

    }


    public Lugar ponerFoto(int id, String uri, ImageView imageView) {
        Lugar lugar = lugares.elemento(id);
        lugar.setFoto(uri);
        visualizarFoto(lugar, imageView);
        return lugar;
    }

    /*public void visualizarFoto(Lugar lugar, ImageView imageView) {
        if (lugar.getFoto() != null && !lugar.getFoto().isEmpty()) {
            imageView.setImageURI(Uri.parse(lugar.getFoto()));
        } else {
            imageView.setImageBitmap(null);
        }
    }*/
    /*public void visualizarFoto(Lugar lugar, ImageView imageView) {
        if (lugar.getFoto() != null && !lugar.getFoto().isEmpty()) {
            imageView.setImageBitmap(reduceBitmap(actividad, lugar.getFoto(), 1024, 1024));
        } else {
            imageView.setImageBitmap(null);
        }
    }*/
    public void visualizarFoto(Lugar lugar, ImageView imageView) {
        if (lugar.getFoto() != null && !lugar.getFoto().isEmpty()) {
            imageView.setImageURI(Uri.parse(lugar.getFoto()));
        } else {
            imageView.setImageBitmap(null);
        }
    }


    public Uri tomarFoto(int codigoSolicitud) {
        try {
            Uri uriUltimaFoto;
            File file = File.createTempFile(
                    "img_" + (System.currentTimeMillis() / 1000), ".jpg",
                    actividad.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            if (Build.VERSION.SDK_INT >= 24) {
                uriUltimaFoto = FileProvider.getUriForFile(
                        actividad, "com.example.mislugares.fileProvider", file);
            } else {
                uriUltimaFoto = Uri.fromFile(file);
            }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriUltimaFoto);
            actividad.startActivityForResult(intent, codigoSolicitud);
            return uriUltimaFoto;
        } catch (IOException ex) {
            Toast.makeText(actividad, "Error al crear fichero de imagen",
                    Toast.LENGTH_LONG).show();
            return null;
        }
    }

    private Bitmap reduceBitmap(Context contexto, String uri,
                                int maxAncho, int maxAlto) {
        try {
            InputStream input = null;
            Uri u = Uri.parse(uri);
            if (u.getScheme().equals("http") || u.getScheme().equals("https")) {
                input = new URL(uri).openStream();
            } else {
                input = contexto.getContentResolver().openInputStream(u);
            }
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inSampleSize = (int) Math.max(
                    Math.ceil(options.outWidth / maxAncho),
                    Math.ceil(options.outHeight / maxAlto));
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(input, null, options);
        } catch (FileNotFoundException e) {
            Toast.makeText(contexto, "Fichero/recurso de imagen no encontrado",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            Toast.makeText(contexto, "Error accediendo a imagen",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return null;
        }
    }

}
