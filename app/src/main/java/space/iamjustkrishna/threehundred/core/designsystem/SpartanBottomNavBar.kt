package space.iamjustkrishna.threehundred.core.designsystem

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class SpartanNavTab {
    DISCOVER,
    STUDIO,
    VAULT,
    SETTINGS
}

/**
 * Docked 4-Tab Spartan Navigation Bar
 * Classical Stoic Luxury floating dock with frosted basalt background,
 * antique satin-gold active indicator, and chiseled typography.
 */
@Composable
fun SpartanBottomNavBar(
    currentTab: SpartanNavTab,
    onTabSelected: (SpartanNavTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xE6101014)) // Frosted obsidian basalt
                .border(
                    width = 1.dp,
                    brush = Brush.verticalGradient(
                        listOf(
                            SatinGoldHairlineRim.copy(alpha = 0.45f),
                            SubtleHairlineBorder.copy(alpha = 0.2f),
                            Color(0x05C5A869)
                        )
                    ),
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SpartanBottomNavItem(
                    tab = SpartanNavTab.DISCOVER,
                    label = "Discover",
                    isSelected = currentTab == SpartanNavTab.DISCOVER,
                    onClick = { onTabSelected(SpartanNavTab.DISCOVER) }
                )
                SpartanBottomNavItem(
                    tab = SpartanNavTab.STUDIO,
                    label = "Studio",
                    isSelected = currentTab == SpartanNavTab.STUDIO,
                    onClick = { onTabSelected(SpartanNavTab.STUDIO) }
                )
                SpartanBottomNavItem(
                    tab = SpartanNavTab.VAULT,
                    label = "Vault",
                    isSelected = currentTab == SpartanNavTab.VAULT,
                    onClick = { onTabSelected(SpartanNavTab.VAULT) }
                )
                SpartanBottomNavItem(
                    tab = SpartanNavTab.SETTINGS,
                    label = "Settings",
                    isSelected = currentTab == SpartanNavTab.SETTINGS,
                    onClick = { onTabSelected(SpartanNavTab.SETTINGS) }
                )
            }
        }
    }
}

@Composable
private fun SpartanBottomNavItem(
    tab: SpartanNavTab,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val contentColor = if (isSelected) SatinGoldPrimary else TextWarmSilverMuted

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Bespoke minimal Canvas Icon for each tab
        Canvas(modifier = Modifier.size(20.dp)) {
            val w = size.width
            val h = size.height
            val strokeW = 1.6.dp.toPx()

            when (tab) {
                SpartanNavTab.DISCOVER -> {
                    // Doric Temple / Greek Shield
                    val p = Path().apply {
                        moveTo(w / 2f, h * 0.1f)
                        lineTo(w * 0.9f, h * 0.35f)
                        lineTo(w * 0.9f, h * 0.65f)
                        lineTo(w / 2f, h * 0.92f)
                        lineTo(w * 0.1f, h * 0.65f)
                        lineTo(w * 0.1f, h * 0.35f)
                        close()
                    }
                    drawPath(p, color = contentColor, style = Stroke(width = strokeW))
                    if (isSelected) {
                        drawCircle(color = contentColor, radius = 2.dp.toPx(), center = Offset(w / 2f, h / 2f))
                    }
                }
                SpartanNavTab.STUDIO -> {
                    // 2x2 Widget grid / Doric column portico
                    val p1 = Path().apply {
                        moveTo(w * 0.15f, h * 0.25f)
                        lineTo(w * 0.85f, h * 0.25f)
                        moveTo(w * 0.28f, h * 0.25f)
                        lineTo(w * 0.28f, h * 0.82f)
                        moveTo(w * 0.50f, h * 0.25f)
                        lineTo(w * 0.50f, h * 0.82f)
                        moveTo(w * 0.72f, h * 0.25f)
                        lineTo(w * 0.72f, h * 0.82f)
                        moveTo(w * 0.15f, h * 0.82f)
                        lineTo(w * 0.85f, h * 0.82f)
                    }
                    drawPath(p1, color = contentColor, style = Stroke(width = strokeW, cap = StrokeCap.Round))
                }
                SpartanNavTab.VAULT -> {
                    // Vault Lock / Arch
                    val lockArch = Path().apply {
                        moveTo(w * 0.25f, h * 0.45f)
                        lineTo(w * 0.25f, h * 0.3f)
                        cubicTo(w * 0.25f, h * 0.1f, w * 0.75f, h * 0.1f, w * 0.75f, h * 0.3f)
                        lineTo(w * 0.75f, h * 0.45f)
                    }
                    drawPath(lockArch, color = contentColor, style = Stroke(width = strokeW))
                    drawRoundRect(
                        color = contentColor,
                        topLeft = Offset(w * 0.18f, h * 0.45f),
                        size = androidx.compose.ui.geometry.Size(w * 0.64f, h * 0.45f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(3.dp.toPx(), 3.dp.toPx()),
                        style = Stroke(width = strokeW)
                    )
                    drawCircle(color = contentColor, radius = 1.5.dp.toPx(), center = Offset(w / 2f, h * 0.65f))
                }
                SpartanNavTab.SETTINGS -> {
                    // Minimal Gear / Council wheel
                    drawCircle(color = contentColor, radius = w * 0.34f, center = Offset(w / 2f, h / 2f), style = Stroke(width = strokeW))
                    drawCircle(color = contentColor, radius = w * 0.12f, center = Offset(w / 2f, h / 2f))
                    // 4 teeth notches
                    drawLine(contentColor, Offset(w / 2f, 0f), Offset(w / 2f, h * 0.18f), strokeW)
                    drawLine(contentColor, Offset(w / 2f, h * 0.82f), Offset(w / 2f, h), strokeW)
                    drawLine(contentColor, Offset(0f, h / 2f), Offset(w * 0.18f, h / 2f), strokeW)
                    drawLine(contentColor, Offset(w * 0.82f, h / 2f), Offset(w, h / 2f), strokeW)
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            fontFamily = CleanSansFontFamily,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            fontSize = 10.sp,
            letterSpacing = 0.4.sp,
            color = contentColor
        )

        // Specular dot under selected item
        if (isSelected) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .size(3.dp)
                    .clip(androidx.compose.foundation.shape.CircleShape)
                    .background(SatinGoldLightGleam)
            )
        }
    }
}
