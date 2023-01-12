package fr.iut.animelist.ui

import fr.iut.animelist.api.AnimeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://kitsu.io/api/edge/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: AnimeService = retrofit.create(AnimeService::class.java)

    fun getAnime(){
        var anime = service.listOneAnimeRepos()
      //  anime.await()
       // var a = anime.awaitResponse()

    }
}

