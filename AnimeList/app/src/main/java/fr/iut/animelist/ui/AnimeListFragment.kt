package fr.iut.animelist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.iut.animelist.R
import fr.iut.animelist.data.Repository.AnimeRepository
import fr.iut.animelist.model.Anime
import fr.iut.animelist.viewmodel.AnimeViewModel
import fr.iut.animelist.viewmodel.AnimeViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

class AnimeListFragment : Fragment()  {

    private val scope = CoroutineScope(Job())
    private lateinit var repository: AnimeRepository
    private lateinit var animeViewModel: AnimeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = AnimeRepository(fr.iut.animelist.data.persistence.AnimeDataBase.getDatabase(requireContext(), scope).animeDao())
        animeViewModel = ViewModelProvider(this, AnimeViewModelFactory(repository)).get()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var  view = inflater.inflate(R.layout.fragment_list_anime, container, false)
        val recyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        val adapter = AdaptateurAnimeList()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        animeViewModel.insert(Anime("111","Test"))
        animeViewModel.allAnimes.observe(viewLifecycleOwner) { anime ->
            anime.let { adapter.submitList(it) }
        }


        //var chien: Chien = MainActivity().liste[position?: 0]
        //view.findViewById<EditText>(R.id.inputNom).setText(chien.nom)
        //view.findViewById<EditText>(R.id.inputRace).setText(chien.race)
        //view.findViewById<EditText>(R.id.inputPoids).setText(chien.messure)
        // view.findViewById<RatingBar>(R.id.ratingBar).numStars(chien.agressivite)

 /*
        ArrayAdapter.createFromResource(
            view.context,
            R.array.genre_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }*/

        return view
    }

}