package com.example.mislugares2022.presentacion;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mislugares2022.R;
import com.example.mislugares2022.adaptadores.AdaptadorLugares;
import com.example.mislugares2022.aplicacion.Aplicacion;
import com.example.mislugares2022.casosdeuso.CasoDeUsoPermisos;
import com.example.mislugares2022.casosdeuso.CasosDeUsoLocalizacion;
import com.example.mislugares2022.casosdeuso.CasosUsoLugar;
import com.example.mislugares2022.modelo.Lugar;
import com.example.mislugares2022.modelo.LugaresVector;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    //public static RepositorioLugares repositorioLugares = new LugaresVector();

    //private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    public AdaptadorLugares adaptador;
    private CasosUsoLugar usoLugar;
    private CasoDeUsoPermisos usoPermisos;
    private int id;
    private Lugar lugar;
    private LugaresVector lugares;
    final static int RESULTADO_EDITAR = 1;
    public static int SOLICITAR_PERMISOS_MULTIPLES = 2;
    private static final int SOLICITUD_PERMISO_LOCALIZACION = 1;
    private CasosDeUsoLocalizacion usoLocalizacion;

    /*public static void main(String[] args) {
        RepositorioLugares lugares = new LugaresVector();
        for (int i=0; i<lugares.tamanyo(); i++) {
            System.out.println(RepositorioLugares.elemento(i).toString());
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(android.R.drawable.ic_input_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usoLugar.nuevo();
            }
        });
////////////////////////////////////
        /*
        Bundle extras = getIntent().getExtras();
        //coge el id para obtener el lugar que le indicamos
        lugares =((Aplicacion) getApplication()).getLugares();
        id = extras.getInt("id", -1);
        lugar = RepositorioLugares.getElementoPorPosicion(id);
        //lugar = lugares.getElementoPorPosicion(id);
        CasoDeUsoPermisos.solicitarPermiso(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                "Necesita permisos de almacenamiento para añadir fotografías", 1);
        */
//////////////////////////////////
        //recycler

        //lugares = ((Aplicacion) getApplication()).lugares;
        lugares = (LugaresVector) ((Aplicacion) getApplication()).getLugares();
        adaptador = ((Aplicacion) getApplication()).adaptador;
        usoLugar = new CasosUsoLugar(this, lugares);


        recyclerView = findViewById(R.id.recycler_view);
        //adaptador = new AdaptadorLugares(lugares,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptador);

        adaptador.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                usoLugar.mostrar(MainActivity.this, pos);
            }
        });
        usoPermisos = new CasoDeUsoPermisos(MainActivity.this, this);
        if (!usoPermisos.hayPermisoAlmacenamiento(MainActivity.this) || !usoPermisos.hayPermisoCamara(MainActivity.this) || usoPermisos.hayPermisoUbicacion(MainActivity.this)) {


            String permisos[] = {
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
            };
            usoPermisos.solicitarPermiso(MainActivity.this, permisos, "Sin permisos no se guardan las fotos", SOLICITAR_PERMISOS_MULTIPLES);
        }
        usoLocalizacion = new CasosDeUsoLocalizacion(this,
                SOLICITUD_PERMISO_LOCALIZACION);

    }

    //SIN RECYCLER
        /*
        Button bAcercaDe = findViewById(R.id.btnAcercade);
        bAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarAcercaDe(null);

            }
        });

        Button btnMostrarLugares = findViewById(R.id.btnMostrarLugares);
        btnMostrarLugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarVistaLugar(null);
            }
        });

        Button bSalir = findViewById(R.id.btnSalir);
        bSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
*////////////////


    /*
    @Override   onOptionsItemSelected(MenuItem item) { public boolean
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            true; return
        }
        if (id == R.id.acercaDe) {
            lanzarAcercaDe( ); null
            return true;
        }
    .onOptionsItemSelected(item); return super
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    public void lanzarAcercaDe(View view) {
        Intent i = new Intent(this, AcercaDeActivity.class);
        startActivity(i);
    }

    /*public void lanzarVistaLugar(View view){
        Intent i = new Intent(this, VistaLugarActivity.class);
        i.putExtra("id", (long)0);
        startActivity(i);
    }*/
    /*protected void  inicializarBotones(){
        FloatingActionButton fab = findViewById(R.id.fab);
        Button btnMostrarLugares = findViewById(R.id.btnMostrarLugares);
        Button btnAcercade = findViewById(R.id.btnAcercade);
        Button btnPreferencias = findViewById(R.id.btnPreferencias);
        Button btnSalir = findViewById(R.id.btnSalir);
    }*/

    public void lanzarVistaLugar(View view) {
        final EditText entrada = new EditText(this);
        entrada.setText("0");
        new AlertDialog.Builder(this)
                .setTitle("Selección de lugar")
                .setMessage("indica su id:")
                .setView(entrada)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        int id = Integer.parseInt(entrada.getText().toString());
                        Intent i = new Intent(MainActivity.this, EdicionLugarActivity.class);
                        i.putExtra("id", (long) 0);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        usoLocalizacion.activar();
    }

    @Override
    protected void onPause() {
        super.onPause();
        usoLocalizacion.desactivar();
    }


    /*@Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
    //…
        if (id == R.id.menu_buscar) {
            lanzarVistaLugar(null);
            return true;
        }
   //…
    }*/

    /*Añadid un elemento inicializar botones para meter la inicialización de todos los botones con sus listener.*/
}