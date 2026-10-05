package space.iamjustkrishna.threehundred.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Onboarding : Screen("onboarding")
    data object Home : Screen("home")
    data object ThemePreview : Screen("theme_preview/{themeId}") {
        fun createRoute(themeId: String): String = "theme_preview/$themeId"
    }
    data object Studio : Screen("studio")
    data object Vault : Screen("vault")
    data object Settings : Screen("settings")
    data object Icons : Screen("icons")
}
