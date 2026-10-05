package space.iamjustkrishna.threehundred.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val SpartanDarkColorScheme = darkColorScheme(
    primary = SatinGoldPrimary,
    onPrimary = CanvasDeepBasaltVoid,
    primaryContainer = SatinGoldDeepBronze,
    onPrimaryContainer = TextHighContrastBone,
    secondary = SatinGoldLightGleam,
    onSecondary = CanvasDeepBasaltVoid,
    tertiary = SpartanOxbloodCrimson,
    onTertiary = TextHighContrastBone,
    background = CanvasDeepBasaltVoid,
    onBackground = TextHighContrastBone,
    surface = SurfaceBasaltStone,
    onSurface = TextHighContrastBone,
    surfaceVariant = SurfaceMutedIron,
    onSurfaceVariant = TextWarmSilverMuted,
    outline = SubtleHairlineBorder
)

@Composable
fun SpartanTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = SpartanDarkColorScheme,
        content = content
    )
}
