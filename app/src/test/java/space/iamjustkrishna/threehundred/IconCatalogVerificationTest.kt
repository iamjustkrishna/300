package space.iamjustkrishna.threehundred

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class IconCatalogVerificationTest {

    private fun iconsDir(): File {
        var directory = File(System.getProperty("user.dir") ?: ".")
        repeat(4) {
            val candidate = File(directory, "assets/icons")
            if (candidate.exists()) return candidate
            directory = directory.parentFile ?: return candidate
        }
        return File(System.getProperty("user.dir") ?: ".", "assets/icons")
    }

    @Test
    fun iconManifest_has_six_families_and_ninety_six_original_glyphs() {
        val directory = iconsDir()
        val manifest = File(directory, "manifest.json")
        assertTrue("Missing assets/icons/manifest.json", manifest.exists())
        val text = manifest.readText()
        assertEquals(96, Regex("\\\"id\\\"\\s*:").findAll(text).count())
        assertEquals(6, Regex("\\\"family\\\"\\s*:").findAll(text).count())
        assertTrue(text.contains("\"applicationMode\": \"export-only\""))
        assertTrue(File(directory, "masters/300-icon-glyphs.svg").exists())
        val exports = File(directory, "exports").listFiles { file -> file.extension == "svg" }.orEmpty()
        assertEquals("All catalog entries need an SVG export", 96, exports.size)
        assertTrue(exports.all { it.length() > 200 })
    }
}
