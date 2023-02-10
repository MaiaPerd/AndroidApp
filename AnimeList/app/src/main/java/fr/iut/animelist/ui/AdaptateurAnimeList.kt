package fr.iut.animelist.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.iut.animelist.R
import fr.iut.animelist.model.Anime

class AdaptateurAnimeList(private val listener: Callbacks) :
    ListAdapter<Anime, AdaptateurAnimeList.AnimeViewHolder>(ANIME_COMPARATOR) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AnimeViewHolder {
        return AnimeViewHolder.create(viewGroup, listener)
    }

    override fun onBindViewHolder(viewHolder: AnimeViewHolder, position: Int) {
        val currentItem = getItem(position)
        viewHolder.bind(currentItem)

    }

    class AnimeViewHolder(itemView: View, listener: Callbacks) : RecyclerView.ViewHolder(itemView) {
        val nomItem: TextView
        val typeItem: TextView
        val anneeItem: TextView
        val animeCard: CardView

        var anime: Anime? = null
            private set

        init {

            nomItem = itemView.findViewById(R.id.nomItem)
            typeItem = itemView.findViewById(R.id.typeItem)
            anneeItem = itemView.findViewById(R.id.anneeItem)
            animeCard = itemView.findViewById<CardView>(R.id.animeItem)
            itemView.setOnClickListener { anime?.let { listener.onAnimeSelected(it.id) } }

        }


        fun bind(anime: Anime?) {
            this.anime = anime
            nomItem.text = anime?.info?.titre?.capitalize()?.replace("-", " ")
            typeItem.text = anime?.info?.subtype
            if (anime?.info?.subtype == "TV"){
                animeCard.setCardBackgroundColor(Color.rgb(162,222,255))
            } else if (anime?.info?.subtype == "movie"){
                animeCard.setCardBackgroundColor(Color.rgb(196,255,204))
            } else if (anime?.info?.subtype == "OVA"){
                animeCard.setCardBackgroundColor(Color.rgb(255,196,196))
            } else if (anime?.info?.subtype == "ONA"){
                animeCard.setCardBackgroundColor(Color.rgb(255,255,196))
            }
            anneeItem.text = anime?.info?.dateSortie
            //  animeCard.setCardBackgroundColor(Color.RED)

        }

        companion object {
            fun create(parent: ViewGroup, listener: Callbacks): AnimeViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_anime, parent, false)
                return AnimeViewHolder(view, listener)
            }
        }
    }

    companion object {
        private val ANIME_COMPARATOR = object : DiffUtil.ItemCallback<Anime>() {
            override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    interface Callbacks {
        fun onAnimeSelected(id: Int)
    }

}

