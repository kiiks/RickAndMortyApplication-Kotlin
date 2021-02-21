package com.example.rickandmortyapplication_kotlin.View

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.rickandmortyapplication_kotlin.Model.InternalStorage
import com.example.rickandmortyapplication_kotlin.Model.WebServices.NetworkManager
import com.example.rickandmortyapplication_kotlin.R
import com.example.rickandmortyapplication_kotlin.View.Characters.CharacterViewModel
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private val viewModel: CharacterViewModel by viewModels()
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        InternalStorage.context = applicationContext
        retrofit = NetworkManager.getRetrofitInstance()

        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .add(R.id.container, HomeFragment.newInstance())
            .addToBackStack("home")
            .commit()
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount;

        if (count == 0) {
            super.onBackPressed();
        } else {
            supportFragmentManager.popBackStack();
        }
    }

    override fun onResume() {
        super.onResume()
        InternalStorage.loadFavorites()
    }

    override fun onPause() {
        super.onPause()
        InternalStorage.saveFavorites()
    }
}