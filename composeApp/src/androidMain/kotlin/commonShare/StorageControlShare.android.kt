package commonShare

import com.dongnh.musicplayer.AndroidMusicPlayerSingleton
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

    override fun loadAllImageMedia(): MutableList<ImagePickerModel> {
       return AndroidMusicPlayerSingleton.loadAllImageOnDevice()
    }
}

actual fun loadPermissionControl(): PermissionControl {
    return PermissionControlAndroid()
}