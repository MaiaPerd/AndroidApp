package fr.iut.animelist.api

import fr.iut.animelist.model.Anime
import retrofit2.Call
import retrofit2.http.GET

interface AnimeService {

        @GET("anime")
        fun listAnimes(): Call<List<Anime>>

        @GET("anime/1")
        fun listOneAnime(): Call<Anime>

        @GET("anime")
        fun getAnime(): List<Anime>
}