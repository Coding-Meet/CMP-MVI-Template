package com.example.cmp_mvi_template

import android.app.Application
import com.example.cmp_mvi_template.di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApplication)
        }
    }
}