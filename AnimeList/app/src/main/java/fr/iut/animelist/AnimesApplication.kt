package fr.iut.animelist

import android.app.Application

class AnimesApplication : Application() {
    /*
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AnimeDataBase.getDatabase(this, applicationScope) }
    val repository by lazy { AnimeRepository(database.animeDao()) }
    //val animeViewModel: AnimeViewModel by viewModels()
    val animeViewModel by lazy { AnimeViewModel(repository) }*/
}