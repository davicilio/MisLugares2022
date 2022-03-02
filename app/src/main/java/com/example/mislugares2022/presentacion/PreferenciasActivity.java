package com.example.mislugares2022.presentacion;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mislugares2022.R;
import com.example.mislugares2022.modelo.Preferencias;

public class PreferenciasActivity extends AppCompatActivity {

    private boolean cambio = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new Preferencias())
                .commit();
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.xml.preferencias, menu);
        return true; /** true -> el menú ya está visible */
    }
   /* @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_salir) {

            finish();
            return true;
        }
        return false;
    }*/
}