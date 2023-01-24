package fr.iut.animelist.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.util.*


/*
@Entity
data class Anime(@SerializedName("id") var id: Int = 0,
                 @SerializedName("attributes.title.en") var name: String = "",
                 @SerializedName("attributes.title.ja_jp") var originalName: String = "",
                 @SerializedName("attributes.synopsis") var synopsis: String = "",
                 @SerializedName("attributes.startDate") var dateSortie: Date? = null,
                 @SerializedName("attributes.episodeCount") var nbEpisode: Int = 0,
                 @SerializedName("attributes.subtype") var type: String = "",
                 @SerializedName("attributes.status") var status: String = "",
                 var img: String = "",
                 var rank: Int = -1 )*/

@Entity(tableName = "anime_table")
data class Anime(@SerializedName("id") var id: String = "",
                 @SerializedName("type") var type: String = "",
                 @SerializedName("attributes") var info: Information)

data class Information(@SerializedName("slug") var titre: String = "",
                       @SerializedName("synopsis") var synopsis: String = "",
                       @SerializedName("startDate") var dateSortie: Date? = null,
                       @SerializedName("episodeCount") var nbEpisode: String = "",
                       @SerializedName("subtype") var type: String = "",
                       @SerializedName("status") var status: String = "")