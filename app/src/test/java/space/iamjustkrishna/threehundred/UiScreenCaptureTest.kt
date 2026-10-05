package space.iamjustkrishna.threehundred

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import space.iamjustkrishna.threehundred.core.data.ThemeRepository
import space.iamjustkrishna.threehundred.core.designsystem.SpartanTheme
import space.iamjustkrishna.threehundred.feature.home.HomeScreen
import space.iamjustkrishna.threehundred.feature.home.HomeScreenContent
import space.iamjustkrishna.threehundred.feature.home.HomeUiState
import space.iamjustkrishna.threehundred.feature.home.SampleArchiveThemes
import space.iamjustkrishna.threehundred.feature.onboarding.OnboardingScreen
import space.iamjustkrishna.threehundred.feature.preview.ThemePreviewScreenContent
import space.iamjustkrishna.threehundred.feature.preview.ThemePreviewUiState
import space.iamjustkrishna.threehundred.feature.settings.SettingsScreen
import space.iamjustkrishna.threehundred.feature.splash.SplashScreen
import space.iamjustkrishna.threehundred.feature.studio.StudioScreen
import space.iamjustkrishna.threehundred.feature.vault.VaultScreen
import java.io.File

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-420dpi")
class UiScreenCaptureTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    companion object {
        private lateinit var outputDir: File

        @JvmStatic
        @BeforeClass
        fun setupOutputDir() {
            val userDir = File(System.getProperty("user.dir") ?: ".")
            val projectRoot = if (userDir.name == "app") userDir.parentFile else userDir
            outputDir = File(projectRoot, "UI_Screen/actual_shots_from_ui").apply {
                mkdirs()
            }
        }
    }

    private fun getTargetPath(fileName: String): String {
        return File(outputDir, fileName).absolutePath
    }

    @Test
    fun capture01_SplashScreen() {
        composeTestRule.setContent {
            SpartanTheme {
                SplashScreen(onComplete = {})
            }
        }
        composeTestRule.waitForIdle()
        composeTestRule.onRoot().captureRoboImage(filePath = getTargetPath("01_Splash_Brand_Genesis.png"))
    }

    @Test
    fun capture02_OnboardingScreen() {
        composeTestRule.setContent {
            SpartanTheme {
                OnboardingScreen(onStartExploring = {})
            }
        }
        composeTestRule.waitForIdle()
        composeTestRule.onRoot().captureRoboImage(filePath = getTargetPath("02_Onboarding_The_Reveal.png"))
    }

    @Test
    fun capture03_HomeScreen() {
        composeTestRule.setContent {
            SpartanTheme {
                HomeScreenContent(
                    uiState = HomeUiState(
                        curatedThemes = SampleArchiveThemes
                    ),
                    onThemeClick = {},
                    onCategorySelect = {},
                    onRetry = {},
                    onNavigateToStudio = {},
                    onNavigateToVault = {},
                    onNavigateToSettings = {}
                )
            }
        }
        composeTestRule.waitForIdle()
        composeTestRule.onRoot().captureRoboImage(filePath = getTargetPath("03_Home_Curated_Themes.png"))
    }

    @Test
    fun capture04_ThemePreviewScreen() {
        composeTestRule.setContent {
            SpartanTheme {
                ThemePreviewScreenContent(
                    uiState = ThemePreviewUiState(
                        theme = ThemeRepository.LAUNCH_THEMES.first()
                    ),
                    onBackClick = {},
                    onModeSelect = {},
                    onFavoriteClick = {},
                    onApplyClick = {},
                    onDismissSuccess = {}
                )
            }
        }
        composeTestRule.waitForIdle()
        composeTestRule.onRoot().captureRoboImage(filePath = getTargetPath("04_Theme_Live_Preview_Apply.png"))
    }

    @Test
    fun capture05_StudioScreen() {
        composeTestRule.setContent {
            SpartanTheme {
                StudioScreen(
                    onNavigateToDiscover = {},
                    onNavigateToVault = {},
                    onNavigateToSettings = {}
                )
            }
        }
        composeTestRule.waitForIdle()
        composeTestRule.onRoot().captureRoboImage(filePath = getTargetPath("05_Studio_Widgets_Wallpapers.png"))
    }

    @Test
    fun capture06_SettingsScreen() {
        composeTestRule.setContent {
            SpartanTheme {
                SettingsScreen(
                    onNavigateToDiscover = {},
                    onNavigateToStudio = {},
                    onNavigateToVault = {}
                )
            }
        }
        composeTestRule.waitForIdle()
        composeTestRule.onRoot().captureRoboImage(filePath = getTargetPath("06_Settings_Vanguard_Pass.png"))
    }

    @Test
    fun capture07_VaultScreen() {
        composeTestRule.setContent {
            SpartanTheme {
                VaultScreen(
                    onNavigateToDiscover = {},
                    onNavigateToStudio = {},
                    onNavigateToSettings = {}
                )
            }
        }
        composeTestRule.waitForIdle()
        composeTestRule.onRoot().captureRoboImage(filePath = getTargetPath("07_Vault_Masterworks_Wallpapers.png"))
    }
}
