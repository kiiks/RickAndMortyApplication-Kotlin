package com.example.rickandmortyapplication_kotlin.Model.Episode

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("info")
    @Expose
    val info: Info,
    @SerializedName("results")
    @Expose
    val results: List<EpisodeResult>
)