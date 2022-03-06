package com.example.mislugares2022.presentacion;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mislugares2022.R;
import com.example.mislugares2022.adaptadores.AdaptadorLugares;
import com.example.mislugares2022.adaptadores.AdaptadorTipoLugarDrawable;
import com.example.mislugares2022.aplicacion.Aplicacion;
import com.example.mislugares2022.casosdeuso.CasosUsoLugar;
import com.example.mislugares2022.modelo.Lugar;
import com.example.mislugares2022.modelo.LugaresBD;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.text.DateFormat;
import java.util.Date;

/*public class VistaLugarActivity extends AppCompatActivity {
    private long id;
    private Lugar lugar;
    RepositorioLugares lugares;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_lugar);
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id", -1);
        lugar = MainActivity.repositorioLugares.elemento((int) id);
        TextView nombre = (TextView) findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        ImageView logo_tipo = (ImageView) findViewById(R.id.logo_tipo);
        logo_tipo.setImageResource(lugar.getTipo().getRecurso());
        TextView tipo = (TextView) findViewById(R.id.tipo);
        tipo.setText(lugar.getTipo().getTexto());

        lugares =((Aplicacion) getApplication()).getLugares();
        id = extras.getInt("id", -1);
        lugar = lugares.elemento(id);

        TextView direccion = (TextView) findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        TextView telefono = (TextView) findViewById(R.id.telefono);
        telefono.setText(Integer.toString((int) lugar.getTelefono()));
        TextView url = (TextView) findViewById(R.id.url);
        url.setText(lugar.getUrl());
        TextView comentario = (TextView) findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
        TextView fecha = (TextView) findViewById(R.id.fecha);
        fecha.setText(DateFormat.getDateInstance().format(
                new Date(lugar.getFecha())));
        TextView hora = (TextView) findViewById(R.id.hora);
        hora.setText(DateFormat.getTimeInstance().format(
                new Date(lugar.getFecha())));
        RatingBar valoracion = (RatingBar) findViewById(R.id.valoracion);
        valoracion.setRating(lugar.getValoracion());
        valoracion.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override public void onRatingChanged(RatingBar ratingBar,
                                                          float valor, boolean fromUser) {
                        lugar.setValoracion(valor);
                    }
                });
    }


}*/

