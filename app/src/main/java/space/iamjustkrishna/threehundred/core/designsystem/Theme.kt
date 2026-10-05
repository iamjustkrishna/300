package space.iamjustkrishna.threehundred.core.designsystem

import android.app.Activity
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

private val SpartanDarkColorScheme = darkColorScheme(
    primary = SatinGoldPrimary,
    onPrimary = CanvasDeepBasaltVoid,
    primaryContainer = SurfaceMutedIron,
    onPrimaryContainer = SatinGoldLightGleam,
    secondary = SatinGoldDeepBronze,
    onSecondary = CanvasDeepBasaltVoid,
    tertiary = SpartanOxbloodCrimson,
    onTertiary = TextHighContrastBone,
    background = CanvasDeepBasaltVoid,
    onBackground = TextHighContrastBone,
    surface = SurfaceBasaltStone,
    onSurface = TextHighContrastBone,
    surfaceVariant = SurfaceMutedIron,
    onSurfaceVariant = TextWarmSilverMuted,
    outline = SubtleHairlineBorder,
    outlineVariant = Color(0x0DC5A869)
)

val SpartanShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(18.dp),
    extraLarge = RoundedCornerShape(999.dp)
)
val ThreeHundredShapes = SpartanShapes

@Composable
fun SpartanTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = CanvasDeepBasaltVoid.toArgb()
                window.navigationBarColor = CanvasDeepBasaltVoid.toArgb()
                val insets = WindowCompat.getInsetsController(window, view)
                insets.isAppearanceLightStatusBars = false
                insets.isAppearanceLightNavigationBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = SpartanDarkColorScheme,
        typography = SpartanTypography,
        shapes = SpartanShapes,
        content = content
    )
}

@Composable
fun ThreeHundredTheme(
    content: @Composable () -> Unit
) = SpartanTheme(content = content)
