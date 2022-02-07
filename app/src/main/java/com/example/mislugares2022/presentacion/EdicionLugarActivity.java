package com.example.mislugares2022.presentacion;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mislugares2022.R;
import com.example.mislugares2022.aplicacion.Aplicacion;
import com.example.mislugares2022.modelo.Lugar;
import com.example.mislugares2022.modelo.LugaresVector;
import com.example.mislugares2022.modelo.RepositorioLugares;
import com.example.mislugares2022.modelo.TipoLugar;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class EdicionLugarActivity extends AppCompatActivity {

    private long id;
    private RepositorioLugares lugares;//
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


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        });
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id", 1);
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

    }

}
