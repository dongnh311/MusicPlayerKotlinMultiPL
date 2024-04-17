package commonShare

import co.touchlab.kermit.Logger
import const.PERMISSION_DENIED
import const.PERMISSION_GRAND
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.NativePtr
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import model.ImagePickerModel
import platform.CoreGraphics.CGSize
import platform.CoreGraphics.CGSizeMake
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSSortDescriptor
import platform.Foundation.NSUserDomainMask
import platform.Photos.*
import platform.UIKit.UIImage
import platform.darwin.NSUInteger

class PermissionControlIOs: PermissionControl {

    override var callBackResultPermission: CallBackResultPermission? = null

    override fun checkPermissionStorage(): Boolean {
        Logger.e("Status : ${PHPhotoLibrary.authorizationStatus()}")
        when (PHPhotoLibrary.authorizationStatus()) {
            PHAuthorizationStatusAuthorized -> {
                return true
            }

            PHAuthorizationStatusNotDetermined -> {
                return false
            }

            PHAuthorizationStatusDenied -> {
                return false
            }
        }

        return false
    }

    override fun requestPermissionStorage()  {
        PHPhotoLibrary.Companion.requestAuthorization { newStatus ->
            when (newStatus) {
                PHAuthorizationStatusAuthorized -> {
                    callBackResultPermission?.onResultPermission(PERMISSION_GRAND)
                }

                PHAuthorizationStatusNotDetermined -> {
                    callBackResultPermission?.onResultPermission(PERMISSION_DENIED)
                }

                PHAuthorizationStatusDenied -> {
                    callBackResultPermission?.onResultPermission(PERMISSION_DENIED)
                }
            }
        }
    }

    override fun loadAllImageMedia() = callbackFlow {
        val listImageReturn = mutableListOf<ImagePickerModel>()

        val fetchOptions = PHFetchOptions()
        val allPhoto = PHAsset.fetchAssetsWithOptions(fetchOptions)
        if (allPhoto.count > 0u) {
            for (index in 0u until allPhoto.count().toUInt()) {
                val item = allPhoto.objectAtIndex(index.toULong()) as PHAsset
                val task = callbackFlow {
                    item.requestContentEditingInputWithOptions(
                        PHContentEditingInputRequestOptions()
                    ) { input, _ ->
                        input?.let {
                            val url = input.fullSizeImageURL

                            val imagePickerModel = ImagePickerModel()
                            imagePickerModel.mediaId = index.toString()
                            if (url != null) {
                                imagePickerModel.mediaPath = url.absoluteString?.removePrefix("file://") ?: ""
                                Logger.e("Path of image: ${imagePickerModel.mediaPath}")
                            }

                            trySend(imagePickerModel)
                        }
                    }
                    awaitClose { close() }
                }
                listImageReturn.add(task.first())

                Logger.e("Load image with size: ${listImageReturn.size}")
            }
        }
        trySend(listImageReturn)
        awaitClose {
            close()
        }
    }
}

actual fun loadPermissionControl(): PermissionControl {
    return PermissionControlIOs()
}