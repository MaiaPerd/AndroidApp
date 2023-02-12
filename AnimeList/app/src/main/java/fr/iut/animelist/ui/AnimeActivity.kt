package fr.iut.animelist.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import fr.iut.animelist.R

class AnimeActivity : SimpleFragmentActivity() {

    companion object {
        private const val EXTRA_ANIME_ID = "fr.iut.animelist.extra_anime_id"

        fun getIntent(context: Context, id: Int) =
            Intent(context, AnimeActivity::class.java).apply {
                putExtra(EXTRA_ANIME_ID, id)
            }
    }

    private var animeID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        animeID = intent.getIntExtra(EXTRA_ANIME_ID, 1)
        super.onCreate(savedInstanceState)
        AnimeFragment.newInstance(animeID)

        setContentView(R.layout.activity_anime)
    }

    override fun createFragment() = AnimeFragment.newInstance(animeID)
    override fun getLayoutResId() = R.layout.activity_anime


}