package commonShare

import const.PLATFORM_IOS
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = PLATFORM_IOS
    override val versionApp: String = "1.0.0"
}

actual fun getPlatform(): Platform = IOSPlatform()