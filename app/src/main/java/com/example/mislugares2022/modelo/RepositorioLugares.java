package com.example.mislugares2022.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * This Interface serves as a base for List of locations in the app
 *
 * @author Carlos@Cano
 * @version 1.1
 */

public interface RepositorioLugares {
    final static String TAG = ConstantesModelo.Lugares_TAG;
    static GeoPunto posicionActual = ConstantesModelo.SIN_POSICION;


    static Lugar elemento(int id)  // Devuelve el elemento dado su id
    {
        return LugaresVector.elemento(id);
    }

    void anyade(Lugar lugar); // Añade el elemento indicado

    int nuevo(); //Añade un elemento en blanco y devueve su id

    void borrar(Lugar lugar);  //Elimina el elemento con el id indicado

    int tamanyo();  //Devuelve el número de elementos

    void actualiza(int id, Lugar lugar); //Reemplaza un elemento

    public List<Lugar> listaLugares();

    static Lugar getElementoPorPosicion(int id) {
        return LugaresVector.elemento(id);
    }


}
