package commonShare

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
    fun uploadAvatar(userModel: UserModel, imagePickerModel: ImagePickerModel) : Flow<*>
}

expect fun loadFireBaseStorage() : FirebaseStorageShare