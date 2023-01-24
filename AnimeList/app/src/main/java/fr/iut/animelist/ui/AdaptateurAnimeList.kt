package fr.iut.animelist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import fr.iut.animelist.R
import fr.iut.animelist.model.Anime


class AdaptateurAnimeList(private val dataSet: List<Anime>, private val view: View) : RecyclerView.Adapter<AdaptateurAnimeList.AnimeViewHolder>(){
    class AnimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nomItem: TextView
        val typeItem: TextView
        val anneeItem: TextView
        val anime: CardView

        init{

            nomItem = view.findViewById(R.id.nomItem)
            typeItem = view.findViewById(R.id.typeItem)
            anneeItem = view.findViewById(R.id.anneeItem)
            anime = view.findViewById<CardView>(R.id.animeItem)

        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_anime, viewGroup, false)

        return AnimeViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: AnimeViewHolder, position: Int) {
        viewHolder.nomItem.text = dataSet[position].id
        viewHolder.typeItem.text = dataSet[position].type
        viewHolder.anneeItem.text = dataSet[position].type

       // viewHolder.anime.setOnClickListener{  listener.onDogSelected(dataSet[position].id) }
        /*
        viewHolder.chien.setOnClickListener{view ->
           voirChien(dataSet[position], position)
        }*/

    }

    override fun getItemCount() = dataSet.size

    /*  fun detailChien(nom: String, race: String){
          MainActivity().startNewActivityChien(nom, race)
      }*/

    interface DogSelected {
        fun onDogSelected(dogId: Long)
    }
}

