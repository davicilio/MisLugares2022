package com.example.mislugares2022.aplicacion;

import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.example.mislugares2022.adaptadores.AdaptadorLugares;
import com.example.mislugares2022.modelo.GeoPunto;
import com.example.mislugares2022.modelo.LugaresBD;

public class Aplicacion extends MultiDexApplication {
    public LugaresBD lugares = null;
    public AdaptadorLugares adaptador = null;
    public GeoPunto posicionActual = new GeoPunto(0.0, 0.0);

    @Override
    public void onCreate() {
        super.onCreate();
        lugares = new LugaresBD(this);
        adaptador = new AdaptadorLugares(lugares, lugares.extraeCursor());

        Log.i("Aplicacion", "Empezamos la aplicacion");
    }

    public LugaresBD getLugares() {
        return lugares;
    }

    public void setLugares(LugaresBD lugares) {
        this.lugares = lugares;
    }


}
