package com.example.rickandmortyapplication_kotlin.View.Episodes

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.rickandmortyapplication_kotlin.R
import com.example.rickandmortyapplication_kotlin.Model.Episode.EpisodeResult

class EpisodeListAdapter constructor(context: Context?, episodeList: List<EpisodeResult?>): RecyclerView.Adapter<EpisodeListAdapter.MyVH>() {

    private var context: Context? = null
    var episodeList: List<EpisodeResult?> = emptyList()

    class MyVH(episodeView: View) : ViewHolder(episodeView)
    {
        var episodeName: TextView
        var episodeNb: TextView
        var airDate: TextView

        init {
            episodeName = episodeView.findViewById(R.id.episodeNameTV)
            episodeNb = episodeView.findViewById(R.id.episodeNbTV)
            airDate = episodeView.findViewById(R.id.airDateTV)
        }
    }

    init {
        this.context = context
        this.episodeList = episodeList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val episodeView: View =
            inflater.inflate(R.layout.episode_item_view, parent, false)

        val vh =
            MyVH(
                episodeView
            )
        return vh
    }

    override fun getItemCount(): Int {
        return episodeList.size
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        val episode: EpisodeResult? = episodeList[position]

        val episodeName = holder.episodeName
        val episodeNb = holder.episodeNb
        val airDate = holder.airDate

        episodeName.setText(episode?.name)
        episodeNb.setText(episode?.episode)
        airDate.setText(episode?.air_date)
    }
}