package space.iamjustkrishna.threehundred

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import space.iamjustkrishna.threehundred.core.system.WallpaperCrop

class WallpaperCropTest {
    @Test
    fun calculates_center_crop_for_tall_wallpaper() {
        val crop = WallpaperCrop.calculate(
            sourceWidth = 1440,
            sourceHeight = 3200,
            viewportWidth = 1080,
            viewportHeight = 1920,
            horizontalOffset = 0f,
            verticalOffset = 0f
        )

        assertEquals(1440, crop.width)
        assertEquals(2560, crop.height)
        assertEquals(0, crop.left)
        assertEquals(320, crop.top)
    }

    @Test
    fun clamps_offsets_to_source_bounds() {
        val crop = WallpaperCrop.calculate(
            sourceWidth = 1440,
            sourceHeight = 3200,
            viewportWidth = 1080,
            viewportHeight = 1920,
            horizontalOffset = 1f,
            verticalOffset = -1f
        )

        assertEquals(0, crop.left)
        assertEquals(0, crop.top)
        assertTrue(crop.left + crop.width <= 1440)
        assertTrue(crop.top + crop.height <= 3200)
    }
}
