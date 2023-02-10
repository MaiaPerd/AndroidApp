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
import fr.iut.animelist.R
import fr.iut.animelist.api.APICall
import fr.iut.animelist.data.Repository.AnimeRepository
import fr.iut.animelist.databinding.FragmentListAnimeBinding
import fr.iut.animelist.viewmodel.AnimeListViewModel
import fr.iut.animelist.viewmodel.AnimeListViewModelFactory

class AnimeListFragment : Fragment(), AdaptateurAnimeList.Callbacks,
    AdapterView.OnItemSelectedListener {

    private lateinit var repository: AnimeRepository
    private lateinit var animeListViewModel: AnimeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = AnimeRepository(
            fr.iut.animelist.data.persistence.AnimeDataBase.getDatabase(
                requireContext()
            ).animeDao()
        )
        animeListViewModel = ViewModelProvider(this, AnimeListViewModelFactory(repository)).get()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListAnimeBinding.inflate(inflater)
        binding.animeListVM = animeListViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val animeAdapter = AdaptateurAnimeList(this)
        with(binding.recyclerView) {
            adapter = animeAdapter
        }

        animeListViewModel.allAnimes.observe(viewLifecycleOwner) { anime ->
            anime.let { animeAdapter.submitList(it) }
        }
        val spinner: Spinner = binding.spinner
        ArrayAdapter.createFromResource(
            binding.root.context, R.array.category_array, android.R.layout.simple_spinner_item
        ).also { ad ->
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = ad
        }
        spinner.onItemSelectedListener = this

        APICall().getGenres()?.observe(viewLifecycleOwner) { list ->
            var arrayList = list.map { l -> l.info?.name }
            val arrayAdapter =
                ArrayAdapter(binding.root.context, android.R.layout.simple_spinner_item, arrayList)
            spinner.adapter = arrayAdapter
        }

        return binding.root
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
        /*if (id == 0L) {
            animeListViewModel.clear()
            APICall().getAnimes()?.observe(viewLifecycleOwner) { list ->
                for (item in list) animeListViewModel.insert(item)
            }
        }*/
        if(parent != null){
            animeListViewModel.clear()
            var p = parent.getItemAtPosition(position)
            if(p == "All"){
                APICall().getAnimes()?.observe(viewLifecycleOwner) { list ->
                    for (item in list) animeListViewModel.insert(item)
                }
            } else {
                APICall().getAnimeGenres(p.toString())?.observe(viewLifecycleOwner) { list ->
                    for (item in list) animeListViewModel.insert(item)
                }
            }
        }


        // peut apparament ce faire en une ligne avec les live data grace a un filter / switch map sur la livedata

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        APICall().getAnimes()?.observe(viewLifecycleOwner) { list ->
            for (item in list) animeListViewModel.insert(item)
        }
    }

}


// faire un bouton refresh avec un rechargement en local