package space.iamjustkrishna.threehundred.core.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import space.iamjustkrishna.threehundred.core.database.ThemeDao
import space.iamjustkrishna.threehundred.core.database.ThreeHundredDatabase
import space.iamjustkrishna.threehundred.core.database.toEntity
import space.iamjustkrishna.threehundred.core.database.toExternalModel
import space.iamjustkrishna.threehundred.core.model.SpartanTheme

/**
 * Repository providing access to curated Spartan themes with offline-first Room caching
 * and automatic first-run launch theme seeding.
 */
class ThemeRepository(
    private val themeDao: ThemeDao
) {
    constructor(context: Context) : this(
        ThreeHundredDatabase.getInstance(context).themeDao()
    )

    @Volatile
    private var isSeeded = false

    /**
     * Observable stream of all curated Spartan themes.
     * Seeds initial launch themes if the database is uninitialized.
     */
    val themes: Flow<List<SpartanTheme>> = flow {
        seedInitialThemesIfEmpty()
        emitAll(themeDao.getAllThemes().map { entities ->
            entities.map { it.toExternalModel() }
        })
    }

    fun getAllThemes(): Flow<List<SpartanTheme>> = flow {
        seedInitialThemesIfEmpty()
        emitAll(themeDao.getAllThemes().map { entities ->
            entities.map { it.toExternalModel() }
        })
    }

    fun getThemesByCategory(category: String): Flow<List<SpartanTheme>> = flow {
        seedInitialThemesIfEmpty()
        emitAll(themeDao.getThemesByCategory(category).map { entities ->
            entities.map { it.toExternalModel() }
        })
    }

    fun getThemeById(id: String): Flow<SpartanTheme?> = flow {
        seedInitialThemesIfEmpty()
        emitAll(themeDao.getThemeById(id).map { it?.toExternalModel() })
    }

    suspend fun toggleFavorite(id: String) {
        themeDao.toggleFavorite(id)
    }

    suspend fun updateTheme(theme: SpartanTheme) {
        themeDao.updateTheme(theme.toEntity())
    }

    suspend fun seedInitialThemesIfEmpty() {
        if (!isSeeded) {
            if (themeDao.getThemeCount() == 0) {
                themeDao.insertThemes(LAUNCH_THEMES.map { it.toEntity() })
            }
            isSeeded = true
        }
    }

    companion object {
        val LAUNCH_THEMES = listOf(
            SpartanTheme(
                id = "obsidian_ritual",
                title = "Obsidian Ritual",
                subtitle = "THE DAILY SET · 088",
                category = "Featured",
                previewUrl = "file:///android_asset/themes/obsidian_ritual.webp",
                blackPercent = "99.2%",
                isVanguardExclusive = false,
                stoicQuote = "Discipline is freedom. Depth over noise.",
                isFavorite = false
            ),
            SpartanTheme(
                id = "spartan_dawn",
                title = "Spartan Dawn",
                subtitle = "ARCHIVE SET · 087",
                category = "AMOLED",
                previewUrl = "file:///android_asset/themes/spartan_dawn.webp",
                blackPercent = "97.8%",
                isVanguardExclusive = false,
                stoicQuote = "Light finds a way through broken shields.",
                isFavorite = false
            ),
            SpartanTheme(
                id = "helm",
                title = "Helm",
                subtitle = "ARCHIVE SET · 086",
                category = "Armor",
                previewUrl = "file:///android_asset/themes/helm.webp",
                blackPercent = "98.5%",
                isVanguardExclusive = true,
                stoicQuote = "Silence looks stronger than hollow words.",
                isFavorite = false
            ),
            SpartanTheme(
                id = "aegean_noir",
                title = "Aegean Noir",
                subtitle = "ARCHIVE SET · 085",
                category = "Minimal",
                previewUrl = "file:///android_asset/themes/aegean_noir.webp",
                blackPercent = "99.4%",
                isVanguardExclusive = false,
                stoicQuote = "Depth over noise. The deep sea swallows storms.",
                isFavorite = false
            ),
            SpartanTheme(
                id = "last_light",
                title = "Last Light",
                subtitle = "ARCHIVE SET · 084",
                category = "AMOLED",
                previewUrl = "file:///android_asset/themes/last_light.webp",
                blackPercent = "98.9%",
                isVanguardExclusive = true,
                stoicQuote = "Stillness remains when empires fall.",
                isFavorite = false
            ),
            SpartanTheme(
                id = "phalanx_crimson",
                title = "Phalanx Crimson",
                subtitle = "ARCHIVE SET · 083",
                category = "Armor",
                previewUrl = "file:///android_asset/themes/phalanx_crimson.webp",
                blackPercent = "96.5%",
                isVanguardExclusive = true,
                stoicQuote = "Stand your ground. With your shield or on it.",
                isFavorite = false
            )
        )
    }
}
