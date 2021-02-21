package com.example.rickandmortyapplication_kotlin.View.Characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapplication_kotlin.Model.Character.CharacterResult
import com.example.rickandmortyapplication_kotlin.Model.WebServices.NetworkManager

class CharacterViewModel : ViewModel() {
    private var characterList: MutableLiveData<List<CharacterResult>> = MutableLiveData()
    private lateinit var characterSelected: CharacterResult

    fun getFirstPage(): LiveData<List<CharacterResult>> {
        characterList = NetworkManager.getCharacterFirstPage()
        return characterList
    }

    fun getNPage(n: Int): LiveData<List<CharacterResult>> {
        characterList = NetworkManager.getCharacterNPage(n)
        return characterList
    }

    fun setCharacterSelected(character: CharacterResult) {
        characterSelected = character
    }

    fun getCharacterSelected(): CharacterResult? {
        return characterSelected
    }
}