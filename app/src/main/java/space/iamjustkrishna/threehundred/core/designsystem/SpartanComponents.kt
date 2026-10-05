package space.iamjustkrishna.threehundred.core.designsystem

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import space.iamjustkrishna.threehundred.R
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

/**
 * Chip icon types for Spartan wireframe feature chips.
 */
enum class ChipIconType {
    TRUE_BLACK,
    NATIVE_WIDGETS,
    ONE_TAP_SETS
}

/**
 * Hoplite Shield Medallion with embossed Spartan Lambda chevron and optional ceremonial spear.
 */
@Composable
fun HopliteShieldMedallion(
    modifier: Modifier = Modifier,
    size: Dp = 96.dp,
    showSpear: Boolean = true
) {
    Canvas(modifier = modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height
        val center = Offset(w / 2f, h / 2f)
        val radius = (w.coerceAtMost(h) / 2f) * 0.88f

        // Optional Ceremonial Spear passing behind the shield
        if (showSpear) {
            val spearWidth = radius * 0.06f
            // Spear shaft
            drawLine(
                brush = Brush.verticalGradient(
                    listOf(SatinGoldLightGleam, SatinGoldDeepBronze)
                ),
                start = Offset(center.x, h * 0.02f),
                end = Offset(center.x, h * 0.98f),
                strokeWidth = spearWidth,
                cap = StrokeCap.Round
            )
            // Spear tip at the top
            val tipPath = Path().apply {
                moveTo(center.x, 0f)
                lineTo(center.x + spearWidth * 2.2f, h * 0.12f)
                lineTo(center.x - spearWidth * 2.2f, h * 0.12f)
                close()
            }
            drawPath(
                path = tipPath,
                brush = SatinGoldVerticalGradient
            )
        }

        // Outer rim glow
        drawCircle(
            color = SatinGoldAmbientGlow,
            radius = radius * 1.05f,
            center = center
        )

        // Shield outer rim (Bronze gradient)
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(SurfaceBasaltStone, SurfaceMutedIron, SatinGoldDeepBronze),
                center = center,
                radius = radius
            ),
            radius = radius,
            center = center
        )

        // Concentric decorative border ring
        drawCircle(
            color = SatinGoldHairlineRim,
            radius = radius,
            center = center,
            style = Stroke(width = 2.dp.toPx())
        )

        // Inner Shield Face (Dark Basalt Stone with subtle bevel)
        val innerRadius = radius * 0.85f
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(SurfaceMutedIron, SurfaceBasaltStone, CanvasDeepBasaltVoid),
                center = center,
                radius = innerRadius
            ),
            radius = innerRadius,
            center = center
        )

        drawCircle(
            color = SubtleHairlineBorder,
            radius = innerRadius,
            center = center,
            style = Stroke(width = 1.dp.toPx())
        )

        // Rivets along the perimeter (12 studs representing the Spartan council)
        val rivetCount = 12
        val rivetRingRadius = (radius + innerRadius) / 2f
        for (i in 0 until rivetCount) {
            val angle = (i.toDouble() / rivetCount) * 2.0 * Math.PI
            val rx = center.x + (rivetRingRadius * cos(angle)).toFloat()
            val ry = center.y + (rivetRingRadius * sin(angle)).toFloat()
            drawCircle(
                color = SatinGoldPrimary,
                radius = 1.8.dp.toPx(),
                center = Offset(rx, ry)
            )
        }

        // Embossed Spartan Lambda (Λ) Chevron in center
        val lambdaHeight = innerRadius * 0.95f
        val lambdaWidth = innerRadius * 0.90f
        val lambdaTopY = center.y - (lambdaHeight * 0.45f)
        val lambdaBottomY = center.y + (lambdaHeight * 0.45f)

        val lambdaPath = Path().apply {
            moveTo(center.x, lambdaTopY)
            lineTo(center.x + (lambdaWidth / 2f), lambdaBottomY)
            lineTo(center.x + (lambdaWidth / 2f) - (lambdaWidth * 0.22f), lambdaBottomY)
            lineTo(center.x, lambdaTopY + (lambdaHeight * 0.26f))
            lineTo(center.x - (lambdaWidth / 2f) + (lambdaWidth * 0.22f), lambdaBottomY)
            lineTo(center.x - (lambdaWidth / 2f), lambdaBottomY)
            close()
        }

        drawPath(
            path = lambdaPath,
            brush = SatinGoldVerticalGradient
        )

        // Lambda specular hairline highlight
        val lambdaOutline = Path().apply {
            moveTo(center.x - (lambdaWidth / 2f), lambdaBottomY)
            lineTo(center.x, lambdaTopY)
            lineTo(center.x + (lambdaWidth / 2f), lambdaBottomY)
        }
        drawPath(
            path = lambdaOutline,
            color = SatinGoldLightGleam,
            style = Stroke(width = 1.5.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        )
    }
}

