package com.example.rickandmortyapplication_kotlin.View.Locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapplication_kotlin.Model.Location.LocationResult
import com.example.rickandmortyapplication_kotlin.Model.WebServices.NetworkManager

class LocationViewModel : ViewModel() {
    private var locationList: MutableLiveData<List<LocationResult>> = MutableLiveData()

    fun getFirstPage(): LiveData<List<LocationResult>> {
        locationList = NetworkManager.getLocationFirstPage()
        return locationList
    }

    fun getNPage(n: Int): LiveData<List<LocationResult>> {
        locationList = NetworkManager.getLocationNPage(n)
        return locationList
    }
}