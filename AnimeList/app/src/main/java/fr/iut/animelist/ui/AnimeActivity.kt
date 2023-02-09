package fr.iut.animelist.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iut.animelist.R

class AnimeActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_ANIME_ID = "fr.iut.animelist.extra_anime_id"

        fun getIntent(context: Context, id: Int) =
            Intent(context, AnimeActivity::class.java).apply {
                putExtra(EXTRA_ANIME_ID, id)
            }
    }

    private var animeID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animeID = intent.getIntExtra(EXTRA_ANIME_ID, 1)
        AnimeFragment.newInstance(animeID)
        /*supportFragmentManager.beginTransaction()
            .replace(R.id.animeFragment, AnimeFragment.newInstance(animeID?:"0"))
            .commit()*/
        setContentView(R.layout.activity_anime)
//fragment manager . relation
    }


}