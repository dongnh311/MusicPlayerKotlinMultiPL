package commonShare

import com.dongnh.musicplayer.AndroidMusicPlayerSingleton
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import model.ImagePickerModel
import utils.exts.checkPermissionAudioStorage

class PermissionControlAndroid : PermissionControl {

    override var callBackResultPermission: CallBackResultPermission? = null

    override fun checkPermissionStorage(): Boolean {
         AndroidMusicPlayerSingleton.mainActivity?.let {
             return it.checkPermissionAudioStorage()
        }

        return false
    }

    override fun requestPermissionStorage()  {
        AndroidMusicPlayerSingleton.requestPermissionStorage {
            callBackResultPermission?.onResultPermission(it)
        }
    }

    override fun loadAllImageMedia() = callbackFlow {
       trySend(AndroidMusicPlayerSingleton.loadAllImageOnDevice())
        awaitClose {
            close()
        }
    }
}

actual fun loadPermissionControl(): PermissionControl {
    return PermissionControlAndroid()
}