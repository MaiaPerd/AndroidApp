package fr.iut.animelist.data


import androidx.room.Entity
import java.util.*

@Entity
data class Anime(var id: Int = 0,
                 var name: String = "",
                 var originalName: String = "",
                 var synopsis: String = "",
                 var dateSortie: Date? = null,
                 var nbEpisode: Int = 0,
                 var type: String = "",
                 var status: String = "",
                 var img: String = "",
                 var rank: Int = -1 )

