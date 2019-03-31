package com.example.squareboat.network

import com.example.squareboat.App
import com.example.squareboat.R
import java.io.IOException
import java.net.SocketTimeoutException

class ErrorHandler {
    companion object {
        fun handleError(e: Throwable): String {
            when (e) {
                is IOException -> return App.instance.getString(R.string.network_error)
                is SocketTimeoutException -> return App.instance.getString(R.string.request_timed_out)
                else -> return App.instance.getString(R.string.something_went_wrong)
            }
        }
    }
}