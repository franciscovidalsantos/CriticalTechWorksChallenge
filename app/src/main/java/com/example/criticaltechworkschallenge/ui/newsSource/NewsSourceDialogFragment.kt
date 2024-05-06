package com.example.criticaltechworkschallenge.ui.newsSource

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.criticaltechworkschallenge.MainActivity
import com.example.criticaltechworkschallenge.R
import com.example.criticaltechworkschallenge.enums.SourcesEnum
import com.example.criticaltechworkschallenge.ui.newsDetails.NewsDetailsViewModel

class NewsSourceDialogFragment : DialogFragment() {

    private var _listener: NewsSourceSelectionListener? = null

    private lateinit var _vm: NewsDetailsViewModel

    private lateinit var _title: TextView
    private lateinit var _list: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_news_source_dialog, container, false)

        _vm = ViewModelProvider(this)[NewsDetailsViewModel::class.java]
        val parent = activity as MainActivity;

        return init(view)
    }

    private fun init(view: View): View {

        // Find Views
        _title = view.findViewById(R.id.title)
        _list = view.findViewById(R.id.list)

        val sources = SourcesEnum.entries.map { it.sourceName }.toTypedArray()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, sources)
        _list.adapter = adapter

        // Set static text
        _title.text = "Select News Source"

        // Set click listener
        _list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedSource = SourcesEnum.entries[position]
            _listener?.onNewsSourceSelected(selectedSource)
            dismiss()
        }

        return view
    }

    fun setNewsSourceSelectionListener(listener: NewsSourceSelectionListener) {
        _listener = listener
    }

}

interface NewsSourceSelectionListener {
    fun onNewsSourceSelected(source: SourcesEnum)
}