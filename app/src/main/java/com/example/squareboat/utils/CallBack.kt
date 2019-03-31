package com.example.squareboat.utils

interface CallBack<T> {
    fun onSuccess(response: T)
    fun onError(message: String)
}