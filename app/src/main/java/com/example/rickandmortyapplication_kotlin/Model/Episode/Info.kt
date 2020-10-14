package com.example.rickandmortyapplication_kotlin.Model.Episode

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("count")
    @Expose
    val count: Int,
    @SerializedName("next")
    @Expose
    val next: String,
    @SerializedName("pages")
    @Expose
    val pages: Int,
    @SerializedName("prev")
    @Expose
    val prev: Any
)