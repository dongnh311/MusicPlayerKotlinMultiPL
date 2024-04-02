package commonShare

import const.PLATFORM_IOS

class IOSPlatform: Platform {
    override val name: String = PLATFORM_IOS
}

actual fun getPlatform(): Platform = IOSPlatform()