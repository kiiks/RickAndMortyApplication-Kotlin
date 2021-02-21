package com.example.rickandmortyapplication_kotlin.View.Episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapplication_kotlin.Model.Episode.EpisodeResult
import com.example.rickandmortyapplication_kotlin.R
import com.example.rickandmortyapplication_kotlin.View.MainActivity

class EpisodeListFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var btnNext: Button
    private lateinit var btnPrevious: Button
    private lateinit var pageTV: TextView
    private lateinit var recyclerView: RecyclerView

    private val viewModel: EpisodeViewModel by viewModels()
    private var episodeList: List<EpisodeResult?> = emptyList()
    private lateinit var adapter: EpisodeListAdapter

    private var currentPage: Int = 1

    private val listObserver =  Observer<List<EpisodeResult?>> { value -> value ?.let {
        episodeList = it
        changeAdapterList(episodeList)
    } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.fragment_episode, container, false)

        // --- UI --- //
        mainActivity = activity as MainActivity
        btnNext = view.findViewById(R.id.nextButton)
        btnPrevious = view.findViewById(R.id.previousButton)
        pageTV = view.findViewById(R.id.pageTV)
        recyclerView = view.findViewById(R.id.recyclerView)

        // --- View Model --- //
        viewModel.getFirstPage().observe(viewLifecycleOwner,listObserver)
        initAdapter()

        // --- Event --- //
        btnNext.setOnClickListener(View.OnClickListener {
            currentPage++
            if (currentPage > 3) {
                currentPage = 3
                return@OnClickListener
            }
            pageTV.text = currentPage.toString()
            viewModel.getNPage(currentPage).observe(viewLifecycleOwner,listObserver)
        })

        btnPrevious.setOnClickListener(View.OnClickListener {
            currentPage--
            if(currentPage <= 0) {
                currentPage = 1
                return@OnClickListener
            }
            pageTV.text = currentPage.toString()
            viewModel.getNPage(currentPage).observe(viewLifecycleOwner,listObserver)
        })

        return view
    }

    fun initAdapter() {
        adapter =
            EpisodeListAdapter(
                context,
                episodeList
            )
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun changeAdapterList(list: List<EpisodeResult?>) {
        adapter.episodeList = list
        adapter.notifyDataSetChanged()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            EpisodeListFragment()
    }
}