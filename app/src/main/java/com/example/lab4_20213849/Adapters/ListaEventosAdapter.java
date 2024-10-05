package com.example.lab4_20213849.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_20213849.Dtos.Event;
import com.example.lab4_20213849.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListaEventosAdapter extends RecyclerView.Adapter<ListaEventosAdapter.EventoViewHolder> {
    private List<Event> listaEventos;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Event> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Event> listaEventos) {
        this.listaEventos = listaEventos;
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.elemento_resultado,parent,false);
        return new ListaEventosAdapter.EventoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        Event event = listaEventos.get(position);
        holder.event = event;

        TextView textViewNombre = holder.itemView.findViewById(R.id.nombreResultado);
        textViewNombre.setText(event.getStrEvent());

        TextView textViewRonda = holder.itemView.findViewById(R.id.rondaResultado);
        textViewRonda.setText(event.getIntRound());

        TextView textViewLocalTeam = holder.itemView.findViewById(R.id.equipoLocalResultado);
        textViewLocalTeam.setText(event.getStrHomeTeam());

        TextView textViewAwayTeam = holder.itemView.findViewById(R.id.equipoVisitanteResultado);
        textViewAwayTeam.setText(event.getStrAwayTeam());

        TextView textViewResultado = holder.itemView.findViewById(R.id.resultadoResultado);
        String nuevoTextoResultado = event.getStrHomeTeam() + " " + event.getIntHomeScore() + " - " + event.getStrAwayTeam() + " " + event.getIntAwayScore();
        textViewResultado.setText(nuevoTextoResultado);

        TextView textViewFecha = holder.itemView.findViewById(R.id.fechaResultado);
        textViewFecha.setText(event.getDateEvent());

        TextView textViewEspectadores = holder.itemView.findViewById(R.id.espectadoresResultado);
        String nuevoTextoEspectadores = event.getIntSpectators()==null?"0":event.getIntSpectators();
        textViewEspectadores.setText(nuevoTextoEspectadores);

        ImageView imagenCompetencia = holder.itemView.findViewById(R.id.logoResultado);
        Picasso.get()
                .load(event.getStrLeagueBadge())
                .into(imagenCompetencia);
    }

    @Override
    public int getItemCount() {
        return listaEventos.size();
    }

    public class EventoViewHolder extends RecyclerView.ViewHolder{
        Event event;

        public EventoViewHolder(@NonNull View item){
            super(item);
        }
    }
}
