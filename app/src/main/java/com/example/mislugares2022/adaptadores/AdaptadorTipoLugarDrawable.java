package com.example.mislugares2022.adaptadores;


import static com.example.mislugares2022.modelo.TipoLugar.*;


import com.example.mislugares2022.R;
import com.example.mislugares2022.modelo.TipoLugar;

import java.util.HashMap;

public class AdaptadorTipoLugarDrawable {


    private static final HashMap<TipoLugar, Integer> drawablesRecursos = new HashMap<TipoLugar, Integer>();

    static {

        drawablesRecursos.put(OTROS, R.drawable.otros);
        drawablesRecursos.put(RESTAURANTE, R.drawable.restaurante);
        drawablesRecursos.put(BAR, R.drawable.bar);
        drawablesRecursos.put(COPAS, R.drawable.copas);
        drawablesRecursos.put(ESPECTACULO, R.drawable.espectaculos);
        drawablesRecursos.put(HOTEL, R.drawable.hotel);
        drawablesRecursos.put(COMPRAS, R.drawable.compras);
        drawablesRecursos.put(EDUCACION, R.drawable.educacion);
        drawablesRecursos.put(DEPORTE, R.drawable.deporte);
        drawablesRecursos.put(NATURALEZA, R.drawable.naturaleza);
        drawablesRecursos.put(GASOLINERA, R.drawable.gasolinera);
    }


    public static final int getRecursoDrawable(TipoLugar tipo) {

        if (tipo != null && tipo.getTexto() != null)
            return drawablesRecursos.get(tipo);
        else return
                drawablesRecursos.get(OTROS);

    }
}
