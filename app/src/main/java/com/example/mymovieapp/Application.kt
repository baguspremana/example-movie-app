package com.example.mymovieapp

import android.app.Application
import com.example.mymovieapp.sqlite.AppDatabase
import com.facebook.stetho.Stetho

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.createDatabase(this)
        Stetho.initializeWithDefaults(this)
    }
}