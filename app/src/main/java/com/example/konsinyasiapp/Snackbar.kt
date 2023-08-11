package com.example.konsinyasiapp

import android.view.View
import com.google.android.material.snackbar.Snackbar

const val SNACKBAR_DURATION = 500

fun View.customSnackbar(msg: String, anchorViewId: Int) {
    val snackbar = Snackbar.make(this, msg, Snackbar.LENGTH_LONG)
    val anchorView = findViewById<View>(anchorViewId)
    snackbar.anchorView = anchorView
    snackbar.show()
    SNACKBAR_DURATION
}