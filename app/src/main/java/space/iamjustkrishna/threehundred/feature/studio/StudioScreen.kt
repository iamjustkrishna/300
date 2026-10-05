package space.iamjustkrishna.threehundred.feature.studio

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import space.iamjustkrishna.threehundred.core.designsystem.CanvasDeepBasaltVoid
import space.iamjustkrishna.threehundred.core.designsystem.CleanSansFontFamily
import space.iamjustkrishna.threehundred.core.designsystem.CrimsonPowerArc
import space.iamjustkrishna.threehundred.core.designsystem.HopliteShieldMedallion
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldDeepBronze
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldHairlineRim
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldLightGleam
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldPrimary
import space.iamjustkrishna.threehundred.core.designsystem.SpartanBottomNavBar
import space.iamjustkrishna.threehundred.core.designsystem.SpartanNavTab
import space.iamjustkrishna.threehundred.core.designsystem.SpartanOxbloodCrimson
import space.iamjustkrishna.threehundred.core.designsystem.SpartanSerifFontFamily
import space.iamjustkrishna.threehundred.core.designsystem.SubtleHairlineBorder
import space.iamjustkrishna.threehundred.core.designsystem.SurfaceBasaltStone
import space.iamjustkrishna.threehundred.core.designsystem.TextCharcoalDimmed
import space.iamjustkrishna.threehundred.core.designsystem.TextHighContrastBone
import space.iamjustkrishna.threehundred.core.designsystem.TextWarmSilverMuted
import space.iamjustkrishna.threehundred.core.system.WallpaperApplier
import space.iamjustkrishna.threehundred.feature.glance.GlancePinningHelper

enum class StudioTab {
    WIDGETS,
    WALLPAPERS
}

/**
 * SCR-005: Studio (Widgets & Wallpapers)
 * Exact 1:1 match of UI_Screen/05_Studio_Widgets_Wallpapers_v2.png:
 * - Header: Classical serif "Studio / SHAPES A CALMER TOMORROW" with gold badge "300 SET ACTIVE".
 * - Dual pill category switcher: "Widgets" (active) vs "Wallpapers".
 * - Featured Widget Forge Cards:
 *   1. Monolith Clock (2x2): Roman numerals (XII, III, VI, IX), analog hands, digital readout,
 *      stoic quote "Time keeps better company here.", "+ Add to home" button.
 *   2. Shield Battery (2x2): Sculpted bronze hoplite shield with Lambda, 78% battery readout,
 *      crimson power arc, quote "Power fuels a quieter path.", "+ Add to home" button.
 * - Wallpaper Sets: 2-column grid featuring Obsidian Sanctuary and Crimson Silence.
 * - Docked 4-tab bar: Discover, Studio (active), Vault, Settings.
 */
