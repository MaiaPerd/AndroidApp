package fr.iut.animelist.api

import android.util.Log
import fr.iut.animelist.model.Anime
import fr.iut.animelist.ui.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APICall {



        fun getAnime(id: Int): Anime?{
            val anime = RetrofitClient().getClient()?.getAnimeById(id)
            var res: Anime? = null
            anime?.enqueue(object: Callback<Anime> {
                override fun onResponse(call: Call<Anime>, response: Response<Anime>) {
                    res = response.body() //LiveData
                    Log.e("Service", "The call is back with success")
                }
                override fun onFailure(call: Call<Anime>, t: Throwable) {
                    Log.e("Service", "Not call success")
                }
            })
            return res
        }

        init {

/*
            RetrofitClient().getClient()?.listAnimes()?.enqueue(object : Callback<List<Anime>>{
                override fun onResponse(call: Call<List<Anime>>, response: Response<List<Anime>>) {
                    var res: List<Anime>? = response.body()
                    Log.e("Service", "The call is back with success ALL")
                }

                override fun onFailure(call: Call<List<Anime>>, t: Throwable) {
                    Log.e("Service", "Not call success ALL");
                }

            })*/

        }
/*
     var anime = RetrofitClient().getClient()?.listRepos()

     init {
         // So you need to make an async call
         anime?.enqueue(object: Callback<List<Anime>> {

             override fun onResponse(call: Call<List<Anime>>, response: Response<List<Anime>>) {
                 var res = response.body()
                 res?.get(0)
                 Log.e("BusinessService", "The call is back with success")

             }

             override fun onFailure(call: Call<List<Anime>>, t: Throwable) {
                 Log.e("BusinessService", "Nottttttttt call success");

             }
         })

     }*/

    }

