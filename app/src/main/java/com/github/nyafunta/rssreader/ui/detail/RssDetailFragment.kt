package com.github.nyafunta.rssreader.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.nyafunta.rssreader.R
import com.github.nyafunta.rssreader.databinding.RssDetailFragmentBinding
import com.github.nyafunta.rssreader.domain.model.RssItem
import com.wada811.databinding.dataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RssDetailFragment : Fragment(R.layout.rss_detail_fragment) {

    private val binding by dataBinding<RssDetailFragmentBinding>()
    private val viewModel by viewModels<RssDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = RssDetailFragmentArgs.fromBundle(requireArguments())
        viewModel.init(args.rssItem)

        binding.viewModel = viewModel
    }

}