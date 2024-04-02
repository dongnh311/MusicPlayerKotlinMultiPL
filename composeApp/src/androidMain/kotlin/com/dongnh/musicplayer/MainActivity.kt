package com.dongnh.musicplayer

import App
import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
        // Save context
        AndroidMusicPlayerSingleton.mainActivity = this@MainActivity
        AndroidMusicPlayerSingleton.initPermissionResult()
        AndroidMusicPlayerSingleton.androidFirebaseAuthControl.initGoogleSignIn()
    }

    override fun onDestroy() {
        super.onDestroy()
        AndroidMusicPlayerSingleton.mainActivity = null
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}