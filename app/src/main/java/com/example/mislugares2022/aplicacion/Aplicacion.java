package com.example.mislugares2022.aplicacion;

import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.example.mislugares2022.adaptadores.AdaptadorLugares;
import com.example.mislugares2022.modelo.GeoPunto;
import com.example.mislugares2022.modelo.LugaresVector;

public class Aplicacion extends MultiDexApplication {
    public LugaresVector lugares = null;
    public AdaptadorLugares adaptador = null;
    public GeoPunto posicionActual = new GeoPunto(0.0, 0.0);

    @Override
    public void onCreate() {
        super.onCreate();
        lugares = new LugaresVector();
        adaptador = new AdaptadorLugares(lugares, this);

        Log.i("Aplicacion", "Empezamos la aplicacion");
    }

    public LugaresVector getLugares() {
        return lugares;
    }

    public void setLugares(LugaresVector lugares) {
        this.lugares = lugares;
    }


}
