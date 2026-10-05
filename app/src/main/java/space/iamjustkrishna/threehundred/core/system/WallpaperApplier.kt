package space.iamjustkrishna.threehundred.core.system

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

/**
 * Native Android WallpaperManager 1-Tap Applier.
 * Sets True AMOLED wallpapers to Home Screen, Lock Screen, or Both with zero root or third-party launchers.
 */
class WallpaperApplier(private val context: Context) {

    enum class TargetScreen {
        HOME,
        LOCK,
        BOTH
    }

    suspend fun applyWallpaper(bitmap: Bitmap, target: TargetScreen = TargetScreen.BOTH): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                val wallpaperManager = WallpaperManager.getInstance(context)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    val whichFlag = when (target) {
                        TargetScreen.HOME -> WallpaperManager.FLAG_SYSTEM
                        TargetScreen.LOCK -> WallpaperManager.FLAG_LOCK
                        TargetScreen.BOTH -> WallpaperManager.FLAG_SYSTEM or WallpaperManager.FLAG_LOCK
                    }
                    wallpaperManager.setBitmap(bitmap, null, true, whichFlag)
                } else {
                    wallpaperManager.setBitmap(bitmap)
                }
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    suspend fun applyWallpaperFromStream(stream: InputStream, target: TargetScreen = TargetScreen.BOTH): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                val bitmap = BitmapFactory.decodeStream(stream) ?: return@withContext Result.failure(
                    IllegalArgumentException("Failed to decode image stream into bitmap")
                )
                applyWallpaper(bitmap, target)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}