/**
 * Corinthian Helmet Graphic with high crimson horsehair crest and chiseled bronze bowl.
 */
@Composable
fun CorinthianHelmetGraphic(
    modifier: Modifier = Modifier,
    size: Dp = 96.dp
) {
    Canvas(modifier = modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height

        // 1. High-Sweeping Crimson Horsehair Crest
        val crestPath = Path().apply {
            moveTo(w * 0.15f, h * 0.50f)
            cubicTo(
                w * 0.15f, h * 0.15f,
                w * 0.55f, h * 0.02f,
                w * 0.88f, h * 0.22f
            )
            cubicTo(
                w * 0.82f, h * 0.32f,
                w * 0.50f, h * 0.20f,
                w * 0.30f, h * 0.52f
            )
            close()
        }
        drawPath(
            path = crestPath,
            brush = Brush.verticalGradient(
                listOf(CrimsonPowerArc, SpartanOxbloodCrimson)
            )
        )

        // Crest tail trailing backward
        val crestTailPath = Path().apply {
            moveTo(w * 0.15f, h * 0.50f)
            cubicTo(
                w * 0.08f, h * 0.60f,
                w * 0.05f, h * 0.75f,
                w * 0.10f, h * 0.85f
            )
            lineTo(w * 0.16f, h * 0.70f)
            close()
        }
        drawPath(
            path = crestTailPath,
            color = SpartanOxbloodCrimson
        )

        // 2. Chiseled Bronze Helmet Dome
        val helmetDome = Path().apply {
            moveTo(w * 0.25f, h * 0.55f)
            cubicTo(
                w * 0.25f, h * 0.28f,
                w * 0.75f, h * 0.26f,
                w * 0.78f, h * 0.55f
            )
            // Cheek guard front
            lineTo(w * 0.74f, h * 0.82f)
            lineTo(w * 0.64f, h * 0.80f)
            // Nose guard
            lineTo(w * 0.58f, h * 0.62f)
            lineTo(w * 0.52f, h * 0.62f)
            lineTo(w * 0.46f, h * 0.80f)
            // Cheek guard back
            lineTo(w * 0.36f, h * 0.82f)
            lineTo(w * 0.30f, h * 0.72f)
            // Neck guard
            lineTo(w * 0.22f, h * 0.78f)
            lineTo(w * 0.22f, h * 0.65f)
            close()
        }
        drawPath(
            path = helmetDome,
            brush = SatinGoldVerticalGradient
        )

        // Almond Eye Cutouts (Deep Basalt Void)
        val leftEye = Path().apply {
            moveTo(w * 0.40f, h * 0.58f)
            lineTo(w * 0.48f, h * 0.56f)
            lineTo(w * 0.46f, h * 0.62f)
            close()
        }
        val rightEye = Path().apply {
            moveTo(w * 0.62f, h * 0.58f)
            lineTo(w * 0.54f, h * 0.56f)
            lineTo(w * 0.56f, h * 0.62f)
            close()
        }
        drawPath(path = leftEye, color = CanvasDeepBasaltVoid)
        drawPath(path = rightEye, color = CanvasDeepBasaltVoid)

        // T-Shaped Spartan Nasal Opening
        val tOpening = Path().apply {
            moveTo(w * 0.48f, h * 0.64f)
            lineTo(w * 0.54f, h * 0.64f)
            lineTo(w * 0.53f, h * 0.78f)
            lineTo(w * 0.49f, h * 0.78f)
            close()
        }
        drawPath(path = tOpening, color = CanvasDeepBasaltVoid)

        // Specular gold hairline edge
        drawPath(
            path = helmetDome,
            color = SatinGoldLightGleam,
            style = Stroke(width = 1.dp.toPx())
        )
    }
}

/**
 * Compact Spartan Lambda Medallion with bronze hairline border.
 */
@Composable
fun SpartanLambdaMedallion(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(SurfaceBasaltStone)
            .border(1.dp, SatinGoldHairlineRim, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.brand_logo_300),
            contentDescription = "300 Spartan Brand Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )
    }
}

/**
 * Primary Pill Button with brushed satin gold gradient.
 */
