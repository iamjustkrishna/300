package space.iamjustkrishna.threehundred.feature.vault

import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.launch
import space.iamjustkrishna.threehundred.core.designsystem.CanvasDeepBasaltVoid
import space.iamjustkrishna.threehundred.core.designsystem.CanvasObsidianDark
import space.iamjustkrishna.threehundred.core.designsystem.CleanSansFontFamily
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldDeepBronze
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldHairlineRim
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldLightGleam
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldPrimary
import space.iamjustkrishna.threehundred.core.designsystem.SpartanBottomNavBar
import space.iamjustkrishna.threehundred.core.designsystem.SpartanLambdaMedallion
import space.iamjustkrishna.threehundred.core.designsystem.SpartanNavTab
import space.iamjustkrishna.threehundred.core.designsystem.SpartanOxbloodCrimson
import space.iamjustkrishna.threehundred.core.designsystem.SpartanSerifFontFamily
import space.iamjustkrishna.threehundred.core.designsystem.SubtleHairlineBorder
import space.iamjustkrishna.threehundred.core.designsystem.SurfaceBasaltStone
import space.iamjustkrishna.threehundred.core.designsystem.SurfaceMutedIron
import space.iamjustkrishna.threehundred.core.designsystem.TextCharcoalDimmed
import space.iamjustkrishna.threehundred.core.designsystem.TextHighContrastBone
import space.iamjustkrishna.threehundred.core.designsystem.TextWarmSilverMuted
import space.iamjustkrishna.threehundred.core.model.UserPreferences
import space.iamjustkrishna.threehundred.core.preferences.VanguardPreferences
import space.iamjustkrishna.threehundred.core.system.WallpaperApplier
import space.iamjustkrishna.threehundred.core.system.WallpaperCrop

/**
 * SCR-007: Wallpaper Vault
 *
 * Dedicated browsing experience for all 12 Masterwork wallpapers:
 * - 6 Free Masters + 6 Vanguard Masters.
 * - Classical Stoic Luxury obsidian aesthetic (#08080A).
 * - Category filtering (All, Featured, AMOLED, Armor, Minimal).
 * - Per-card True-Black % badge, resolution label, offline status.
 * - Direct 1-tap Android WallpaperManager applier (Home, Lock, Both).
 * - Vanguard Lifetime Pass protection for premium armor and monolith masters.
 * - Docked 4-tab bar (Discover, Studio, Vault [active], Settings).
 */
