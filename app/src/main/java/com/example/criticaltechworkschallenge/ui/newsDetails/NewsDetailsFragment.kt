package com.example.criticaltechworkschallenge.ui.newsDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.criticaltechworkschallenge.MainActivity
import com.example.criticaltechworkschallenge.R
import com.squareup.picasso.Picasso

class NewsDetailsFragment : Fragment() {

    private lateinit var _vm: NewsDetailsViewModel

    private lateinit var _title: TextView
    private lateinit var _image: ImageView
    private lateinit var _description: TextView
    private lateinit var _content: TextView
    private lateinit var _url: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_news_details, container, false)

        _vm = ViewModelProvider(this)[NewsDetailsViewModel::class.java]
        val parent = activity as MainActivity;
        parent.supportActionBar?.title = "Article Details"

        // parent.supportActionBar?.setDisplayHomeAsUpEnabled(true) // enable back button in top bar - its activated by default
        return init(view)
    }

    private fun init(view: View): View {

        // Find Views
        _title = view.findViewById(R.id.title)
        _image = view.findViewById(R.id.image)
        _description = view.findViewById(R.id.description)
        _content= view.findViewById(R.id.content)
        _url = view.findViewById(R.id.url)

        // Get arguments
        val title = arguments?.getString("titleArg")
        val urlToImage = arguments?.getString("urlToImageArg")
        val description = arguments?.getString("descriptionArg")
        val content = arguments?.getString("contentArg")
        val url = arguments?.getString("urlArg")

        // Set static text
        _title.text = title
        _description.text = description
        _content.text = content
        _url.text = url

        // Set image
        Picasso.get().load(urlToImage).into(_image)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}