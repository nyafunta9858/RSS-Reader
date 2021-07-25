package com.github.nyafunta.rssreader.ui.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.nyafunta.rssreader.R
import com.github.nyafunta.rssreader.databinding.RssFeedFragmentBinding
import com.github.nyafunta.rssreader.domain.infra.enum.Category
import com.github.nyafunta.rssreader.domain.model.RssItem
import com.github.nyafunta.rssreader.ui.util.EventObserver
import com.wada811.databinding.dataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RssFeedFragment : Fragment(R.layout.rss_feed_fragment) {

    private val binding by dataBinding<RssFeedFragmentBinding>()
    private val viewModel by viewModels<RssFeedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.init(Category.GENERAL)
        binding.viewModel = viewModel
        observe()
    }

    private fun observe() {
        viewModel.onItemClicked.observe(viewLifecycleOwner, EventObserver {
            transition(it)
        })
    }

    private fun transition(rssItem: RssItem) {
        findNavController().navigate(RssFeedFragmentDirections.toDetail(rssItem))
    }
}