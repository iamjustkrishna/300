package space.iamjustkrishna.threehundred.feature.onboarding

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import space.iamjustkrishna.threehundred.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import space.iamjustkrishna.threehundred.ui.theme.CanvasDeepBasaltVoid
import space.iamjustkrishna.threehundred.ui.theme.SatinGoldDeepBronze
import space.iamjustkrishna.threehundred.ui.theme.SatinGoldLightGleam
import space.iamjustkrishna.threehundred.ui.theme.SatinGoldPrimary
import space.iamjustkrishna.threehundred.ui.theme.SubtleHairlineBorder
import space.iamjustkrishna.threehundred.ui.theme.SurfaceBasaltStone
import space.iamjustkrishna.threehundred.ui.theme.TextCharcoalDimmed
import space.iamjustkrishna.threehundred.ui.theme.TextHighContrastBone
import space.iamjustkrishna.threehundred.ui.theme.TextWarmSilverMuted

/**
 * SCR-002: Onboarding (The Reveal)
 * Exact 1:1 match of UI_Screen/02_Onboarding_The_Reveal_v2.png:
 * - Top pill: "300 · SPARTA"
 * - Monumental stone portico phone mockup with reflective water and brazier flame ("A CALMER BRIGHTER YOU")
 * - Headline: "Make your phone\nfeel intentional."
 * - Subtitle: "Themes, widgets, and icons — curated as one set."
 * - 3 Wireframe gold chips: "True black", "Native widgets", "One-tap sets"
 * - Full-width brushed satin-gold CTA: "Start exploring →" (calls onStartExploring)
 * - Footer: "100% NATIVE ANDROID · ZERO ADS"
 */
@Composable
fun OnboardingScreen(
    onStartExploring: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CanvasDeepBasaltVoid)
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // 1. Top Header: Classical "300" with gold hairline / pill badge
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 16.dp, bottom = 12.dp)
        ) {
            Text(
                text = "3 0 0",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                letterSpacing = 0.2.sp,
                color = SatinGoldPrimary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .width(42.dp)
                    .height(1.dp)
                    .background(SatinGoldPrimary.copy(alpha = 0.5f))
            )
        }

        // 2. Monumental Stone Portico Phone Mockup
        PhoneMockupPortico(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )

        // 3. Headline & Subtitle Narrative
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            Text(
                text = "Make your phone\nfeel intentional.",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Normal,
                fontSize = 32.sp,
                lineHeight = 38.sp,
                textAlign = TextAlign.Center,
                color = TextHighContrastBone
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Themes, widgets, and icons — curated as one set.",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = TextWarmSilverMuted
            )
        }

        // 4. 3 Wireframe Gold Feature Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WireframeFeatureChip(
                iconType = FeatureIconType.TRUE_BLACK,
                label = "True black"
            )

            // Vertical subtle divider
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(36.dp)
                    .background(SubtleHairlineBorder)
            )

            WireframeFeatureChip(
                iconType = FeatureIconType.NATIVE_WIDGETS,
                label = "Native widgets"
            )

            // Vertical subtle divider
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(36.dp)
                    .background(SubtleHairlineBorder)
            )

            WireframeFeatureChip(
                iconType = FeatureIconType.ONE_TAP_SETS,
                label = "One-tap sets"
            )
        }

        // 5. Full-width Brushed Satin-Gold CTA Button & Footer
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onStartExploring,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(999.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = androidx.compose.foundation.layout.PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    SatinGoldDeepBronze,
                                    SatinGoldPrimary,
                                    SatinGoldLightGleam,
                                    SatinGoldPrimary
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Start exploring →",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        letterSpacing = 0.5.sp,
                        color = CanvasDeepBasaltVoid
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "100% NATIVE ANDROID · ZERO ADS",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp,
                letterSpacing = 1.8.sp,
                color = TextCharcoalDimmed
            )
        }
    }
}

private enum class FeatureIconType {
    TRUE_BLACK,
    NATIVE_WIDGETS,
    ONE_TAP_SETS
}

