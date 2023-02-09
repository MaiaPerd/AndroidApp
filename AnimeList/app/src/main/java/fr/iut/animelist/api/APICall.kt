package fr.iut.animelist.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.iut.animelist.model.Anime
import fr.iut.animelist.model.Genres
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APICall {


    fun getAnime(id: Int): LiveData<Anime>? {
        val anime = RetrofitClient().getClient()?.getAnimeById(id)
        val liveData = MutableLiveData<Anime>()

        anime?.enqueue(object : Callback<Anime> {
            override fun onResponse(call: Call<Anime>, response: Response<Anime>) {
                liveData.value = response.body()
                Log.e("Service", "The call is back with success")
            }

            override fun onFailure(call: Call<Anime>, t: Throwable) {
                Log.e("Service", "Not call success")
            }
        })
        return liveData
    }

    fun getAnimeGenres(genres: String): LiveData<Array<Anime>>? {
        val anime = RetrofitClient().getClient()?.listAnimesGenres(genres)
        val liveData = MutableLiveData<Array<Anime>>()

        anime?.enqueue(object : Callback<Array<Anime>> {
            override fun onResponse(call: Call<Array<Anime>>, response: Response<Array<Anime>>) {
                liveData.value = response.body()
                Log.e("Service", "The call is back with success")
            }

            override fun onFailure(call: Call<Array<Anime>>, t: Throwable) {
                Log.e("Service", "Not call success")
            }
        })
        return liveData
    }

    fun getAnimes(): LiveData<Array<Anime>>? {
        val anime = RetrofitClient().getClient()?.listAnimes()
        val liveData = MutableLiveData<Array<Anime>>()

        anime?.enqueue(object : Callback<Array<Anime>> {
            override fun onResponse(call: Call<Array<Anime>>, response: Response<Array<Anime>>) {
                liveData.value = response.body()
                Log.e("Service", "The call is back with success")
            }

            override fun onFailure(call: Call<Array<Anime>>, t: Throwable) {
                Log.e("Service", "Not call success")
            }
        })
        return liveData
    }

    fun getGenres(): LiveData<Array<Genres>>? {
        val anime = RetrofitClient().getClient()?.listGenres()
        val liveData = MutableLiveData<Array<Genres>>()

        anime?.enqueue(object : Callback<Array<Genres>> {
            override fun onResponse(call: Call<Array<Genres>>, response: Response<Array<Genres>>) {
                liveData.value = response.body()
                Log.e("Service", "The call is back with success")
            }

            override fun onFailure(call: Call<Array<Genres>>, t: Throwable) {
                Log.e("Service", "Not call success")
            }
        })
        return liveData
    }

    init {
    }

}

