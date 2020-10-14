package com.example.rickandmortyapplication_kotlin.WebServices

import com.example.rickandmortyapplication_kotlin.Model.Character.Character
import com.example.rickandmortyapplication_kotlin.Model.Location.Location
import com.example.rickandmortyapplication_kotlin.Model.Episode.Episode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RickAndMortyAPI {

    @GET("character/")
    fun getCharacterFirstPage(): Call<Character?>?

    @GET("character")
    fun getCharacterNPage(@Query("page") page: Int): Call<Character?>

    @GET("episode")
    fun getEpisodeFirstPage(): Call<Episode?>?

    @GET("episode")
    fun getEpisodeNPage(@Query("page") page: Int): Call<Episode?>

    @GET("location")
    fun getLocationFirstPage(): Call<Location?>?

    @GET("location")
    fun getLocationNPage(@Query("page") page: Int): Call<Location?>
}