package com.example.mislugares2022.modelo;

import java.io.Serializable;

/**
 * This Class implements place, locations.
 *
 * @author Carlos@Cano
 * @version 1.1
 * @since 1.0
 */
public class Lugar implements Serializable {


    private static final long serialVersionUID = -4898561824002428353L;


    //private int id = CasosUsoLugar.ID_LUGAR_NUEVO;
    private int id = -1 /*ConstantesModelo.ID_INICIAL*/;
    private String nombre = "";

    private String direccion = "";


    private TipoLugar tipo = TipoLugar.OTROS;
    private GeoPunto posicion = GeoPunto.getPuntoSinPosicion();
    private String foto = "";

    private long telefono = -1;
    private String url = "";
    private String comentario = "";
    private long fecha = -1;
    private float valoracion = 0;


    public Lugar(int id, String nombre, String direccion, double longitud, double latitud, TipoLugar tipo, int telefono,
                 String url, String comentario, int valoracion) {
        fecha = System.currentTimeMillis();
        posicion = new GeoPunto(longitud, latitud);
        this.id = id;
        this.tipo = tipo;
        this.foto = ConstantesModelo.FOTO_INICIAL;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.url = url;
        this.comentario = comentario;
        this.valoracion = valoracion;
    }

    public Lugar() {
        fecha = System.currentTimeMillis();
        posicion = ConstantesModelo.SIN_POSICION;
        tipo = TipoLugar.OTROS;
    }


    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    /**
     * This method returns an object TipoLugar
     *
     * @return TipoLugar the kind o Lugar
     */

    public TipoLugar getTipo() {
        return tipo;
    }

    /**
     * This method changes/sets the kind of place
     *
     * @param tipo kind of place
     * @return void
     * @author Carlos Cano
     */

    public void setTipo(TipoLugar tipo) {
        this.tipo = tipo;
    }

    /**
     * This method returns the place name
     *
     * @return String the object Lugar name
     */
    public String getNombre() {
        return nombre;
    }


    /**
     * This method changes/sets the name of the place
     *
     * @param nombre Name of the place
     * @return void
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * This method returns an Address
     *
     * @return String the object Lugar address
     */

    public GeoPunto getPosicion() {
        return posicion;
    }

    public void setPosicion(GeoPunto posicion) {
        this.posicion = posicion;
    }

    public String getDireccion() {
        return direccion;
    }

    /**
     * This method changes/sets the address of the place
     *
     * @param direccion Place Address
     *                  *
     * @return void
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * This method returns a phone number
     *
     * @return int telephone number
     */

    public long getTelefono() {
        return telefono;
    }

    /**
     * This method changes/sets the address of the place
     *
     * @param telefono telephone number
     *                 *
     * @return void
     * @author Carlos Cano
     */

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    /**
     * This method returns the photo url from this place
     *
     * @return String the photo url
     * @author Carlos Cano
     */
    public String getFoto() {
        return foto;
    }

    /**
     * This method changes/sets the photo from the place
     *
     * @param foto Address
     *             *
     * @return void
     * @author Carlos Cano
     */

    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * This method return the Lugar (place) website address
     *
     * @return String the website address of this Lugar
     */

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method return Lugar comments
     *
     * @return String comments from this Lugar
     */

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * This method return lsat visit date to this Lugar
     *
     * @return long last visit date to the place
     */

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    /**
     * This method return the rate for this Lugar
     *
     * @return float the rate for the place
     */

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }


    @Override
    public String toString() {
        return "Lugar{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", posicion=" + posicion +
                ", tipo=" + tipo +
                ", foto='" + foto + '\'' +
                ", telefono=" + telefono +
                ", url='" + url + '\'' +
                ", comentario='" + comentario + '\'' +
                ", fecha=" + fecha +
                ", valoracion=" + valoracion +
                '}';
    }


}




