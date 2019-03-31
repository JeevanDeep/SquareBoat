package com.example.squareboat.utils

import android.view.View

fun View.setGone() {
    visibility = View.GONE
}

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}

fun String.toDollar() = "$this$"