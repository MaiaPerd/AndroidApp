package fr.iut.animelist.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.iut.animelist.model.Anime
import fr.iut.animelist.data.DAO.AnimeDao

@Database(entities = arrayOf(Anime::class), version = 1, exportSchema = false)
abstract class AnimeDataBase: RoomDatabase() {

    abstract fun animeDao(): AnimeDao

    companion object {

        @Volatile
        private var INSTANCE: AnimeDataBase? = null

        fun getDatabase(context: Context): AnimeDataBase{
            return  INSTANCE ?: synchronized(this) {
                val  instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimeDataBase::class.java,
                    "anime_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}