@Composable
private fun WireframeFeatureChip(
    iconType: FeatureIconType,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    brush = Brush.linearGradient(
                        listOf(
                            SatinGoldPrimary.copy(alpha = 0.6f),
                            SatinGoldDeepBronze.copy(alpha = 0.2f)
                        )
                    ),
                    shape = CircleShape
                )
                .background(SurfaceBasaltStone),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(22.dp)) {
                when (iconType) {
                    FeatureIconType.TRUE_BLACK -> {
                        // Crescent Moon
                        val moonPath = Path().apply {
                            moveTo(size.width * 0.7f, size.height * 0.15f)
                            cubicTo(
                                size.width * 0.2f, size.height * 0.2f,
                                size.width * 0.2f, size.height * 0.8f,
                                size.width * 0.7f, size.height * 0.85f
                            )
                            cubicTo(
                                size.width * 0.45f, size.height * 0.7f,
                                size.width * 0.45f, size.height * 0.3f,
                                size.width * 0.7f, size.height * 0.15f
                            )
                            close()
                        }
                        drawPath(
                            path = moonPath,
                            color = SatinGoldPrimary
                        )
                    }
                    FeatureIconType.NATIVE_WIDGETS -> {
                        // 2x2 Grid Wireframe
                        val pad = 2.dp.toPx()
                        val boxSize = (size.width - pad * 3) / 2f
                        val r = 2.dp.toPx()
                        // Top-left
                        drawRoundRect(
                            color = SatinGoldPrimary,
                            topLeft = Offset(pad, pad),
                            size = Size(boxSize, boxSize),
                            cornerRadius = CornerRadius(r, r),
                            style = Stroke(width = 1.5.dp.toPx())
                        )
                        // Top-right
                        drawRoundRect(
                            color = SatinGoldPrimary,
                            topLeft = Offset(pad * 2 + boxSize, pad),
                            size = Size(boxSize, boxSize),
                            cornerRadius = CornerRadius(r, r),
                            style = Stroke(width = 1.5.dp.toPx())
                        )
                        // Bottom-left
                        drawRoundRect(
                            color = SatinGoldPrimary,
                            topLeft = Offset(pad, pad * 2 + boxSize),
                            size = Size(boxSize, boxSize),
                            cornerRadius = CornerRadius(r, r),
                            style = Stroke(width = 1.5.dp.toPx())
                        )
                        // Bottom-right
                        drawRoundRect(
                            color = SatinGoldPrimary,
                            topLeft = Offset(pad * 2 + boxSize, pad * 2 + boxSize),
                            size = Size(boxSize, boxSize),
                            cornerRadius = CornerRadius(r, r),
                            style = Stroke(width = 1.5.dp.toPx())
                        )
                    }
                    FeatureIconType.ONE_TAP_SETS -> {
                        // Stacked Layers / Cards
                        val w = size.width
                        val h = size.height
                        val p1 = Path().apply {
                            moveTo(w / 2f, h * 0.18f)
                            lineTo(w * 0.88f, h * 0.38f)
                            lineTo(w / 2f, h * 0.58f)
                            lineTo(w * 0.12f, h * 0.38f)
                            close()
                        }
                        drawPath(p1, color = SatinGoldPrimary, style = Stroke(width = 1.5.dp.toPx()))

                        val p2 = Path().apply {
                            moveTo(w * 0.12f, h * 0.52f)
                            lineTo(w / 2f, h * 0.72f)
                            lineTo(w * 0.88f, h * 0.52f)
                        }
                        drawPath(p2, color = SatinGoldPrimary.copy(alpha = 0.75f), style = Stroke(width = 1.5.dp.toPx(), cap = StrokeCap.Round))

                        val p3 = Path().apply {
                            moveTo(w * 0.12f, h * 0.66f)
                            lineTo(w / 2f, h * 0.86f)
                            lineTo(w * 0.88f, h * 0.66f)
                        }
                        drawPath(p3, color = SatinGoldPrimary.copy(alpha = 0.5f), style = Stroke(width = 1.5.dp.toPx(), cap = StrokeCap.Round))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = label,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = TextWarmSilverMuted
        )
    }
}

/**
 * Phone Mockup floating in Stone Portico Sanctuary
 * Shows exact screen layout: Mon, Apr 28, "A CALMER BRIGHTER YOU", Weather, Search Pill, 4 Bronze dock icons.
 */
@Composable
private fun PhoneMockupPortico(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(380.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.asset_onboarding_portico),
            contentDescription = "Spartan Sanctuary Stone Portico Phone Mockup",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )
    }
}
