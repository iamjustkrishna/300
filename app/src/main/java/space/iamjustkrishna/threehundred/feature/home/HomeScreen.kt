package space.iamjustkrishna.threehundred.feature.home

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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import space.iamjustkrishna.threehundred.core.designsystem.CanvasDeepBasaltVoid
import space.iamjustkrishna.threehundred.core.designsystem.CanvasObsidianDark
import space.iamjustkrishna.threehundred.core.designsystem.CleanSansFontFamily
import space.iamjustkrishna.threehundred.core.designsystem.CorinthianHelmetGraphic
import space.iamjustkrishna.threehundred.core.designsystem.HopliteShieldMedallion
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldAmbientGlow
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldDeepBronze
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldHairlineRim
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldLightGleam
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldPrimary
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldVerticalGradient
import space.iamjustkrishna.threehundred.core.designsystem.SpartanBottomNavBar
import space.iamjustkrishna.threehundred.core.designsystem.SpartanEmptyState
import space.iamjustkrishna.threehundred.core.designsystem.SpartanErrorCard
import space.iamjustkrishna.threehundred.core.designsystem.SpartanLambdaMedallion
import space.iamjustkrishna.threehundred.core.designsystem.SpartanNavTab
import space.iamjustkrishna.threehundred.core.designsystem.SpartanOxbloodCrimson
import space.iamjustkrishna.threehundred.core.designsystem.SpartanSerifFontFamily
import space.iamjustkrishna.threehundred.core.designsystem.SpartanShimmerCard
import space.iamjustkrishna.threehundred.core.designsystem.SubtleHairlineBorder
import space.iamjustkrishna.threehundred.core.designsystem.SurfaceBasaltStone
import space.iamjustkrishna.threehundred.core.designsystem.SurfaceMutedIron
import space.iamjustkrishna.threehundred.core.designsystem.TextCharcoalDimmed
import space.iamjustkrishna.threehundred.core.designsystem.TextHighContrastBone
import space.iamjustkrishna.threehundred.core.designsystem.TextWarmSilverMuted

/**
 * SCR-003: Home Dashboard (Curated Themes)
 * Exact 1:1 match of UI_Screen/03_Home_Curated_Themes_v2.png:
 * - Top bar: Spartan Lambda badge, "3 0 0 CURATED THEMES", search pill, profile indicator.
 * - Hero Card: "THE DAILY SET · 088" Obsidian Ritual with stone colonnade, flame braziers, "99.2% Black · 4K UHD", "Preview theme >".
 * - Category Filter Rail: "Featured", "AMOLED", "Armor", "Minimal".
 * - Curated Archive: 2-column masonry cards (Spartan Dawn, Helm, Aegean Noir, Last Light).
 * - Stoic Philosophy banner: "DISCIPLINE IS FREEDOM · CURATED SPARTAN ECOSYSTEM · ZERO BLOATWARE".
 * - Docked 4-tab bar: Discover (active), Studio, Vault, Settings.
 */
@Composable
fun HomeScreen(
    onThemeClick: (String) -> Unit,
    onNavigateToStudio: () -> Unit,
    onNavigateToVault: () -> Unit,
    onNavigateToSettings: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreenContent(
        uiState = uiState,
        onThemeClick = onThemeClick,
        onCategorySelect = { viewModel.selectCategory(it) },
        onRetry = { viewModel.retry() },
        onNavigateToStudio = onNavigateToStudio,
        onNavigateToVault = onNavigateToVault,
        onNavigateToSettings = onNavigateToSettings,
        modifier = modifier
    )
}

