package com.github.nyafunta.rssreader.ui.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.github.nyafunta.rssreader.domain.model.RssItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RssDetailViewModel @Inject constructor() : ViewModel() {

    val title = ObservableField("")

    fun init(rssFeedItem: RssItem) {
        title.set(rssFeedItem.title)
    }

}