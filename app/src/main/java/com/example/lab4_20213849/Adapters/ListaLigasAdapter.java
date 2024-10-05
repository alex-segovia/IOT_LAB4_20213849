package com.example.lab4_20213849.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_20213849.Dtos.League;
import com.example.lab4_20213849.R;

import java.util.List;

public class ListaLigasAdapter extends RecyclerView.Adapter<ListaLigasAdapter.LigaViewHolder>{
    private List<League> listaLigas;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<League> getListaLigas() {
        return listaLigas;
    }

    public void setListaLigas(List<League> listaLigas) {
        this.listaLigas = listaLigas;
    }

    @NonNull
    @Override
    public LigaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.elemento_liga,parent,false);
        return new LigaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LigaViewHolder holder, int position) {
        League liga = listaLigas.get(position);
        holder.liga = liga;

        TextView textViewNombre = holder.itemView.findViewById(R.id.nombreLiga);
        textViewNombre.setText(liga.getStrLeague());

        TextView textViewId = holder.itemView.findViewById(R.id.idLiga);
        textViewId.setText(liga.getIdLeague());

        LinearLayout cajita1 = holder.itemView.findViewById(R.id.cajitaAlternativo1);
        LinearLayout cajita2 = holder.itemView.findViewById(R.id.cajitaAlternativo2);
        LinearLayout cajita3 = holder.itemView.findViewById(R.id.cajitaAlternativo3);
        LinearLayout cajita4 = holder.itemView.findViewById(R.id.cajitaAlternativo4);
        LinearLayout cajita5 = holder.itemView.findViewById(R.id.cajitaAlternativo5);

        cajita1.setVisibility(View.VISIBLE);
        cajita2.setVisibility(View.VISIBLE);
        cajita3.setVisibility(View.VISIBLE);
        cajita4.setVisibility(View.VISIBLE);
        cajita5.setVisibility(View.VISIBLE);

        if(liga.getStrLeagueAlternate().trim().isEmpty()){
            cajita1.setVisibility(View.GONE);
            cajita2.setVisibility(View.GONE);
            cajita3.setVisibility(View.GONE);
            cajita4.setVisibility(View.GONE);
            cajita5.setVisibility(View.GONE);
        }else if(liga.getStrLeagueAlternate().split(",").length==1){
            TextView textViewAlternativo1 = holder.itemView.findViewById(R.id.nombreAlternativoLiga);
            textViewAlternativo1.setText(liga.getStrLeagueAlternate().split(",")[0]);
            cajita2.setVisibility(View.GONE);
            cajita3.setVisibility(View.GONE);
            cajita4.setVisibility(View.GONE);
            cajita5.setVisibility(View.GONE);
        }else if(liga.getStrLeagueAlternate().split(",").length==2){
            TextView textViewAlternativo1 = holder.itemView.findViewById(R.id.nombreAlternativoLiga);
            textViewAlternativo1.setText(liga.getStrLeagueAlternate().split(",")[0]);
            TextView textViewAlternativo2 = holder.itemView.findViewById(R.id.nombreAlternativo2Liga);
            textViewAlternativo2.setText(liga.getStrLeagueAlternate().split(",")[1].trim());
            cajita3.setVisibility(View.GONE);
            cajita4.setVisibility(View.GONE);
            cajita5.setVisibility(View.GONE);
        }else if(liga.getStrLeagueAlternate().split(",").length==3){
            TextView textViewAlternativo1 = holder.itemView.findViewById(R.id.nombreAlternativoLiga);
            textViewAlternativo1.setText(liga.getStrLeagueAlternate().split(",")[0]);
            TextView textViewAlternativo2 = holder.itemView.findViewById(R.id.nombreAlternativo2Liga);
            textViewAlternativo2.setText(liga.getStrLeagueAlternate().split(",")[1].trim());
            TextView textViewAlternativo3 = holder.itemView.findViewById(R.id.nombreAlternativo3Liga);
            textViewAlternativo3.setText(liga.getStrLeagueAlternate().split(",")[2]);
            cajita4.setVisibility(View.GONE);
            cajita5.setVisibility(View.GONE);
        }else if(liga.getStrLeagueAlternate().split(",").length==4){
            TextView textViewAlternativo1 = holder.itemView.findViewById(R.id.nombreAlternativoLiga);
            textViewAlternativo1.setText(liga.getStrLeagueAlternate().split(",")[0]);
            TextView textViewAlternativo2 = holder.itemView.findViewById(R.id.nombreAlternativo2Liga);
            textViewAlternativo2.setText(liga.getStrLeagueAlternate().split(",")[1].trim());
            TextView textViewAlternativo3 = holder.itemView.findViewById(R.id.nombreAlternativo3Liga);
            textViewAlternativo3.setText(liga.getStrLeagueAlternate().split(",")[2]);
            TextView textViewAlternativo4 = holder.itemView.findViewById(R.id.nombreAlternativo4Liga);
            textViewAlternativo4.setText(liga.getStrLeagueAlternate().split(",")[3]);
            cajita5.setVisibility(View.GONE);
        }else{
            TextView textViewAlternativo1 = holder.itemView.findViewById(R.id.nombreAlternativoLiga);
            textViewAlternativo1.setText(liga.getStrLeagueAlternate().split(",")[0]);
            TextView textViewAlternativo2 = holder.itemView.findViewById(R.id.nombreAlternativo2Liga);
            textViewAlternativo2.setText(liga.getStrLeagueAlternate().split(",")[1].trim());
            TextView textViewAlternativo3 = holder.itemView.findViewById(R.id.nombreAlternativo3Liga);
            textViewAlternativo3.setText(liga.getStrLeagueAlternate().split(",")[2]);
            TextView textViewAlternativo4 = holder.itemView.findViewById(R.id.nombreAlternativo4Liga);
            textViewAlternativo4.setText(liga.getStrLeagueAlternate().split(",")[3]);
            TextView textViewAlternativo5 = holder.itemView.findViewById(R.id.nombreAlternativo5Liga);
            textViewAlternativo5.setText(liga.getStrLeagueAlternate().split(",")[4]);
        }
    }

    @Override
    public int getItemCount() {
        return listaLigas.size();
    }

    public class LigaViewHolder extends RecyclerView.ViewHolder{
        League liga;

        public LigaViewHolder(@NonNull View item){
            super(item);
        }
    }
}
