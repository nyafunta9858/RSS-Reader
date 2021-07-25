package com.github.nyafunta.rssreader.ui.feed

import android.view.View
import com.github.nyafunta.rssreader.R
import com.github.nyafunta.rssreader.databinding.ItemRssFeedBinding
import com.xwray.groupie.viewbinding.BindableItem

class RssFeedItem(
    private val store: RssFeedItemDataStore,
    private val onItemClicked: OnRssItemClickListener
) : BindableItem<ItemRssFeedBinding>() {

    override fun initializeViewBinding(view: View): ItemRssFeedBinding =
        ItemRssFeedBinding.bind(view)

    override fun bind(viewBinding: ItemRssFeedBinding, position: Int) {
        viewBinding.store = store
        viewBinding.root.setOnClickListener {
            onItemClicked(store.link)
        }
    }

    override fun getLayout(): Int = R.layout.item_rss_feed
}