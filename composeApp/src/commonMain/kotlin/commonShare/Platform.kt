package commonShare

interface Platform {
    val name: String
    val versionApp : String
}

expect fun getPlatform(): Platform