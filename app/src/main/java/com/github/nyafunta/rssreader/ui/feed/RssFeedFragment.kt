package com.github.nyafunta.rssreader.ui.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.nyafunta.rssreader.R
import com.github.nyafunta.rssreader.databinding.RssFeedFragmentBinding
import com.wada811.databinding.dataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RssFeedFragment : Fragment(R.layout.rss_feed_fragment) {

    private val binding by dataBinding<RssFeedFragmentBinding>()
    private val viewModel by viewModels<RssFeedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.offscreenPageLimit = 2
        binding.viewPager.adapter = RssFeedCategoryPagerAdapter(this)
        binding.viewModel = viewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewPager.adapter = null
    }
}