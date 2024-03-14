package base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
@Serializable
open class BaseModel {
    @SerialName("id")
    open var id: Int = 0

    @SerialName("createdAt")
    var createdAt: Long = 0L

    @SerialName("updatedAt")
    var updatedAt: Long? = 0L

    /**
     * Compare object
     *
     * @param targetCompare : BaseModel
     * @return : Boolean
     */
    fun compareObject(targetCompare: BaseModel): Boolean {
        return this@BaseModel === targetCompare
    }

}