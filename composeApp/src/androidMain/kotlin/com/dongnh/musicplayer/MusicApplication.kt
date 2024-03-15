package com.dongnh.musicplayer

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize
import di.AppModule

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 13/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class MusicApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
    }
}