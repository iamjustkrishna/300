package space.iamjustkrishna.threehundred.feature.vault

import androidx.annotation.DrawableRes
import space.iamjustkrishna.threehundred.R

/**
 * Wallpaper metadata representing an approved master in the 300 Masterworks Vault.
 */
data class VaultWallpaperItem(
    val id: String,
    val title: String,
    val themeId: String,
    val entitlement: String, // "free" or "vanguard"
    val category: String,    // "Featured", "AMOLED", "Armor", "Minimal"
    @DrawableRes val cardDrawableRes: Int,
    @DrawableRes val masterDrawableRes: Int,
    val blackPercentage: String,
    val resolution: String = "1440×3200 4K UHD",
    val isAvailableOffline: Boolean = true
) {
    val isVanguard: Boolean get() = entitlement.equals("vanguard", ignoreCase = true)
}

/**
 * Canonical 12-item master wallpaper catalog corresponding exactly to
 * assets/wallpapers/manifest.json (6 Free, 6 Vanguard).
 */
val MasterWallpaperCatalog: List<VaultWallpaperItem> = listOf(
    // 6 Free Masters
    VaultWallpaperItem(
        id = "wp_obsidian_ritual",
        title = "Colonnade of Braziers",
        themeId = "obsidian_ritual",
        entitlement = "free",
        category = "Featured",
        cardDrawableRes = R.drawable.card_obsidian_ritual,
        blackPercentage = "98.4% Black",
        masterDrawableRes = R.drawable.master_wp_obsidian_ritual_1440x3200
    ),
    VaultWallpaperItem(
        id = "wp_aegean_noir",
        title = "Aegean Deep Trench",
        themeId = "obsidian_ritual",
        entitlement = "free",
        category = "Minimal",
        cardDrawableRes = R.drawable.card_aegean_noir,
        blackPercentage = "100.0% Black",
        masterDrawableRes = R.drawable.master_wp_aegean_noir_1440x3200
    ),
    VaultWallpaperItem(
        id = "wp_spartan_dawn",
        title = "The Spartan Sunrise",
        themeId = "spartan_dawn",
        entitlement = "free",
        category = "AMOLED",
        cardDrawableRes = R.drawable.card_spartan_dawn,
        blackPercentage = "98.3% Black",
        masterDrawableRes = R.drawable.master_wp_spartan_dawn_1440x3200
    ),
    VaultWallpaperItem(
        id = "wp_last_light",
        title = "Thermopylae Dusk",
        themeId = "spartan_dawn",
        entitlement = "free",
        category = "AMOLED",
        cardDrawableRes = R.drawable.card_last_light,
        blackPercentage = "98.3% Black",
        masterDrawableRes = R.drawable.master_wp_last_light_1440x3200
    ),
    VaultWallpaperItem(
        id = "wp_night_sanctuary",
        title = "Night Sanctuary Colonnade",
        themeId = "obsidian_ritual",
        entitlement = "free",
        category = "Minimal",
        cardDrawableRes = R.drawable.card_night_sanctuary,
        blackPercentage = "95.0% Black",
        masterDrawableRes = R.drawable.master_wp_night_sanctuary_1440x3200
    ),
    VaultWallpaperItem(
        id = "wp_embers_at_dawn",
        title = "Embers at Dawn Archway",
        themeId = "spartan_dawn",
        entitlement = "free",
        category = "AMOLED",
        cardDrawableRes = R.drawable.card_embers_at_dawn,
        blackPercentage = "95.1% Black",
        masterDrawableRes = R.drawable.master_wp_embers_at_dawn_1440x3200
    ),

    // 6 Vanguard Masters
    VaultWallpaperItem(
        id = "wp_corinthian_helm",
        title = "Corinthian Bronze Helm",
        themeId = "corinthian_arsenal",
        entitlement = "vanguard",
        category = "Armor",
        cardDrawableRes = R.drawable.card_corinthian_helm,
        blackPercentage = "98.3% Black",
        masterDrawableRes = R.drawable.master_wp_corinthian_helm_1440x3200
    ),
    VaultWallpaperItem(
        id = "wp_phalanx_crimson",
        title = "The Crimson Phalanx",
        themeId = "crimson_phalanx",
        entitlement = "vanguard",
        category = "Armor",
        cardDrawableRes = R.drawable.card_phalanx_crimson,
        blackPercentage = "95.5% Black",
        masterDrawableRes = R.drawable.master_wp_phalanx_crimson_1440x3200
    ),
    VaultWallpaperItem(
        id = "wp_bronze_sentinel",
        title = "The Bronze Sentinel Gates",
        themeId = "corinthian_arsenal",
        entitlement = "vanguard",
        category = "Armor",
        cardDrawableRes = R.drawable.card_bronze_sentinel,
        blackPercentage = "95.1% Black",
        masterDrawableRes = R.drawable.master_wp_bronze_sentinel_1440x3200
    ),
    VaultWallpaperItem(
        id = "wp_black_standard",
        title = "The Black Standard",
        themeId = "corinthian_arsenal",
        entitlement = "vanguard",
        category = "Minimal",
        cardDrawableRes = R.drawable.card_black_standard,
        blackPercentage = "95.2% Black",
        masterDrawableRes = R.drawable.master_wp_black_standard_1440x3200
    ),
    VaultWallpaperItem(
        id = "wp_crimson_gate",
        title = "The Crimson Gate Monolith",
        themeId = "crimson_phalanx",
        entitlement = "vanguard",
        category = "AMOLED",
        cardDrawableRes = R.drawable.card_crimson_gate,
        blackPercentage = "95.7% Black",
        masterDrawableRes = R.drawable.master_wp_crimson_gate_1440x3200
    ),
    VaultWallpaperItem(
        id = "wp_last_stand",
        title = "Thermopylae Last Stand",
        themeId = "crimson_phalanx",
        entitlement = "vanguard",
        category = "Armor",
        cardDrawableRes = R.drawable.card_last_stand,
        blackPercentage = "95.3% Black",
        masterDrawableRes = R.drawable.master_wp_last_stand_1440x3200
    )
)
