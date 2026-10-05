package space.iamjustkrishna.threehundred.feature.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import space.iamjustkrishna.threehundred.R
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import space.iamjustkrishna.threehundred.ui.theme.CanvasDeepBasaltVoid
import space.iamjustkrishna.threehundred.ui.theme.SatinGoldDeepBronze
import space.iamjustkrishna.threehundred.ui.theme.SatinGoldLightGleam
import space.iamjustkrishna.threehundred.ui.theme.SatinGoldPrimary
import space.iamjustkrishna.threehundred.ui.theme.TextWarmSilverMuted

/**
 * SCR-001: Splash (Brand Genesis)
 * Exact 1:1 match of UI_Screen/01_Splash_Brand_Genesis_v2.png:
 * - HopliteShieldMedallion with vertical spear piercing and embossed Spartan Lambda
 * - Classical serif "300"
 * - Spaced "SPARTA THEME" with gold hairline divider
 * - Motto: "RETURN WITH YOUR SHIELD, OR ON IT."
 * - Gold progress bar with caption "PREPARING YOUR SET · V1.0 × PHALANX"
 * - Auto-progress animation with onComplete() callback
 */
@Composable
fun SplashScreen(
    onComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val progress = remember { Animatable(0f) }

    // Breathing shimmer for the bronze medallion
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val shimmerAlpha by infiniteTransition.animateFloat(
        initialValue = 0.85f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "medallionGlow"
    )

    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1800, easing = FastOutSlowInEasing)
        )
        delay(250)
        onComplete()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CanvasDeepBasaltVoid)
            .statusBarsPadding()
            .navigationBarsPadding()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onComplete()
            },
        contentAlignment = Alignment.Center
    ) {
        // Atmospheric stone cave floor glow
        Canvas(modifier = Modifier.fillMaxSize()) {
            val centerGlow = Offset(size.width / 2f, size.height * 0.78f)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0x28C5A869),
                        Color(0x0C9E8045),
                        Color.Transparent
                    ),
                    center = centerGlow,
                    radius = size.width * 0.65f
                ),
                radius = size.width * 0.65f,
                center = centerGlow
            )
        }

        // Center Content Block
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            // Official Spartan 300 Brand Logo Medallion
            Image(
                painter = painterResource(R.drawable.brand_splash_medallion),
                contentDescription = "Spartan 300 Brand Logo",
                modifier = Modifier
                    .size(260.dp)
                    .alpha(shimmerAlpha)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Delicate gold hairline divider
            Box(
                modifier = Modifier
                    .width(64.dp)
                    .height(1.dp)
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color.Transparent,
                                SatinGoldPrimary.copy(alpha = 0.6f),
                                Color.Transparent
                            )
                        )
                    )
            )

            Spacer(modifier = Modifier.height(14.dp))

            // SPARTA THEME spaced subtitle
            Text(
                text = "S P A R T A   T H E M E",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                letterSpacing = 2.4.sp,
                color = SatinGoldPrimary.copy(alpha = 0.85f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Stoic Motto
            Text(
                text = "\"RETURN WITH YOUR SHIELD, OR ON IT.\"",
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Normal,
                fontSize = 11.5.sp,
                letterSpacing = 0.8.sp,
                color = TextWarmSilverMuted.copy(alpha = 0.8f)
            )
        }

        // Bottom Progress Indicator & Micro-caption
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 44.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Gold Progress Bar
            Box(
                modifier = Modifier
                    .width(180.dp)
                    .height(2.5.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(Color(0xFF16161D))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress.value)
                        .height(2.5.dp)
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    SatinGoldDeepBronze,
                                    SatinGoldPrimary,
                                    SatinGoldLightGleam
                                )
                            )
                        )
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Micro-caption
            Text(
                text = "PREPARING YOUR SET · V1.0 × PHALANX",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                fontSize = 9.sp,
                letterSpacing = 2.2.sp,
                color = TextWarmSilverMuted.copy(alpha = 0.65f)
            )
        }
    }
}

/**
 * Custom Canvas Hoplite Shield Medallion
 * Draws the weathered bronze circular shield rim with studs,
 * vertical penetrating spear, and the embossed classical Spartan Lambda chevron.
 */
