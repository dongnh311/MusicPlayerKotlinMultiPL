package utils.helper

import const.FB_DATABASE_USER
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import model.UserModel
import utils.exts.toStringRemoveNull

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
    suspend fun logoutAuth(callback: () -> Unit) {
        if (auth.currentUser != null) {
            auth.signOut()
            callback.invoke()
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
        return auth.currentUser?.uid.toStringRemoveNull()
    }

    /**
     * Create new account with email and password
     *
     * @param email
     * @param password
     * @return
     */
    suspend fun createNewAccountWithEmail(email: String, password: String) : FirebaseUser? {
        val result = auth.createUserWithEmailAndPassword(email, password)
        return result.user
    }

    /**
     * Login with email and password
     *
     * @param email
     * @param password
     * @return
     */
    suspend fun loginWithEmailPassword(email: String, password: String): String {
        val result = auth.signInWithEmailAndPassword(email, password)
        return if (result.user?.uid?.isNotEmpty() == true) {
            result.user?.uid.toString()
        } else {
            ""
        }
    }

    /**
     * Send email reset pass word
     *
     * @param email
     */
    suspend fun forgotPassword(email: String) {
        auth.sendPasswordResetEmail(email)
    }

    /**
     * Update new password
     *
     * @param password
     * @param code
     */
    suspend fun resetPasswordForEmail( code: String, password: String) {
        auth.confirmPasswordReset(code, password)
    }

    /**
     * Send email verify to user
     */
    suspend fun sendVerifiedEmail() {
        auth.currentUser?.let {
            if (!it.isEmailVerified) {
                auth.languageCode = "vi"
                it.sendEmailVerification()
            }
        }
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
     * Update information of user
     *
     * @param userModel
     */
    suspend fun updateInformationUser(userModel: UserModel) {
        firebaseStore.collection(FB_DATABASE_USER).document(userModel.id).update(Pair("userName", userModel.userName))
        firebaseStore.collection(FB_DATABASE_USER).document(userModel.id).update(Pair("profileImage", userModel.profileImage))
        firebaseStore.collection(FB_DATABASE_USER).document(userModel.id).update(Pair("fcmToken", userModel.fcmToken))
        firebaseStore.collection(FB_DATABASE_USER).document(userModel.id).update(Pair("platform", userModel.platform))
        firebaseStore.collection(FB_DATABASE_USER).document(userModel.id).update(Pair("dayOfBirth", userModel.dayOfBirth))
        firebaseStore.collection(FB_DATABASE_USER).document(userModel.id).update(Pair("accountType", userModel.accountType))
    }

    /**
     * Update token and platform
     *
     * @param userModel
     */
    suspend fun updatePlatformAndToken(userModel: UserModel) {
        firebaseStore.collection(FB_DATABASE_USER).document(userModel.id).update(Pair("fcmToken", userModel.fcmToken))
        firebaseStore.collection(FB_DATABASE_USER).document(userModel.id).update(Pair("platform", userModel.platform))
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

    /**
     * Update new Password
     *
     * @param email
     * @param oldPassword
     * @param newPassword
     * @return
     */
    suspend fun updateNewPassword(oldPassword: String, newPassword: String) : Boolean {
        val result = auth.signInWithEmailAndPassword(auth.currentUser?.email.toString(), oldPassword)
        return if (result.user != null) {
            auth.currentUser?.updatePassword(newPassword)
            true
        } else {
            false
        }
    }
}