package com.example.lab4_20213849.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_20213849.Dtos.League;
import com.example.lab4_20213849.Dtos.Position;
import com.example.lab4_20213849.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListaPosicionesAdapter extends RecyclerView.Adapter<ListaPosicionesAdapter.PosicionViewHolder>{
    private List<Position> listaPosiciones;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Position> getListaPosiciones() {
        return listaPosiciones;
    }

    public void setListaPosiciones(List<Position> listaPosiciones) {
        this.listaPosiciones = listaPosiciones;
    }

    @NonNull
    @Override
    public PosicionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.elemento_posicion,parent,false);
        return new PosicionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosicionViewHolder holder, int pos) {
        Position posicion = listaPosiciones.get(pos);
        holder.position = posicion;

        TextView textViewNombre = holder.itemView.findViewById(R.id.nombreEquipo);
        textViewNombre.setText(posicion.getStrTeam());

        TextView textViewPosicion = holder.itemView.findViewById(R.id.posicionEquipo);
        textViewPosicion.setText(posicion.getIntRank());

        TextView textViewPartidos = holder.itemView.findViewById(R.id.victoriasEmpatesDerrotasEquipo);
        String nuevoTextoPartidos = "Victorias: " + posicion.getIntWin() + ", Empates: " + posicion.getIntDraw() + ", Derrotas: " + posicion.getIntLoss();
        textViewPartidos.setText(nuevoTextoPartidos);

        TextView textViewGoles = holder.itemView.findViewById(R.id.golesEquipo);
        String nuevoTextoGoles = "Goles anotados: " + posicion.getIntGoalsFor() + ", Goles concedidos: " + posicion.getIntGoalsAgainst() + ", Diferencia: " + posicion.getIntGoalDifference();
        textViewGoles.setText(nuevoTextoGoles);

        ImageView imagenEquipo = holder.itemView.findViewById(R.id.logoEquipo);
        Picasso.get()
                .load(posicion.getStrBadge())
                .into(imagenEquipo);
    }

    @Override
    public int getItemCount() {
        return listaPosiciones.size();
    }

    public class PosicionViewHolder extends RecyclerView.ViewHolder{
        Position position;

        public PosicionViewHolder(@NonNull View item){
            super(item);
        }
    }
}
