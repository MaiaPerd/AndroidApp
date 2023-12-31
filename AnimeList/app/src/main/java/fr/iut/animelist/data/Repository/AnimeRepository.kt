package fr.iut.animelist.data.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import fr.iut.animelist.data.DAO.AnimeDao
import fr.iut.animelist.model.Anime

class AnimeRepository(private val animeDao: AnimeDao) {

    val allAnimes: LiveData<List<Anime>> = animeDao.getAnimes()

    val allAnimesView: LiveData<List<Anime>> = animeDao.getAnimeVue()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(anime: Anime) {
        animeDao.insert(anime)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun clear() {
        animeDao.deleteAll()
    }

    fun getAnime(animeId: Int): LiveData<Anime>  {
        return animeDao.getAnime(animeId)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(it: Anime) {
        animeDao.update(it)
    }


}