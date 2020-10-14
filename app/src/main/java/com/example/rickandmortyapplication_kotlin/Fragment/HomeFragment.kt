package com.example.rickandmortyapplication_kotlin.Fragment

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.example.rickandmortyapplication_kotlin.Model.Episode.Episode
import com.example.rickandmortyapplication_kotlin.R
import com.example.rickandmortyapplication_kotlin.View.MainActivity
import kotlinx.android.synthetic.*

class HomeFragment : Fragment() {

    lateinit var btnCharacter: Button
    lateinit var btnEpisode: Button
    lateinit var btnLocation: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        btnCharacter = view.findViewById(R.id.character_view_btn)
        btnEpisode = view.findViewById(R.id.episode_view_btn)
        btnLocation = view.findViewById(R.id.location_view_btn)

        btnCharacter.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()

            transaction
                ?.replace(R.id.container,CharacterFragment.newInstance())
                ?.commit()
        }

        btnEpisode.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()

            transaction
                ?.replace(R.id.container,EpisodeFragment.newInstance())
                ?.commit()
        }

        btnLocation.setOnClickListener {

        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()

    }
}