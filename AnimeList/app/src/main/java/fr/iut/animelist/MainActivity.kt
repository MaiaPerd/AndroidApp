package fr.iut.animelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val newAnimeActivityRequestCode = 1

   /* val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AnimeDataBase.getDatabase(this, applicationScope) }
    val repository by lazy { AnimeRepository(database.animeDao()) }
    //val animeViewModel: AnimeViewModel by viewModels()
    val animeViewModel by lazy { AnimeViewModel(repository) }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //APICall().getAnime(1)?.let { listAnime.add(it) }
/*
        val animeObserver = Observer<List<Anime>> { newName ->
            val test: TextView = this.findViewById<TextView>(R.id.nomTest)
            test.setText(newName[0].id)
            listAnime.addAll(newName)
        }

        var ab = animeViewModel.allAnimes.observe(this, animeObserver)
*/

/*
        APICall().getAnime(2)?.let { animeViewModel.insert(it) }

        APICall().getAnime(1)?.let { animeViewModel.insert(it) }
*/
/*
        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = AdaptateurAnimeList()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val animeObserver = Observer<List<Anime>> { anime ->
            anime.let { adapter.submitList(it) }
        }
        animeViewModel.allAnimes.observe(this, animeObserver)
*/



    }

}