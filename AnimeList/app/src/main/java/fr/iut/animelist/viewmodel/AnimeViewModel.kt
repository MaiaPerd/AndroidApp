package fr.iut.animelist.viewmodel

import androidx.lifecycle.*
import fr.iut.animelist.data.Repository.AnimeRepository
import fr.iut.animelist.model.Anime
import kotlinx.coroutines.launch

class AnimeViewModel(private val repository: AnimeRepository): ViewModel() {
    val allAnimes: LiveData<List<Anime>> = repository.allAnimes.asLiveData()

    fun insert(anime: Anime) = viewModelScope.launch {
        repository.insert(anime)
    }

}

class AnimeViewModelFactory(private val repository: AnimeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnimeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
