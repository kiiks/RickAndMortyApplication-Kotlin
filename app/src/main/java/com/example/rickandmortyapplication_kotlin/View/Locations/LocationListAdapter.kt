package com.example.rickandmortyapplication_kotlin.View.Locations

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.rickandmortyapplication_kotlin.R
import com.example.rickandmortyapplication_kotlin.Model.Location.LocationResult

class LocationListAdapter constructor(context: Context?, locationList: List<LocationResult?>): RecyclerView.Adapter<LocationListAdapter.MyVH>() {

    private var context: Context? = null
    var locationList: List<LocationResult?> = emptyList()

    class MyVH(locationView: View) : ViewHolder(locationView)
    {
        var locationName: TextView
        var type: TextView
        var dimension: TextView

        init {
            locationName = locationView.findViewById(R.id.locationNameTV)
            type = locationView.findViewById(R.id.locationTypeTV)
            dimension = locationView.findViewById(R.id.locationDimensionTV)
        }
    }

    init {
        this.context = context
        this.locationList = locationList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        Log.d("TestAdapter", "Success")
        val locationView: View =
            inflater.inflate(R.layout.location_item_view, parent, false)

        val vh =
            MyVH(
                locationView
            )
        return vh
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        val location: LocationResult? = locationList[position]

        val locationName = holder.locationName
        val type = holder.type
        val dimension = holder.dimension

        locationName.setText(location?.name)
        type.setText(location?.type)
        dimension.setText(location?.dimension)
    }
}