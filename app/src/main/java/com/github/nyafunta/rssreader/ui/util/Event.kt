package com.github.nyafunta.rssreader.ui.util

import androidx.lifecycle.Observer

class Event<out T>(private val content: T) {
    private var alreadyHandled = false

    fun getOrNull(): T? = if (!alreadyHandled) {
        alreadyHandled = true
        content
    } else null

}

class EventObserver<T>(private val onEventUnhandled: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getOrNull()?.let(onEventUnhandled)
    }
}