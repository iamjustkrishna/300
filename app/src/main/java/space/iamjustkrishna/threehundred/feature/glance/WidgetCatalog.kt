package space.iamjustkrishna.threehundred.feature.glance

/** Stable launch inventory used by Studio and release verification. */
data class WidgetDefinition(
    val id: String,
    val title: String,
    val size: String,
    val isNativeGlance: Boolean = true
)

object WidgetCatalog {
    val launchWidgets = listOf(
        WidgetDefinition("monolith_clock", "Monolith Clock", "2x2"),
        WidgetDefinition("shield_battery", "Shield Battery", "2x2"),
        WidgetDefinition("creed_ticker", "Creed Ticker", "4x1"),
        WidgetDefinition("war_calendar", "War Calendar", "4x2")
    )
}
