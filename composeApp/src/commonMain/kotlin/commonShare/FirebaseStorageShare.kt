package commonShare

import dev.gitlive.firebase.storage.StorageReference
import kotlinx.coroutines.flow.Flow
import model.ImagePickerModel
import model.UserModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 9/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
interface FirebaseStorageShare {
    /**
     * Upload avatar of user
     *
     * @param userModel
     * @param imagePickerModel
     * @return
     */
    fun uploadAvatar(userModel: UserModel, imagePickerModel: ImagePickerModel) : Flow<*>

    /**
     * Load url form gs
     *
     * @param firebaseUrl
     * @return
     */
    fun loadUrlFileStorage(firebaseUrl: String) : Flow<String>

    /**
     * Upload image to path
     *
     * @param imagePath
     * @param storageReference
     */
    fun uploadImageFileToStorage(imagePath: String, storageReference: String) : Flow<String>
}

expect fun loadFireBaseStorage() : FirebaseStorageShare