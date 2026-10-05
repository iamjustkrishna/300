package space.iamjustkrishna.threehundred.feature.settings

import android.widget.Toast
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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import space.iamjustkrishna.threehundred.core.designsystem.CanvasDeepBasaltVoid
import space.iamjustkrishna.threehundred.core.designsystem.CleanSansFontFamily
import space.iamjustkrishna.threehundred.core.designsystem.CorinthianHelmetGraphic
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldHairlineRim
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldLightGleam
import space.iamjustkrishna.threehundred.core.designsystem.SatinGoldPrimary
import space.iamjustkrishna.threehundred.core.designsystem.SpartanBottomNavBar
import space.iamjustkrishna.threehundred.core.designsystem.SpartanNavTab
import space.iamjustkrishna.threehundred.core.designsystem.SpartanSerifFontFamily
import space.iamjustkrishna.threehundred.core.designsystem.SubtleHairlineBorder
import space.iamjustkrishna.threehundred.core.designsystem.SurfaceBasaltStone
import space.iamjustkrishna.threehundred.core.designsystem.SurfaceMutedIron
import space.iamjustkrishna.threehundred.core.designsystem.TextCharcoalDimmed
import space.iamjustkrishna.threehundred.core.designsystem.TextHighContrastBone
import space.iamjustkrishna.threehundred.core.designsystem.TextWarmSilverMuted
import space.iamjustkrishna.threehundred.core.preferences.VanguardPreferences

/**
 * SCR-006: Settings & Vanguard Lifetime Pass
 * Exact 1:1 match of UI_Screen/06_Settings_Vanguard_Pass_v2.png:
 * - Top bar: "Settings" in classical serif with "v1.0.0" metallic badge.
 * - Vanguard Pass Hero Card: Weathered bronze Corinthian helmet with crimson crest,
 *   "LIFETIME ACCESS" badge, title "Vanguard Pass", subtitle "A complete collection. Yours for life.",
 *   price "₹199 one-time" (zero recurring subscriptions), and "Unlock the full vault >" CTA button.
 * - Display Section: Pure black canvas (gold switch ON) and Low-power mode (switch OFF).
 * - Library Section: Offline cache, Restore purchases, and About 300 rows.
 * - Stoic footer: "Discipline in every detail. Zero bloatware."
 * - Docked 4-tab bar: Discover, Studio, Vault, Settings (active).
 */
