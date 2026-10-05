package space.iamjustkrishna.threehundred.core.model

/**
 * Domain model representing a curated Spartan aesthetic theme.
 */
data class SpartanTheme(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: String,
    val previewUrl: String,
    val blackPercent: String,
    val isVanguardExclusive: Boolean,
    val stoicQuote: String,
    val isFavorite: Boolean = false
)

/**
 * Domain model representing a 4K True AMOLED wallpaper.
 */
data class SpartanWallpaper(
    val id: String,
    val title: String,
    val category: String,
    val resUrl: String,
    val isVanguardExclusive: Boolean,
    val isFavorite: Boolean = false
)

/**
 * Domain model representing persistent Spartan user preferences.
 */
data class UserPreferences(
    val hasCompletedOnboarding: Boolean = false,
    val isVanguardUnlocked: Boolean = false,
    val isPureBlackOled: Boolean = true,
    val isLowPowerMode: Boolean = false,
    val activeThemeId: String = "obsidian_ritual"
)
