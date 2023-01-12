package fr.iut.animelist.api

import androidx.annotation.Nullable
import fr.iut.animelist.data.Anime
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.ref.WeakReference

public open class APICall {
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://kitsu.io/api/edge/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        // 2 - Public method to start fetching users following by Jake Wharton
        fun fetchUserFollowing(callbacks: Callbacks?, username: String?) {

            // 2.1 - Create a weak reference to callback (avoid memory leaks)
            val callbacksWeakReference: WeakReference<Callbacks> =
                WeakReference<Callbacks>(callbacks)

            // 2.2 - Get a Retrofit instance and the related endpoints
            val animeService: AnimeService =
                retrofit.create(AnimeService::class.java)

            // 2.3 - Create the call on Github API
            val call: Call<Anime> = animeService.listOneAnimeRepos()
            // 2.4 - Start the call
            call.enqueue(object : Callback<Anime> {
                override fun onResponse(
                    call: Call<Anime>,
                    response: Response<Anime>
                ) {
                    // 2.5 - Call the proper callback used in controller (MainFragment)
                    if (callbacksWeakReference.get() != null) callbacksWeakReference.get()!!
                        .onResponse(response.body())
                }

                override fun onFailure(call: Call<Anime>, t: Throwable) {
                    // 2.5 - Call the proper callback used in controller (MainFragment)
                    if (callbacksWeakReference.get() != null) callbacksWeakReference.get()!!
                        .onFailure()
                }
            })
        }

        // 1 - Creating a callback
        interface Callbacks {
            fun onResponse(@Nullable users: Anime?)
            fun onFailure()
        }
}