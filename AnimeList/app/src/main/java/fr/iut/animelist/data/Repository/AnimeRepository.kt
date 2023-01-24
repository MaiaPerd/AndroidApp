package fr.iut.animelist.data.Repository

import androidx.annotation.WorkerThread
import fr.iut.animelist.data.DAO.AnimeDao
import fr.iut.animelist.model.Anime
import kotlinx.coroutines.flow.Flow

class AnimeRepository(private val animeDao: AnimeDao) {

    val allAnimes: Flow<List<Anime>> = animeDao.getAnimes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(anime: Anime) {
        animeDao.insert(anime)
    }

}