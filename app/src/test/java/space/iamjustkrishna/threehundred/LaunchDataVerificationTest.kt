package space.iamjustkrishna.threehundred

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import space.iamjustkrishna.threehundred.core.data.ThemeRepository
import space.iamjustkrishna.threehundred.core.data.WallpaperRepository

class LaunchDataVerificationTest {

    @Test
    fun launchThemes_containAllSixApprovedThemes() {
        val themes = ThemeRepository.LAUNCH_THEMES
        assertEquals(6, themes.size)

        val themeIds = themes.map { it.id }.toSet()
        val expectedIds = setOf(
            "obsidian_ritual",
            "spartan_dawn",
            "helm",
            "aegean_noir",
            "last_light",
            "phalanx_crimson"
        )
        assertEquals(expectedIds, themeIds)

        // Verify Daily Hero theme is Obsidian Ritual
        val hero = themes.first()
        assertEquals("obsidian_ritual", hero.id)
        assertEquals("Obsidian Ritual", hero.title)
        assertEquals("Featured", hero.category)
        assertEquals("99.2%", hero.blackPercent)
    }

    @Test
    fun launchWallpapers_containAllSixCuratedWallpapers() {
        val wallpapers = WallpaperRepository.LAUNCH_WALLPAPERS
        assertEquals(6, wallpapers.size)

        val categories = wallpapers.map { it.category }.toSet()
        assertTrue(categories.contains("Featured"))
        assertTrue(categories.contains("AMOLED"))
        assertTrue(categories.contains("Armor"))
        assertTrue(categories.contains("Minimal"))
    }
}
