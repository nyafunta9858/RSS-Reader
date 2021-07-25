package com.github.nyafunta.rssreader.ui.feed

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.nyafunta.rssreader.R
import com.github.nyafunta.rssreader.databinding.RssFeedPageItemFragmentBinding
import com.github.nyafunta.rssreader.domain.infra.predefine.Category
import com.github.nyafunta.rssreader.ui.util.EventObserver
import com.google.android.material.snackbar.Snackbar
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
        viewModel.onRssItemClicked.observe(viewLifecycleOwner, EventObserver {
            transition(it)
        })
        viewModel.onError.observe(viewLifecycleOwner, EventObserver {
            Snackbar
                .make(binding.root, R.string.error_on_fetch_rss_feed, Snackbar.LENGTH_SHORT)
                .show()
        })
    }

    private fun transition(link: String) {
        val uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rssFeedList.adapter = null
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