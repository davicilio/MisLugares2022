package com.example.mislugares2022.adaptadores;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mislugares2022.R;
import com.example.mislugares2022.aplicacion.Aplicacion;
import com.example.mislugares2022.modelo.GeoPunto;
import com.example.mislugares2022.modelo.Lugar;
import com.example.mislugares2022.modelo.LugaresVector;
import com.example.mislugares2022.modelo.RepositorioLugares;

public class AdaptadorLugares extends
        RecyclerView.Adapter<AdaptadorLugares.ViewHolder> {
    protected LugaresVector lugares;
    protected LayoutInflater inflador;
    protected View.OnClickListener onClickListener;


    // Lista de lugares a mostrar
    public AdaptadorLugares(LugaresVector lugares, Context contexto) {
        this.lugares = lugares;
        inflador = (LayoutInflater) contexto
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, direccion;
        public ImageView foto;
        public RatingBar valoracion;
        public TextView distancia;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            direccion = itemView.findViewById(R.id.direccion);
            foto = itemView.findViewById(R.id.foto);
            valoracion = itemView.findViewById(R.id.valoracion);
            distancia = itemView.findViewById(R.id.distancia);
        }

        // Personalizamos un ViewHolder a partir de un lugar
        public void personaliza(ViewHolder holder, Lugar lugar) {
            holder.nombre.setText(lugar.getNombre());
            holder.direccion.setText(lugar.getDireccion());
            int id = R.drawable.otros;
            switch (lugar.getTipo()) {
                case RESTAURANTE:
                    id = R.drawable.restaurante;
                    break;
                case BAR:
                    id = R.drawable.bar;
                    break;
                case COPAS:
                    id = R.drawable.copas;
                    break;
                case ESPECTACULO:
                    id = R.drawable.espectaculos;
                    break;
                case HOTEL:
                    id = R.drawable.hotel;
                    break;
                case COMPRAS:
                    id = R.drawable.compras;
                    break;
                case EDUCACION:
                    id = R.drawable.educacion;
                    break;
                case DEPORTE:
                    id = R.drawable.deporte;
                    break;
                case NATURALEZA:
                    id = R.drawable.naturaleza;
                    break;
                case GASOLINERA:
                    id = R.drawable.gasolinera;
                    break;
            }
            holder.foto.setImageResource(id);
            holder.foto.setScaleType(ImageView.ScaleType.FIT_END);
            holder.valoracion.setRating(lugar.getValoracion());

            GeoPunto pos = ((Aplicacion) itemView.getContext().getApplicationContext())
                    .posicionActual;
            if (pos.equals(GeoPunto.SIN_POSICION) ||
                    lugar.getPosicion().equals(GeoPunto.SIN_POSICION)) {
                distancia.setText("... Km");
            } else {
                int d = (int) pos.distancia(lugar.getPosicion());
                if (d < 2000) distancia.setText(d + " m");
                else distancia.setText(d / 1000 + " Km");
            }

        }
    }

    @NonNull
    // Creamos el ViewHolder con la vista de un elemento sin personalizar
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos la vista desde el xml
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elemento_lista, parent, false);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }

    // Indicamos el número de elementos de la lista
    @Override
    public int getItemCount() {
        return lugares.tamanyo();
    }

    // Usando como base el ViewHolder y lo personalizamos
    @Override
    public void onBindViewHolder(ViewHolder holder, int posicion) {
        Lugar lugar = RepositorioLugares.getElementoPorPosicion(posicion);
        holder.personaliza(holder, lugar);
    }


}

