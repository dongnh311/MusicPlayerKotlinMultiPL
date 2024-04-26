package commonShare

import co.touchlab.kermit.Logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import model.ImagePickerModel
import model.UserModel
import cocoapods.FirebaseStorage.*
import const.FIREBASE_STORAGE_AVATAR
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.await
import dev.gitlive.firebase.storage.File
import dev.gitlive.firebase.storage.Progress
import dev.gitlive.firebase.storage.StorageReference
import dev.gitlive.firebase.storage.await
import dev.gitlive.firebase.storage.awaitResult
import dev.gitlive.firebase.storage.storage
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import platform.Foundation.NSCoder
import platform.Foundation.NSError
import platform.Foundation.NSURL

open class IOsFirebaseStorageShare : FirebaseStorageShare {

    // Firebase storage
    private val storage = Firebase.storage

    // Refer to upload avatar
    private lateinit var storageUpload: StorageReference

    override fun uploadAvatar(
        userModel: UserModel,
        imagePickerModel: ImagePickerModel
    ): Flow<*> {
        return callbackFlow {
            val file = File(NSURL(string = "file://" + imagePickerModel.mediaPath))
            storageUpload = storage.reference.child("$FIREBASE_STORAGE_AVATAR/${userModel.id}")

            val avatarRefer =
                storageUpload.child("/avatar.jpg")

            try {
                val jobUpload = avatarRefer.putFile(file)
                jobUpload.await {
                    val newAvatarUrl = avatarRefer.getDownloadUrl()
                    userModel.profileImage = newAvatarUrl
                    trySend(true)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

            trySend(true)
            awaitClose {
                close()
            }
        }
    }

    override fun loadUrlFileStorage(firebaseUrl: String) = callbackFlow {
        val introRefer = storage.reference(firebaseUrl)
        val task = introRefer.getDownloadUrl()
        trySend(task)

        awaitClose { close() }
    }

    override fun uploadImageFileToStorage(imagePath: String, storageReference: String) = callbackFlow {
        val storageReferenceUpload = storage.reference.child(storageReference)
        val file = File(NSURL(string = "file://$imagePath"))

        val avatarRefer =
            storageReferenceUpload.child("/thumbnail.jpg")

        try {
            avatarRefer.putFile(file).await {
                Logger.e("Upload done")
                val newAvatarUrl = avatarRefer.getDownloadUrl()
                trySend(newAvatarUrl)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        trySend("")
        awaitClose { close() }
    }
}

actual fun loadFireBaseStorage(): FirebaseStorageShare {
    return IOsFirebaseStorageShare()
}