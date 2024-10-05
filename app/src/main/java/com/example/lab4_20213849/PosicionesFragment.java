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
import com.example.lab4_20213849.Adapters.ListaPosicionesAdapter;
import com.example.lab4_20213849.Dtos.League;
import com.example.lab4_20213849.Dtos.LeagueDto;
import com.example.lab4_20213849.Dtos.Position;
import com.example.lab4_20213849.Dtos.PositionDto;
import com.example.lab4_20213849.Services.TheSportsDbService;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PosicionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PosicionesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PosicionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PosicionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PosicionesFragment newInstance(String param1, String param2) {
        PosicionesFragment fragment = new PosicionesFragment();
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
        View view = inflater.inflate(R.layout.fragment_posiciones, container, false);
        Button botonBuscar = view.findViewById(R.id.botonBuscarPosiciones);
        EditText idBusqueda = view.findViewById(R.id.idLigaPosiciones);
        EditText seasonBusqueda = view.findViewById(R.id.seasonPosiciones);
        botonBuscar.setOnClickListener(view1 -> {
            if(idBusqueda.getText().toString().trim().isEmpty() || seasonBusqueda.getText().toString().trim().isEmpty()){
                if(idBusqueda.getText().toString().trim().isEmpty() && !seasonBusqueda.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(),"Debes llenar el campo de idLiga",Toast.LENGTH_LONG).show();
                }else if(!idBusqueda.getText().toString().trim().isEmpty() && seasonBusqueda.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(),"Debes llenar el campo de Temporada",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"Debes llenar los campos de idLiga y Temporada",Toast.LENGTH_LONG).show();
                }
            }else{
                obtenerPosicionesBusqueda(view,idBusqueda.getText().toString(),seasonBusqueda.getText().toString());
            }
        });
        return view;
    }

    public void obtenerPosicionesBusqueda(View view,String idLiga,String temporada){
        TheSportsDbService theSportsDbService = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheSportsDbService.class);

        theSportsDbService.listarPosiciones(idLiga,temporada).enqueue(new Callback<PositionDto>() {
            @Override
            public void onResponse(Call<PositionDto> call, Response<PositionDto> response) {
                if(response.isSuccessful()){
                    Log.d("aviso","yei");
                    PositionDto positionDto = response.body();
                    Position[] listaPosiciones = positionDto.getTable();

                    if(listaPosiciones==null){
                        Toast.makeText(getContext(),"No se han encontrado resultados",Toast.LENGTH_LONG).show();
                    }else{
                        ListaPosicionesAdapter adapter = new ListaPosicionesAdapter();
                        adapter.setContext(getContext());
                        adapter.setListaPosiciones(Arrays.asList(listaPosiciones));

                        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPosiciones);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }
                }else{
                    Toast.makeText(getContext(),"No se han encontrado resultados",Toast.LENGTH_LONG).show();
                    Log.d("aviso","aaaa");
                }
            }

            @Override
            public void onFailure(Call<PositionDto> call, Throwable t) {
                Toast.makeText(getContext(),"Resultados no encontrados o datos inexistentes",Toast.LENGTH_LONG).show();
            }
        });
    }
}