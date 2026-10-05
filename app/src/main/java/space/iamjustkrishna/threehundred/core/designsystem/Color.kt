package space.iamjustkrishna.threehundred.core.designsystem

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val CanvasDeepBasaltVoid = Color(0xFF08080A) // 100% OLED True Black
val CanvasObsidianDark = Color(0xFF0B0B0C)   // Secondary canvas depth
val SurfaceBasaltStone = Color(0xFF121216)   // Sculpted dark basalt stone
val SurfaceFrostedBasalt = Color(0xD9121216) // 85% Frosted modal overlay
val SurfaceMutedIron = Color(0xFF1A1A22)     // Inactive tracks & controls
val SubtleHairlineBorder = Color(0x1FC5A869) // rgba(197, 168, 105, 0.12)

val SpartanCanvas = CanvasDeepBasaltVoid
val SpartanSurface = SurfaceBasaltStone
val SpartanSurfaceVariant = SurfaceMutedIron

val SatinGoldPrimary = Color(0xFFC5A869)      // Warm antique gold
val SatinGoldLightGleam = Color(0xFFE2CA8F)   // Specular light edge
val SatinGoldDeepBronze = Color(0xFF9E8045)   // Metallic gradient base
val SatinGoldAmbientGlow = Color(0x1AC5A869)  // rgba(197, 168, 105, 0.10)
val SatinGoldHairlineRim = Color(0x59C5A869)  // rgba(197, 168, 105, 0.35)

val SpartanGold = SatinGoldPrimary
val SpartanGoldDark = SatinGoldDeepBronze
val SpartanGoldGlow = SatinGoldAmbientGlow
val SpartanGoldBorder = SatinGoldHairlineRim

val SpartanOxbloodCrimson = Color(0xFF8A1C14) // Deep ceremonial red
val CrimsonPowerArc = Color(0xFFA62820)       // Gauge & active accents
val CrimsonMutedBadge = Color(0x338A1C14)     // rgba(138, 28, 20, 0.20)
val SpartanCrimson = SpartanOxbloodCrimson

val TextHighContrastBone = Color(0xFFF5F3EF)  // Warm off-white
val TextWarmSilverMuted = Color(0xFF8E8E98)   // 65% opacity body text
val TextCharcoalDimmed = Color(0xFF585862)    // 40% micro captions
val TextAccentGold = SatinGoldPrimary

val SpartanTextPrimary = TextHighContrastBone
val SpartanTextSecondary = TextWarmSilverMuted
val SpartanTextTertiary = TextCharcoalDimmed

val SpartanDivider = Color(0x1FC5A869)

val SatinGoldGradient = Brush.horizontalGradient(
    listOf(SatinGoldDeepBronze, SatinGoldPrimary, SatinGoldLightGleam, SatinGoldPrimary)
)
val SpartanGoldGradient = SatinGoldGradient

val SatinGoldVerticalGradient = Brush.verticalGradient(
    listOf(SatinGoldLightGleam, SatinGoldPrimary, SatinGoldDeepBronze)
)

val CardBorderGradient = Brush.verticalGradient(
    listOf(SatinGoldHairlineRim, Color(0x0DC5A869), Color.Transparent)
)

val BasaltCardGradient = Brush.verticalGradient(
    listOf(SurfaceBasaltStone, CanvasObsidianDark)
)
val SpartanSurfaceGradient = BasaltCardGradient
