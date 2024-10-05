package com.example.lab4_20213849.Services;

import com.example.lab4_20213849.Dtos.EventDto;
import com.example.lab4_20213849.Dtos.LeagueDto;
import com.example.lab4_20213849.Dtos.PositionDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheSportsDbService {
    @GET("/api/v1/json/3/all_leagues.php")
    Call<LeagueDto> listarLigas();

    @GET("/api/v1/json/3/search_all_leagues.php")
    Call<LeagueDto> listarLigasBusqueda(@Query("c") String country);

    @GET("/api/v1/json/3/lookuptable.php")
    Call<PositionDto> listarPosiciones(@Query("l") String idLiga,
                                       @Query("s") String temporada);

    @GET("/api/v1/json/3/eventsround.php")
    Call<EventDto> listarEventos(@Query("id") String idLiga,
                                 @Query("r") String ronda,
                                 @Query("s") String temporada);
}
