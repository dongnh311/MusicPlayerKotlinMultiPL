package singleton

import kotlinx.serialization.json.Json
import network.ApiNetworkRest

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
object NetworkManager {
    var apiNetRest = ApiNetworkRest()

    /**
     * Init data
     *
     * @param baseUrl
     */
    fun iniAPIRest(baseUrl: String) {
        apiNetRest.createKtor(baseUrl)
    }

    // Config manager
    val jsonManager = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }
}