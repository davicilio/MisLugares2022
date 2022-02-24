package com.example.mislugares2022.presentacion;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mislugares2022.R;
import com.example.mislugares2022.adaptadores.AdaptadorLugares;
import com.example.mislugares2022.aplicacion.Aplicacion;
import com.example.mislugares2022.casosdeuso.CasosUsoLugar;
import com.example.mislugares2022.modelo.Lugar;
import com.example.mislugares2022.modelo.LugaresVector;
import com.example.mislugares2022.modelo.TipoLugar;

public class EdicionLugarActivity extends AppCompatActivity {

    private long id;
    private LugaresVector lugares;//
    private AdaptadorLugares adaptador;
    private CasosUsoLugar usoLugar;
    private Lugar lugar;
    private EditText nombre;
    private Spinner tipo;
    private EditText direccion;
    private EditText telefono;
    private EditText url;
    private EditText comentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edicion_lugar_content);
        lugares = ((Aplicacion) getApplication()).lugares;
        adaptador = ((Aplicacion) getApplication()).adaptador;
        usoLugar = new CasosUsoLugar(this, lugares);
        Bundle extras = getIntent().getExtras();

        id = extras.getInt("id", -1);
        lugar = lugares.getElemento((int) id);
        actualizaVistas();


        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        //Bundle extras = getIntent().getExtras();

        /*id = extras.getLong("id", -1);
        lugares = ((Aplicacion) getApplication()).getLugares();//
        lugar = RepositorioLugares.getElementoPorPosicion((int) id);

        nombre = findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());

        tipo = (Spinner) findViewById(R.id.tipo);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, TipoLugar.getNombres());
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipo.setAdapter(adaptador);
        tipo.setSelection(lugar.getTipo().ordinal());

        direccion = findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());

        telefono = findViewById(R.id.telefono);
        telefono.setText(lugar.getDireccion());

        url = findViewById(R.id.url);
        url.setText(lugar.getDireccion());

        comentario = findViewById(R.id.comentario);
        comentario.setText(lugar.getDireccion());
*/

    }

    public void actualizaVistas() {
        nombre = findViewById(R.id.nombre);
        nombre.setText(
                lugar.getNombre());
        direccion = findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        telefono = findViewById(R.id.telefono);
        telefono.setText(Integer.toString((int) lugar.getTelefono()));
        url = findViewById(R.id.url);
        url.setText(lugar.getUrl());
        comentario = findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
        tipo = findViewById(R.id.tipo);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, TipoLugar.getNombres());
        adaptador.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);
        tipo.setAdapter(adaptador);
        tipo.setSelection(lugar.getTipo().ordinal());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edicion_lugar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accion_guardar:
                lugar.setNombre(nombre.getText().toString());
                lugar.setTipo(TipoLugar.values()[tipo.getSelectedItemPosition()]);
                lugar.setDireccion(direccion.getText().toString());
                lugar.setTelefono(Integer.parseInt(telefono.getText().toString()));
                lugar.setUrl(url.getText().toString());
                lugar.setComentario(comentario.getText().toString());
                if (id == -1) id = adaptador.getItemCount();
                usoLugar.guardar(this, lugares, (int) id, lugar);
                finish();
                return true;
            case R.id.accion_cancelar:
                if (id != -1) lugares.borrar(lugar);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
