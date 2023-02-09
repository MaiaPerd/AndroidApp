package fr.iut.animelist.viewmodel

import android.util.Log
import androidx.lifecycle.*
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

    val showEmptyView = Transformations.map(allAnimes, List<Anime>::isEmpty)


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

