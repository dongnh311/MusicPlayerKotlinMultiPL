package commonShare

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import model.ImagePickerModel

class DesktopPermissionControl: PermissionControl {
    override var callBackResultPermission: CallBackResultPermission? = null

    override fun checkPermissionStorage(): Boolean {
        return false
    }

    override fun requestPermissionStorage() {
    }

    override fun loadAllImageMedia(): Flow<MutableList<ImagePickerModel>> {
        return callbackFlow {
            trySend(arrayListOf())
            awaitClose {
                close()
            }
        }
    }
}

actual fun loadPermissionControl(): PermissionControl {
    return DesktopPermissionControl()
}