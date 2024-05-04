package com.example.criticaltechworkschallenge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.criticaltechworkschallenge.R
import com.example.criticaltechworkschallenge.adapters.NewsAdapter


class HomeFragment : Fragment() {

    private lateinit var _vm: HomeViewModel

    private lateinit var _adapter: NewsAdapter
    private lateinit var _title: TextView
    private lateinit var _title2: TextView
    private lateinit var _recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        _vm = ViewModelProvider(this)[HomeViewModel::class.java]
        (activity as AppCompatActivity?)!!.supportActionBar!!.title =
            _vm.newsSource.value // temporary provider

        return init(view)
    }

    private fun init(view: View): View {

        // Find Views
        _title = view.findViewById(R.id.text_home)
        _title2 = view.findViewById(R.id.text_home2)
        _recyclerView = view.findViewById(R.id.recyclerView)

        // Set static text
        _title.text = _vm.text.value
        _title2.text = _vm.text.value


        // Set adapters
        _adapter = NewsAdapter(mutableListOf())
        _recyclerView.layoutManager = LinearLayoutManager(context)

        _recyclerView.adapter = _adapter

        _vm.articles.observe(viewLifecycleOwner) { articles ->
            _adapter.updateData(articles)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}