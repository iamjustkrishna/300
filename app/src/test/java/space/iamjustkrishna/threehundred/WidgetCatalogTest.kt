package space.iamjustkrishna.threehundred

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import space.iamjustkrishna.threehundred.feature.glance.WidgetCatalog

class WidgetCatalogTest {

    @Test
    fun launchCatalog_exposes_four_native_widget_presets() {
        val widgets = WidgetCatalog.launchWidgets
        assertEquals(4, widgets.size)
        assertEquals(
            setOf("monolith_clock", "shield_battery", "creed_ticker", "war_calendar"),
            widgets.map { it.id }.toSet()
        )
        assertTrue(widgets.all { it.isNativeGlance })
        assertEquals("4x1", widgets.first { it.id == "creed_ticker" }.size)
        assertEquals("4x2", widgets.first { it.id == "war_calendar" }.size)
    }
}
