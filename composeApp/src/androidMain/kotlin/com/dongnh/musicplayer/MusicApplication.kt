package com.dongnh.musicplayer

import android.app.Application
import di.AppModule
import di.appModule
import org.koin.core.context.startKoin

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 13/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class MusicApplication: Application() {
    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            modules(appModule())
//        }
    }
}