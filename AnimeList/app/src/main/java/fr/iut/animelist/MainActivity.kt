package fr.iut.animelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iut.animelist.ui.AnimeActivity
import fr.iut.animelist.ui.AnimeFragment
import fr.iut.animelist.ui.AnimeListFragment

class MainActivity : AppCompatActivity(), AnimeListFragment.OnInteractionListener {

    private var isTwoPane: Boolean = false

    private lateinit var masterFragment: AnimeListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setIcon(R.mipmap.ic_launcher)

        isTwoPane = supportFragmentManager.findFragmentById(R.id.container_fragment_detail) != null
        if (savedInstanceState != null)
            masterFragment =
                supportFragmentManager.findFragmentById(R.id.container_fragment) as AnimeListFragment

        if (!isTwoPane) {
            removeDisplayedFragment()
        }


    }

    override fun onAnimeSelected(id: Int) {
        if (isTwoPane) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_fragment_detail, AnimeFragment.newInstance(id))
                .commit()
        } else {
            startActivity(AnimeActivity.getIntent(this, id))
        }
    }

    private fun removeDisplayedFragment() {
        supportFragmentManager.findFragmentById(R.id.container_fragment_detail)?.let {
            supportFragmentManager.beginTransaction().remove(it).commit()
        }
    }

}