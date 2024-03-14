package network.services

import de.jensklingenberg.ktorfit.Response
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Url
import model.EventModel
import network.response.NotifyResponse

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
interface EventServices {
    @GET("")
    suspend fun loadListEvent(@Url url: String): List<EventModel>

    /**
     * Get list faq
     */
    @GET
    suspend fun loadListNotify(
        @Url url: String,
    ): Response<String>
}