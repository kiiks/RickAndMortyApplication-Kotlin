package com.example.rickandmortyapplication_kotlin.View

import android.os.Bundle
import android.view.FrameMetrics
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rickandmortyapplication_kotlin.Fragment.HomeFragment
import com.example.rickandmortyapplication_kotlin.R
import com.example.rickandmortyapplication_kotlin.WebServices.RickAndMortyAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val url = "https://rickandmortyapi.com/api/"
    lateinit var retrofit: Retrofit
    lateinit var service: RickAndMortyAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create<RickAndMortyAPI>(RickAndMortyAPI::class.java)

        val transaction = supportFragmentManager.beginTransaction()

        transaction
            .add(R.id.container, HomeFragment.newInstance())
            .commit()
    }

    override fun onBackPressed() {
        val currentFragment: Fragment = supportFragmentManager.findFragmentById(R.id.container) as Fragment

        if (currentFragment is HomeFragment) {
            super.onBackPressed()
        }

        val transaction = supportFragmentManager.beginTransaction()

        transaction
            .replace(R.id.container, HomeFragment.newInstance())
            .commit()
    }
}