@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onThemeClick: (String) -> Unit,
    onCategorySelect: (String) -> Unit,
    onRetry: () -> Unit,
    onNavigateToStudio: () -> Unit,
    onNavigateToVault: () -> Unit,
    onNavigateToSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CanvasDeepBasaltVoid)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(bottom = 84.dp) // Clearance for floating docked bottom bar
        ) {
            // 1. Top Bar
            item {
                HomeTopBar(
                    onSearchClick = {},
                    onProfileClick = onNavigateToSettings
                )
            }

            // 2. Daily Hero Theme Card
            item {
                DailyHeroThemeCard(
                    theme = uiState.dailyHeroTheme,
                    onPreviewClick = { onThemeClick(uiState.dailyHeroTheme.id) }
                )
            }

            // 3. Category Filter Rail
            item {
                CategoryFilterRail(
                    categories = uiState.categories,
                    selectedCategory = uiState.selectedCategory,
                    onCategorySelect = onCategorySelect
                )
            }

            // Error or Loading State handling
            if (uiState.isLoading) {
                item {
                    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
                        SpartanShimmerCard(height = 200.dp)
                    }
                }
            } else if (uiState.errorMessage != null) {
                item {
                    Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
                        SpartanErrorCard(
                            message = uiState.errorMessage ?: "Unknown error",
                            onRetry = onRetry
                        )
                    }
                }
            } else if (uiState.curatedThemes.isEmpty()) {
                item {
                    SpartanEmptyState(
                        message = "No Spartan themes found in this category.",
                        onReset = { onCategorySelect("Featured") }
                    )
                }
            } else {
                // 4. Curated Archive: 2-Column Grid
                item {
                    ArchiveThemesGrid(
                        themes = uiState.curatedThemes,
                        onThemeClick = onThemeClick
                    )
                }
            }

            // 5. Stoic Philosophy Banner
            item {
                StoicPhilosophyBanner()
            }
        }

        // Docked 4-Tab Spartan Navigation Bar
        SpartanBottomNavBar(
            currentTab = SpartanNavTab.DISCOVER,
            onTabSelected = { tab ->
                when (tab) {
                    SpartanNavTab.DISCOVER -> { /* Already on Discover */ }
                    SpartanNavTab.STUDIO -> onNavigateToStudio()
                    SpartanNavTab.VAULT -> onNavigateToVault()
                    SpartanNavTab.SETTINGS -> onNavigateToSettings()
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

/**
 * Top App Bar with Spartan Lambda badge, title, search pill, and profile indicator.
 */
@Composable
private fun HomeTopBar(
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            SpartanLambdaMedallion(size = 36.dp)
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "3 0 0",
                    fontFamily = SpartanSerifFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    letterSpacing = 0.12.sp,
                    color = SatinGoldPrimary
                )
                Text(
                    text = "CURATED THEMES",
                    fontFamily = CleanSansFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 9.sp,
                    letterSpacing = 1.6.sp,
                    color = TextWarmSilverMuted
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Search pill button
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(SurfaceBasaltStone)
                    .border(1.dp, SubtleHairlineBorder, CircleShape)
                    .clickable(onClick = onSearchClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = TextWarmSilverMuted,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            // User / Laurel Badge indicator
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(SurfaceBasaltStone)
                    .border(1.dp, SatinGoldHairlineRim, CircleShape)
                    .clickable(onClick = onProfileClick),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "V",
                    fontFamily = SpartanSerifFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = SatinGoldPrimary
                )
            }
        }
    }
}

/**
 * Hero Card: "THE DAILY SET · 088" Obsidian Ritual with stone colonnade, flame braziers.
 */
@Composable
private fun DailyHeroThemeCard(
    theme: HomeThemeItem,
    onPreviewClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(22.dp))
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    listOf(
                        SatinGoldHairlineRim.copy(alpha = 0.5f),
                        SubtleHairlineBorder.copy(alpha = 0.25f),
                        Color.Transparent
                    )
                ),
                shape = RoundedCornerShape(22.dp)
            )
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF141419),
                        Color(0xFF0C0C0F),
                        CanvasDeepBasaltVoid
                    )
                )
            )
            .clickable(onClick = onPreviewClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Micro-header row: "THE DAILY SET · 088" + "99.2% Black · 4K UHD"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "THE DAILY SET · 088",
                    fontFamily = CleanSansFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                    letterSpacing = 1.5.sp,
                    color = SatinGoldPrimary
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(Color(0xFF191922))
                        .border(0.5.dp, SatinGoldHairlineRim.copy(alpha = 0.4f), RoundedCornerShape(999.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "${theme.blackPercentage} · ${theme.resolutionBadge}",
                        fontFamily = CleanSansFontFamily,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextWarmSilverMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Hero Artwork: Colonnade of braziers with glowing flame & archway (v2 photorealistic art)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF09090C)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.asset_obsidian_ritual),
                    contentDescription = "Obsidian Ritual Hero Colonnade",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Title & Subtitle + CTA Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = theme.title,
                        fontFamily = SpartanSerifFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                        color = TextHighContrastBone
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = theme.subtitle,
                        fontFamily = CleanSansFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.5.sp,
                        lineHeight = 16.sp,
                        color = TextWarmSilverMuted,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Gold Preview CTA Button
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(SatinGoldPrimary)
                        .clickable(onClick = onPreviewClick)
                        .padding(horizontal = 14.dp, vertical = 9.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Preview theme >",
                        fontFamily = CleanSansFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.5.sp,
                        color = CanvasDeepBasaltVoid
                    )
                }
            }
        }
    }
}

