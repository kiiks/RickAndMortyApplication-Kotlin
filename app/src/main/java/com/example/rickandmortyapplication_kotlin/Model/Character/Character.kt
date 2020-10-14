package com.example.rickandmortyapplication_kotlin.Model.Character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("info")
    @Expose
    val info: Info,
    @SerializedName("results")
    @Expose
    val results: List<CharacterResult>
)