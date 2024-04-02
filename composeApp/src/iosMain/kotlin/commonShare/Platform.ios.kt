package commonShare

import const.PLATFORM_IOS
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = PLATFORM_IOS
}

actual fun getPlatform(): Platform = IOSPlatform()