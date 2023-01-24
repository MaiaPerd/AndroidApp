package fr.iut.animelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iut.animelist.api.APICall
import fr.iut.animelist.model.Anime
import fr.iut.animelist.model.Information

class MainActivity : AppCompatActivity() {

    var listAnime: ArrayList<Anime> = ArrayList()

    init {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        APICall().getAnime(1)?.let { listAnime.add(it) }
        listAnime.add(Anime("a","a", Information("a","a")))
    }

}