@Composable
fun StudioScreen(
    onNavigateToDiscover: () -> Unit,
    onNavigateToVault: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToIcons: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var currentTab by remember { mutableStateOf(StudioTab.WIDGETS) }
    val wallpaperApplier = remember { WallpaperApplier(context) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CanvasDeepBasaltVoid)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(bottom = 84.dp)
        ) {
            // 1. Studio Header & Laurel Badge
            item {
                StudioHeader()
            }

            // 2. Dual Category Switcher (Widgets vs Wallpapers)
            item {
                StudioTabSwitcher(
                    selectedTab = currentTab,
                    onTabSelect = { currentTab = it }
                )
            }

            item {
                Text(
                    text = "OPEN ICON FORGE  →",
                    fontFamily = CleanSansFontFamily,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SatinGoldPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onNavigateToIcons)
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }

            if (currentTab == StudioTab.WIDGETS) {
                // 3. Featured Widget Forge: Monolith Clock (2x2)
                item {
                    MonolithClockForgeCard(
                        onAddToHome = {
                            GlancePinningHelper.pinMonolithClock(context)
                            Toast.makeText(context, "Requesting Monolith Clock widget pin…", Toast.LENGTH_SHORT).show()
                        }
                    )
                }

                // 4. Featured Widget Forge: Shield Battery (2x2)
                item {
                    ShieldBatteryForgeCard(
                        onAddToHome = {
                            GlancePinningHelper.pinShieldBattery(context)
                            Toast.makeText(context, "Requesting Shield Battery widget pin…", Toast.LENGTH_SHORT).show()
                        }
                    )
                }

                item {
                    CompactWidgetForgeCard(
                        title = "Creed Ticker",
                        size = "4×1 GLANCE WIDGET",
                        preview = "Λ  ·  DAILY CREED\nDISCIPLINE IS FREEDOM.",
                        quote = "A maxim for the line ahead.",
                        onAddToHome = {
                            GlancePinningHelper.pinCreedTicker(context)
                            Toast.makeText(context, "Requesting Creed Ticker widget pin…", Toast.LENGTH_SHORT).show()
                        }
                    )
                }

                item {
                    CompactWidgetForgeCard(
                        title = "War Calendar",
                        size = "4×2 GLANCE WIDGET",
                        preview = "Λ  ·  TACTICAL WEEK\nM  T  W  T  F  S  S",
                        quote = "Hold the line, one day at a time.",
                        onAddToHome = {
                            GlancePinningHelper.pinWarCalendar(context)
                            Toast.makeText(context, "Requesting War Calendar widget pin…", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            } else {
                // Wallpapers Catalog Grid
                item {
                    StudioWallpapersGrid(
                        onApplyWallpaper = { title ->
                            coroutineScope.launch {
                                val width = 1080
                                val height = 2400
                                val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                                val canvas = Canvas(bitmap)
                                canvas.drawColor(android.graphics.Color.parseColor("#08080A"))
                                val paint = Paint().apply {
                                    isAntiAlias = true
                                    color = android.graphics.Color.parseColor("#8A1C14")
                                    style = Paint.Style.FILL
                                }
                                canvas.drawCircle(width / 2f, height * 0.45f, width * 0.4f, paint)
                                wallpaperApplier.applyWallpaper(bitmap)
                                Toast.makeText(context, "$title applied to device!", Toast.LENGTH_SHORT).show()
                            }
                        },
                        onUnlockVanguard = onNavigateToSettings
                    )
                }
            }
        }

        // Docked 4-Tab Spartan Navigation Bar
        SpartanBottomNavBar(
            currentTab = SpartanNavTab.STUDIO,
            onTabSelected = { tab ->
                when (tab) {
                    SpartanNavTab.DISCOVER -> onNavigateToDiscover()
                    SpartanNavTab.STUDIO -> { /* Already on Studio */ }
                    SpartanNavTab.VAULT -> onNavigateToVault()
                    SpartanNavTab.SETTINGS -> onNavigateToSettings()
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

/**
 * Header with "Studio / SHAPES A CALMER TOMORROW" and "300 SET ACTIVE" badge.
 */
@Composable
private fun StudioHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Studio",
                fontFamily = SpartanSerifFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 28.sp,
                color = TextHighContrastBone
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "SHAPES A CALMER TOMORROW",
                fontFamily = CleanSansFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 9.sp,
                letterSpacing = 1.4.sp,
                color = SatinGoldPrimary
            )
        }

        // 300 SET ACTIVE pill badge
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(999.dp))
                .background(SurfaceBasaltStone)
                .border(1.dp, SatinGoldHairlineRim, RoundedCornerShape(999.dp))
                .padding(horizontal = 10.dp, vertical = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "300 SET ACTIVE",
                fontFamily = CleanSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 9.sp,
                letterSpacing = 1.2.sp,
                color = SatinGoldPrimary
            )
        }
    }
}

/**
 * Dual Pill Tab Switcher: "Widgets" vs "Wallpapers"
 */
@Composable
private fun StudioTabSwitcher(
    selectedTab: StudioTab,
    onTabSelect: (StudioTab) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .height(44.dp)
            .clip(RoundedCornerShape(999.dp))
            .background(SurfaceBasaltStone)
            .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(999.dp))
            .padding(3.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            val isWidgets = selectedTab == StudioTab.WIDGETS
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (isWidgets) SatinGoldPrimary else androidx.compose.ui.graphics.Color.Transparent)
                    .clickable { onTabSelect(StudioTab.WIDGETS) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Widgets",
                    fontFamily = CleanSansFontFamily,
                    fontWeight = if (isWidgets) FontWeight.SemiBold else FontWeight.Medium,
                    fontSize = 13.sp,
                    color = if (isWidgets) CanvasDeepBasaltVoid else TextWarmSilverMuted
                )
            }

            val isWallpapers = selectedTab == StudioTab.WALLPAPERS
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (isWallpapers) SatinGoldPrimary else androidx.compose.ui.graphics.Color.Transparent)
                    .clickable { onTabSelect(StudioTab.WALLPAPERS) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Wallpapers",
                    fontFamily = CleanSansFontFamily,
                    fontWeight = if (isWallpapers) FontWeight.SemiBold else FontWeight.Medium,
                    fontSize = 13.sp,
                    color = if (isWallpapers) CanvasDeepBasaltVoid else TextWarmSilverMuted
                )
            }
        }
    }
}

/**
 * Featured Widget Card 1: Monolith Clock (2x2)
 */
@Composable
private fun MonolithClockForgeCard(
    onAddToHome: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(SurfaceBasaltStone)
            .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Monolith Clock",
                        fontFamily = SpartanSerifFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = TextHighContrastBone
                    )
                    Text(
                        text = "2×2 GLANCE WIDGET",
                        fontFamily = CleanSansFontFamily,
                        fontSize = 9.sp,
                        letterSpacing = 1.2.sp,
                        color = SatinGoldPrimary
                    )
                }

                // Add to Home gold CTA
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(SatinGoldPrimary)
                        .clickable(onClick = onAddToHome)
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+ Add to home",
                        fontFamily = CleanSansFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.5.sp,
                        color = CanvasDeepBasaltVoid
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Widget Visual: Monolith Clock (v2 photorealistic artwork)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF0A0A0D))
                    .border(0.5.dp, SubtleHairlineBorder, RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.widget_monolith_clock),
                    contentDescription = "Monolith Clock Widget",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "\"Time keeps better company here.\"",
                fontFamily = SpartanSerifFontFamily,
                fontSize = 11.5.sp,
                color = TextWarmSilverMuted
            )
        }
    }
}

