package commonShare

import android.os.Build
import const.PLATFORM_ANDROID

class AndroidPlatform : Platform {
    override val name: String = PLATFORM_ANDROID
}

actual fun getPlatform(): Platform = AndroidPlatform()