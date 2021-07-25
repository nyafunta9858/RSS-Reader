package com.github.nyafunta.rssreader.ui.feed

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.nyafunta.rssreader.R
import com.github.nyafunta.rssreader.databinding.RssFeedPageItemFragmentBinding
import com.github.nyafunta.rssreader.domain.infra.predefine.Category
import com.github.nyafunta.rssreader.domain.model.RssItem
import com.github.nyafunta.rssreader.ui.util.EventObserver
import com.wada811.databinding.dataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RssFeedPageItemFragment : Fragment(R.layout.rss_feed_page_item_fragment) {

    private val binding by dataBinding<RssFeedPageItemFragmentBinding>()
    private val viewModel by viewModels<RssFeedPageItemViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = requireArguments()
        val category = args[KEY_CATEGORY] as Category
        viewModel.init(category)
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

    companion object {
        private const val KEY_CATEGORY = "KEY_CATEGORY"

        fun newInstance(category: Category): Fragment = RssFeedPageItemFragment().apply {
            arguments = bundleOf(
                KEY_CATEGORY to category
            )
        }
    }
}