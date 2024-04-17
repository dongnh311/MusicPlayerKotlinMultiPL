package commonShare

import kotlinx.coroutines.flow.Flow
import model.ImagePickerModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 02/04/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
interface PermissionControl {

    var callBackResultPermission: CallBackResultPermission?

    fun checkPermissionStorage() : Boolean

    fun requestPermissionStorage()

    fun loadAllImageMedia() : Flow<MutableList<ImagePickerModel>>
}

interface CallBackResultPermission {
    fun onResultPermission(result: Int)
}

expect fun loadPermissionControl() : PermissionControl