package com.github.nyafunta.rssreader.ui.feed

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.nyafunta.rssreader.domain.infra.HatenaFeedRepository
import com.github.nyafunta.rssreader.domain.infra.enum.Category
import com.github.nyafunta.rssreader.domain.model.RssFeed
import com.github.nyafunta.rssreader.domain.model.RssItem
import com.github.nyafunta.rssreader.ui.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RssFeedViewModel @Inject constructor(
    private val repository: HatenaFeedRepository
) : ViewModel() {

    val feed = ObservableField<RssFeed>()

    private val _onItemClicked = MutableLiveData<Event<RssItem>>()
    val onItemClicked: LiveData<Event<RssItem>> = _onItemClicked

    val listener = OnItemClickListener(::postRssItem)

    fun init(category: Category) {
        viewModelScope.launch {
            feed.set(repository.get(category))
        }
    }

    private fun postRssItem(position: Int) {
        val feed = feed.get() ?: return
        feed.takeIf { position in it.items.indices } ?: return

        _onItemClicked.value = Event(feed.items[position])
    }
}