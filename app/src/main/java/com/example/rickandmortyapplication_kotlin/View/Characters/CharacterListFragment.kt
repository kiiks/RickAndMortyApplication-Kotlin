package com.example.rickandmortyapplication_kotlin.View.Characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapplication_kotlin.Model.Character.CharacterResult
import com.example.rickandmortyapplication_kotlin.R
import com.example.rickandmortyapplication_kotlin.View.MainActivity

class CharacterListFragment : onCharacterListener,Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var btnNext: Button
    private lateinit var btnPrevious: Button
    private lateinit var pageTV: TextView
    private lateinit var recyclerView: RecyclerView

    private var characterList: List<CharacterResult?> = emptyList()
    private lateinit var adapter: CharacterListAdapter
    private val viewModel: CharacterViewModel by activityViewModels()

    private var currentPage: Int = 1

    private val listObserver = Observer<List<CharacterResult?>> { value ->  value ?.let {
            characterList = it
            changeAdapterList(characterList)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_character, container, false)

        //--- UI ---//
        mainActivity = activity as MainActivity
        btnNext = view.findViewById(R.id.nextButton)
        btnPrevious = view.findViewById(R.id.previousButton)
        pageTV = view.findViewById(R.id.pageTV)
        recyclerView = view.findViewById(R.id.recyclerView)

        //--- ViewModel ---//
        viewModel.getFirstPage().observe(viewLifecycleOwner,listObserver)
        initAdapter()

        //--- Event ---//
        btnNext.setOnClickListener(View.OnClickListener {
            currentPage++
            if (currentPage > 34) {
                currentPage = 34
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
            CharacterListAdapter(
                context,
                characterList,
                this
            )
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun changeAdapterList(list: List<CharacterResult?>) {
        adapter.characterList = list
        adapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CharacterListFragment()
    }

    //Go to detail on click
    override fun onCharacterClick(character: CharacterResult) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        print(character.name)
        viewModel.setCharacterSelected(character)

        transaction
            ?.replace(R.id.container,
                CharacterListDetailFragment.newInstance()
            )
            ?.addToBackStack("characterItem")
            ?.commit()
    }
}