@Composable
fun SpartanPillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(52.dp)
            .clip(RoundedCornerShape(999.dp)),
        enabled = enabled,
        shape = RoundedCornerShape(999.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = SatinGoldPrimary,
            contentColor = CanvasDeepBasaltVoid,
            disabledContainerColor = SurfaceMutedIron,
            disabledContentColor = TextCharcoalDimmed
        ),
        border = BorderStroke(1.dp, SatinGoldLightGleam)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                fontFamily = CleanSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                letterSpacing = 0.5.sp,
                color = if (enabled) CanvasDeepBasaltVoid else TextCharcoalDimmed
            )
            if (trailingIcon != null) {
                Spacer(modifier = Modifier.width(8.dp))
                trailingIcon()
            }
        }
    }
}

/**
 * Bespoke Full-Width Gold Button for checkout, activation, and high-value CTAs.
 */
@Composable
fun BespokeGoldButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .clip(RoundedCornerShape(999.dp))
            .background(
                if (enabled) SatinGoldGradient else Brush.linearGradient(listOf(SurfaceMutedIron, SurfaceMutedIron))
            )
            .border(
                1.dp,
                if (enabled) SatinGoldLightGleam else SubtleHairlineBorder,
                RoundedCornerShape(999.dp)
            )
            .clickable(
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontFamily = CleanSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            letterSpacing = 0.6.sp,
            color = if (enabled) CanvasDeepBasaltVoid else TextCharcoalDimmed
        )
    }
}

/**
 * Standard solid Spartan button.
 */
@Composable
fun SpartanButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: (@Composable () -> Unit)? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        enabled = enabled,
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = SatinGoldPrimary,
            contentColor = CanvasDeepBasaltVoid,
            disabledContainerColor = SurfaceMutedIron,
            disabledContentColor = TextCharcoalDimmed
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                icon()
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                fontFamily = CleanSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = if (enabled) CanvasDeepBasaltVoid else TextCharcoalDimmed
            )
        }
    }
}

/**
 * Spartan Outlined Button with subtle hairline bronze rim.
 */
@Composable
fun SpartanOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: (@Composable () -> Unit)? = null
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        enabled = enabled,
        shape = RoundedCornerShape(999.dp),
        border = BorderStroke(1.dp, SatinGoldHairlineRim),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = SatinGoldPrimary,
            disabledContentColor = TextCharcoalDimmed
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                icon()
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                fontFamily = CleanSansFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = if (enabled) SatinGoldPrimary else TextCharcoalDimmed
            )
        }
    }
}

/**
 * Spartan Sculpted Basalt Stone Card container.
 */
@Composable
fun SpartanCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val cardModifier = if (onClick != null) {
        modifier
            .clip(RoundedCornerShape(18.dp))
            .background(BasaltCardGradient)
            .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(18.dp))
            .clickable(onClick = onClick)
    } else {
        modifier
            .clip(RoundedCornerShape(18.dp))
            .background(BasaltCardGradient)
            .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(18.dp))
    }

    Column(
        modifier = cardModifier.padding(16.dp),
        content = content
    )
}

/**
 * Spartan Filter Chip for horizontal category switchers.
 */
@Composable
fun SpartanFilterChip(
    selected: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (selected) SatinGoldPrimary else SurfaceBasaltStone)
            .border(
                1.dp,
                if (selected) SatinGoldLightGleam else SubtleHairlineBorder,
                RoundedCornerShape(999.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 9.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leadingIcon != null) {
                leadingIcon()
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = text,
                fontFamily = CleanSansFontFamily,
                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
                fontSize = 13.sp,
                color = if (selected) CanvasDeepBasaltVoid else TextWarmSilverMuted
            )
        }
    }
}

/**
 * Spartan Badge for micro-tags and metrics.
 */
@Composable
fun SpartanBadge(
    text: String,
    modifier: Modifier = Modifier,
    isCrimson: Boolean = false
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (isCrimson) CrimsonMutedBadge else SatinGoldAmbientGlow)
            .border(
                0.5.dp,
                if (isCrimson) CrimsonPowerArc else SatinGoldHairlineRim,
                RoundedCornerShape(999.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text.uppercase(),
            fontFamily = CleanSansFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            letterSpacing = 0.8.sp,
            color = if (isCrimson) CrimsonPowerArc else SatinGoldPrimary
        )
    }
}

/**
 * Spartan Switch component with dark basalt and satin gold accents.
 */
@Composable
fun SpartanSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        colors = SwitchDefaults.colors(
            checkedThumbColor = CanvasDeepBasaltVoid,
            checkedTrackColor = SatinGoldPrimary,
            checkedBorderColor = SatinGoldLightGleam,
            uncheckedThumbColor = TextWarmSilverMuted,
            uncheckedTrackColor = SurfaceMutedIron,
            uncheckedBorderColor = SubtleHairlineBorder
        )
    )
}

/**
 * Spartan Divider hairline.
 */
@Composable
fun SpartanDivider(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(SpartanDivider)
    )
}

