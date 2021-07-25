package com.github.nyafunta.rssreader.ui.feed

import android.view.View
import com.github.nyafunta.rssreader.R
import com.github.nyafunta.rssreader.databinding.ItemRssFeedSecondaryBinding
import com.xwray.groupie.viewbinding.BindableItem

class RssFeedItemSecondary(
    private val store: RssFeedItemDataStore,
    private val onItemClicked: OnItemClickListener
) : BindableItem<ItemRssFeedSecondaryBinding>() {

    override fun initializeViewBinding(view: View): ItemRssFeedSecondaryBinding =
        ItemRssFeedSecondaryBinding.bind(view)

    override fun bind(viewBinding: ItemRssFeedSecondaryBinding, position: Int) {
        viewBinding.store = store
        viewBinding.root.setOnClickListener {
            onItemClicked(position)
        }
    }

    override fun getLayout(): Int = R.layout.item_rss_feed_secondary
}