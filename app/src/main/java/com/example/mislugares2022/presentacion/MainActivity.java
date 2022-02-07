package com.example.mislugares2022.presentacion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.mislugares2022.R;
import com.example.mislugares2022.aplicacion.Aplicacion;
import com.example.mislugares2022.casosdeuso.CasosUsoLugar;
import com.example.mislugares2022.adaptadores.AdaptadorLugares;
import com.example.mislugares2022.modelo.RepositorioLugares;
import com.example.mislugares2022.modelo.LugaresVector;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static RepositorioLugares repositorioLugares = new LugaresVector();

    //private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    public AdaptadorLugares adaptador;
    private RepositorioLugares lugares = new LugaresVector();
    private CasosUsoLugar usoLugar;

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


        //recycler

        lugares = ((Aplicacion) getApplication()).lugares;
        adaptador = ((Aplicacion) getApplication()).adaptador;
        usoLugar = new CasosUsoLugar(this, lugares);

        recyclerView = findViewById(R.id.recycler_view);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

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