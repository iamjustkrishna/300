package space.iamjustkrishna.threehundred.core.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val ClassicalSerifFontFamily = FontFamily.Serif
val SpartanSerifFontFamily = FontFamily.Serif
val SpartanSansSerifFontFamily = FontFamily.SansSerif
val CleanSansFontFamily = FontFamily.SansSerif

val SpartanTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = ClassicalSerifFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 38.sp,
        letterSpacing = 0.5.sp,
        color = TextHighContrastBone
    ),
    displayMedium = TextStyle(
        fontFamily = ClassicalSerifFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.25.sp,
        color = TextHighContrastBone
    ),
    headlineLarge = TextStyle(
        fontFamily = ClassicalSerifFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        color = TextHighContrastBone
    ),
    headlineMedium = TextStyle(
        fontFamily = ClassicalSerifFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        color = TextHighContrastBone
    ),
    headlineSmall = TextStyle(
        fontFamily = ClassicalSerifFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        color = TextHighContrastBone
    ),
    titleLarge = TextStyle(
        fontFamily = ClassicalSerifFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        color = TextHighContrastBone
    ),
    titleMedium = TextStyle(
        fontFamily = CleanSansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        color = TextHighContrastBone
    ),
    bodyLarge = TextStyle(
        fontFamily = CleanSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = TextWarmSilverMuted
    ),
    bodyMedium = TextStyle(
        fontFamily = CleanSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        color = TextWarmSilverMuted
    ),
    bodySmall = TextStyle(
        fontFamily = CleanSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = TextCharcoalDimmed
    ),
    labelLarge = TextStyle(
        fontFamily = CleanSansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        color = CanvasDeepBasaltVoid
    ),
    labelMedium = TextStyle(
        fontFamily = CleanSansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = TextWarmSilverMuted
    ),
    labelSmall = TextStyle(
        fontFamily = CleanSansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 1.sp,
        color = SatinGoldPrimary
    )
)

val ThreeHundredTypography = SpartanTypography
