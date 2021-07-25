package com.github.nyafunta.rssreader.ui.feed

import androidx.lifecycle.ViewModel
import com.github.nyafunta.rssreader.domain.infra.predefine.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RssFeedViewModel @Inject constructor() : ViewModel() {

    val categories = Category.values().toList()

}