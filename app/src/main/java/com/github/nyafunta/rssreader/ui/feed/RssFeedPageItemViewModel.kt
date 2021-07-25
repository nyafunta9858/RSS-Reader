package com.github.nyafunta.rssreader.ui.feed

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.nyafunta.rssreader.domain.infra.HatenaFeedRepository
import com.github.nyafunta.rssreader.domain.infra.predefine.Category
import com.github.nyafunta.rssreader.domain.model.RssFeed
import com.github.nyafunta.rssreader.ui.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RssFeedPageItemViewModel @Inject constructor(
    private val repository: HatenaFeedRepository
) : ViewModel() {

    val feed = ObservableField<RssFeed>()

    private val _onRssItemClicked = MutableLiveData<Event<String>>()
    val onRssItemClicked: LiveData<Event<String>> = _onRssItemClicked

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _onError = MutableLiveData<Event<Throwable>>()
    val onError: LiveData<Event<Throwable>> = _onError

    val listener = OnRssItemClickListener(::postRequestLinkCall)

    fun init(category: Category) {
        viewModelScope.launch {
            _isLoading.value = true

            repository.runCatching {
                if (category == Category.ALL) {
                    fetchAll()
                } else {
                    fetchByCategory(category)
                }
            }.onSuccess {
                feed.set(it)
            }.onFailure {
                _onError.value = Event(it)
            }

            _isLoading.value = false
        }
    }

    private fun postRequestLinkCall(link: String) {
        _onRssItemClicked.value = Event(link)
    }
}