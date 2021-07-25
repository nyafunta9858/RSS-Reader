package com.github.nyafunta.rssreader.ui.feed

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.nyafunta.rssreader.domain.infra.HatenaFeedRepository
import com.github.nyafunta.rssreader.domain.infra.enum.Category
import com.github.nyafunta.rssreader.domain.model.RssFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RssFeedViewModel @Inject constructor(
    private val repository: HatenaFeedRepository
) : ViewModel() {

    val feed = ObservableField<RssFeed>()

    fun init(category: Category) {
        viewModelScope.launch {
            feed.set(repository.get(category))
        }
    }

}