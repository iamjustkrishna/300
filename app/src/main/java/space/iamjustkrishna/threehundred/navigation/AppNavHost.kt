package space.iamjustkrishna.threehundred.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import space.iamjustkrishna.threehundred.core.preferences.VanguardPreferences
import space.iamjustkrishna.threehundred.feature.home.HomeScreen
import space.iamjustkrishna.threehundred.feature.icons.IconForgeScreen
import space.iamjustkrishna.threehundred.feature.onboarding.OnboardingScreen
import space.iamjustkrishna.threehundred.feature.preview.ThemePreviewScreen
import space.iamjustkrishna.threehundred.feature.settings.SettingsScreen
import space.iamjustkrishna.threehundred.feature.splash.SplashScreen
import space.iamjustkrishna.threehundred.feature.studio.StudioScreen
import space.iamjustkrishna.threehundred.feature.vault.VaultScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val preferences = VanguardPreferences(context)

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onComplete = {
                    coroutineScope.launch {
                        val hasCompleted = preferences.hasCompletedOnboarding.first()
                        val destination = if (hasCompleted) Screen.Home.route else Screen.Onboarding.route
                        navController.navigate(destination) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onStartExploring = {
                    coroutineScope.launch {
                        preferences.setOnboardingCompleted(true)
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Onboarding.route) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onThemeClick = { themeId ->
                    navController.navigate(Screen.ThemePreview.createRoute(themeId))
                },
                onNavigateToStudio = {
                    navController.navigate(Screen.Studio.route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToVault = {
                    navController.navigate(Screen.Vault.route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(
            route = Screen.ThemePreview.route,
            arguments = listOf(
                navArgument("themeId") {
                    type = NavType.StringType
                    defaultValue = "obsidian_ritual"
                }
            )
        ) { backStackEntry ->
            val themeId = backStackEntry.arguments?.getString("themeId") ?: "obsidian_ritual"
            ThemePreviewScreen(
                themeId = themeId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Studio.route) {
            StudioScreen(
                onNavigateToDiscover = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onNavigateToVault = {
                    navController.navigate(Screen.Vault.route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToIcons = { navController.navigate(Screen.Icons.route) }
            )
        }

        composable(Screen.Vault.route) {
            VaultScreen(
                onNavigateToDiscover = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onNavigateToStudio = {
                    navController.navigate(Screen.Studio.route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onPreviewTheme = { themeId ->
                    navController.navigate(Screen.ThemePreview.createRoute(themeId))
                }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateToDiscover = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onNavigateToStudio = {
                    navController.navigate(Screen.Studio.route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToVault = {
                    navController.navigate(Screen.Vault.route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(Screen.Icons.route) {
            IconForgeScreen(onBack = { navController.popBackStack() })
        }
    }
}