/**
 * Custom Canvas Artwork for Obsidian Ritual:
 * Classical stone archway portal with glowing golden braziers and distant obsidian columns.
 */
@Composable
fun ColonnadeSanctuaryCanvas(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Deep cave background glow
        drawRect(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFF241F16),
                    Color(0xFF0D0D10),
                    CanvasDeepBasaltVoid
                ),
                center = Offset(w / 2f, h * 0.45f),
                radius = w * 0.6f
            )
        )

        // Colonnade Pillars Left & Right
        val pillarCount = 3
        for (i in 0 until pillarCount) {
            val leftX = w * (0.08f + i * 0.10f)
            val rightX = w * (0.92f - i * 0.10f)
            val topY = h * (0.15f + i * 0.08f)
            val pillarW = 14.dp.toPx() * (1f - i * 0.2f)

            // Left column
            drawLine(
                brush = Brush.verticalGradient(
                    listOf(Color(0x30C5A869), Color(0x10121216))
                ),
                start = Offset(leftX, topY),
                end = Offset(leftX, h),
                strokeWidth = pillarW
            )
            // Right column
            drawLine(
                brush = Brush.verticalGradient(
                    listOf(Color(0x30C5A869), Color(0x10121216))
                ),
                start = Offset(rightX, topY),
                end = Offset(rightX, h),
                strokeWidth = pillarW
            )
        }

        // Center Grand Archway
        val arch = Path().apply {
            moveTo(w * 0.32f, h)
            lineTo(w * 0.32f, h * 0.35f)
            cubicTo(
                w * 0.32f, h * 0.12f,
                w * 0.68f, h * 0.12f,
                w * 0.68f, h * 0.35f
            )
            lineTo(w * 0.68f, h)
        }
        drawPath(
            path = arch,
            brush = Brush.verticalGradient(
                listOf(SatinGoldPrimary.copy(alpha = 0.4f), Color(0x10C5A869), Color.Transparent)
            ),
            style = Stroke(width = 1.5.dp.toPx())
        )

        // Center Spartan Shield Emblem in arch
        val shieldCenter = Offset(w / 2f, h * 0.48f)
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(SatinGoldLightGleam, SatinGoldDeepBronze),
                center = shieldCenter,
                radius = 28.dp.toPx()
            ),
            radius = 22.dp.toPx(),
            center = shieldCenter,
            style = Stroke(width = 2.dp.toPx())
        )

        // Mini Lambda in shield
        val lPath = Path().apply {
            moveTo(shieldCenter.x, shieldCenter.y - 12.dp.toPx())
            lineTo(shieldCenter.x + 10.dp.toPx(), shieldCenter.y + 12.dp.toPx())
            lineTo(shieldCenter.x - 10.dp.toPx(), shieldCenter.y + 12.dp.toPx())
            close()
        }
        drawPath(lPath, color = SatinGoldPrimary, style = Stroke(width = 2.dp.toPx()))

        // Left & Right Brazier Flames
        val leftBrazier = Offset(w * 0.22f, h * 0.72f)
        val rightBrazier = Offset(w * 0.78f, h * 0.72f)

        // Flame glows
        drawCircle(
            brush = Brush.radialGradient(
                listOf(SpartanOxbloodCrimson.copy(alpha = 0.6f), Color.Transparent),
                center = leftBrazier,
                radius = 24.dp.toPx()
            ),
            radius = 24.dp.toPx(),
            center = leftBrazier
        )
        drawCircle(
            brush = Brush.radialGradient(
                listOf(SpartanOxbloodCrimson.copy(alpha = 0.6f), Color.Transparent),
                center = rightBrazier,
                radius = 24.dp.toPx()
            ),
            radius = 24.dp.toPx(),
            center = rightBrazier
        )

        // Fire embers
        drawCircle(color = SatinGoldLightGleam, radius = 2.5.dp.toPx(), center = leftBrazier)
        drawCircle(color = SatinGoldLightGleam, radius = 2.5.dp.toPx(), center = rightBrazier)
    }
}

