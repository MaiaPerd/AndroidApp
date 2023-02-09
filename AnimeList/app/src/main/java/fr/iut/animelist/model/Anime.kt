package fr.iut.animelist.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "anime_table")
data class Anime(
    @PrimaryKey @SerializedName("id") var id: Int = 0,
    @SerializedName("type") var type: String = "",
    @Embedded @SerializedName("attributes") var info: Information?
)

data class Information(
    @SerializedName("slug") var titre: String = "",
    @SerializedName("synopsis") var synopsis: String? = "",
    @SerializedName("startDate") var dateSortie: String? = "",
    @SerializedName("episodeCount") var nbEpisode: String? = "",
    @SerializedName("subtype") var subtype: String = "",
    @SerializedName("status") var status: String = ""
)


