package com.example.mislugares2022.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class LugaresBD extends SQLiteOpenHelper implements RepositorioLugares {

    SQLiteDatabase bd;

    public LugaresBD(Context context) {
        super(context, "lugares", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        bd.execSQL("CREATE TABLE lugares (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "direccion TEXT, " +
                "longitud REAL, " +
                "latitud REAL, " +
                "tipo INTEGER, " +
                "foto TEXT, " +
                "telefono INTEGER, " +
                "url TEXT, " +
                "comentario TEXT, " +
                "fecha BIGINT, " +
                "valoracion REAL)");
        llenarBD(bd);

    }

    private void llenarBD(SQLiteDatabase bd) {
        bd.execSQL("INSERT INTO lugares VALUES (null, " +
                "'Escuela Politécnica Superior de Gandía', " +
                "'C/ Paranimf, 1 46730 Gandia (SPAIN)', -0.166093, 38.995656, " +
                TipoLugar.EDUCACION.ordinal() + ", '', 962849300, " +
                "'http://www.epsg.upv.es', " +
                "'Uno de los mejores lugares para formarse.', " +
                System.currentTimeMillis() + ", 3.0)");
        bd.execSQL("INSERT INTO lugares VALUES (null, 'Al de siempre', " +
                "'P.Industrial Junto Molí Nou - 46722, Benifla (Valencia)', " +
                " -0.190642, 38.925857, " + TipoLugar.BAR.ordinal() + ", '', " +
                "636472405, '', " + "'No te pierdas el arroz en calabaza.', " +
                System.currentTimeMillis() + ", 3.0)");
        bd.execSQL("INSERT INTO lugares VALUES (null, 'androidcurso.com', " +
                "'ciberespacio', 0.0, 0.0," + TipoLugar.EDUCACION.ordinal() + ", '', " +
                "962849300, 'http://androidcurso.com', " +
                "'Amplia tus conocimientos sobre Android.', " +
                System.currentTimeMillis() + ", 5.0)");
        bd.execSQL("INSERT INTO lugares VALUES (null,'Barranco del Infierno'," +
                "'Vía Verde del río Serpis. Villalonga (Valencia)', -0.295058, " +
                "38.867180, " + TipoLugar.NATURALEZA.ordinal() + ", '', 0, " +
                "'http://sosegaos.blogspot.com.es/2009/02/lorcha-villalonga-via-verde-del-" +
                "rio.html', 'Espectacular ruta para bici o andar', " +
                System.currentTimeMillis() + ", 4.0)");
        bd.execSQL("INSERT INTO lugares VALUES (null, 'La Vital', " +
                "'Avda. La Vital,0 46701 Gandia (Valencia)',-0.1720092,38.9705949," +
                TipoLugar.COMPRAS.ordinal() + ", '', 962881070, " +
                "'http://www.lavital.es', 'El típico centro comercial', " +
                System.currentTimeMillis() + ", 2.0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void anyade(Lugar lugar) {
        /*Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT"
        );*/
    }

    @Override
    public int nuevo() {
        int _id = -1;
        Lugar lugar = new Lugar();
        getWritableDatabase().execSQL("INSERT INTO lugares (nombre, " +
                "direccion, longitud, latitud, tipo, foto, telefono, url, " +
                "comentario, fecha, valoracion) VALUES ('', '',  " +
                lugar.getPosicion().getLongitud() + "," +
                lugar.getPosicion().getLatitud() + ", " + lugar.getTipo().ordinal() +
                ", '', 0, '', '', " + lugar.getFecha() + ", 0)");
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT _id FROM lugares WHERE fecha = " + lugar.getFecha(), null);
        if (c.moveToNext()) _id = c.getInt(0);
        c.close();
        return _id;
    }

    @Override
    public void borrar(Lugar lugar) {

    }

    @Override
    public int tamanyo() {
        return 0;
    }

    @Override
    public void actualiza(int id, Lugar lugar) {

    }

    @Override
    public List<Lugar> listaLugares() {
        return null;
    }


}
