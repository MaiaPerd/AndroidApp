package fr.iut.animelist.ui

import android.os.Bundle
import fr.iut.animelist.R

class AnimeListActivity : SimpleFragmentActivity(), AnimeListFragment.OnInteractionListener {

    private var isTwoPane: Boolean = false

    private lateinit var masterFragment: AnimeListFragment


    override fun createFragment() = AnimeListFragment().also { masterFragment = it }
    override fun getLayoutResId() = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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