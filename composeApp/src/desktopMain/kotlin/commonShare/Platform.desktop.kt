package commonShare

import const.PLATFORM_IOS

class DesktopPlatform: Platform {
    override val name: String = "Desktop"
    override val versionApp: String = "1.0.0"
}

actual fun getPlatform(): Platform {
    return DesktopPlatform()
}