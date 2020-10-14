package com.example.rickandmortyapplication_kotlin.Model.Episode

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EpisodeResult(
    @SerializedName("air_date")
    @Expose
    val air_date: String,
    @SerializedName("characters")
    @Expose
    val characters: List<String>,
    @SerializedName("created")
    @Expose
    val created: String,
    @SerializedName("episode")
    @Expose
    val episode: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("url")
    @Expose
    val url: String
)