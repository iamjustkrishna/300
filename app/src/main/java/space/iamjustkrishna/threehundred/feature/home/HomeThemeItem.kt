package space.iamjustkrishna.threehundred.feature.home

import androidx.compose.ui.graphics.Color
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldDeepBronze
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldPrimary
import space.iamjustkrishna.threehundred.core.designsystem.SpartanOxbloodCrimson
import space.iamjustkrishna.threehundred.core.designsystem.TextCharcoalDimmed

enum class ThemeArtworkType {
    COLONNADE_PORTAL, // Obsidian Ritual Hero
    SPARTAN_DAWN,     // Monolithic rock, red banner, gold moon
    HELM,             // Weathered Corinthian bronze helmet
    AEGEAN_NOIR,      // Split basalt stone with gold sun disk
    LAST_LIGHT        // Stone colonnade with crimson drape & sea
}

data class HomeThemeItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val blackPercentage: String,
    val resolutionBadge: String = "4K UHD",
    val artworkType: ThemeArtworkType,
    val colorDots: List<Color> = listOf(
        SatinGoldPrimary,
        SpartanOxbloodCrimson,
        TextCharcoalDimmed
    ),
    val category: String = "Featured",
    val isDailySet: Boolean = false
)

val SampleDailyHeroTheme = HomeThemeItem(
    id = "obsidian-ritual",
    title = "Obsidian Ritual",
    subtitle = "Discipline in every detail.\nA bolder, calmer home screen.",
    blackPercentage = "99.2% Black",
    resolutionBadge = "4K UHD",
    artworkType = ThemeArtworkType.COLONNADE_PORTAL,
    isDailySet = true
)

val SampleArchiveThemes = listOf(
    HomeThemeItem(
        id = "spartan-dawn",
        title = "Spartan Dawn",
        subtitle = "Light finds a way.",
        blackPercentage = "98.9% Black",
        artworkType = ThemeArtworkType.SPARTAN_DAWN,
        colorDots = listOf(SatinGoldPrimary, SpartanOxbloodCrimson, TextCharcoalDimmed),
        category = "AMOLED"
    ),
    HomeThemeItem(
        id = "helm",
        title = "Helm",
        subtitle = "Silence looks stronger.",
        blackPercentage = "99.5% Black",
        artworkType = ThemeArtworkType.HELM,
        colorDots = listOf(SatinGoldPrimary, SatinGoldDeepBronze, TextCharcoalDimmed),
        category = "Armor"
    ),
    HomeThemeItem(
        id = "aegean-noir",
        title = "Aegean Noir",
        subtitle = "Depth over noise.",
        blackPercentage = "99.8% Black",
        artworkType = ThemeArtworkType.AEGEAN_NOIR,
        colorDots = listOf(SatinGoldPrimary, SatinGoldDeepBronze, Color(0xFF2C2C34)),
        category = "Minimal"
    ),
    HomeThemeItem(
        id = "last-light",
        title = "Last Light",
        subtitle = "Stillness remains.",
        blackPercentage = "99.1% Black",
        artworkType = ThemeArtworkType.LAST_LIGHT,
        colorDots = listOf(SatinGoldPrimary, SpartanOxbloodCrimson, TextCharcoalDimmed),
        category = "Featured"
    )
)
