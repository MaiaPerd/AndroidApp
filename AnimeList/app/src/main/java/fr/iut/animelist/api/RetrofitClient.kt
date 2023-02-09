package fr.iut.animelist.api

import com.google.gson.*
import fr.iut.animelist.model.Anime
import fr.iut.animelist.model.Genres
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


class RetrofitClient {


    fun getClient(): AnimeService? {
        val gson: Gson? = GsonBuilder()
            .registerTypeAdapter(Anime::class.java, AnimeDeserializer())
            .registerTypeAdapter(Array<Anime>::class.java, AnimeListDeserializer())
            .registerTypeAdapter(Genres::class.java, GenreDeserializer())
            .registerTypeAdapter(Array<Genres>::class.java, GenreListDeserializer())
            .create()
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://kitsu.io/api/edge/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(AnimeService::class.java)
    }


}

class AnimeDeserializer : JsonDeserializer<Anime> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Anime {
        val a = json?.asJsonObject?.get("data")
        return Gson().fromJson(a, Anime::class.java)
    }
}

class AnimeListDeserializer : JsonDeserializer<Array<Anime>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Array<Anime> {
        val a = json?.asJsonObject?.get("data")
        return Gson().fromJson(a, Array<Anime>::class.java)
    }
}

class GenreDeserializer : JsonDeserializer<Genres> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Genres {
        val a = json?.asJsonObject?.get("data")
        return Gson().fromJson(a, Genres::class.java)
    }
}

class GenreListDeserializer : JsonDeserializer<Array<Genres>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Array<Genres> {
        val a = json?.asJsonObject?.get("data")
        return Gson().fromJson(a, Array<Genres>::class.java)
    }
}