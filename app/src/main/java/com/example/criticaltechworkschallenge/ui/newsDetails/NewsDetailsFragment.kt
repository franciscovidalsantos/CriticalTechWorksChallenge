package com.example.criticaltechworkschallenge.ui.newsDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.criticaltechworkschallenge.MainActivity
import com.example.criticaltechworkschallenge.R

class NewsDetailsFragment : Fragment() {

    private lateinit var _vm: NewsDetailsViewModel

    private lateinit var _title: TextView
    private lateinit var _description: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_news_details, container, false)

        _vm = ViewModelProvider(this)[NewsDetailsViewModel::class.java]
        val parent = activity as MainActivity;
        parent.supportActionBar?.title = "article detail"

        // parent.supportActionBar?.setDisplayHomeAsUpEnabled(true) // enable back button in top bar - its activated by default
        return init(view)
    }

    private fun init(view: View): View {

        // Find Views
        _title = view.findViewById(R.id.title)
        _description = view.findViewById(R.id.description)

        // Set static text
        _title.text = _vm.text.value
        _description.text = _vm.text.value

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}