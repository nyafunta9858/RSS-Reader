package com.github.nyafunta.rssreader.ui.detail

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.github.nyafunta.rssreader.domain.model.RssItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RssDetailViewModel @Inject constructor() : ViewModel() {

    val title = ObservableField("")
    val imageUrl = ObservableField("")
    val description = ObservableField("")
    val isThumbnailVisible = ObservableBoolean(false)

    fun init(rssFeedItem: RssItem) {
        title.set(rssFeedItem.title.orEmpty())
        imageUrl.set(rssFeedItem.image.orEmpty())
        description.set(rssFeedItem.description.orEmpty())
        isThumbnailVisible.set(!rssFeedItem.image.isNullOrEmpty())
    }

}