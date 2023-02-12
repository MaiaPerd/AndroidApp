package fr.iut.animelist.data.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.iut.animelist.model.Anime

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime_table WHERE vue = false ORDER BY id ASC")
    fun getAnimes(): LiveData<List<Anime>>

    @Query("SELECT * FROM anime_table WHERE id = :id")
    fun getAnime(id: Int): LiveData<Anime>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(anime: Anime)

    @Query("DELETE FROM anime_table WHERE vue = false")
    suspend fun deleteAll()

    @Query("SELECT * FROM anime_table WHERE vue = true")
    fun getAnimeVue(): LiveData<List<Anime>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(it: Anime)
}