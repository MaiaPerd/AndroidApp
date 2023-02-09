package fr.iut.animelist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.iut.animelist.data.Repository.AnimeRepository
import fr.iut.animelist.model.Anime

class AnimeViewModel(private val repository: AnimeRepository, private val animeId: Int): ViewModel() {

    var anime: LiveData<Anime> = repository.getAnime(animeId)

}


class AnimeViewModelFactory(private val repository: AnimeRepository, private val animeId: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimeViewModel(repository, animeId) as T
    }
}