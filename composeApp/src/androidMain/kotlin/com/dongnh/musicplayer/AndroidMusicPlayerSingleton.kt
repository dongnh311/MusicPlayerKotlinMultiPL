package com.dongnh.musicplayer

import android.Manifest
import android.database.Cursor
import android.media.MediaScannerConnection
import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import commonShare.AndroidFirebaseAuthControl
import const.PERMISSION_DENIED
import const.PERMISSION_GRAND
import model.ImagePickerModel
import timber.log.Timber

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 28/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
object AndroidMusicPlayerSingleton {
    var mainActivity: MainActivity? = null
    val androidFirebaseAuthControl: AndroidFirebaseAuthControl = AndroidFirebaseAuthControl()

    var callBackReturn: ((Int) -> Unit)? = null

    // Call back permissions Storage Result
    var permissionsStoreResultCallback : ActivityResultLauncher<Array<String>>? = null

    /**
     * Ini permission result
     *
     */
    fun initPermissionResult() {
        permissionsStoreResultCallback = mainActivity?.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            var allPermissionOK = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (result[Manifest.permission.READ_MEDIA_IMAGES] == true &&
                    result[Manifest.permission.READ_MEDIA_AUDIO] == true
                ) {
                    allPermissionOK = true
                }
            } else {
                if (result[Manifest.permission.READ_EXTERNAL_STORAGE] == true &&
                    result[Manifest.permission.RECORD_AUDIO] == true
                ) {
                    allPermissionOK = true
                }
            }

            if (allPermissionOK) {
                callBackReturn?.invoke(PERMISSION_GRAND)
            } else {
                callBackReturn?.invoke(PERMISSION_DENIED)
            }
        }
    }

    /**
     * Request permission storage
     */
    fun requestPermissionStorage(callBack: (Int) -> Unit) {
        this@AndroidMusicPlayerSingleton.callBackReturn = callBack

        var listPermission = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.BLUETOOTH
        )

        // Permission for notify of android 33
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            listPermission = arrayOf(
                Manifest.permission.POST_NOTIFICATIONS,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.BLUETOOTH
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listPermission = arrayOf(
                Manifest.permission.POST_NOTIFICATIONS,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.BLUETOOTH
            )
        }
        permissionsStoreResultCallback?.launch(
            listPermission
        )
    }

    /**
     * Load images
     */
    fun loadAllImageOnDevice() : MutableList<ImagePickerModel> {
        val externalString = "external"
        val sortOrder = " DESC"
        val duration = "duration"
        val columns = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.MEDIA_TYPE,
            MediaStore.Files.FileColumns.MIME_TYPE,
            MediaStore.Files.FileColumns.TITLE,
            duration
        )

        val selection =  (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)

        val queryUri = MediaStore.Files.getContentUri(externalString)
        MediaScannerConnection.scanFile(
            mainActivity, arrayOf(externalString), null
        ) { _, _ ->
            Timber.e("Reload media")
        }
        val imageCursor: Cursor? = mainActivity?.contentResolver?.query(
            queryUri,
            columns,
            selection,
            null,  // Selection args (none).
            MediaStore.Files.FileColumns.DATE_ADDED + sortOrder // Sort order.
        )

        // List image
        val listImageModel = mutableListOf<ImagePickerModel>()

        if (imageCursor != null) {
            val imageColumnIndex = imageCursor.getColumnIndex(MediaStore.Files.FileColumns._ID)
            val count = imageCursor.count
            for (i in 0 until count) {
                imageCursor.moveToPosition(i)
                val id = imageCursor.getInt(imageColumnIndex)
                val dataColumnIndex = imageCursor.getColumnIndex(MediaStore.Files.FileColumns.DATA)
                val typeString = imageCursor.getColumnIndex(MediaStore.Files.FileColumns.MIME_TYPE)
                val indexName =
                    imageCursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME)
                val name = imageCursor.getString(indexName)
                val stringType = imageCursor.getString(typeString)
                val path = imageCursor.getString(dataColumnIndex)
                val imageObject = ImagePickerModel()
                imageObject.mediaPath = path
                imageObject.mediaId = id.toString()
                imageObject.mediaType = stringType
                imageObject.mediaName = name

                listImageModel.add(imageObject)
            }
        }

        // Close
        imageCursor?.close()

        return listImageModel
    }
}