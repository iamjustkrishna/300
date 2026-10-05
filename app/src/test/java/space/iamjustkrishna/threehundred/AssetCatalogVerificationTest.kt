package space.iamjustkrishna.threehundred

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * Release guard for the M3 launch catalog. It intentionally reads the durable
 * asset contract rather than Android resources, because 4K masters stay out of
 * the APK and are delivered as versioned packs.
 */
class AssetCatalogVerificationTest {

    private fun wallpapersDir(): File {
        var directory = File(System.getProperty("user.dir"))
        repeat(4) {
            val candidate = File(directory, "assets/wallpapers")
            if (candidate.exists()) return candidate
            directory = directory.parentFile ?: return candidate
        }
        return File(System.getProperty("user.dir"), "assets/wallpapers")
    }

    @Test
    fun launchManifest_declares_twelve_original_downloadable_masters() {
        val directory = wallpapersDir()
        val manifest = File(directory, "manifest.json")
        assertTrue("Missing wallpaper manifest", manifest.exists())
        val text = manifest.readText()
        val entries = Regex("\\\"id\\\"\\s*:").findAll(text).count()
        assertEquals("M3 launch catalog must contain 12 wallpaper masters", 12, entries)
        assertEquals("Launch entitlement must contain six free wallpapers", 6,
            Regex("\\\"entitlement\\\"\\s*:\\s*\\\"free\\\"").findAll(text).count())
        assertEquals("Launch entitlement must contain six Vanguard wallpapers", 6,
            Regex("\\\"entitlement\\\"\\s*:\\s*\\\"vanguard\\\"").findAll(text).count())
        assertTrue("Every catalog entry needs original provenance", text.contains("\"provenance\": \"original-ai-art-directed\""))
        assertTrue("Every catalog entry needs download derivatives", text.contains("\"thumbnail\":"))
        assertTrue("Every catalog entry needs card derivatives", text.contains("\"card\":"))

        val masters = Regex("\\\"file\\\"\\s*:\\s*\\\"([^\\\"]+)\\\"")
            .findAll(text).map { it.groupValues[1] }.toList()
        assertEquals("Every catalog item must reference one approved master", 12, masters.size)
        masters.forEach { relativePath ->
            val master = File(directory, relativePath)
            assertTrue("Missing approved wallpaper master: $relativePath", master.exists())
            assertTrue("Wallpaper master is unexpectedly small: $relativePath", master.length() > 5_000)
        }
        assertEquals("Only visual-QA-approved assets can ship", 12,
            Regex("\\\"qaStatus\\\"\\s*:\\s*\\\"approved\\\"").findAll(text).count())
    }
}
