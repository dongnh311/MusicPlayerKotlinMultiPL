package singleton

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
}