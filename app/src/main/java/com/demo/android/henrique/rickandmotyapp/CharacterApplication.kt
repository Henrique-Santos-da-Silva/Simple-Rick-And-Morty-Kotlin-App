package com.demo.android.henrique.rickandmotyapp

import android.app.Application
import com.demo.android.henrique.rickandmotyapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CharacterApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CharacterApplication)

            modules(viewModelModule)
        }
    }
}