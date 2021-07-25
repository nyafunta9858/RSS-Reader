package com.github.nyafunta.rssreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.nyafunta.rssreader.domain.infra.HatenaFeedRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}