package com.example.p12_

import android.app.Application
import com.example.p12_.dependenciesinjection.AppContainer
import com.example.p12_.dependenciesinjection.MahasiswaContainer

class MahasiswaApplication:Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}