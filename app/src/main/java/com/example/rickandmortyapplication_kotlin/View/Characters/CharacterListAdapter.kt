package com.example.rickandmortyapplication_kotlin.View.Characters

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.rickandmortyapplication_kotlin.Model.Character.CharacterResult
import com.example.rickandmortyapplication_kotlin.Model.InternalStorage
import com.example.rickandmortyapplication_kotlin.R
import com.squareup.picasso.Picasso

class CharacterListAdapter constructor(
    context: Context?,
    characterList: List<CharacterResult?>,
    listener: onCharacterListener
): RecyclerView.Adapter<CharacterListAdapter.MyVH>() {

    private var context: Context? = null
    var characterList: List<CharacterResult?> = emptyList()
    private var listener: onCharacterListener

    class MyVH(characterView: View) : ViewHolder(characterView)
    {
        var characterName: TextView
        var status: TextView
        var species: TextView
        var lastLocation: TextView
        var characterPicture: ImageView
        var favoriteIcon: ImageView

        init {
            characterName = characterView.findViewById(R.id.characterNameTV)
            status = characterView.findViewById(R.id.statusTV)
            species = characterView.findViewById(R.id.speciesTV)
            lastLocation = characterView.findViewById(R.id.lastLocationTV)
            characterPicture = characterView.findViewById(R.id.characterPicture)
            favoriteIcon = characterView.findViewById(R.id.favoriteIV)
        }
    }

    init {
        this.context = context
        this.characterList = characterList
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH
    {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val characterView: View =
            inflater.inflate(R.layout.character_item_view, parent, false)

        val vh =
            MyVH(
                characterView
            )
        return vh
    }

    override fun getItemCount(): Int
    {
        return characterList.size
    }

    override fun onBindViewHolder(holder: MyVH, position: Int)
    {
        val character: CharacterResult? = characterList[position]
        var str: String = ""

        holder.itemView.setOnClickListener(View.OnClickListener {
            if (character != null) {
                listener.onCharacterClick(character)
            }
        })

        val characterName = holder.characterName
        val status = holder.status
        val species = holder.species
        val lastLocation = holder.lastLocation
        val characterPicture = holder.characterPicture
        val favoriteIcon = holder.favoriteIcon

        Picasso.get().load(character?.image).into(characterPicture)
        str = "Last location : ${character?.location?.name}"

        characterName.setText(character?.name)
        status.setText(character?.status)
        species.setText(character?.species)
        lastLocation.setText(str)

        //Display favorite star
        if(InternalStorage.isFavorites(character)) {
            val bm = BitmapFactory.decodeResource(context?.resources,android.R.drawable.btn_star_big_on)
            favoriteIcon.setImageBitmap(bm)
        }
        else {
            val bm = BitmapFactory.decodeResource(context?.resources,android.R.drawable.btn_star_big_off)
            favoriteIcon.setImageBitmap(bm)
        }
    }
}

interface onCharacterListener {
    fun onCharacterClick(character: CharacterResult)
}