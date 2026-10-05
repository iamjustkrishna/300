package space.iamjustkrishna.threehundred

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import space.iamjustkrishna.threehundred.feature.icons.IconCatalog

class IconCatalogModelTest {
    @Test
    fun exposes_all_ninety_six_catalog_entries() {
        assertEquals(96, IconCatalog.size)
        assertEquals(16, IconCatalog.groupBy { it.family }.values.first().size)
        assertTrue(IconCatalog.any { it.id == "forge_lambda" })
    }
}
