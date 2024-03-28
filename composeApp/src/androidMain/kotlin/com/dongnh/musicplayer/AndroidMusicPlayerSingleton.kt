package com.dongnh.musicplayer

import commonShare.AndroidFirebaseAuthControl

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 28/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
object AndroidMusicPlayerSingleton {
    var mainActivity: MainActivity? = null
    val androidFirebaseAuthControl: AndroidFirebaseAuthControl = AndroidFirebaseAuthControl()
}