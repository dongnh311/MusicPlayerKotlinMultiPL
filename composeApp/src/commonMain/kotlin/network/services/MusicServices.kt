package network.services

import de.jensklingenberg.ktorfit.http.GET
import model.MusicModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
interface MusicServices {
    @GET("/endPoint")
    suspend fun loadListMusics(): List<MusicModel>
}