/**
 * Category Filter Rail: Horizontal pills (Featured, AMOLED, Armor, Minimal).
 */
@Composable
private fun CategoryFilterRail(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelect: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(categories) { cat ->
            val isSelected = cat == selectedCategory
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (isSelected) SatinGoldPrimary else SurfaceBasaltStone)
                    .border(
                        1.dp,
                        if (isSelected) SatinGoldLightGleam else SubtleHairlineBorder,
                        RoundedCornerShape(999.dp)
                    )
                    .clickable { onCategorySelect(cat) }
                    .padding(horizontal = 18.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = cat,
                    fontFamily = CleanSansFontFamily,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                    fontSize = 12.sp,
                    color = if (isSelected) CanvasDeepBasaltVoid else TextWarmSilverMuted
                )
            }
        }
    }
}

/**
 * 2-Column Curated Archive Themes Grid
 */
@Composable
private fun ArchiveThemesGrid(
    themes: List<HomeThemeItem>,
    onThemeClick: (String) -> Unit
) {
    val rows = themes.chunked(2)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        rows.forEach { pair ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                pair.forEach { item ->
                    Box(modifier = Modifier.weight(1f)) {
                        ArchiveThemeCard(theme = item, onClick = { onThemeClick(item.id) })
                    }
                }
                if (pair.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

/**
 * Individual Curated Theme Card in 2-column grid.
 */
@Composable
private fun ArchiveThemeCard(
    theme: HomeThemeItem,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(SurfaceBasaltStone)
            .border(
                1.dp,
                Brush.verticalGradient(
                    listOf(
                        SatinGoldHairlineRim.copy(alpha = 0.35f),
                        SubtleHairlineBorder,
                        Color.Transparent
                    )
                ),
                RoundedCornerShape(18.dp)
            )
            .clickable(onClick = onClick)
            .padding(12.dp)
    ) {
        Column {
            // Artwork canvas with black % badge overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.15f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF09090C)),
                contentAlignment = Alignment.Center
            ) {
                val cardRes = when (theme.artworkType) {
                    ThemeArtworkType.SPARTAN_DAWN -> R.drawable.card_spartan_dawn
                    ThemeArtworkType.HELM -> R.drawable.card_helm
                    ThemeArtworkType.AEGEAN_NOIR -> R.drawable.card_aegean_noir
                    ThemeArtworkType.LAST_LIGHT -> R.drawable.card_last_light
                    else -> R.drawable.asset_obsidian_ritual
                }
                Image(
                    painter = painterResource(cardRes),
                    contentDescription = theme.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Black % badge in top right
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .clip(RoundedCornerShape(999.dp))
                        .background(Color(0xCC09090C))
                        .border(0.5.dp, SubtleHairlineBorder, RoundedCornerShape(999.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = theme.blackPercentage,
                        fontFamily = CleanSansFontFamily,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextWarmSilverMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Title
            Text(
                text = theme.title,
                fontFamily = SpartanSerifFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = TextHighContrastBone,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Subtitle
            Text(
                text = theme.subtitle,
                fontFamily = CleanSansFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 11.sp,
                color = TextWarmSilverMuted,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Color Dot Palette
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                theme.colorDots.forEach { dotColor ->
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(dotColor)
                    )
                }
            }
        }
    }
}

/** Artwork for Spartan Dawn: Monolith rock with red banner and gold moon */
@Composable
private fun SpartanDawnArtwork(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        // Golden dawn horizon glow
        drawCircle(
            brush = Brush.radialGradient(
                listOf(SatinGoldLightGleam.copy(alpha = 0.45f), Color.Transparent),
                center = Offset(w * 0.5f, h * 0.5f),
                radius = w * 0.4f
            ),
            radius = w * 0.4f,
            center = Offset(w * 0.5f, h * 0.5f)
        )
        // Monolith silhouette
        val monolith = Path().apply {
            moveTo(w * 0.35f, h)
            lineTo(w * 0.42f, h * 0.35f)
            lineTo(w * 0.58f, h * 0.35f)
            lineTo(w * 0.65f, h)
            close()
        }
        drawPath(monolith, color = SurfaceBasaltStone)
        // Red crest banner atop monolith
        drawLine(
            color = SpartanOxbloodCrimson,
            start = Offset(w * 0.5f, h * 0.35f),
            end = Offset(w * 0.5f, h * 0.18f),
            strokeWidth = 2.dp.toPx()
        )
    }
}

/** Artwork for Helm: Corinthian bronze helmet */
@Composable
private fun HelmArtwork(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CorinthianHelmetGraphic(size = 56.dp)
    }
}

/** Artwork for Aegean Noir: Split basalt stone with radiant gold sun disk */
@Composable
private fun AegeanNoirArtwork(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        // Radiant sun disk
        drawCircle(
            brush = SatinGoldVerticalGradient,
            radius = 16.dp.toPx(),
            center = Offset(w / 2f, h * 0.48f)
        )
        // Split stone slabs left and right
        val leftSlab = Path().apply {
            moveTo(0f, h)
            lineTo(w * 0.42f, h)
            lineTo(w * 0.40f, h * 0.35f)
            lineTo(0f, h * 0.45f)
            close()
        }
        val rightSlab = Path().apply {
            moveTo(w, h)
            lineTo(w * 0.58f, h)
            lineTo(w * 0.60f, h * 0.35f)
            lineTo(w, h * 0.45f)
            close()
        }
        drawPath(leftSlab, color = SurfaceBasaltStone)
        drawPath(rightSlab, color = SurfaceBasaltStone)
    }
}

/** Artwork for Last Light: Stone colonnade overlooking twilight sea */
@Composable
private fun LastLightArtwork(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        // Sea reflection line
        drawLine(
            brush = Brush.horizontalGradient(
                listOf(Color.Transparent, SatinGoldPrimary.copy(alpha = 0.5f), Color.Transparent)
            ),
            start = Offset(0f, h * 0.65f),
            end = Offset(w, h * 0.65f),
            strokeWidth = 1.5.dp.toPx()
        )
        // Colonnade pillars
        val pW = 4.dp.toPx()
        drawLine(SatinGoldPrimary.copy(alpha = 0.5f), Offset(w * 0.3f, h * 0.2f), Offset(w * 0.3f, h * 0.65f), pW)
        drawLine(SatinGoldPrimary.copy(alpha = 0.5f), Offset(w * 0.5f, h * 0.2f), Offset(w * 0.5f, h * 0.65f), pW)
        drawLine(SatinGoldPrimary.copy(alpha = 0.5f), Offset(w * 0.7f, h * 0.2f), Offset(w * 0.7f, h * 0.65f), pW)
        // Top lintel
        drawLine(SatinGoldPrimary.copy(alpha = 0.6f), Offset(w * 0.22f, h * 0.2f), Offset(w * 0.78f, h * 0.2f), pW)
    }
}

/**
 * Stoic Philosophy Banner at bottom of home feed:
 * "DISCIPLINE IS FREEDOM · CURATED SPARTAN ECOSYSTEM · ZERO BLOATWARE"
 */
@Composable
private fun StoicPhilosophyBanner() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(48.dp)
                .height(1.dp)
                .background(SatinGoldPrimary.copy(alpha = 0.35f))
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "DISCIPLINE IS FREEDOM · CURATED SPARTAN ECOSYSTEM · ZERO BLOATWARE",
            fontFamily = CleanSansFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 9.sp,
            letterSpacing = 1.8.sp,
            color = TextCharcoalDimmed,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(14.dp))
        Box(
            modifier = Modifier
                .width(48.dp)
                .height(1.dp)
                .background(SatinGoldPrimary.copy(alpha = 0.35f))
        )
    }
}
