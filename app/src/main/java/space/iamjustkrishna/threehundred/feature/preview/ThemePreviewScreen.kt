package space.iamjustkrishna.threehundred.feature.preview

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import space.iamjustkrishna.threehundred.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import space.iamjustkrishna.threehundred.core.designsystem.BasaltCardGradient
import space.iamjustkrishna.threehundred.core.designsystem.CanvasDeepBasaltVoid
import space.iamjustkrishna.threehundred.core.designsystem.CleanSansFontFamily
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldDeepBronze
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldHairlineRim
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldLightGleam
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldPrimary
import space.iamjustkrishna.threehundred.core.designsystem.SpartanOxbloodCrimson
import space.iamjustkrishna.threehundred.core.designsystem.SpartanSerifFontFamily
import space.iamjustkrishna.threehundred.core.designsystem.SubtleHairlineBorder
import space.iamjustkrishna.threehundred.core.designsystem.SurfaceBasaltStone
import space.iamjustkrishna.threehundred.core.designsystem.SurfaceMutedIron
import space.iamjustkrishna.threehundred.core.designsystem.TextCharcoalDimmed
import space.iamjustkrishna.threehundred.core.designsystem.TextHighContrastBone
import space.iamjustkrishna.threehundred.core.designsystem.TextWarmSilverMuted

/**
 * SCR-004: Theme Live Preview & 1-Tap Apply
 * Exact 1:1 match of UI_Screen/04_Theme_Live_Preview_Apply_v2.png:
 * - Top bar: Back chevron, title "Obsidian Ritual (CURATED THEME · 088)", favorite heart.
 * - Segmented Mode Switcher: "Home screen" (active brushed gold) vs "Lock screen".
 * - Center Hero Smartphone Mockup: Shows the exact live theme with basalt portal wallpaper,
 *   minimal clock widget ("09:41 · Tue, Apr 22"), and dock of 5 bronze glyph icons.
 * - Feature Badges: "TRUE BLACK", "NATIVE WIDGET", "BRASS GLYPHS".
 * - Component List: Live inspectable rows for Wallpaper, Clock widget, and Icon pack with chevrons.
 * - Master CTA: Large brushed satin-gold pill button: "Apply this set →" with micro-caption
 *   "1-TAP ANDROID SYSTEM APPLIER · ZERO ROOT REQUIRED".
 */
@Composable
fun ThemePreviewScreen(
    themeId: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ThemePreviewViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(themeId) {
        viewModel.loadTheme(themeId)
    }

    ThemePreviewScreenContent(
        uiState = uiState,
        onBackClick = onBackClick,
        onModeSelect = { viewModel.setPreviewMode(it) },
        onFavoriteClick = { viewModel.toggleFavorite() },
        onApplyClick = { viewModel.applyTheme() },
        onDismissSuccess = { viewModel.dismissSuccessModal() },
        modifier = modifier
    )
}