/**
 * Spartan Shimmer Card for loading states.
 */
@Composable
fun SpartanShimmerCard(
    modifier: Modifier = Modifier,
    height: Dp = 120.dp
) {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val alpha = transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmerAlpha"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(18.dp))
            .background(SurfaceBasaltStone)
            .border(1.dp, SubtleHairlineBorder.copy(alpha = alpha.value), RoundedCornerShape(18.dp))
    )
}

/**
 * Spartan Empty State display.
 */
@Composable
fun SpartanEmptyState(
    message: String,
    onReset: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SpartanLambdaMedallion(size = 56.dp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "THE VAULT IS SILENT",
            fontFamily = SpartanSerifFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = TextHighContrastBone,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = message,
            fontFamily = CleanSansFontFamily,
            fontSize = 13.sp,
            color = TextWarmSilverMuted,
            textAlign = TextAlign.Center
        )
        if (onReset != null) {
            Spacer(modifier = Modifier.height(20.dp))
            SpartanOutlinedButton(
                text = "Reset Filters",
                onClick = onReset
            )
        }
    }
}

/**
 * Spartan Error Card display.
 */
@Composable
fun SpartanErrorCard(
    message: String,
    onRetry: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CrimsonMutedBadge)
            .border(1.dp, CrimsonPowerArc.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "ANOMALY DETECTED",
                fontFamily = CleanSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                letterSpacing = 1.sp,
                color = CrimsonPowerArc
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = message,
                fontFamily = CleanSansFontFamily,
                fontSize = 13.sp,
                color = TextHighContrastBone
            )
            if (onRetry != null) {
                Spacer(modifier = Modifier.height(12.dp))
                SpartanOutlinedButton(
                    text = "Retry",
                    onClick = onRetry
                )
            }
        }
    }
}

/**
 * Spartan Offline Banner.
 */
@Composable
fun SpartanOfflineBanner(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceMutedIron)
            .border(0.5.dp, SatinGoldHairlineRim)
            .padding(vertical = 6.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(SatinGoldPrimary)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "OFFLINE MODE · LOCAL VAULT ACTIVE",
                fontFamily = CleanSansFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                letterSpacing = 0.8.sp,
                color = SatinGoldLightGleam
            )
        }
    }
}

/**
 * Wireframe Gold Chip for feature highlights on onboarding and hero screens.
 */
@Composable
fun WireframeGoldChip(
    title: String,
    iconType: ChipIconType,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .background(SurfaceBasaltStone)
            .border(1.dp, SatinGoldHairlineRim, RoundedCornerShape(999.dp))
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = Modifier.size(16.dp)) {
            val w = size.width
            val h = size.height

            when (iconType) {
                ChipIconType.TRUE_BLACK -> {
                    // Crescent Moon
                    val moonPath = Path().apply {
                        moveTo(w * 0.5f, 0f)
                        cubicTo(w * 0.8f, h * 0.2f, w * 0.8f, h * 0.8f, w * 0.5f, h)
                        cubicTo(w * 0.25f, h * 0.75f, w * 0.25f, h * 0.25f, w * 0.5f, 0f)
                        close()
                    }
                    drawPath(path = moonPath, color = SatinGoldPrimary)
                }
                ChipIconType.NATIVE_WIDGETS -> {
                    // 4-Square Grid
                    val gap = 2.dp.toPx()
                    val s = (w - gap) / 2f
                    drawRect(color = SatinGoldPrimary, topLeft = Offset(0f, 0f), size = Size(s, s))
                    drawRect(color = SatinGoldPrimary, topLeft = Offset(s + gap, 0f), size = Size(s, s))
                    drawRect(color = SatinGoldPrimary, topLeft = Offset(0f, s + gap), size = Size(s, s))
                    drawRect(color = SatinGoldPrimary, topLeft = Offset(s + gap, s + gap), size = Size(s, s))
                }
                ChipIconType.ONE_TAP_SETS -> {
                    // Layered stack
                    val hBar = 2.dp.toPx()
                    drawRoundRect(
                        color = SatinGoldLightGleam,
                        topLeft = Offset(w * 0.1f, h * 0.15f),
                        size = Size(w * 0.8f, hBar)
                    )
                    drawRoundRect(
                        color = SatinGoldPrimary,
                        topLeft = Offset(w * 0.05f, h * 0.45f),
                        size = Size(w * 0.9f, hBar)
                    )
                    drawRoundRect(
                        color = SatinGoldDeepBronze,
                        topLeft = Offset(0f, h * 0.75f),
                        size = Size(w, hBar)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            fontFamily = CleanSansFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = TextHighContrastBone
        )
    }
}