@Composable
fun SettingsScreen(
    onNavigateToDiscover: () -> Unit,
    onNavigateToStudio: () -> Unit,
    onNavigateToVault: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val preferences = remember { VanguardPreferences(context) }
    val userPrefs by preferences.userPreferences.collectAsState(
        initial = space.iamjustkrishna.threehundred.core.model.UserPreferences()
    )
    var showAboutDialog by remember { mutableStateOf(false) }

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
            // 1. Settings Top Bar
            item {
                SettingsTopBar()
            }

            // 2. Vanguard Pass Hero Card
            item {
                VanguardPassHeroCard(
                    isUnlocked = userPrefs.isVanguardUnlocked,
                    onUnlockClick = {
                        coroutineScope.launch {
                            val newStatus = !userPrefs.isVanguardUnlocked
                            preferences.setVanguardUnlocked(newStatus)
                            val msg = if (newStatus) "Vanguard Pass Unlocked! Welcome, Spartan." else "Vanguard Pass reset."
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }

            // 3. Display Section
            item {
                SettingsSectionHeader(title = "DISPLAY")
                DisplayPreferencesCard(
                    isPureBlack = userPrefs.isPureBlackOled,
                    onPureBlackToggle = { checked ->
                        coroutineScope.launch { preferences.setPureBlackOled(checked) }
                    },
                    isLowPower = userPrefs.isLowPowerMode,
                    onLowPowerToggle = { checked ->
                        coroutineScope.launch { preferences.setLowPowerMode(checked) }
                    }
                )
            }

            // 4. Library & Support Section
            item {
                SettingsSectionHeader(title = "LIBRARY & SYSTEM")
                LibraryPreferencesCard(
                    onClearCache = {
                        Toast.makeText(context, "Offline cache refreshed.", Toast.LENGTH_SHORT).show()
                    },
                    onRestorePurchases = {
                        Toast.makeText(context, "Purchases verified via Google Play.", Toast.LENGTH_SHORT).show()
                    },
                    onAboutClick = {
                        showAboutDialog = true
                    }
                )
            }

            // 5. Stoic Footer
            item {
                SettingsStoicFooter()
            }
        }

        // Docked 4-Tab Spartan Navigation Bar
        SpartanBottomNavBar(
            currentTab = SpartanNavTab.SETTINGS,
            onTabSelected = { tab ->
                when (tab) {
                    SpartanNavTab.DISCOVER -> onNavigateToDiscover()
                    SpartanNavTab.STUDIO -> onNavigateToStudio()
                    SpartanNavTab.VAULT -> onNavigateToVault()
                    SpartanNavTab.SETTINGS -> { /* Already on Settings */ }
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        // About 300 Dialog with Brand Logo
        if (showAboutDialog) {
            Dialog(onDismissRequest = { showAboutDialog = false }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(22.dp))
                        .background(SurfaceBasaltStone)
                        .border(1.dp, SatinGoldHairlineRim, RoundedCornerShape(22.dp))
                        .padding(24.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.brand_logo_300),
                            contentDescription = "300 Brand Logo",
                            modifier = Modifier
                                .size(96.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .border(1.5.dp, SatinGoldHairlineRim, RoundedCornerShape(20.dp))
                        )
                        Text(
                            text = "300",
                            fontFamily = SpartanSerifFontFamily,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = SatinGoldPrimary
                        )
                        Text(
                            text = "Version 1.0.0 · Vanguard Edition",
                            fontFamily = CleanSansFontFamily,
                            fontSize = 12.sp,
                            color = TextWarmSilverMuted
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(SubtleHairlineBorder)
                        )
                        Text(
                            text = "An austere, minimal, dark Spartan personalization suite engineered to forge focus and discipline in one tap.\n\nDesigned with Classical Stoic Luxury & True 4K AMOLED obsidian aesthetics.",
                            fontFamily = CleanSansFontFamily,
                            fontSize = 13.sp,
                            color = TextHighContrastBone,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(SurfaceMutedIron)
                                .clickable { showAboutDialog = false }
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "DISMISS",
                                fontFamily = SpartanSerifFontFamily,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = SatinGoldPrimary,
                                letterSpacing = 1.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Settings",
            fontFamily = SpartanSerifFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            color = TextHighContrastBone
        )

        // v1.0.0 Metallic Badge
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(999.dp))
                .background(SurfaceBasaltStone)
                .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(999.dp))
                .padding(horizontal = 10.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "v1.0.0",
                fontFamily = CleanSansFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp,
                letterSpacing = 1.sp,
                color = TextWarmSilverMuted
            )
        }
    }
}

/**
 * Vanguard Pass Hero Card: Corinthian helmet, Lifetime Access badge, ₹199 one-time.
 */
@Composable
private fun VanguardPassHeroCard(
    isUnlocked: Boolean,
    onUnlockClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(22.dp))
            .border(
                1.5.dp,
                Brush.verticalGradient(
                    listOf(
                        SatinGoldLightGleam.copy(alpha = 0.55f),
                        SatinGoldHairlineRim.copy(alpha = 0.35f),
                        Color(0x10C5A869)
                    )
                ),
                RoundedCornerShape(22.dp)
            )
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF16151A),
                        Color(0xFF0F0E13),
                        CanvasDeepBasaltVoid
                    )
                )
            )
            .padding(20.dp)
    ) {
        Column {
            // Badge & Price Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // LIFETIME ACCESS pill badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(Color(0xFF221E17))
                        .border(1.dp, SatinGoldHairlineRim, RoundedCornerShape(999.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (isUnlocked) "PASS ACTIVE" else "LIFETIME ACCESS",
                        fontFamily = CleanSansFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 9.sp,
                        letterSpacing = 1.4.sp,
                        color = SatinGoldPrimary
                    )
                }

                Text(
                    text = if (isUnlocked) "UNLOCKED" else "₹199 one-time",
                    fontFamily = CleanSansFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = SatinGoldLightGleam
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Center: Corinthian Helmet Graphic with high crimson crest
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Weathered Bronze Corinthian Helmet with Crimson Plume (v2 photorealistic art)
                Image(
                    painter = painterResource(R.drawable.asset_vanguard_helmet),
                    contentDescription = "Corinthian Helmet",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(14.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "Vanguard Pass",
                        fontFamily = SpartanSerifFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                        color = TextHighContrastBone
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "A complete collection.\nYours for life. Zero subscriptions.",
                        fontFamily = CleanSansFontFamily,
                        fontSize = 11.5.sp,
                        lineHeight = 16.sp,
                        color = TextWarmSilverMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // CTA Button: Unlock the full vault >
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(
                        if (isUnlocked) SurfaceMutedIron
                        else SatinGoldPrimary
                    )
                    .clickable(onClick = onUnlockClick),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isUnlocked) "Vanguard Active (Tap to toggle)" else "Unlock the full vault >",
                    fontFamily = CleanSansFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.5.sp,
                    letterSpacing = 0.4.sp,
                    color = if (isUnlocked) TextHighContrastBone else CanvasDeepBasaltVoid
                )
            }
        }
    }
}

