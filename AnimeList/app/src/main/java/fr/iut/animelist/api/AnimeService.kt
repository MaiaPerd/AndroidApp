package fr.iut.animelist.api

import fr.iut.animelist.data.Anime
import retrofit2.Call
import retrofit2.http.GET

interface AnimeService {

        @GET("anime")
        fun listRepos(): Call<List<Anime?>?>?

        @GET("anime/1")
        fun listOneAnimeRepos(): Call<Anime>
}