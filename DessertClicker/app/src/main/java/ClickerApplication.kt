package com.example.android.dessertclicker

import android.app.Application
import timber.log.Timber

class ClickerApplication : Application() {

    //    initialize the Timber library inside the onCreate method
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}