package com.example.rickandmortyapplication_kotlin.Model

import android.content.Context
import android.util.Log
import com.example.rickandmortyapplication_kotlin.Model.Character.CharacterResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception
import java.lang.reflect.Type

//Gestionnaire de sauvegardes
object InternalStorage {
    const val FILENAME = "favorites"

    var favorites: MutableList<CharacterResult> = mutableListOf()
    lateinit var context: Context


    fun saveFavorites() {
        val gson = Gson()
        val json: String = gson.toJson(favorites)

        try {
            context.openFileOutput(FILENAME, Context.MODE_PRIVATE).use {
                it?.write(json.toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadFavorites() {
        val gson = Gson()

        try {
            context.openFileInput(FILENAME).use {
                val text = it.bufferedReader().use {
                    it.readText()
                }
                favorites = gson.fromJson(text, Array<CharacterResult>::class.java).toMutableList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isFavorites(character: CharacterResult?): Boolean {
        for(favorite in favorites) {
            if(favorite.name == character?.name) {
                return true
            }
        }
        return false
    }
}