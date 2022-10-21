package com.example.example.presentation.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ExampleApplication : Application() {
    init{
        Timber.plant(Timber.DebugTree())
    }
}