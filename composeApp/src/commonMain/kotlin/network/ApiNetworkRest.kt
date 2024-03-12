package network

import co.touchlab.kermit.Logger.Companion.config
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.accept
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.isActive
import kotlinx.serialization.json.Json

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class ApiNetworkRest {
    // URL
    private  var baseUrl = ""
    // Headers
    private var additionalHeaders: HashMap<String, String> = HashMap()

    lateinit var ktorfit : Ktorfit

    private var ktorClient = makeKtorClient()

    /**
     * Create Kotr
     *
     * @param url
     */
    fun createKtor(url: String) {
        ktorClient = makeKtorClient()
        this@ApiNetworkRest.baseUrl = url
        this@ApiNetworkRest.ktorfit = Ktorfit.Builder()
            .baseUrl(url)
            .httpClient(ktorClient)
            .build()
    }

    /**
     * Update header
     *
     * @param additionalHeaders
     */
    fun updateAdditionalHeaders(additionalHeaders: Map<String, String?>) {
        additionalHeaders.forEach { headerItem ->
            val headerValue = headerItem.value
            if (headerValue.isNullOrBlank()) {
                this@ApiNetworkRest.additionalHeaders.remove(headerItem.key)
            } else {
                this@ApiNetworkRest.additionalHeaders[headerItem.key] = headerValue
            }
        }
    }

    /**
     * Clear all header
     */
    fun clearAllAdditionalHeaders() {
        this@ApiNetworkRest.additionalHeaders.clear()
    }

    /**
     * Make Http Client
     *
     * @return HttpClient
     */
    private fun makeKtorClient() : HttpClient { val urlAPI = this@ApiNetworkRest.baseUrl.ifEmpty { "https://google.com" }
       return  HttpClient() {
            install(ContentNegotiation) {
                json(Json { isLenient = true; ignoreUnknownKeys = true })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
           defaultRequest {
               url(urlAPI)
               contentType(ContentType.Application.Json)
               accept(ContentType.Application.Json)
           }
           install(HttpTimeout) {
               requestTimeoutMillis = 10000
               connectTimeoutMillis = 10000
           }
        }.apply {
           plugin(HttpSend).intercept { req ->
               // Add headers
               additionalHeaders.forEach {
                   req.headers.append(it.key, it.value)
               }

               // Make request send
               val call = execute(req)

               // Handle Unauthorized if need
               if (call.response.status == Unauthorized) {

               }
               call
           }
       }
    }
}