package com.example.lab4_20213849;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab4_20213849.Adapters.ListaEventosAdapter;
import com.example.lab4_20213849.Adapters.ListaPosicionesAdapter;
import com.example.lab4_20213849.Dtos.Event;
import com.example.lab4_20213849.Dtos.EventDto;
import com.example.lab4_20213849.Dtos.Position;
import com.example.lab4_20213849.Dtos.PositionDto;
import com.example.lab4_20213849.Services.TheSportsDbService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultadosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultadosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<Event[]> listaTotal = new ArrayList<>();

    public ArrayList<Event[]> getListaTotal() {
        return listaTotal;
    }

    public void setListaTotal(ArrayList<Event[]> listaTotal) {
        this.listaTotal = listaTotal;
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResultadosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultadosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultadosFragment newInstance(String param1, String param2) {
        ResultadosFragment fragment = new ResultadosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resultados, container, false);
        Button botonBuscar = view.findViewById(R.id.botonBuscarResultados);
        EditText idBusqueda = view.findViewById(R.id.idLigaResultados);
        EditText seasonBusqueda = view.findViewById(R.id.seasonResultados);
        EditText rondaBusqueda = view.findViewById(R.id.rondaResultados);
        botonBuscar.setOnClickListener(view1 -> {
            if(idBusqueda.getText().toString().trim().isEmpty() || seasonBusqueda.getText().toString().trim().isEmpty() || rondaBusqueda.getText().toString().trim().isEmpty()){
                if(idBusqueda.getText().toString().trim().isEmpty() && !seasonBusqueda.getText().toString().trim().isEmpty() && !rondaBusqueda.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(),"Debes llenar el campo de idLiga",Toast.LENGTH_LONG).show();
                }else if(!idBusqueda.getText().toString().trim().isEmpty() && seasonBusqueda.getText().toString().trim().isEmpty() && !rondaBusqueda.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(),"Debes llenar el campo de Season",Toast.LENGTH_LONG).show();
                }else if(!idBusqueda.getText().toString().trim().isEmpty() && !seasonBusqueda.getText().toString().trim().isEmpty() && rondaBusqueda.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(),"Debes llenar el campo de Ronda",Toast.LENGTH_LONG).show();
                }else if(idBusqueda.getText().toString().trim().isEmpty() && seasonBusqueda.getText().toString().trim().isEmpty() && !rondaBusqueda.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(),"Debes llenar el campo de idLiga y Season",Toast.LENGTH_LONG).show();
                }else if(!idBusqueda.getText().toString().trim().isEmpty() && seasonBusqueda.getText().toString().trim().isEmpty() && rondaBusqueda.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(),"Debes llenar el campo de Season y Ronda",Toast.LENGTH_LONG).show();
                }else if(idBusqueda.getText().toString().trim().isEmpty() && !seasonBusqueda.getText().toString().trim().isEmpty() && rondaBusqueda.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(),"Debes llenar el campo de idLiga y Ronda",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"Debes llenar los campos de idLiga, Season y Ronda",Toast.LENGTH_LONG).show();
                }
            }else{
                obtenerResultadosBusqueda(view,idBusqueda.getText().toString(),rondaBusqueda.getText().toString(),seasonBusqueda.getText().toString());
            }
        });
        return view;
    }

    public void obtenerResultadosBusqueda(View view,String idLiga, String ronda, String temporada){
        TheSportsDbService theSportsDbService = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheSportsDbService.class);

        theSportsDbService.listarEventos(idLiga,ronda,temporada).enqueue(new Callback<EventDto>() {
            @Override
            public void onResponse(Call<EventDto> call, Response<EventDto> response) {
                if(response.isSuccessful()){
                    EventDto eventDto = response.body();
                    Event[] listaEventos = eventDto.getEvents();

                    if(listaEventos==null){
                        Toast.makeText(getContext(),"Resultados no encontrados o datos inexistentes",Toast.LENGTH_LONG).show();
                    }else{
                        listaTotal.add(0,listaEventos);
                        ListaEventosAdapter adapter = new ListaEventosAdapter();
                        adapter.setContext(getContext());

                        List<Event> listaAMostrar = new ArrayList<>();

                        for(Event[] listEventos: listaTotal){
                            listaAMostrar.addAll(Arrays.asList(listEventos));
                        }

                        adapter.setListaEventos(listaAMostrar);

                        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewResultados);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }
                }else{
                    Toast.makeText(getContext(),"Resultados no encontrados o datos inexistentes",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EventDto> call, Throwable t) {
                Toast.makeText(getContext(),"Resultados no encontrados o datos inexistentes",Toast.LENGTH_LONG).show();
            }
        });
    }
}