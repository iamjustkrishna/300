package space.iamjustkrishna.threehundred.core.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import space.iamjustkrishna.threehundred.core.database.ThreeHundredDatabase
import space.iamjustkrishna.threehundred.core.database.WallpaperDao
import space.iamjustkrishna.threehundred.core.database.toEntity
import space.iamjustkrishna.threehundred.core.database.toExternalModel
import space.iamjustkrishna.threehundred.core.model.SpartanWallpaper

/**
 * Repository providing access to curated 4K True AMOLED wallpapers with offline-first Room caching
 * and automatic first-run wallpaper catalog seeding.
 */
class WallpaperRepository(
    private val wallpaperDao: WallpaperDao
) {
    constructor(context: Context) : this(
        ThreeHundredDatabase.getInstance(context).wallpaperDao()
    )

    @Volatile
    private var isSeeded = false

    /**
     * Observable stream of all curated 4K AMOLED wallpapers.
     * Seeds initial launch wallpapers if the database is uninitialized.
     */
    val wallpapers: Flow<List<SpartanWallpaper>> = flow {
        seedInitialWallpapersIfEmpty()
        emitAll(wallpaperDao.getAllWallpapers().map { entities ->
            entities.map { it.toExternalModel() }
        })
    }

    fun getAllWallpapers(): Flow<List<SpartanWallpaper>> = flow {
        seedInitialWallpapersIfEmpty()
        emitAll(wallpaperDao.getAllWallpapers().map { entities ->
            entities.map { it.toExternalModel() }
        })
    }

    fun getWallpapersByCategory(category: String): Flow<List<SpartanWallpaper>> = flow {
        seedInitialWallpapersIfEmpty()
        emitAll(wallpaperDao.getWallpapersByCategory(category).map { entities ->
            entities.map { it.toExternalModel() }
        })
    }

    fun getWallpaperById(id: String): Flow<SpartanWallpaper?> = flow {
        seedInitialWallpapersIfEmpty()
        emitAll(wallpaperDao.getWallpaperById(id).map { it?.toExternalModel() })
    }

    suspend fun toggleFavorite(id: String) {
        wallpaperDao.toggleFavorite(id)
    }

    suspend fun seedInitialWallpapersIfEmpty() {
        if (!isSeeded) {
            if (wallpaperDao.getWallpaperCount() == 0) {
                wallpaperDao.insertWallpapers(LAUNCH_WALLPAPERS.map { it.toEntity() })
            }
            isSeeded = true
        }
    }

    companion object {
        val LAUNCH_WALLPAPERS = listOf(
            SpartanWallpaper(
                id = "wp_obsidian_ritual",
                title = "Colonnade of Braziers",
                category = "Featured",
                resUrl = "file:///android_asset/wallpapers/obsidian_ritual.webp",
                isVanguardExclusive = false,
                isFavorite = false
            ),
            SpartanWallpaper(
                id = "wp_spartan_dawn",
                title = "The Spartan Sunrise",
                category = "AMOLED",
                resUrl = "file:///android_asset/wallpapers/spartan_dawn.webp",
                isVanguardExclusive = false,
                isFavorite = false
            ),
            SpartanWallpaper(
                id = "wp_corinthian_helm",
                title = "Corinthian Bronze Helm",
                category = "Armor",
                resUrl = "file:///android_asset/wallpapers/corinthian_helm.webp",
                isVanguardExclusive = true,
                isFavorite = false
            ),
            SpartanWallpaper(
                id = "wp_aegean_noir",
                title = "Aegean Deep Trench",
                category = "Minimal",
                resUrl = "file:///android_asset/wallpapers/aegean_noir.webp",
                isVanguardExclusive = false,
                isFavorite = false
            ),
            SpartanWallpaper(
                id = "wp_last_light",
                title = "Thermopylae Dusk",
                category = "AMOLED",
                resUrl = "file:///android_asset/wallpapers/last_light.webp",
                isVanguardExclusive = true,
                isFavorite = false
            ),
            SpartanWallpaper(
                id = "wp_phalanx_crimson",
                title = "The Crimson Phalanx",
                category = "Armor",
                resUrl = "file:///android_asset/wallpapers/phalanx_crimson.webp",
                isVanguardExclusive = true,
                isFavorite = false
            )
        )
    }
}
