package commonShare

import android.net.Uri
import co.touchlab.kermit.Logger
import com.google.firebase.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import const.FIREBASE_STORAGE_AVATAR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import model.ImagePickerModel
import model.UserModel
import timber.log.Timber
import java.io.File
import java.io.FileInputStream

class AndroidFirebaseStorageShare : FirebaseStorageShare {

    // Firebase storage
    private val storage = Firebase.storage

    // Refer to upload avatar
    private lateinit var storageUpload: StorageReference

    /**
     * Init storage refer for upload file
     */
    private fun initReferUpload(userModel: UserModel) {
        if (!::storageUpload.isInitialized) {
            storageUpload = storage.reference.child("$FIREBASE_STORAGE_AVATAR/${userModel.id}")
        }
    }

    /**
     * Upload avatar to user
     */
    override fun uploadAvatar(userModel: UserModel, imagePickerModel: ImagePickerModel) = callbackFlow {
        val file = File(imagePickerModel.mediaPath)
        if (file.exists()) {
            initReferUpload(userModel)
            val avatarRefer =
                storageUpload.child("/avatar.jpg")
            val stream = FileInputStream(file)

            val uploadTask = avatarRefer.putStream(stream)
            uploadTask.addOnFailureListener {
                Logger.e("Upload avatar not complete")
            }.addOnSuccessListener { taskSnapshot ->
                if (taskSnapshot.task.isSuccessful) {
                    loadUrlDownLoadOfRefer(avatarRefer) { uri ->
                        userModel.profileImage = uri.toString()
                        trySend(true)
                    }
                } else {
                    Logger.e("Can't find path for file")
                    trySend(false)
                }
            }
        } else {
            trySend(false)
        }
        awaitClose { close() }
    }.flowOn(Dispatchers.IO)

    /**
     * Load url download file
     *
     * @param introRefer
     * @param callBack
     */
    private fun loadUrlDownLoadOfRefer(introRefer: StorageReference, callBack: (Uri) -> Unit) {
        introRefer.downloadUrl.addOnCompleteListener {
            callBack.invoke(it.result)
        }
    }

    /**
     * Load firebase url image
     *
     * @param firebaseUrl
     */
    override fun loadUrlFileStorage(firebaseUrl: String) = callbackFlow {
        val introRefer = storage.getReferenceFromUrl(firebaseUrl)
        introRefer.downloadUrl.addOnCompleteListener {
            trySend(it.result.toString())
        }.addOnFailureListener {
            it.printStackTrace()
            Timber.e("Fail load url file in storage firebase")
        }

        awaitClose { close() }
    }

    /**
     * Upload image to path
     *
     * @param imagePath
     * @param storageReference
     */
    override fun uploadImageFileToStorage(imagePath: String, storageReference: String) = callbackFlow {
        val storageReferenceUpload = storage.reference.child(storageReference)
        val file = File(imagePath)
        if (file.exists()) {
            val avatarRefer =
                storageReferenceUpload.child("/thumbnail.jpg")
            val stream = FileInputStream(file)

            val uploadTask = avatarRefer.putStream(stream)
            uploadTask.addOnFailureListener {
                Logger.e("Upload avatar not complete")
            }.addOnSuccessListener { taskSnapshot ->
                if (taskSnapshot.task.isSuccessful) {
                    loadUrlDownLoadOfRefer(avatarRefer) { uri ->
                        val thumbnailUrl = uri.toString()
                        trySend(thumbnailUrl)
                    }
                } else {
                    Logger.e("Can't find path for file")
                    trySend("")
                }
            }
        } else {
            trySend("")
        }
        awaitClose { close() }
    }.flowOn(Dispatchers.IO)
}

actual fun loadFireBaseStorage(): FirebaseStorageShare {
    return AndroidFirebaseStorageShare()
}