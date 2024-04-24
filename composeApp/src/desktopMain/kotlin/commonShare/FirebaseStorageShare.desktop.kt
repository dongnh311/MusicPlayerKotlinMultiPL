package commonShare

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import model.ImagePickerModel
import model.UserModel

class DesktopFirebaseStorageShare : FirebaseStorageShare {
    override fun uploadAvatar(userModel: UserModel, imagePickerModel: ImagePickerModel): Flow<*> {
        return callbackFlow {
            trySend("")
            awaitClose {
                close()
            }
        }
    }

    override fun loadUrlFileStorage(firebaseUrl: String): Flow<String> {
        return callbackFlow {
            trySend("")
            awaitClose {
                close()
            }
        }
    }

    override fun uploadImageFileToStorage(
        imagePath: String,
        storageReference: String
    ) =  kotlinx.coroutines.flow.callbackFlow {
        trySend("")
        awaitClose {
            close()
        }
    }
}

actual fun loadFireBaseStorage(): FirebaseStorageShare {
    return DesktopFirebaseStorageShare()
}