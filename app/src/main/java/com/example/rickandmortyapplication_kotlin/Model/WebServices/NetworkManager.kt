package com.example.rickandmortyapplication_kotlin.Model.WebServices

import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyapplication_kotlin.BuildConfig
import com.example.rickandmortyapplication_kotlin.Model.Character.Character
import com.example.rickandmortyapplication_kotlin.Model.Character.CharacterResult
import com.example.rickandmortyapplication_kotlin.Model.Episode.Episode
import com.example.rickandmortyapplication_kotlin.Model.Location.Location
import com.example.rickandmortyapplication_kotlin.Model.Location.LocationResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val baseUrl = BuildConfig.WSUrl

object NetworkManager {
    lateinit var service: RickAndMortyAPI
    lateinit var retrofit: Retrofit

    fun getRetrofitInstance(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create<RickAndMortyAPI>(RickAndMortyAPI::class.java)

        return retrofit
    }

    fun getCharacterFirstPage(): MutableLiveData<List<CharacterResult>> {
       val response = service.getCharacterFirstPage()
       val list: MutableLiveData<List<CharacterResult>> = MutableLiveData()

        response?.enqueue(object : Callback<Character?> {
            override fun onFailure(call: Call<Character?>, t: Throwable) {
                println("Erreur API")
            }
            override fun onResponse(call: Call<Character?>, response: Response<Character?>) {
                println("Succès API")
                list.postValue(response.body()?.results)
            }
        })
        return list
    }

    fun getCharacterNPage(n: Int):  MutableLiveData<List<CharacterResult>> {
        val response = service.getCharacterNPage(n)
        val list: MutableLiveData<List<CharacterResult>> = MutableLiveData()

        response.enqueue(object : Callback<Character?> {
            override fun onResponse(call: Call<Character?>, response: Response<Character?>)
            {
                println("Succès API")
                list.postValue(response.body()?.results)

            }
            override fun onFailure(call: Call<Character?>, t: Throwable) {
                println("Erreur API")
            }
        })
        return list
    }

    fun getEpisodeFirstPage(): MutableLiveData<List<com.example.rickandmortyapplication_kotlin.Model.Episode.EpisodeResult>> {
        val response = service.getEpisodeFirstPage()
        val list: MutableLiveData<List<com.example.rickandmortyapplication_kotlin.Model.Episode.EpisodeResult>> = MutableLiveData()

        response?.enqueue(object : Callback<Episode?> {
            override fun onResponse(call: Call<Episode?>, response: Response<Episode?>)
            {
                println("Succès API")
                list.postValue(response.body()?.results)

            }
            override fun onFailure(call: Call<Episode?>, t: Throwable) {
                println("Erreur API")
            }
        })
        return list
    }

    fun getEpisodeNPage(n: Int): MutableLiveData<List<com.example.rickandmortyapplication_kotlin.Model.Episode.EpisodeResult>> {
        val response = service.getEpisodeNPage(n)
        val list: MutableLiveData<List<com.example.rickandmortyapplication_kotlin.Model.Episode.EpisodeResult>> = MutableLiveData()

        response?.enqueue(object : Callback<Episode?> {
            override fun onResponse(call: Call<Episode?>, response: Response<Episode?>)
            {
                println("Succès API")
                list.postValue(response.body()?.results)

            }
            override fun onFailure(call: Call<Episode?>, t: Throwable) {
                println("Erreur API")
            }
        })
        return list
    }

    fun getLocationFirstPage(): MutableLiveData<List<LocationResult>> {
        val response = service.getLocationFirstPage()
        val list: MutableLiveData<List<LocationResult>> = MutableLiveData()

        response?.enqueue(object : Callback<Location?> {
            override fun onResponse(call: Call<Location?>, response: Response<Location?>)
            {
                println("Succès API")
                list.postValue(response.body()?.results)

            }
            override fun onFailure(call: Call<Location?>, t: Throwable) {
                println("Erreur API")
            }
        })
        return list
    }

    fun getLocationNPage(n: Int): MutableLiveData<List<LocationResult>> {
        val response = service.getLocationNPage(n)
        val list: MutableLiveData<List<LocationResult>> = MutableLiveData()

        response.enqueue(object : Callback<Location?> {
            override fun onResponse(call: Call<Location?>, response: Response<Location?>)
            {
                println("Succès API")
                list.postValue(response.body()?.results)

            }
            override fun onFailure(call: Call<Location?>, t: Throwable) {
                println("Erreur API")
            }
        })
        return list
    }
}