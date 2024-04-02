package utils.exts

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 28/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

tailrec fun Context.findActivity(): Activity? = when {
    this is Activity -> this
    else -> (this as? ContextWrapper)?.baseContext?.findActivity()
}

/**
 * Safe check permission
 *
 * @param targetPermission
 * @return
 */
fun Activity.checkSafePermission(targetPermission: String): Boolean {
    return (ActivityCompat.checkSelfPermission(
        this,
        targetPermission
    ) == PackageManager.PERMISSION_GRANTED)
}

/**
 * Check permission read/write Storage and audio
 *
 * @return true if have permission
 */
fun Activity.checkPermissionAudioStorage(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        checkSafePermission(Manifest.permission.READ_MEDIA_IMAGES) &&
                checkSafePermission(Manifest.permission.READ_MEDIA_VIDEO) &&
                checkSafePermission(Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED)
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        checkSafePermission(Manifest.permission.READ_MEDIA_IMAGES) &&
                checkSafePermission(Manifest.permission.READ_MEDIA_VIDEO)
    } else {
        checkSafePermission(Manifest.permission.READ_EXTERNAL_STORAGE) &&
                checkSafePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }
}