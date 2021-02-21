package com.example.rickandmortyapplication_kotlin.View.Episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapplication_kotlin.Model.Episode.EpisodeResult
import com.example.rickandmortyapplication_kotlin.Model.WebServices.NetworkManager

class EpisodeViewModel : ViewModel() {
    private var episodeList: MutableLiveData<List<EpisodeResult>> = MutableLiveData()

    fun getFirstPage(): LiveData<List<EpisodeResult>> {
        episodeList = NetworkManager.getEpisodeFirstPage()
        return episodeList
    }

    fun getNPage(n: Int): LiveData<List<EpisodeResult>> {
        episodeList = NetworkManager.getEpisodeNPage(n)
        return episodeList
    }
}