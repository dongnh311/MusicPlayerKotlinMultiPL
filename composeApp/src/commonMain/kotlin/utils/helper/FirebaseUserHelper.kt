package utils.helper

import const.FB_DATABASE_USER
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import model.UserModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 15/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class FirebaseUserHelper {
    // Save auth
    private var auth: FirebaseAuth = Firebase.auth

    // Firebase store
    private val firebaseStore = Firebase.firestore

    /**
     * Sign Out auth
     */
    suspend fun logoutAuth() {
        if (auth.currentUser != null) {
            auth.signOut()
        }
    }

    /**
     * Check user have login
     *
     * @return
     */
    fun checkUserLogin(): Boolean {
        return auth.currentUser != null
    }

    /**
     * Load user id
     *
     * @return user id
     */
    fun loadUserId(): String {
        return auth.currentUser?.uid.toString()
    }


    /**
     * Write user to firebase
     *
     * @param user
     */
    suspend fun writeUserToFirebaseStore(user: UserModel) {
        firebaseStore.collection(FB_DATABASE_USER).document(user.id).set(UserModel.serializer(), user)
    }

    /**
     * Load information of user
     *
     * @param userId : User Id
     */
    fun loadUserDetailInformation(userId: String) = callbackFlow {
        val document = firebaseStore.collection(FB_DATABASE_USER).document(userId).get()
        val userData = document.data<UserModel>()
        userData.id = userId
        trySend(userData)
        awaitClose {
            close()
        }
    }

    /**
     * Check user have save data
     *
     * @param userId
     */
    fun checkUserInformationExits(userId: String) = callbackFlow {
        trySend(firebaseStore.collection(FB_DATABASE_USER).document(userId).get().exists)
        awaitClose {
            close()
        }
    }
}