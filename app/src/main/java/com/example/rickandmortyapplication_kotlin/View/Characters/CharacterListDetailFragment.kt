package com.example.rickandmortyapplication_kotlin.View.Characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.rickandmortyapplication_kotlin.Model.Character.CharacterResult
import com.example.rickandmortyapplication_kotlin.Model.InternalStorage
import com.example.rickandmortyapplication_kotlin.R
import com.squareup.picasso.Picasso

class CharacterListDetailFragment : Fragment() {

    private val viewModel: CharacterViewModel by activityViewModels()
    private var character: CharacterResult? = null

    lateinit var characterName: TextView
    lateinit var status: TextView
    lateinit var species: TextView
    lateinit var origin: TextView
    lateinit var lastLocation: TextView
    lateinit var characterPicture: ImageView
    lateinit var favoriteSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        character = viewModel.getCharacterSelected()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_list_detail, container, false)

        characterName = view.findViewById(R.id.characterNameTV_detail)
        status = view.findViewById(R.id.statusTV_detail)
        species = view.findViewById(R.id.speciesTV_detail)
        origin = view.findViewById(R.id.originTV_detail)
        lastLocation = view.findViewById(R.id.locationTV_detail)
        characterPicture = view.findViewById(R.id.characterPicture_detail)
        favoriteSwitch = view.findViewById(R.id.addFavSW_detail)

        characterName.text = character?.name
        status.text = "Status : " + character?.status
        species.text = "Species : " + character?.species
        origin.text = "Origin : " + character?.origin?.name
        lastLocation.text = "Last location : " + character?.location?.name
        Picasso.get().load(character?.image).into(characterPicture)

        //Set switch on if is in favorites
        if(InternalStorage.isFavorites(character)) {
            favoriteSwitch.isChecked = true
        }

        //Add to favorites if switch on
        favoriteSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                character?.let { InternalStorage.favorites.add(it) }
            } else {
                val characterToSupress = InternalStorage.favorites.find { character == it }
                InternalStorage.favorites.remove(characterToSupress)
            }
        })
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CharacterListDetailFragment()
    }
}