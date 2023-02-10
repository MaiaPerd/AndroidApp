package fr.iut.animelist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import fr.iut.animelist.data.Repository.AnimeRepository
import fr.iut.animelist.data.persistence.AnimeDataBase
import fr.iut.animelist.databinding.FragmentAnimeBinding
import fr.iut.animelist.viewmodel.AnimeViewModel
import fr.iut.animelist.viewmodel.AnimeViewModelFactory

class AnimeFragment : Fragment() {

    private var id: Int? = null

    companion object {
        private const val EXTRA_ANIME_ID = "fr.iut.animelist.extra_anime_id"
        fun newInstance(id: Int) = AnimeFragment().apply {
            arguments = bundleOf(EXTRA_ANIME_ID to id)
        }
    }

    private lateinit var repository: AnimeRepository
    private lateinit var animeViewModel: AnimeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = AnimeRepository(
            AnimeDataBase.getDatabase(
                requireContext()
            ).animeDao()
        )

        var a = savedInstanceState?.getInt(EXTRA_ANIME_ID)
        var b = arguments?.getInt(EXTRA_ANIME_ID)
        id = (savedInstanceState?.getInt(EXTRA_ANIME_ID) ?: arguments?.getInt(EXTRA_ANIME_ID))
        var animeId: Int = id?:1
        animeViewModel = ViewModelProvider(this,  AnimeViewModelFactory(repository, animeId) ).get()

    }



    lateinit var nameAnime: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAnimeBinding.inflate(inflater)
        binding.animeVM = animeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.checkBox.setOnClickListener{
            animeViewModel.save()
        }

        binding.ratingbar.setOnClickListener{
            animeViewModel.save()
        }

        return binding.root
    }


}
