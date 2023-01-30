package fr.iut.animelist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.iut.animelist.api.APICall
import fr.iut.animelist.data.Repository.AnimeRepository
import fr.iut.animelist.model.Anime
import kotlinx.coroutines.launch

class AnimeViewModel(private val repository: AnimeRepository): ViewModel() {
    val allAnimes: LiveData<List<Anime>> = repository.allAnimes

    init{
        viewModelScope.launch {
            try {
                APICall().getAnime(2)?.let { repository.insert(it) }
            } catch (e: Exception) {
                Log.e("Repository", "Not success")
            }
        }

    }


    fun insert(anime: Anime) = viewModelScope.launch {
        repository.insert(anime)
    }

}

class AnimeViewModelFactory(private val repository: AnimeRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimeViewModel(repository) as T
    }
}

