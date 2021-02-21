package com.example.rickandmortyapplication_kotlin.Model.Location

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("info")
    @Expose
    val info: Info,
    @SerializedName("results")
    @Expose
    val results: List<LocationResult>
)