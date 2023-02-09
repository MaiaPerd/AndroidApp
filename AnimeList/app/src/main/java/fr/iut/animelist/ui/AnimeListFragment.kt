package fr.iut.animelist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.iut.animelist.R
import fr.iut.animelist.api.APICall
import fr.iut.animelist.data.Repository.AnimeRepository
import fr.iut.animelist.viewmodel.AnimeListViewModel
import fr.iut.animelist.viewmodel.AnimeListViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

class AnimeListFragment : Fragment(), AdaptateurAnimeList.Callbacks,
    AdapterView.OnItemSelectedListener {

    private val scope = CoroutineScope(Job())
    private lateinit var repository: AnimeRepository
    private lateinit var animeListViewModel: AnimeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = AnimeRepository(
            fr.iut.animelist.data.persistence.AnimeDataBase.getDatabase(
                requireContext(), scope
            ).animeDao()
        )
        animeListViewModel = ViewModelProvider(this, AnimeListViewModelFactory(repository)).get()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_list_anime, container, false)
        val recyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        val adapter = AdaptateurAnimeList(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        animeListViewModel.allAnimes.observe(viewLifecycleOwner) { anime ->
            anime.let { adapter.submitList(it) }
        }

        val spinner: Spinner = view.findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            view.context, R.array.category_array, android.R.layout.simple_spinner_item
        ).also { ad ->
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = ad
        }
        spinner.onItemSelectedListener = this

        APICall().getGenres()?.observe(viewLifecycleOwner) { list ->
            var arrayList = list.map { l -> l.info?.name }
            val arrayAdapter =
                ArrayAdapter(view.context, android.R.layout.simple_spinner_item, arrayList)
            spinner.adapter = arrayAdapter
        }

        APICall().getAnimes()?.observe(viewLifecycleOwner) { list ->
            for (item in list) animeListViewModel.insert(item)
        }



        return view
    }

    override fun onAnimeSelected(id: Int) {
        listener?.onAnimeSelected(id)
    }

    private var listener: OnInteractionListener? = null

    interface OnInteractionListener {
        fun onAnimeSelected(id: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (id == 0L) {

            animeListViewModel.clear()
            APICall().getAnimes()?.observe(viewLifecycleOwner) { list ->
                for (item in list) animeListViewModel.insert(item)
            }
        }

        if (id == 1L) {
            animeListViewModel.clear() // peut apparament ce faire en une ligne avec les live data grace a un filter / switch map sur la livedata
            APICall().getAnimeGenres("")?.observe(viewLifecycleOwner) { list ->
                for (item in list) animeListViewModel.insert(item)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        APICall().getAnimes()?.observe(viewLifecycleOwner) { list ->
            for (item in list) animeListViewModel.insert(item)
        }
    }

}


// faire un bouton refresh avec un rechargement en local