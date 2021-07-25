package com.github.nyafunta.rssreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        confirmFinishing()
    }

    private fun confirmFinishing() {
        MaterialAlertDialogBuilder(this)
            .setMessage(getString(R.string.snackbar_message_confirm_finishing))
            .setPositiveButton(android.R.string.ok) { _, _ ->
                finish()
            }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .show()
    }

}