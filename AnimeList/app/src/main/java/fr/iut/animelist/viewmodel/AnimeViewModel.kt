package fr.iut.animelist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.iut.animelist.data.Repository.AnimeRepository
import fr.iut.animelist.model.Anime
import kotlinx.coroutines.launch

class AnimeViewModel(private val repository: AnimeRepository, private val animeId: Int) :
    ViewModel() {

    var anime: LiveData<Anime> = repository.getAnime(animeId)

    fun save() = anime.value?.let {
        viewModelScope.launch {
            repository.update(it)
        }
        true
    }
}


class AnimeViewModelFactory(private val repository: AnimeRepository, private val animeId: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimeViewModel(repository, animeId) as T
    }
}