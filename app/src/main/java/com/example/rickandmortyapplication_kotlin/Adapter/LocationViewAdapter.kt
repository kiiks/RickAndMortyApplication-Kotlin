package com.example.rickandmortyapplication_kotlin.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.rickandmortyapplication_kotlin.R
import com.squareup.picasso.Picasso
import com.example.rickandmortyapplication_kotlin.Model.Character.CharacterResult

class LocationViewAdapter constructor(context: Context?, characterList: List<CharacterResult?>): RecyclerView.Adapter<LocationViewAdapter.MyVH>() {

    private var context: Context? = null
    var characterList: List<CharacterResult?> = emptyList()

    class MyVH(characterView: View) : ViewHolder(characterView)
    {
        var characterName: TextView
        var status: TextView
        var species: TextView
        var lastLocation: TextView
        var characterPicture: ImageView

        init {
            characterName = characterView.findViewById(R.id.characterNameTV)
            status = characterView.findViewById(R.id.statusTV)
            species = characterView.findViewById(R.id.speciesTV)
            lastLocation = characterView.findViewById(R.id.lastLocationTV)
            characterPicture = characterView.findViewById(R.id.characterPicture)
        }
    }

    init
    {
        this.context = context
        this.characterList = characterList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewAdapter.MyVH
    {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        Log.d("TestAdapter", "Success")
        val characterView: View =
            inflater.inflate(R.layout.character_item_view, parent, false)

        val vh = MyVH(characterView)
        return vh
    }

    override fun getItemCount(): Int
    {
        return characterList.size
    }

    override fun onBindViewHolder(holder: LocationViewAdapter.MyVH, position: Int)
    {
        val character: CharacterResult? = characterList[position]
        var str: String = ""

        val characterName = holder.characterName
        val status = holder.status
        val species = holder.species
        val lastLocation = holder.lastLocation
        val characterPicture = holder.characterPicture

        Picasso.get().load(character?.image).into(characterPicture)
        str = "Last location : ${character?.location?.name}"

        characterName.setText(character?.name)
        status.setText(character?.status)
        species.setText(character?.species)
        lastLocation.setText(str)
    }
}