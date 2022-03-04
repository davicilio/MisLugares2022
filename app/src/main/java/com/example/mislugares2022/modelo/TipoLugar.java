package com.example.mislugares2022.modelo;


import java.io.Serializable;

public enum TipoLugar implements Serializable {
    OTROS("Otros", 1),
    RESTAURANTE("Restaurante", 2),
    BAR("Bar", 3),
    COPAS("Copas", 4),
    ESPECTACULO("Espectáculo", 5),
    HOTEL("Hotel", 6),
    COMPRAS("Compras", 7),
    EDUCACION("Educación", 8),
    DEPORTE("Deporte", 9),
    NATURALEZA("Naturaleza", 10),
    GASOLINERA("Gasolinera", 11);


    private final String texto;
    private final int recurso;


    TipoLugar(String texto, int recurso) {
        this.texto = texto;
        this.recurso = recurso;
    }

    public static TipoLugar getTipoPorNombre(String nombre) {
        TipoLugar tipoRes = null;

        for (TipoLugar tipo : TipoLugar.values()) {
            if (tipo.getTexto().contains(nombre)) {
                tipoRes = tipo;
                break;
            }
        }

        return tipoRes;

    }

    public String getTexto() {
        return texto;
    }

    public int getRecurso() {
        return recurso;
    }

    @Override
    public String toString() {
        return "TipoLugar{" +
                "texto='" + texto + '\'' +
                ", recurso=" + recurso +
                '}';
    }

    public static String[] getNombres() {
        String[] resultado = new String[TipoLugar.values().length];
        for (TipoLugar tipo : TipoLugar.values()) {
            resultado[tipo.ordinal()] = tipo.texto;
        }
        return resultado;
    }
}