@Composable
fun ThemePreviewScreenContent(
    uiState: ThemePreviewUiState,
    onBackClick: () -> Unit,
    onModeSelect: (PreviewMode) -> Unit,
    onFavoriteClick: () -> Unit,
    onApplyClick: () -> Unit,
    onDismissSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CanvasDeepBasaltVoid)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Top Bar
            PreviewTopBar(
                title = uiState.theme?.title ?: "Obsidian Ritual",
                isFavorite = uiState.isFavorite,
                onBackClick = onBackClick,
                onFavoriteClick = onFavoriteClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Segmented Mode Switcher (Home screen vs Lock screen)
            SegmentedModeSwitcher(
                selectedMode = uiState.previewMode,
                onModeSelect = onModeSelect
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 3. Center Hero Smartphone Mockup
            PreviewPhoneMockup(
                mode = uiState.previewMode,
                themeTitle = uiState.theme?.title ?: "Obsidian Ritual",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // 4. Feature Badges: TRUE BLACK · NATIVE WIDGET · BRASS GLYPHS
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PreviewFeatureBadge(text = "TRUE BLACK")
                Spacer(modifier = Modifier.width(8.dp))
                PreviewFeatureBadge(text = "NATIVE WIDGET")
                Spacer(modifier = Modifier.width(8.dp))
                PreviewFeatureBadge(text = "BRASS GLYPHS")
            }

            Spacer(modifier = Modifier.height(22.dp))

            // 5. Component List Rows
            ComponentListSection()

            Spacer(modifier = Modifier.height(24.dp))

            // 6. Master CTA Button & Micro-caption
            MasterApplySection(
                isApplying = uiState.isApplying,
                onApplyClick = onApplyClick
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Applied Celebration Dialog / Overlay
        if (uiState.isAppliedSuccess) {
            AppliedSuccessDialog(
                themeTitle = uiState.theme?.title ?: "Theme",
                onDismiss = onDismissSuccess
            )
        }
    }
}

/**
 * Top App Bar with back button, screen title, and favorite icon.
 */
@Composable
private fun PreviewTopBar(
    title: String,
    isFavorite: Boolean,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(SurfaceBasaltStone)
                .border(1.dp, SubtleHairlineBorder, CircleShape)
                .clickable(onClick = onBackClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = TextWarmSilverMuted,
                modifier = Modifier.size(18.dp)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = title,
                fontFamily = SpartanSerifFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp,
                color = TextHighContrastBone
            )
            Text(
                text = "CURATED THEME · 088",
                fontFamily = CleanSansFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 9.sp,
                letterSpacing = 1.4.sp,
                color = SatinGoldPrimary
            )
        }

        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(SurfaceBasaltStone)
                .border(1.dp, SubtleHairlineBorder, CircleShape)
                .clickable(onClick = onFavoriteClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = if (isFavorite) SpartanOxbloodCrimson else TextWarmSilverMuted,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

/**
 * Segmented pill switcher between "Home screen" and "Lock screen".
 */
@Composable
private fun SegmentedModeSwitcher(
    selectedMode: PreviewMode,
    onModeSelect: (PreviewMode) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(RoundedCornerShape(999.dp))
            .background(SurfaceBasaltStone)
            .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(999.dp))
            .padding(3.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Home screen option
            val isHome = selectedMode == PreviewMode.HOME_SCREEN
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (isHome) SatinGoldPrimary else Color.Transparent)
                    .clickable { onModeSelect(PreviewMode.HOME_SCREEN) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Home screen",
                    fontFamily = CleanSansFontFamily,
                    fontWeight = if (isHome) FontWeight.SemiBold else FontWeight.Medium,
                    fontSize = 13.sp,
                    color = if (isHome) CanvasDeepBasaltVoid else TextWarmSilverMuted
                )
            }

            // Lock screen option
            val isLock = selectedMode == PreviewMode.LOCK_SCREEN
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (isLock) SatinGoldPrimary else Color.Transparent)
                    .clickable { onModeSelect(PreviewMode.LOCK_SCREEN) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Lock screen",
                    fontFamily = CleanSansFontFamily,
                    fontWeight = if (isLock) FontWeight.SemiBold else FontWeight.Medium,
                    fontSize = 13.sp,
                    color = if (isLock) CanvasDeepBasaltVoid else TextWarmSilverMuted
                )
            }
        }
    }
}

/**
 * Center Hero Smartphone Mockup
 */
@Composable
private fun PreviewPhoneMockup(
    mode: PreviewMode,
    themeTitle: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // Phone Chassis
        Box(
            modifier = Modifier
                .width(220.dp)
                .height(370.dp)
                .clip(RoundedCornerShape(26.dp))
                .border(
                    width = 1.5.dp,
                    brush = Brush.verticalGradient(
                        listOf(
                            SatinGoldLightGleam.copy(alpha = 0.5f),
                            Color(0xFF33333C),
                            SatinGoldDeepBronze.copy(alpha = 0.35f)
                        )
                    ),
                    shape = RoundedCornerShape(26.dp)
                )
                .background(Color(0xFF09090C))
                .padding(6.dp)
        ) {
            // Inside Phone Screen
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp))
                    .background(CanvasDeepBasaltVoid)
            ) {
                // Photorealistic Colonnade Sanctuary Wallpaper (from v2 artwork)
                Image(
                    painter = painterResource(R.drawable.asset_obsidian_ritual),
                    contentDescription = "Obsidian Ritual Wallpaper",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Contrast overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.35f))
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Status bar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "09:41", fontSize = 8.sp, color = TextWarmSilverMuted)
                        Text(text = "100%", fontSize = 8.sp, color = TextWarmSilverMuted)
                    }

                    if (mode == PreviewMode.HOME_SCREEN) {
                        // Home Screen Widgets: Date, Monolith Clock, Stoic quote
                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                        ) {
                            Text(
                                text = "Tue, Apr 22",
                                fontFamily = SpartanSerifFontFamily,
                                fontSize = 11.sp,
                                color = TextHighContrastBone
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "09:41",
                                fontFamily = SpartanSerifFontFamily,
                                fontWeight = FontWeight.Light,
                                fontSize = 32.sp,
                                color = SatinGoldPrimary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "DEPTH OVER NOISE",
                                fontFamily = CleanSansFontFamily,
                                fontSize = 7.sp,
                                letterSpacing = 1.2.sp,
                                color = TextWarmSilverMuted
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        // Search pill
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(24.dp)
                                .clip(RoundedCornerShape(999.dp))
                                .background(Color(0xFF141418))
                                .border(0.5.dp, Color(0x25C5A869), RoundedCornerShape(999.dp))
                                .padding(horizontal = 8.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(text = "Search vault…", fontSize = 8.sp, color = TextCharcoalDimmed)
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // 5 Handcrafted Bronze Dock Icons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            val glyphs = listOf("📞", "💬", "🛡", "🌐", "📷")
                            glyphs.forEach { glyph ->
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Color(0xFF18171B))
                                        .border(0.5.dp, SatinGoldPrimary.copy(alpha = 0.35f), RoundedCornerShape(6.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = glyph, fontSize = 9.sp)
                                }
                            }
                        }
                    } else {
                        // Lock Screen Mode: Big classical clock & stoic quote
                        Spacer(modifier = Modifier.weight(0.5f))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "09:41",
                                fontFamily = SpartanSerifFontFamily,
                                fontSize = 42.sp,
                                color = SatinGoldPrimary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Tuesday, April 22",
                                fontFamily = CleanSansFontFamily,
                                fontSize = 9.sp,
                                color = TextWarmSilverMuted
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Box(
                                modifier = Modifier
                                    .width(28.dp)
                                    .height(1.dp)
                                    .background(SatinGoldPrimary.copy(alpha = 0.5f))
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "\"RETURN WITH YOUR SHIELD,\nOR ON IT.\"",
                                fontFamily = SpartanSerifFontFamily,
                                fontSize = 8.sp,
                                textAlign = TextAlign.Center,
                                color = TextWarmSilverMuted
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "🔒", fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Gesture bar
                    Box(
                        modifier = Modifier
                            .width(36.dp)
                            .height(2.dp)
                            .clip(RoundedCornerShape(999.dp))
                            .background(TextWarmSilverMuted.copy(alpha = 0.5f))
                    )
                }
            }
        }
    }
}