public class VistaLugarActivity extends AppCompatActivity {
    private LugaresBD lugares;
    private CasosUsoLugar usoLugar;
    private long id = -1;
    private Lugar lugar;
    private AdaptadorLugares adaptador;
    private ImageView foto;
    private Uri uriUltimaFoto;
    final static int RESULTADO_EDITAR = 1;
    final static int RESULTADO_GALERIA = 2;
    final static int RESULTADO_FOTO = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_lugar);
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id", 0);
        lugares = ((Aplicacion) getApplication()).getLugares();
        adaptador = ((Aplicacion) getApplication()).adaptador;
        usoLugar = new CasosUsoLugar(this, lugares, adaptador);
        lugar = lugares.elemento((int) id);
        foto = (ImageView) findViewById(R.id.foto);
        actualizaVistas();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(android.R.drawable.ic_input_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    /*public void galeria(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULTADO_GALERIA);
    }*/

    public void actualizaVistas() {

        TextView nombre = findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        ImageView logo_tipo = findViewById(R.id.logo_tipo);

        //logo_tipo.setImageResource(lugar.getTipo().getRecurso());

        logo_tipo.setImageResource(AdaptadorTipoLugarDrawable.getRecursoDrawable(lugar.getTipo()));
        TextView tipo = findViewById(R.id.tipo);
        tipo.setText(lugar.getTipo().getTexto());
        TextView direccion = findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        TextView telefono = findViewById(R.id.telefono);
        telefono.setText(Integer.toString((int) lugar.getTelefono()));
        TextView url = findViewById(R.id.url);
        url.setText(lugar.getUrl());
        TextView comentario = findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
        TextView fecha = findViewById(R.id.fecha);
        fecha.setText(DateFormat.getDateInstance().format(
                new Date(lugar.getFecha())));
        TextView hora = findViewById(R.id.hora);
        hora.setText(DateFormat.getTimeInstance().format(
                new Date(lugar.getFecha())));
        RatingBar valoracion = findViewById(R.id.valoracion);
        valoracion.setOnRatingBarChangeListener(null);
        valoracion.setRating(lugar.getValoracion());
        valoracion.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar,
                                                float valor, boolean fromUser) {
                        lugar.setValoracion(valor);
                        usoLugar.actualizaPosLugar((int) id, lugar);
                        //usoLugar.guardar(VistaLugarActivity.this,lugares,(int)id,lugar);
                    }
                });
        usoLugar.visualizarFoto(lugar, foto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vista_lugar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//pagina 174
        switch (item.getItemId()) {
            case R.id.accion_compartir:
                usoLugar.compartir(lugar);
                return true;
            case R.id.accion_llegar:
                usoLugar.verMapa(lugar);
                return true;
            case R.id.accion_editar:
                usoLugar.editar((int) id);
                return true;
            case R.id.accion_borrar:
                usoLugar.borrar((int) id);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void ponerDeGaleria(View view) {
        usoLugar.ponerDeGaleria(RESULTADO_GALERIA);
        usoLugar.actualizaPosLugar((int) id, lugar);
    }

    public void tomarFoto(View view) {
        uriUltimaFoto = usoLugar.tomarFoto(RESULTADO_FOTO);
        usoLugar.actualizaPosLugar((int) id, lugar);
    }


    public void eliminarFoto(View view) {
        lugar = usoLugar.ponerFoto((int) id, "", foto);
        usoLugar.actualizaPosLugar((int) id, lugar);
    }


    public void verMapa(View view) {
        usoLugar.verMapa(lugar);
    }

    public void llamarTelefono(View view) {
        usoLugar.llamarTelefono(lugar);
    }

    public void verPgWeb(View view) {
        usoLugar.verPgWeb(lugar);
    }


    @Override /*protected*/ public void onActivityResult(int requestCode, int resultCode,
                                                         Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULTADO_EDITAR) {
            //int id = adaptador.idPosicion(pos);
            lugar = lugares.elemento((int) id);
            usoLugar.actualizaPosLugar((int) id, lugar);
            //adaptador.cursor = lugares.extraeCursor();
            //pos = adaptador.posicionId(_id);
            actualizaVistas();
        } else if (requestCode == RESULTADO_GALERIA) {
            if (resultCode == Activity.RESULT_OK) {
                //usoLugar.ponerDeGaleria(RESULTADO_GALERIA);
                lugar.setFoto(data.getDataString());
                lugar = usoLugar.ponerFoto((int) id, lugar.getFoto(), foto);
                usoLugar.actualizaPosLugar((int) id, lugar);
            } else {
                Toast.makeText(this, "Foto no cargada", Toast.LENGTH_LONG).show();
            }

        } else if (requestCode == RESULTADO_FOTO) {
            if (resultCode == Activity.RESULT_OK && uriUltimaFoto.toString().trim() != null) {
                lugar.setFoto(uriUltimaFoto.toString());
                lugar = usoLugar.ponerFoto((int) id, lugar.getFoto(), foto);
                usoLugar.actualizaPosLugar((int) id, lugar);
            } else {
                Toast.makeText(this, "Error en captura", Toast.LENGTH_LONG).show();
            }
        }
    }

    //PONER PARA QUE SE QUEDE GUARDADA LA VALORACIÓN
    @Override
    protected void onResume() {
        super.onResume();
        //adaptador = new AdaptadorLugares(lugares,lugares.extraeCursor());
        //usoLugar.guardar(this, lugares, (int) id, lugar);
        adaptador.notifyDataSetChanged();


    }

}
