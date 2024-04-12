package commonShare

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import model.ImagePickerModel
import model.UserModel

class IOsFirebaseStorageShare : FirebaseStorageShare {
    override fun uploadAvatar(
        userModel: UserModel,
        imagePickerModel: ImagePickerModel
    ): Flow<*> {
        return callbackFlow {
            trySend(true)
            awaitClose {
                close()
            }
        }
    }

    override fun loadUrlFileStorage(firebaseUrl: String) = callbackFlow {
        trySend("")
        awaitClose {
            close()
        }
    }

    override fun uploadImageFileToStorage(imagePath: String, storageReference: String) = callbackFlow {
        trySend("")
        awaitClose {
            close()
        }
    }
}

actual fun loadFireBaseStorage(): FirebaseStorageShare {
    return IOsFirebaseStorageShare()
}