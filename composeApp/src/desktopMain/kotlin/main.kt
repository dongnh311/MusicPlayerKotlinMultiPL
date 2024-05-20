import android.app.Application
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.google.firebase.FirebasePlatform
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.initialize

fun main() = application {
    try {
        FirebasePlatform.initializeFirebasePlatform(object : FirebasePlatform() {
            val storage = mutableMapOf<String, String>()
            override fun store(key: String, value: String) = storage.set(key, value)
            override fun retrieve(key: String) = storage[key]
            override fun clear(key: String) { storage.remove(key) }
            override fun log(msg: String) = println(msg)
        })

        // Manually configure Firebase Options. The following fields are REQUIRED:
        //   - Project ID "my-firebase-project"
        //   - App ID
        //   - API Key
        val options = FirebaseOptions(applicationId = "1:997870831832:android:92c96e0c8812f1c8eaafad", apiKey = "AIzaSyDcicZCkslTsMmrlsqPwmedb9fO6YE5UvU", projectId = "musicplayer-c39cb")

        Firebase.initialize(Application(), options)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    val windowState = WindowState(size = DpSize(width = 450.dp, height = 950.dp))
    Window(
        state = windowState,
        onCloseRequest = ::exitApplication,
        title = "Desktop-MusicPlayer",
    ) {
        App()
    }
}