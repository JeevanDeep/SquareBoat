package com.example.squareboat.network

import com.example.squareboat.model.icon.IconResponse
import com.example.squareboat.utils.CallBack
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IconRepo @Inject constructor(private var apiClient: ApiInterface) {
    private val compositeDisposable = CompositeDisposable()

    fun getIcons(callBack: CallBack<IconResponse>) {
        compositeDisposable.add(
            apiClient.getIcons()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    callBack.onSuccess(it)
                }, {
                    callBack.onError(ErrorHandler.handleError(it))
                })
        )
    }

    fun getMoreIcons(callBack: CallBack<IconResponse>, offset: Int) {
        compositeDisposable.add(
            apiClient.getMoreIcons(offset = offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    callBack.onSuccess(it)
                }, {
                    callBack.onError(ErrorHandler.handleError(it))
                })
        )
    }

    fun searchIcons(callBack: CallBack<IconResponse>, query: String, offset: Int) {
        compositeDisposable.add(
            apiClient.searchIcons(query = query, offset = offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    callBack.onSuccess(it)
                }, {
                    callBack.onError(ErrorHandler.handleError(it))
                })
        )
    }


    fun clear() {
        compositeDisposable.clear()
    }

}