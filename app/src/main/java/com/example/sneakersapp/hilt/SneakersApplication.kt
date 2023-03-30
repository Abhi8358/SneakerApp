package com.example.sneakersapp.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SneakersApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

}