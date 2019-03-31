package com.example.squareboat

import android.app.Application
import com.example.squareboat.di.ApplicationComponent
import com.example.squareboat.di.ApplicationModule
import com.example.squareboat.di.DaggerApplicationComponent
import com.example.squareboat.utils.TLSSocketFactoryCompat
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        setupComponent()
    }

    private fun setupComponent() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.inject(this)


        val client = OkHttpClient.Builder()
            .sslSocketFactory(TLSSocketFactoryCompat())
            .build()

        val picasso = Picasso.Builder(this)
            .downloader(OkHttp3Downloader(client))
            .build()

        Picasso.setSingletonInstance(picasso)
    }


    companion object {

        lateinit var component: ApplicationComponent
            private set
        lateinit var instance: App
            private set
    }
}