@Composable
fun VaultScreen(
    onNavigateToDiscover: () -> Unit,
    onNavigateToStudio: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onPreviewTheme: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val preferences = remember { VanguardPreferences(context) }
    val userPrefs by preferences.userPreferences.collectAsState(initial = UserPreferences())
    val wallpaperApplier = remember { WallpaperApplier(context) }

    var selectedCategory by remember { mutableStateOf("All") }
    var selectedItemForApply by remember { mutableStateOf<VaultWallpaperItem?>(null) }
    var showVanguardModal by remember { mutableStateOf(false) }

    val categories = listOf("All", "Featured", "AMOLED", "Armor", "Minimal")

    val filteredItems = remember(selectedCategory) {
        if (selectedCategory == "All") {
            MasterWallpaperCatalog
        } else {
            MasterWallpaperCatalog.filter { it.category.equals(selectedCategory, ignoreCase = true) }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CanvasDeepBasaltVoid)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(bottom = 88.dp)
        ) {
            // 1. Vault Header
            item {
                VaultHeader(
                    isVanguardUnlocked = userPrefs.isVanguardUnlocked,
                    onVanguardBadgeClick = {
                        if (!userPrefs.isVanguardUnlocked) {
                            showVanguardModal = true
                        } else {
                            onNavigateToSettings()
                        }
                    }
                )
            }

            // 2. Category Filter Bar
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { cat ->
                        val isSelected = cat == selectedCategory
                        VaultFilterChip(
                            label = cat,
                            isSelected = isSelected,
                            onClick = { selectedCategory = cat }
                        )
                    }
                }
            }

            if (filteredItems.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp, vertical = 56.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = SatinGoldHairlineRim,
                            modifier = Modifier.size(36.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "NO MASTERWORKS IN THIS FILTER",
                            fontFamily = SpartanSerifFontFamily,
                            fontSize = 13.sp,
                            color = TextHighContrastBone,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Reset the arsenal filter to continue browsing.",
                            fontFamily = CleanSansFontFamily,
                            fontSize = 11.sp,
                            color = TextWarmSilverMuted,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                        OutlinedButton(
                            onClick = { selectedCategory = "All" },
                            border = androidx.compose.foundation.BorderStroke(1.dp, SatinGoldHairlineRim),
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Text("RESET FILTERS", fontSize = 10.sp, color = SatinGoldPrimary)
                        }
                    }
                }
            }

            // 3. Grid of Wallpapers (2 items per row)
            val chunkedItems = filteredItems.chunked(2)
            items(chunkedItems) { pair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val first = pair[0]
                    val isFirstLocked = first.isVanguard && !userPrefs.isVanguardUnlocked
                    VaultWallpaperCard(
                        item = first,
                        isLocked = isFirstLocked,
                        onClick = {
                            if (isFirstLocked) {
                                showVanguardModal = true
                            } else {
                                selectedItemForApply = first
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )

                    if (pair.size > 1) {
                        val second = pair[1]
                        val isSecondLocked = second.isVanguard && !userPrefs.isVanguardUnlocked
                        VaultWallpaperCard(
                            item = second,
                            isLocked = isSecondLocked,
                            onClick = {
                                if (isSecondLocked) {
                                    showVanguardModal = true
                                } else {
                                    selectedItemForApply = second
                                }
                            },
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            // 4. Stoic Philosophy Footer
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "DISCIPLINE IN EVERY DETAIL",
                        fontFamily = SpartanSerifFontFamily,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SatinGoldHairlineRim,
                        letterSpacing = 2.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "12 original master scenes engineered for OLED longevity.\nZero burn-in risk · 100% black top status area.",
                        fontFamily = CleanSansFontFamily,
                        fontSize = 10.sp,
                        color = TextCharcoalDimmed,
                        textAlign = TextAlign.Center,
                        lineHeight = 14.sp
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Apply Wallpaper Dialog
        selectedItemForApply?.let { item ->
            ApplyWallpaperDialog(
                item = item,
                onDismiss = { selectedItemForApply = null },
                onApply = { target, horizontalOffset, verticalOffset ->
                    coroutineScope.launch {
                        try {
                            val bitmap = BitmapFactory.decodeResource(context.resources, item.masterDrawableRes)
                            if (bitmap != null) {
                                val crop = WallpaperCrop.calculate(
                                    sourceWidth = bitmap.width,
                                    sourceHeight = bitmap.height,
                                    viewportWidth = context.resources.displayMetrics.widthPixels,
                                    viewportHeight = context.resources.displayMetrics.heightPixels,
                                    horizontalOffset = horizontalOffset,
                                    verticalOffset = verticalOffset
                                )
                                val cropped = Bitmap.createBitmap(
                                    bitmap,
                                    crop.left,
                                    crop.top,
                                    crop.width,
                                    crop.height
                                )
                                wallpaperApplier.applyWallpaper(cropped, target)
                                if (cropped !== bitmap) cropped.recycle()
                                bitmap.recycle()
                                Toast.makeText(
                                    context,
                                    "Applied \"${item.title}\" to $target!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(context, "Failed to decode wallpaper", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            Toast.makeText(context, "Apply error: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                        selectedItemForApply = null
                    }
                },
                onFullPreview = {
                    selectedItemForApply = null
                    onPreviewTheme(item.themeId)
                }
            )
        }

        // Vanguard Pass Unlock Dialog
        if (showVanguardModal) {
            VanguardPassModal(
                onDismiss = { showVanguardModal = false },
                onUnlock = {
                    coroutineScope.launch {
                        preferences.setVanguardUnlocked(true)
                        Toast.makeText(context, "Vanguard Pass Unlocked! 12 Masterworks Active.", Toast.LENGTH_SHORT).show()
                        showVanguardModal = false
                    }
                },
                onGoToSettings = {
                    showVanguardModal = false
                    onNavigateToSettings()
                }
            )
        }

        // Docked 4-Tab Spartan Bottom Navigation Bar
        SpartanBottomNavBar(
            currentTab = SpartanNavTab.VAULT,
            onTabSelected = { tab ->
                when (tab) {
                    SpartanNavTab.DISCOVER -> onNavigateToDiscover()
                    SpartanNavTab.STUDIO -> onNavigateToStudio()
                    SpartanNavTab.VAULT -> { /* Current screen */ }
                    SpartanNavTab.SETTINGS -> onNavigateToSettings()
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

/**
 * Top branding header for the Wallpaper Vault.
 */
@Composable
private fun VaultHeader(
    isVanguardUnlocked: Boolean,
    onVanguardBadgeClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SpartanLambdaMedallion(size = 36.dp)
            Column {
                Text(
                    text = "THE VAULT",
                    fontFamily = SpartanSerifFontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHighContrastBone,
                    letterSpacing = 1.5.sp
                )
                Text(
                    text = "12 MASTERWORKS · TRUE-BLACK",
                    fontFamily = CleanSansFontFamily,
                    fontSize = 9.sp,
                    color = SatinGoldHairlineRim,
                    letterSpacing = 1.sp
                )
            }
        }

        // Vanguard entitlement status pill
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(
                    if (isVanguardUnlocked) SurfaceMutedIron else SpartanOxbloodCrimson.copy(alpha = 0.25f)
                )
                .border(
                    1.dp,
                    if (isVanguardUnlocked) SatinGoldPrimary else SpartanOxbloodCrimson,
                    RoundedCornerShape(20.dp)
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onVanguardBadgeClick
                )
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(
                text = if (isVanguardUnlocked) "VANGUARD ACTIVE" else "6 FREE · 6 PASS",
                fontFamily = CleanSansFontFamily,
                fontSize = 9.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (isVanguardUnlocked) SatinGoldPrimary else TextHighContrastBone
            )
        }
    }
}

/**
 * Category filter chip matching Spartan design system.
 */
@Composable
private fun VaultFilterChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) SatinGoldPrimary else SurfaceBasaltStone)
            .border(
                1.dp,
                if (isSelected) SatinGoldPrimary else SubtleHairlineBorder,
                RoundedCornerShape(16.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            fontFamily = CleanSansFontFamily,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) CanvasDeepBasaltVoid else TextWarmSilverMuted
        )
    }
}

/**
 * Single wallpaper portrait card with badges and overlays.
 */
@Composable
private fun VaultWallpaperCard(
    item: VaultWallpaperItem,
    isLocked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(9f / 15f)
            .clip(RoundedCornerShape(18.dp))
            .background(SurfaceBasaltStone)
            .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(18.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        // Wallpaper Thumbnail
        Image(
            painter = painterResource(item.cardDrawableRes),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Top Badges
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // True Black % badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(CanvasDeepBasaltVoid.copy(alpha = 0.85f))
                    .border(0.5.dp, SatinGoldHairlineRim, RoundedCornerShape(6.dp))
                    .padding(horizontal = 5.dp, vertical = 2.dp)
            ) {
                Text(
                    text = item.blackPercentage,
                    fontFamily = CleanSansFontFamily,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    color = SatinGoldPrimary
                )
            }

            // Entitlement badge
            if (item.isVanguard) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(
                            if (isLocked) SpartanOxbloodCrimson.copy(alpha = 0.9f)
                            else SatinGoldPrimary.copy(alpha = 0.9f)
                        )
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = if (isLocked) "PASS" else "VANGUARD",
                        fontFamily = CleanSansFontFamily,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isLocked) TextHighContrastBone else CanvasDeepBasaltVoid
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(CanvasDeepBasaltVoid.copy(alpha = 0.85f))
                        .border(0.5.dp, SubtleHairlineBorder, RoundedCornerShape(6.dp))
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "FREE",
                        fontFamily = CleanSansFontFamily,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWarmSilverMuted
                    )
                }
            }
        }

        // Locked Scrim Overlay
        if (isLocked) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(CanvasDeepBasaltVoid.copy(alpha = 0.65f)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Locked",
                        tint = SatinGoldPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "LOCKED",
                        fontFamily = SpartanSerifFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextHighContrastBone,
                        letterSpacing = 1.sp
                    )
                }
            }
        }

        // Bottom Gradient Scrim with Title & Resolution
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, CanvasDeepBasaltVoid.copy(alpha = 0.95f))
                    )
                )
                .padding(horizontal = 10.dp, vertical = 8.dp)
        ) {
            Column {
                Text(
                    text = item.title,
                    fontFamily = SpartanSerifFontFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHighContrastBone,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "OFFLINE READY",
                        fontFamily = CleanSansFontFamily,
                        fontSize = 8.sp,
                        color = SatinGoldHairlineRim
                    )
                    Text(
                        text = "·",
                        fontFamily = CleanSansFontFamily,
                        fontSize = 8.sp,
                        color = TextCharcoalDimmed
                    )
                    Text(
                        text = item.category,
                        fontFamily = CleanSansFontFamily,
                        fontSize = 8.sp,
                        color = TextWarmSilverMuted
                    )
                }
            }
        }
    }
}