@Composable
fun HopliteShieldMedallion(
    modifier: Modifier = Modifier,
    alpha: Float = 1.0f
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val cx = w / 2f
        val cy = h / 2f
        val shieldRadius = w * 0.33f

        // 1. Vertical Spear (Spearhead at top, butt-spike at bottom)
        val spearColor = SatinGoldPrimary.copy(alpha = alpha)
        val spearShade = SatinGoldDeepBronze.copy(alpha = alpha)

        // Spearhead (top leaf point)
        val spearHeadPath = Path().apply {
            moveTo(cx, cy - shieldRadius - 48.dp.toPx()) // Top apex
            lineTo(cx + 6.dp.toPx(), cy - shieldRadius - 22.dp.toPx())
            lineTo(cx + 2.dp.toPx(), cy - shieldRadius - 12.dp.toPx())
            lineTo(cx - 2.dp.toPx(), cy - shieldRadius - 12.dp.toPx())
            lineTo(cx - 6.dp.toPx(), cy - shieldRadius - 22.dp.toPx())
            close()
        }
        drawPath(
            path = spearHeadPath,
            brush = Brush.linearGradient(
                colors = listOf(SatinGoldLightGleam, spearColor, spearShade),
                start = Offset(cx, cy - shieldRadius - 48.dp.toPx()),
                end = Offset(cx, cy - shieldRadius - 12.dp.toPx())
            )
        )

        // Spear shaft passing through center
        drawLine(
            brush = Brush.verticalGradient(
                colors = listOf(spearColor, SatinGoldLightGleam, spearShade)
            ),
            start = Offset(cx, cy - shieldRadius - 12.dp.toPx()),
            end = Offset(cx, cy + shieldRadius + 12.dp.toPx()),
            strokeWidth = 2.5.dp.toPx(),
            cap = StrokeCap.Round
        )

        // Spear butt-spike (bottom point)
        val spearButtPath = Path().apply {
            moveTo(cx, cy + shieldRadius + 38.dp.toPx()) // Bottom apex
            lineTo(cx + 5.dp.toPx(), cy + shieldRadius + 12.dp.toPx())
            lineTo(cx - 5.dp.toPx(), cy + shieldRadius + 12.dp.toPx())
            close()
        }
        drawPath(
            path = spearButtPath,
            brush = Brush.linearGradient(
                colors = listOf(spearColor, spearShade),
                start = Offset(cx, cy + shieldRadius + 12.dp.toPx()),
                end = Offset(cx, cy + shieldRadius + 38.dp.toPx())
            )
        )

        // 2. Shield Outer Outer Subtle Glow
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0x22C5A869),
                    Color.Transparent
                ),
                center = Offset(cx, cy),
                radius = shieldRadius * 1.35f
            ),
            radius = shieldRadius * 1.35f,
            center = Offset(cx, cy)
        )

        // 3. Shield Outer Rim Ring (Brushed Bronze)
        val ringGradient = Brush.sweepGradient(
            colors = listOf(
                SatinGoldDeepBronze.copy(alpha = alpha),
                SatinGoldPrimary.copy(alpha = alpha),
                SatinGoldLightGleam.copy(alpha = alpha),
                SatinGoldDeepBronze.copy(alpha = alpha),
                SatinGoldPrimary.copy(alpha = alpha)
            ),
            center = Offset(cx, cy)
        )

        drawCircle(
            brush = ringGradient,
            radius = shieldRadius,
            center = Offset(cx, cy),
            style = Stroke(width = 5.dp.toPx())
        )

        // Inner hairline ring
        drawCircle(
            color = SatinGoldPrimary.copy(alpha = 0.35f * alpha),
            radius = shieldRadius - 7.dp.toPx(),
            center = Offset(cx, cy),
            style = Stroke(width = 1.dp.toPx())
        )

        // 4. Studs/Rivets at 9 o'clock and 3 o'clock
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(SatinGoldLightGleam, SatinGoldDeepBronze),
                center = Offset(cx - shieldRadius, cy),
                radius = 4.dp.toPx()
            ),
            radius = 3.5.dp.toPx(),
            center = Offset(cx - shieldRadius, cy)
        )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(SatinGoldLightGleam, SatinGoldDeepBronze),
                center = Offset(cx + shieldRadius, cy),
                radius = 4.dp.toPx()
            ),
            radius = 3.5.dp.toPx(),
            center = Offset(cx + shieldRadius, cy)
        )

        // 5. Spartan Lambda (Chevron)
        val lambdaTop = cy - shieldRadius * 0.65f
        val lambdaBottom = cy + shieldRadius * 0.65f
        val lambdaHalfWidth = shieldRadius * 0.52f
        val strokeW = 12.dp.toPx()

        // Chiseled Spartan Lambda Left Leg
        val leftLeg = Path().apply {
            moveTo(cx, lambdaTop)
            lineTo(cx - lambdaHalfWidth, lambdaBottom)
            lineTo(cx - lambdaHalfWidth + strokeW * 0.9f, lambdaBottom)
            lineTo(cx, lambdaTop + strokeW * 1.2f)
            close()
        }
        drawPath(
            path = leftLeg,
            brush = Brush.linearGradient(
                colors = listOf(SatinGoldLightGleam, SatinGoldPrimary, SatinGoldDeepBronze),
                start = Offset(cx, lambdaTop),
                end = Offset(cx - lambdaHalfWidth, lambdaBottom)
            )
        )

        // Spartan Lambda Right Leg
        val rightLeg = Path().apply {
            moveTo(cx, lambdaTop)
            lineTo(cx + lambdaHalfWidth, lambdaBottom)
            lineTo(cx + lambdaHalfWidth - strokeW * 0.9f, lambdaBottom)
            lineTo(cx, lambdaTop + strokeW * 1.2f)
            close()
        }
        drawPath(
            path = rightLeg,
            brush = Brush.linearGradient(
                colors = listOf(SatinGoldPrimary, SatinGoldDeepBronze, Color(0xFF5A441B)),
                start = Offset(cx, lambdaTop),
                end = Offset(cx + lambdaHalfWidth, lambdaBottom)
            )
        )

        // Lambda Feet Serif Details (Flared base edges)
        drawRect(
            brush = Brush.horizontalGradient(
                colors = listOf(SatinGoldLightGleam, SatinGoldDeepBronze)
            ),
            topLeft = Offset(cx - lambdaHalfWidth - 4.dp.toPx(), lambdaBottom - 2.5.dp.toPx()),
            size = Size(strokeW + 6.dp.toPx(), 3.dp.toPx())
        )
        drawRect(
            brush = Brush.horizontalGradient(
                colors = listOf(SatinGoldDeepBronze, SatinGoldLightGleam)
            ),
            topLeft = Offset(cx + lambdaHalfWidth - strokeW - 2.dp.toPx(), lambdaBottom - 2.5.dp.toPx()),
            size = Size(strokeW + 6.dp.toPx(), 3.dp.toPx())
        )
    }
}
