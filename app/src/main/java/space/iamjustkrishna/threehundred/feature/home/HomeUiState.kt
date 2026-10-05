package space.iamjustkrishna.threehundred.feature.home

/**
 * UI State for SCR-003: Home Dashboard (Curated Themes).
 */
data class HomeUiState(
    val categories: List<String> = listOf("Featured", "AMOLED", "Armor", "Minimal"),
    val selectedCategory: String = "Featured",
    val dailyHeroTheme: HomeThemeItem = SampleDailyHeroTheme,
    val curatedThemes: List<HomeThemeItem> = SampleArchiveThemes,
    val isLoading: Boolean = false,
    val isOffline: Boolean = false,
    val errorMessage: String? = null
)
