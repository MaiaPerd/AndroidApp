package fr.iut.animelist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import fr.iut.animelist.R
import fr.iut.animelist.api.APICall

class AnimeFragment : Fragment() {

    private var id: Int? = null

    companion object {
        private const val EXTRA_ANIME_ID = "fr.iut.animelist.extra_anime_id"
        fun newInstance(id: Int) = AnimeFragment().apply {
            arguments = bundleOf(EXTRA_ANIME_ID to id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = (savedInstanceState?.getInt(EXTRA_ANIME_ID) ?: arguments?.getInt(EXTRA_ANIME_ID))

    }


    lateinit var nameAnime: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_anime, container, false)
        updateViewFromCurrentDog()
        nameAnime = view.findViewById<TextView>(R.id.txtNom)


        return view
    }

    private fun updateViewFromCurrentDog() {

        APICall().getAnime(id ?: 1)?.observe(viewLifecycleOwner) { anime ->
            nameAnime.text = anime.info?.titre
        }
    }

}
