package commonShare

import android.os.Build
import const.PLATFORM_ANDROID

class AndroidPlatform : Platform {
    override val name: String = PLATFORM_ANDROID
    override val versionApp: String = "1.0.0"
}

actual fun getPlatform(): Platform = AndroidPlatform()