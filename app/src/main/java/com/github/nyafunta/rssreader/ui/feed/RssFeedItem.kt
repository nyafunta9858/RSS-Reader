package com.github.nyafunta.rssreader.ui.feed

import android.view.View
import coil.imageLoader
import com.github.nyafunta.rssreader.R
import com.github.nyafunta.rssreader.databinding.ItemRssFeedBinding
import com.xwray.groupie.viewbinding.BindableItem

class RssFeedItem(private val store: RssFeedItemDataStore) : BindableItem<ItemRssFeedBinding>() {

    override fun initializeViewBinding(view: View): ItemRssFeedBinding =
        ItemRssFeedBinding.bind(view)

    override fun bind(viewBinding: ItemRssFeedBinding, position: Int) {
        viewBinding.store = store
    }

    override fun getLayout(): Int = R.layout.item_rss_feed
}