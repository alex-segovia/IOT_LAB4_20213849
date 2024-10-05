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

import com.example.lab4_20213849.Adapters.ListaLigasAdapter;
import com.example.lab4_20213849.Dtos.League;
import com.example.lab4_20213849.Dtos.LeagueDto;
import com.example.lab4_20213849.Services.TheSportsDbService;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LigasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LigasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LigasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LigasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LigasFragment newInstance(String param1, String param2) {
        LigasFragment fragment = new LigasFragment();
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
        View view = inflater.inflate(R.layout.fragment_ligas, container, false);
        Button botonBuscar = view.findViewById(R.id.botonBuscarLigas);
        EditText textoBusqueda = view.findViewById(R.id.buscarPorPaisLigas);
        botonBuscar.setOnClickListener(view1 -> {
            if(textoBusqueda.getText().toString().isEmpty()){
                obtenerLigas(view);
            }else{
                obtenerLigasBusqueda(view,textoBusqueda.getText().toString());
            }
        });
        return view;
    }

    public void obtenerLigas(View view){
        TheSportsDbService theSportsDbService = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheSportsDbService.class);

        theSportsDbService.listarLigas().enqueue(new Callback<LeagueDto>() {
            @Override
            public void onResponse(Call<LeagueDto> call, Response<LeagueDto> response) {
                if(response.isSuccessful()){
                    LeagueDto ligaDto = response.body();
                    League[] listaLigas = ligaDto.getLeagues();

                    ListaLigasAdapter adapter = new ListaLigasAdapter();
                    adapter.setContext(getContext());
                    adapter.setListaLigas(Arrays.asList(listaLigas));

                    RecyclerView recyclerView = view.findViewById(R.id.recyclerViewLigas);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }

            @Override
            public void onFailure(Call<LeagueDto> call, Throwable t) {

            }
        });
    }

    public void obtenerLigasBusqueda(View view,String textoBusqueda){
        TheSportsDbService theSportsDbService = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheSportsDbService.class);

        theSportsDbService.listarLigasBusqueda(textoBusqueda).enqueue(new Callback<LeagueDto>() {
            @Override
            public void onResponse(Call<LeagueDto> call, Response<LeagueDto> response) {
                if(response.isSuccessful()){
                    LeagueDto ligaDto = response.body();
                    League[] listaLigas = ligaDto.getCountries();

                    if(listaLigas==null){
                        Toast.makeText(getContext(),"No se han encontrado resultados",Toast.LENGTH_LONG).show();
                    }else{
                        ListaLigasAdapter adapter = new ListaLigasAdapter();
                        adapter.setContext(getContext());
                        adapter.setListaLigas(Arrays.asList(listaLigas));

                        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewLigas);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }
                }
            }

            @Override
            public void onFailure(Call<LeagueDto> call, Throwable t) {

            }
        });
    }
}