/**
 * Dialog for 1-Tap Wallpaper application (Home, Lock, Both) or full theme preview.
 */
@Composable
private fun ApplyWallpaperDialog(
    item: VaultWallpaperItem,
    onDismiss: () -> Unit,
    onApply: (WallpaperApplier.TargetScreen, Float, Float) -> Unit,
    onFullPreview: () -> Unit
) {
    var horizontalOffset by remember { mutableStateOf(0f) }
    var verticalOffset by remember { mutableStateOf(0f) }
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = SurfaceBasaltStone,
            border = androidx.compose.foundation.BorderStroke(1.dp, SatinGoldHairlineRim),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "SET WALLPAPER",
                        fontFamily = SpartanSerifFontFamily,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = SatinGoldPrimary,
                        letterSpacing = 1.sp
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = TextCharcoalDimmed,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable(onClick = onDismiss)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Wallpaper preview
                Box(
                    modifier = Modifier
                        .width(140.dp)
                        .aspectRatio(9f / 16f)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(12.dp))
                ) {
                    Image(
                        painter = painterResource(item.masterDrawableRes),
                        contentDescription = item.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Text(
                    text = "POSITION ARTWORK",
                    fontFamily = CleanSansFontFamily,
                    fontSize = 9.sp,
                    color = SatinGoldHairlineRim,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    text = "Adjust the crop before applying. Home and Lock use the same position.",
                    fontFamily = CleanSansFontFamily,
                    fontSize = 10.sp,
                    color = TextWarmSilverMuted,
                    textAlign = TextAlign.Center
                )
                Slider(
                    value = horizontalOffset,
                    onValueChange = { horizontalOffset = it },
                    valueRange = -1f..1f,
                    modifier = Modifier.fillMaxWidth(),
                    colors = androidx.compose.material3.SliderDefaults.colors(
                        thumbColor = SatinGoldPrimary,
                        activeTrackColor = SatinGoldPrimary
                    )
                )
                Slider(
                    value = verticalOffset,
                    onValueChange = { verticalOffset = it },
                    valueRange = -1f..1f,
                    modifier = Modifier.fillMaxWidth(),
                    colors = androidx.compose.material3.SliderDefaults.colors(
                        thumbColor = SatinGoldPrimary,
                        activeTrackColor = SatinGoldPrimary
                    )
                )
                Text(
                    text = "RESET POSITION",
                    fontFamily = CleanSansFontFamily,
                    fontSize = 9.sp,
                    color = TextWarmSilverMuted,
                    modifier = Modifier
                        .clickable {
                            horizontalOffset = 0f
                            verticalOffset = 0f
                        }
                        .padding(vertical = 4.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = item.title,
                    fontFamily = SpartanSerifFontFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHighContrastBone,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "${item.resolution} · ${item.blackPercentage}",
                    fontFamily = CleanSansFontFamily,
                    fontSize = 10.sp,
                    color = SatinGoldHairlineRim,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Apply Target Buttons
                Button(
                    onClick = { onApply(WallpaperApplier.TargetScreen.BOTH, horizontalOffset, verticalOffset) },
                    colors = ButtonDefaults.buttonColors(containerColor = SatinGoldPrimary),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Apply to Home & Lock Screen",
                        fontFamily = CleanSansFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = CanvasDeepBasaltVoid
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { onApply(WallpaperApplier.TargetScreen.HOME, horizontalOffset, verticalOffset) },
                        border = androidx.compose.foundation.BorderStroke(1.dp, SubtleHairlineBorder),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Home Only",
                            fontFamily = CleanSansFontFamily,
                            fontSize = 11.sp,
                            color = TextHighContrastBone
                        )
                    }

                    OutlinedButton(
                        onClick = { onApply(WallpaperApplier.TargetScreen.LOCK, horizontalOffset, verticalOffset) },
                        border = androidx.compose.foundation.BorderStroke(1.dp, SubtleHairlineBorder),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Lock Only",
                            fontFamily = CleanSansFontFamily,
                            fontSize = 11.sp,
                            color = TextHighContrastBone
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Full Preview with Dock CTA
                Text(
                    text = "Inspect with clock & dock overlay →",
                    fontFamily = CleanSansFontFamily,
                    fontSize = 11.sp,
                    color = SatinGoldPrimary,
                    modifier = Modifier
                        .clickable(onClick = onFullPreview)
                        .padding(vertical = 4.dp)
                )
            }
        }
    }
}

