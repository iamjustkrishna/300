package space.iamjustkrishna.threehundred

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * TDD guard for Sprint 03 asset polish: dedicated text-free 1440x3200
 * wallpaper masters kept OUTSIDE the APK, plus lean in-APK thumbnails.
 *
 * Policy: assets/wallpapers/README.md forbids crops of UI mockups.
 * Masters must be original scenes, text-free, no phone frames.
 */
class WallpaperMastersVerificationTest {

    private fun repoRoot(): File {
        // Unit tests run with workingDir = app/. Walk up until assets/wallpapers exists.
        var dir = File(System.getProperty("user.dir"))
        repeat(4) {
            if (File(dir, "assets/wallpapers").exists()) return dir
            // Also support running from repo root directly.
            if (dir.name == "300" && File(dir, "assets/wallpapers").exists()) return dir
            dir = dir.parentFile ?: return dir
        }
        return File(System.getProperty("user.dir"))
    }

    private fun mastersDir(): File {
        val root = repoRoot()
        // Candidate locations: <root>/assets/wallpapers or <root>/../assets/wallpapers
        val direct = File(root, "assets/wallpapers")
        if (direct.exists()) return direct
        val viaApp = File(root.parentFile ?: root, "assets/wallpapers")
        if (viaApp.exists()) return viaApp
        // Fallback: absolute repo path used in CI/dev machine.
        return direct
    }

    @Test
    fun wallpaperMasters_manifest_exists_with_six_textFree_masters() {
        val manifest = File(mastersDir(), "manifest.json")
        assertTrue(
            "Missing assets/wallpapers/manifest.json. Create 6 text-free 1440x3200 masters outside APK. Looked in: ${manifest.absolutePath}",
            manifest.exists()
        )
        val text = manifest.readText()

        val expected = listOf(
            "wp_obsidian_ritual",
            "wp_spartan_dawn",
            "wp_corinthian_helm",
            "wp_aegean_noir",
            "wp_last_light",
            "wp_phalanx_crimson"
        )
        expected.forEach { id ->
            assertTrue("Manifest missing master id: $id", text.contains("\"id\": \"$id\""))
        }
        // Spot-check spec flags without requiring org.json on JVM unit tests.
        assertTrue("Manifest must declare 1440 width", text.contains("\"width\": 1440"))
        assertTrue("Manifest must declare 3200 height", text.contains("\"height\": 3200"))
        assertTrue("Manifest must declare textFree", text.contains("\"textFree\": true"))
        assertTrue("Manifest must declare noPhoneFrame", text.contains("\"noPhoneFrame\": true"))

        // Each master file referenced by manifest must exist; parse "file" entries via regex.
        val fileRegex = Regex("\"file\"\\s*:\\s*\"([^\"]+)\"")
        val files = fileRegex.findAll(text).map { it.groupValues[1] }.toList()
        assertTrue("Expected the approved six-master baseline or a larger catalog", files.size >= 6)
        files.forEach { rel ->
            val file = File(mastersDir(), rel)
            assertTrue("Missing master file: $rel at ${file.absolutePath}", file.exists())
            // True-black 1440x3200 WebPs compress tiny (mostly flat #08080A); 5KB proves non-empty.
            assertTrue("Master file empty: $rel", file.length() > 5_000)
        }
    }

    @Test
    fun inApk_thumbnails_stay_lean_for_25MB_guardrail() {
        val root = repoRoot()
        val candidates = listOf(
            File(root, "app/src/main/res/drawable-nodpi"),
            File(root, "src/main/res/drawable-nodpi")
        )
        val resDir = candidates.firstOrNull { it.exists() }
            ?: File(root, "app/src/main/res/drawable-nodpi")
        assertTrue("Missing drawable-nodpi at ${resDir.absolutePath}", resDir.exists())

        val webps = resDir.listFiles { f -> f.extension == "webp" } ?: emptyArray()
        assertTrue("Expected >=10 in-APK WebP thumbnails", webps.size >= 10)

        val totalBytes = webps.sumOf { it.length() }
        // Keep in-APK previews lean: full 4K lives outside APK.
        assertTrue(
            "In-APK WebP total ${totalBytes / 1024}KB exceeds 6144KB lean budget",
            totalBytes < 6 * 1024 * 1024
        )
        webps.forEach {
            assertTrue("Thumbnail too large: ${it.name} ${it.length() / 1024}KB", it.length() < 600 * 1024)
        }
    }
}
