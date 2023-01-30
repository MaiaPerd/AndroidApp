package fr.iut.animelist.api

import fr.iut.animelist.model.Anime
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface AnimeService {

        @GET("anime")
        fun listAnimes(): Call<List<Anime>>

        @GET("anime/1")
        fun listOneAnime(): Call<Anime>

        @GET("anime")
        fun getAnime(): List<Anime>

        @GET("anime/{id}")
        fun getAnimeById(@Path("id") id: Int): Call<Anime>
}