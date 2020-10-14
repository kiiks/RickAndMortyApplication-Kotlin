package com.example.rickandmortyapplication_kotlin.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapplication_kotlin.Adapter.CharacterViewAdapter
import com.example.rickandmortyapplication_kotlin.Model.Character.Character
import com.example.rickandmortyapplication_kotlin.Model.Character.CharacterResult
import com.example.rickandmortyapplication_kotlin.R
import com.example.rickandmortyapplication_kotlin.View.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var btnNext: Button
    private lateinit var btnPrevious: Button
    private lateinit var pageTV: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var characterList: List<CharacterResult?>
    private lateinit var adapter: CharacterViewAdapter

    private var currentPage: Int = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_character, container, false)

        mainActivity = activity as MainActivity
        btnNext = view.findViewById(R.id.nextButton)
        btnPrevious = view.findViewById(R.id.previousButton)
        pageTV = view.findViewById(R.id.pageTV)
        recyclerView = view.findViewById(R.id.recyclerView)

        getFirstPage()

        btnNext.setOnClickListener(View.OnClickListener {
            currentPage++
            if (currentPage > 34) {
                currentPage = 34
                return@OnClickListener
            }
            pageTV.text = currentPage.toString()
            getNPage(currentPage)
        })

        btnPrevious.setOnClickListener(View.OnClickListener {
            currentPage--
            if(currentPage <= 0) {
                currentPage = 1
                return@OnClickListener
            }
            pageTV.text = currentPage.toString()
            getNPage(currentPage)
        })

        return view
    }

    fun getFirstPage() {
        val response = mainActivity.service.getCharacterFirstPage()

        response?.enqueue(object : Callback<Character?> {
            override fun onFailure(call: Call<Character?>, t: Throwable) {
                println("Erreur API")

            }

            override fun onResponse(call: Call<Character?>, response: Response<Character?>) {
                println("Succès API")
                characterList = response.body()?.results ?: emptyList()

                adapter = CharacterViewAdapter(context, characterList)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

        })
    }

    private fun getNPage(n: Int) {
        val response: Call<Character?> = mainActivity.service.getCharacterNPage(n)

        response.enqueue(object : Callback<Character?> {
            override fun onResponse(call: Call<Character?>, response: Response<Character?>)
            {
                println("Succès API")
                changeList(response.body()?.results ?: emptyList())

            }

            override fun onFailure(call: Call<Character?>, t: Throwable) {
                println("Erreur API")
            }
        })
    }

    fun changeList(list: List<CharacterResult?>) {
        adapter.characterList = list
        adapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharacterFragment()
    }
}