package fr.iut.animelist.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fr.iut.animelist.data.DAO.AnimeDao
import fr.iut.animelist.model.Anime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Anime::class), version = 1, exportSchema = false)
abstract class AnimeDataBase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao

    class AnimeDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var animeDao = database.animeDao()

                    // Delete all content here.
                    animeDao.deleteAll()

                    // Add sample words.


                }
            }
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: AnimeDataBase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AnimeDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimeDataBase::class.java,
                    "anime_database_V2"
                )
                    // .addCallback(AnimeDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }


    }
}

