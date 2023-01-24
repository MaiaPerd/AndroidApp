package fr.iut.animelist.ui

import com.google.gson.*
import fr.iut.animelist.api.AnimeService
import fr.iut.animelist.model.Anime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


class RetrofitClient {



    fun getClient(): AnimeService? {
        val gson: Gson? = GsonBuilder()
            .registerTypeAdapter(Anime::class.java, AnimeDeserializer())
            .create()
        //Using Default HttpClient
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://kitsu.io/api/edge/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(AnimeService::class.java)
    }


/*
    fun getAnime(){
     var animeRequete = service.listRepos()
        animeRequete?.enqueue(object: Callback<List<Anime?>?>{

            override fun onResponse(call: Call<List<Anime?>?>, response: Response<List<Anime?>?>) {
                val anime = response.body()
                if (anime != null) {
                    println(anime[0]?.name)
                }
            }

            override fun onFailure(call: Call<List<Anime?>?>, t: Throwable) {
                error("KO")
            }
        })

    }*/




}

class AnimeDeserializer : JsonDeserializer<Anime>{
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Anime {
        val a = json?.asJsonObject?.get("data")
        return Gson().fromJson(a, Anime::class.java)
    }
}