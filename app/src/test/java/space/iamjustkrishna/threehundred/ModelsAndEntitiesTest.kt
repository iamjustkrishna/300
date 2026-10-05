package space.iamjustkrishna.threehundred

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import space.iamjustkrishna.threehundred.core.database.ThemeEntity
import space.iamjustkrishna.threehundred.core.database.WallpaperEntity
import space.iamjustkrishna.threehundred.core.database.toEntity
import space.iamjustkrishna.threehundred.core.database.toExternalModel
import space.iamjustkrishna.threehundred.core.model.SpartanTheme
import space.iamjustkrishna.threehundred.core.model.SpartanWallpaper
import space.iamjustkrishna.threehundred.core.model.UserPreferences

class ModelsAndEntitiesTest {

    @Test
    fun spartanTheme_toEntity_and_back_preserves_all_properties() {
        val theme = SpartanTheme(
            id = "obsidian_ritual",
            title = "Obsidian Ritual",
            subtitle = "THE DAILY SET · 088",
            category = "Featured",
            previewUrl = "file:///android_asset/themes/obsidian_ritual.webp",
            blackPercent = "99.2%",
            isVanguardExclusive = false,
            stoicQuote = "Discipline is freedom.",
            isFavorite = true
        )

        val entity = theme.toEntity()
        assertEquals("obsidian_ritual", entity.id)
        assertEquals("Obsidian Ritual", entity.title)
        assertEquals("THE DAILY SET · 088", entity.subtitle)
        assertEquals("Featured", entity.category)
        assertEquals("99.2%", entity.blackPercent)
        assertFalse(entity.isVanguardExclusive)
        assertEquals("Discipline is freedom.", entity.stoicQuote)
        assertTrue(entity.isFavorite)

        val reconstructed = entity.toExternalModel()
        assertEquals(theme, reconstructed)
    }

    @Test
    fun spartanWallpaper_toEntity_and_back_preserves_all_properties() {
        val wallpaper = SpartanWallpaper(
            id = "wp_spartan_dawn",
            title = "The Spartan Sunrise",
            category = "AMOLED",
            resUrl = "file:///android_asset/wallpapers/spartan_dawn.webp",
            isVanguardExclusive = false,
            isFavorite = false
        )

        val entity = wallpaper.toEntity()
        assertEquals("wp_spartan_dawn", entity.id)
        assertEquals("The Spartan Sunrise", entity.title)
        assertEquals("AMOLED", entity.category)
        assertEquals("file:///android_asset/wallpapers/spartan_dawn.webp", entity.resUrl)
        assertFalse(entity.isVanguardExclusive)
        assertFalse(entity.isFavorite)

        val reconstructed = entity.toExternalModel()
        assertEquals(wallpaper, reconstructed)
    }

    @Test
    fun userPreferences_defaultValues_areDisciplined() {
        val prefs = UserPreferences()
        assertFalse(prefs.hasCompletedOnboarding)
        assertFalse(prefs.isVanguardUnlocked)
        assertTrue(prefs.isPureBlackOled)
        assertFalse(prefs.isLowPowerMode)
        assertEquals("obsidian_ritual", prefs.activeThemeId)
    }
}
