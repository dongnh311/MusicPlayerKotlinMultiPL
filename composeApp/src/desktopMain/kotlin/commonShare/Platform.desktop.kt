package commonShare

import const.PLATFORM_DESKTOP
import const.PLATFORM_IOS

class DesktopPlatform: Platform {
    override val name: String = PLATFORM_DESKTOP
    override val versionApp: String = "1.0.0"
}

actual fun getPlatform(): Platform {
    return DesktopPlatform()
}