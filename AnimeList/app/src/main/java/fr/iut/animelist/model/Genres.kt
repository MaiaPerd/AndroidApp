package fr.iut.animelist.model

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("attributes") var info: Info?
)

data class Info(@SerializedName("name") var name: String = "")