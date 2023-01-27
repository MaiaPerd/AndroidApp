package fr.iut.animelist.data.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.iut.animelist.model.Anime

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime_table ORDER BY id ASC")
    fun getAnimes(): LiveData<List<Anime>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(anime: Anime)

    @Query("DELETE FROM anime_table")
    suspend fun deleteAll()
}