/**
 * Vanguard Pass Unlock Modal for premium armor and monolith masters.
 */
@Composable
private fun VanguardPassModal(
    onDismiss: () -> Unit,
    onUnlock: () -> Unit,
    onGoToSettings: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = SurfaceBasaltStone,
            border = androidx.compose.foundation.BorderStroke(1.dp, SatinGoldHairlineRim),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "VANGUARD LIFETIME PASS",
                        fontFamily = SpartanSerifFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = SatinGoldPrimary,
                        letterSpacing = 1.sp
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = TextCharcoalDimmed,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable(onClick = onDismiss)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                SpartanLambdaMedallion(size = 54.dp)

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Unlock the Complete 12-Master Vault",
                    fontFamily = SpartanSerifFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHighContrastBone,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Gain permanent access to the 6 Vanguard armor & monolithic monolith masters. One-time purchase, zero subscriptions.",
                    fontFamily = CleanSansFontFamily,
                    fontSize = 11.sp,
                    color = TextWarmSilverMuted,
                    textAlign = TextAlign.Center,
                    lineHeight = 16.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(CanvasDeepBasaltVoid)
                        .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "LIFETIME ACCESS",
                                fontFamily = SpartanSerifFontFamily,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextHighContrastBone
                            )
                            Text(
                                text = "All future masterworks included",
                                fontFamily = CleanSansFontFamily,
                                fontSize = 9.sp,
                                color = TextCharcoalDimmed
                            )
                        }
                        Text(
                            text = "₹199",
                            fontFamily = SpartanSerifFontFamily,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = SatinGoldPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = onUnlock,
                    colors = ButtonDefaults.buttonColors(containerColor = SatinGoldPrimary),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Unlock Vanguard Pass (Tap to Activate)",
                        fontFamily = CleanSansFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = CanvasDeepBasaltVoid
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onGoToSettings,
                    border = androidx.compose.foundation.BorderStroke(1.dp, SubtleHairlineBorder),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "View in Settings",
                        fontFamily = CleanSansFontFamily,
                        fontSize = 11.sp,
                        color = TextWarmSilverMuted
                    )
                }
            }
        }
    }
}
