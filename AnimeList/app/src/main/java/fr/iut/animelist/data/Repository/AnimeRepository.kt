package fr.iut.animelist.data.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import fr.iut.animelist.data.DAO.AnimeDao
import fr.iut.animelist.model.Anime

class AnimeRepository(private val animeDao: AnimeDao) {

    val allAnimes: LiveData<List<Anime>> = animeDao.getAnimes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(anime: Anime) {
        animeDao.insert(anime)
    }

}