package com.example.mislugares2022.modelo;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class implements a list of Lugar objects (places)
 *
 * @author Carlos@Cano
 * @version 1.1
 */
public class LugaresVector implements RepositorioLugares, Iterator {
    protected static List<Lugar> vectorLugares = ejemploLugares();
    private static int iteratorCount = 0;


    public LugaresVector() {

        vectorLugares = (ArrayList) ejemploLugares();
    }

    /**
     * This method add a Lugar object to the list
     *
     * @param id identifier of this place
     * @return Lugar a place in the list
     * @author Carlos Cano
     */

    public static Lugar elemento(int id) {


        //return vectorLugares.parallelStream().filter((l)->l.getId()==id).findFirst().get();

        for (Lugar lugar : vectorLugares) {

            if (lugar.getId() == id)
                return lugar;
        }

        return null;

    }

    /**
     * This method add a Lugar object to the list
     *
     * @param lugar this a place
     * @return void
     * @author Carlos Cano
     */

    public void anyade(Lugar lugar) {

        lugar.setId(siguienteId());
        vectorLugares.add(lugar);

    }

    public int nuevo() {
        Lugar lugar = new Lugar();
        lugar.setComentario("");
        lugar.setDireccion("");
        lugar.setValoracion(0);
        lugar.setNombre("");
        lugar.setTelefono(-1);
        lugar.setUrl("");
        anyade(lugar);
        return vectorLugares.size() - 1;
    }


    /**
     * This method delete a Lugar object to the list
     *
     * @param lugar the object of this a place
     * @return void
     * @author Carlos Cano
     */

    public void borrar(Lugar lugar) {
        vectorLugares.remove(lugar);
    }

    /**
     * This method delete a Lugar object to the list
     *
     * @return int
     * @author Carlos Cano
     */

    public int tamanyo() {
        return vectorLugares.size();
    }


    public List<Lugar> listaLugares() {

        return vectorLugares;
    }

    //@Override
    public Lugar getElemento(int pos) {
        return vectorLugares.get(pos);
    }


    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub

        if (iteratorCount < vectorLugares.size())
            return true;
        else {

            iteratorCount = 0;

            return false;
        }
    }

    @Override
    public Object next() {
        // TODO Auto-generated method stub
        return vectorLugares.get(iteratorCount);
    }


    /**
     * This method update a Lugar object to the list
     *
     * @param id    the identifier of this a place
     * @param lugar updated version of the place
     * @return void
     * @author Carlos Cano
     */


    public void actualiza(int id, Lugar lugar) {
        vectorLugares.set(id, lugar);
    }

    @NonNull
    public static ArrayList<Lugar> ejemploLugares() {
        ArrayList<Lugar> lugares = new ArrayList<Lugar>();


        lugares.add(new Lugar(0, "Escuela Politécnica Superior de Gandía",
                "C/ Paraninf 1, 46730 Gandía (SPAIN)", -0.166093, 38.995656,
                TipoLugar.EDUCACION, 962849300, "http://www.epsg.upv.es",
                "Uno de los mejores lugares para formarse", 2));

        lugares.add(new Lugar(1, "Al de siempre",
                "P. Industrial Junto Molí Nou -46722,benifal (Valencia)",
                0.190642, 38.925857, TipoLugar.BAR, 636472405, "",
                "No te pierdas el arroz en calabaza.", 3));

        lugares.add(new Lugar(2, "androidcurso.com",
                "ciberespacio", 0.0, 0.0, TipoLugar.EDUCACION,
                962849300, "http://androidcurso.com",
                "Amplia tus conocimientos sobre Android.", 5));

        lugares.add(new Lugar(3, "Barranco del Infierno",
                "Via verde del rio Serpis. Villalonga (Valencia)",
                -0.295058, 38.867180, TipoLugar.NATURALEZA, 0,
                "http://sosegaos.blogspot.com.es/2009/02/lorcha-villalonga-via-" +
                        "verde-del-rio.html", "Espectacular ruta por bici o andar", 4));
        lugares.add(new Lugar(4, "La Vital", "Avda. de la Vital, 0 46701 Gandía (Valencia)",
                -0.1720092, 38.9705949, TipoLugar.COMPRAS, 962881070,
                "http://www.lavital.es/", "El típico centro comercial", 2));


        return lugares;

    }

    //TODO AQUI ESTÁ EL ERROR, NO SE INICIALIZA BIEN EL ID
    private static int siguienteId() {

        if (vectorLugares == null)
            return ConstantesModelo.ID_INICIAL;
        else
            return maxId() + 1;


    }

    public static int maxId() {

        int maxId = 0;

        if (vectorLugares != null) {
            for (Lugar lugar : vectorLugares) {
                System.out.println(lugar);
                if (lugar.getId() > maxId)
                    maxId = lugar.getId();
            }
        }
        return maxId;
    }
}