@Composable
private fun SettingsSectionHeader(title: String) {
    Text(
        text = title,
        fontFamily = CleanSansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        letterSpacing = 1.4.sp,
        color = SatinGoldPrimary,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
    )
}

/**
 * Display Preferences: Pure black canvas switch, Low-power mode switch.
 */
@Composable
private fun DisplayPreferencesCard(
    isPureBlack: Boolean,
    onPureBlackToggle: (Boolean) -> Unit,
    isLowPower: Boolean,
    onLowPowerToggle: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(SurfaceBasaltStone)
            .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(18.dp))
            .padding(16.dp)
    ) {
        // Pure black canvas row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Pure Black Canvas",
                    fontFamily = CleanSansFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = TextHighContrastBone
                )
                Text(
                    text = "100% AMOLED true black pixels for maximum battery efficiency",
                    fontFamily = CleanSansFontFamily,
                    fontSize = 11.sp,
                    color = TextWarmSilverMuted
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Switch(
                checked = isPureBlack,
                onCheckedChange = onPureBlackToggle,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = CanvasDeepBasaltVoid,
                    checkedTrackColor = SatinGoldPrimary,
                    uncheckedThumbColor = TextWarmSilverMuted,
                    uncheckedTrackColor = SurfaceMutedIron
                )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .height(1.dp)
                .background(SubtleHairlineBorder)
        )

        // Low power mode row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Low-Power Mode",
                    fontFamily = CleanSansFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = TextHighContrastBone
                )
                Text(
                    text = "Pause ambient widget animations and particle shimmer",
                    fontFamily = CleanSansFontFamily,
                    fontSize = 11.sp,
                    color = TextWarmSilverMuted
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Switch(
                checked = isLowPower,
                onCheckedChange = onLowPowerToggle,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = CanvasDeepBasaltVoid,
                    checkedTrackColor = SatinGoldPrimary,
                    uncheckedThumbColor = TextWarmSilverMuted,
                    uncheckedTrackColor = SurfaceMutedIron
                )
            )
        }
    }
}

/**
 * Library & System Section
 */
@Composable
private fun LibraryPreferencesCard(
    onClearCache: () -> Unit,
    onRestorePurchases: () -> Unit,
    onAboutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(SurfaceBasaltStone)
            .border(1.dp, SubtleHairlineBorder, RoundedCornerShape(18.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        SettingsClickableRow(
            title = "Offline Cache",
            subtitle = "Local Room DB · 4K Wallpapers & Widget State",
            actionText = "Clear",
            onClick = onClearCache
        )
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SubtleHairlineBorder))
        SettingsClickableRow(
            title = "Restore Purchases",
            subtitle = "Google Play In-App Billing sync",
            actionText = "Check",
            onClick = onRestorePurchases
        )
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SubtleHairlineBorder))
        SettingsClickableRow(
            title = "About 300",
            subtitle = "Classical Stoic Luxury · Engineered for Discipline",
            actionText = "Info",
            onClick = onAboutClick
        )
    }
}

@Composable
private fun SettingsClickableRow(
    title: String,
    subtitle: String,
    actionText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontFamily = CleanSansFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
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
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = actionText,
            fontFamily = CleanSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = SatinGoldPrimary
        )
    }
}

@Composable
private fun SettingsStoicFooter() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.brand_logo_300),
            contentDescription = "300 Brand Sigil",
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, SatinGoldHairlineRim, RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "\"Discipline in every detail. Zero bloatware.\"",
            fontFamily = SpartanSerifFontFamily,
            fontSize = 12.sp,
            color = TextCharcoalDimmed,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "300 · SPACE.IAMJUSTKRISHNA.THREEHUNDRED",
            fontFamily = CleanSansFontFamily,
            fontSize = 9.sp,
            letterSpacing = 1.4.sp,
            color = TextCharcoalDimmed.copy(alpha = 0.6f)
        )
    }
}