@Composable
private fun PreviewFeatureBadge(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(SurfaceBasaltStone)
            .border(0.5.dp, SatinGoldHairlineRim.copy(alpha = 0.4f), RoundedCornerShape(999.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = text,
            fontFamily = CleanSansFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 9.5.sp,
            letterSpacing = 0.8.sp,
            color = SatinGoldPrimary
        )
    }
}

/**
 * Component List Rows: Wallpaper, Clock widget, Icon pack
 */
@Composable
private fun ComponentListSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(SurfaceBasaltStone)
            .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(18.dp))
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ComponentRow(title = "Wallpaper", subtitle = "Colonnade Sanctuary · 4K True Black")
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SubtleHairlineBorder))
        ComponentRow(title = "Clock Widget", subtitle = "Monolith 2x2 · Chiseled Roman Numerals")
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SubtleHairlineBorder))
        ComponentRow(title = "Icon Pack", subtitle = "Spartan Bronze · 300 Handcrafted Glyphs")
    }
}

@Composable
private fun ComponentRow(title: String, subtitle: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = title,
                fontFamily = CleanSansFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                color = TextHighContrastBone
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                fontFamily = CleanSansFontFamily,
                fontSize = 11.sp,
                color = TextWarmSilverMuted
            )
        }
        Text(
            text = "›",
            fontFamily = SpartanSerifFontFamily,
            fontSize = 18.sp,
            color = TextWarmSilverMuted
        )
    }
}

/**
 * Master CTA Button: Large brushed satin-gold pill button: "Apply this set →"
 */
@Composable
private fun MasterApplySection(
    isApplying: Boolean,
    onApplyClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onApplyClick,
            enabled = !isApplying,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(999.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
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
                if (isApplying) {
                    CircularProgressIndicator(
                        color = CanvasDeepBasaltVoid,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "Apply this set →",
                        fontFamily = CleanSansFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        letterSpacing = 0.6.sp,
                        color = CanvasDeepBasaltVoid
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "1-TAP ANDROID SYSTEM APPLIER · ZERO ROOT REQUIRED",
            fontFamily = CleanSansFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 9.sp,
            letterSpacing = 1.6.sp,
            color = TextCharcoalDimmed
        )
    }
}

/**
 * Applied Success Celebration Modal Dialog
 */
@Composable
private fun AppliedSuccessDialog(
    themeTitle: String,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xCC08080A))
            .clickable(onClick = onDismiss)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(SurfaceBasaltStone)
                .border(1.dp, SatinGoldHairlineRim, RoundedCornerShape(24.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {}
                .padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Gold shield checkmark
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(SatinGoldPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "✓",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = CanvasDeepBasaltVoid
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "THEME APPLIED",
                    fontFamily = SpartanSerifFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    letterSpacing = 0.2.sp,
                    color = SatinGoldPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$themeTitle has been forged onto your device. Enjoy your focused, disciplined canvas.",
                    fontFamily = CleanSansFontFamily,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    color = TextWarmSilverMuted
                )

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(999.dp))
                        .background(SatinGoldPrimary)
                        .clickable(onClick = onDismiss),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Return to Vault",
                        fontFamily = CleanSansFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = CanvasDeepBasaltVoid
                    )
                }
            }
        }
    }
}
