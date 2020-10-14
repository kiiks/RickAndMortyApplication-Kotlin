package com.example.rickandmortyapplication_kotlin.Model.Location

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("created")
    @Expose
    val created: String,
    @SerializedName("dimension")
    @Expose
    val dimension: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("residents")
    @Expose
    val residents: List<String>,
    @SerializedName("type")
    @Expose
    val type: String,
    @SerializedName("url")
    @Expose
    val url: String
)