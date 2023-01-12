package fr.iut.animelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iut.animelist.api.APICall
import fr.iut.animelist.ui.RetrofitClient

class MainActivity : AppCompatActivity(), APICall() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofit=RetrofitClient()
        retrofit.getAnime()
    }
}