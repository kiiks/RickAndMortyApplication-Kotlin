package com.example.rickandmortyapplication_kotlin.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.rickandmortyapplication_kotlin.R
import com.example.rickandmortyapplication_kotlin.View.Characters.CharacterListFragment
import com.example.rickandmortyapplication_kotlin.View.Episodes.EpisodeListFragment
import com.example.rickandmortyapplication_kotlin.View.Locations.LocationListFragment

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

        // --- UI --- //
        btnCharacter = view.findViewById(R.id.character_view_btn)
        btnEpisode = view.findViewById(R.id.episode_view_btn)
        btnLocation = view.findViewById(R.id.location_view_btn)

        // --- Event --- //
        btnCharacter.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()

            transaction
                ?.replace(R.id.container,
                    CharacterListFragment.newInstance()
                )
                ?.addToBackStack("characters")
                ?.commit()
        }

        btnEpisode.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()

            transaction
                ?.replace(R.id.container,
                    EpisodeListFragment.newInstance()
                )
                ?.addToBackStack("episode")
                ?.commit()
        }

        btnLocation.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()

            transaction
                ?.replace(R.id.container,
                    LocationListFragment.newInstance()
                )
                ?.addToBackStack("location")
                ?.commit()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()

    }
}