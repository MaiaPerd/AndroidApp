package fr.iut.animelist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.iut.animelist.data.Repository.AnimeRepository
import fr.iut.animelist.model.Anime
import kotlinx.coroutines.launch

class AnimeListViewModel(private val repository: AnimeRepository) : ViewModel() {
    val allAnimes: LiveData<List<Anime>> = repository.allAnimes

    init {
        viewModelScope.launch {
            try {
            } catch (e: Exception) {
                Log.e("Repository", "Not success")
            }
        }

    }


    fun insert(anime: Anime) = viewModelScope.launch {
        repository.insert(anime)
    }

    fun clear() = viewModelScope.launch {
        repository.clear()
    }


}

class AnimeListViewModelFactory(private val repository: AnimeRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimeListViewModel(repository) as T
    }
}