/**
 * Featured Widget Card 2: Shield Battery (2x2)
 */
@Composable
private fun ShieldBatteryForgeCard(
    onAddToHome: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(SurfaceBasaltStone)
            .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Shield Battery",
                        fontFamily = SpartanSerifFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = TextHighContrastBone
                    )
                    Text(
                        text = "2×2 GLANCE WIDGET",
                        fontFamily = CleanSansFontFamily,
                        fontSize = 9.sp,
                        letterSpacing = 1.2.sp,
                        color = SatinGoldPrimary
                    )
                }

                // Add to Home gold CTA
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(SatinGoldPrimary)
                        .clickable(onClick = onAddToHome)
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+ Add to home",
                        fontFamily = CleanSansFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.5.sp,
                        color = CanvasDeepBasaltVoid
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Widget Visual: Shield Battery (v2 photorealistic artwork)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF0A0A0D))
                    .border(0.5.dp, SubtleHairlineBorder, RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.widget_shield_battery),
                    contentDescription = "Shield Battery Widget",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "\"Power fuels a quieter path.\"",
                fontFamily = SpartanSerifFontFamily,
                fontSize = 11.5.sp,
                color = TextWarmSilverMuted
            )
        }
    }
}

/** Native-only previews for the compact 4×1 and 4×2 Glance widgets. */
@Composable
private fun CompactWidgetForgeCard(
    title: String,
    size: String,
    preview: String,
    quote: String,
    onAddToHome: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(SurfaceBasaltStone)
            .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(title, fontFamily = SpartanSerifFontFamily, fontWeight = FontWeight.Medium,
                        fontSize = 18.sp, color = TextHighContrastBone)
                    Text(size, fontFamily = CleanSansFontFamily, fontSize = 9.sp,
                        letterSpacing = 1.2.sp, color = SatinGoldPrimary)
                }
                Box(
                    modifier = Modifier.clip(RoundedCornerShape(999.dp)).background(SatinGoldPrimary)
                        .clickable(onClick = onAddToHome).padding(horizontal = 14.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("+ Add to home", fontFamily = CleanSansFontFamily, fontWeight = FontWeight.SemiBold,
                        fontSize = 11.5.sp, color = CanvasDeepBasaltVoid)
                }
            }
            Spacer(Modifier.height(14.dp))
            Box(
                modifier = Modifier.fillMaxWidth().height(88.dp).clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF0A0A0D)).border(0.5.dp, SubtleHairlineBorder, RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(preview, fontFamily = CleanSansFontFamily, fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp, lineHeight = 20.sp, letterSpacing = 1.sp,
                    color = SatinGoldPrimary, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            }
            Spacer(Modifier.height(10.dp))
            Text("\"$quote\"", fontFamily = SpartanSerifFontFamily, fontSize = 11.5.sp, color = TextWarmSilverMuted)
        }
    }
}

/**
 * Wallpapers 2-Column Grid
 */
@Composable
private fun StudioWallpapersGrid(
    onApplyWallpaper: (String) -> Unit,
    onUnlockVanguard: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Wallpaper 1: Obsidian Sanctuary (Free)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(18.dp))
                    .background(SurfaceBasaltStone)
                    .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(18.dp))
                    .clickable { onApplyWallpaper("Obsidian Sanctuary") }
                    .padding(12.dp)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.85f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF0C0C10)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.card_wp_sanctuary),
                            contentDescription = "Obsidian Sanctuary Wallpaper",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Obsidian Sanctuary",
                        fontFamily = SpartanSerifFontFamily,
                        fontSize = 14.sp,
                        color = TextHighContrastBone
                    )
                    Text(
                        text = "Tap to set 1-tap",
                        fontFamily = CleanSansFontFamily,
                        fontSize = 10.sp,
                        color = SatinGoldPrimary
                    )
                }
            }

            // Wallpaper 2: Crimson Silence (Vanguard Locked)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(18.dp))
                    .background(SurfaceBasaltStone)
                    .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(18.dp))
                    .clickable { onUnlockVanguard() }
                    .padding(12.dp)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.85f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF140D0F)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.wallpaper_crimson_silence),
                            contentDescription = "Crimson Silence Wallpaper",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.35f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "🔒", fontSize = 20.sp)
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(
                                    text = "VANGUARD",
                                    fontFamily = CleanSansFontFamily,
                                    fontSize = 8.sp,
                                    letterSpacing = 1.sp,
                                    color = SatinGoldPrimary
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Crimson Silence",
                        fontFamily = SpartanSerifFontFamily,
                        fontSize = 14.sp,
                        color = TextHighContrastBone
                    )
                    Text(
                        text = "Vanguard Exclusive",
                        fontFamily = CleanSansFontFamily,
                        fontSize = 10.sp,
                        color = SpartanOxbloodCrimson
                    )
                }
            }
        }
    }
}
