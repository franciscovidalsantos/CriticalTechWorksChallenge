package com.example.criticaltechworkschallenge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.criticaltechworkschallenge.dto.Article
import com.example.criticaltechworkschallenge.MainActivity
import com.example.criticaltechworkschallenge.R
import com.example.criticaltechworkschallenge.adapters.NewsAdapter
import com.example.criticaltechworkschallenge.enums.SourcesEnum
import com.example.criticaltechworkschallenge.ui.newsSource.NewsSourceDialogFragment
import com.example.criticaltechworkschallenge.ui.newsSource.NewsSourceSelectionListener

class HomeFragment : Fragment(), NewsAdapter.OnItemClickListener, NewsSourceSelectionListener {

    private lateinit var _vm: HomeViewModel

    private lateinit var _adapter: NewsAdapter
    private lateinit var _button: Button
    private lateinit var _recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        _vm = ViewModelProvider(this)[HomeViewModel::class.java]
        val parent = activity as MainActivity

        _vm.newsSource.observe(viewLifecycleOwner) { source ->
            parent.supportActionBar?.title = source.sourceName
        }

        return init(view)
    }

    private fun init(view: View): View {

        // Find Views
        _button = view.findViewById(R.id.button)
        _recyclerView = view.findViewById(R.id.recyclerView)

        // Set static text
        _button.text = _vm.buttonText.value

        // Set click listener
        _button.setOnClickListener {
            // Show the news source dialog
            showNewsSourceDialog()
        }

        // Set adapters
        _adapter = NewsAdapter(mutableListOf())
        _adapter.setOnItemClickListener(this)
        _recyclerView.layoutManager = LinearLayoutManager(context)
        _recyclerView.adapter = _adapter

        _vm.articles.observe(viewLifecycleOwner) { articles ->
            _adapter.updateData(articles)
        }

        return view
    }

    private fun showNewsSourceDialog() {
        val dialog = NewsSourceDialogFragment()
        dialog.setNewsSourceSelectionListener(this)
        dialog.show(childFragmentManager, "NewsSourceDialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onItemClick(article: Article) {
        // Bundling items
        val bundle = Bundle()
        bundle.putString("titleArg", article.title)
        bundle.putString("urlToImageArg", article.urlToImage)
        bundle.putString("descriptionArg", article.description)
        bundle.putString("contentArg", article.content)
        bundle.putString("urlArg", article.url)

        findNavController().navigate(R.id.navigation_news_details, bundle)
    }

    override fun onNewsSourceSelected(source: SourcesEnum) {
        // Update the news source in the view model
        _vm.updateSource(source)
        // Reload the news to display the new source
        _vm.loadHeadlines()
    }
}