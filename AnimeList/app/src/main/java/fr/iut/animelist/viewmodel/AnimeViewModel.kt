package fr.iut.animelist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.iut.animelist.data.Repository.AnimeRepository
import fr.iut.animelist.model.Anime
import kotlinx.coroutines.launch

class AnimeViewModel(private val repository: AnimeRepository): ViewModel() {
    val allAnimes: LiveData<List<Anime>> = repository.allAnimes

    fun insert(anime: Anime) = viewModelScope.launch {
        repository.insert(anime)
    }

}

