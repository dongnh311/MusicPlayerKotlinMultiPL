package utils.helper

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 15/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class FirebaseAccountHelper {
    // Save auth
    var auth: FirebaseAuth = Firebase.auth

    /**
     * Sign Out auth
     */
    suspend fun logoutAuth() {
        if (auth.currentUser != null) {
            auth.signOut()
        }
    }
}