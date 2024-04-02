package commonShare

import const.PERMISSION_DENIED
import model.ImagePickerModel

class PermissionControlIOs: PermissionControl {

    override var callBackResultPermission: CallBackResultPermission? = null

    override fun checkPermissionStorage(): Boolean {
        return true
    }

    override fun requestPermissionStorage()  {
        callBackResultPermission?.onResultPermission(PERMISSION_DENIED)
    }

    override fun loadAllImageMedia(): MutableList<ImagePickerModel> {
        return arrayListOf()
    }
}

actual fun loadPermissionControl(): PermissionControl {
    return PermissionControlIOs()
}