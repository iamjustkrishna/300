# M3 Asset Catalog Delivery Plan

## Decision record

**Status:** In progress  
**Visual baseline:** `UI_Screen/*_v2.png`  
**Catalog target:** 12 wallpapers, 4 theme bundles, 4 native widgets, 96 exportable icon glyphs.  
**Entitlement:** two free bundles / six wallpapers; two Vanguard bundles / six wallpapers.  
**Delivery:** compact previews in the APK; master WebP files delivered as versioned download packs and cached locally.

## Asset matrix

| Set | Entitlement | Wallpapers | Widget pairing | Icon family | Status |
| --- | --- | --- | --- | --- | --- |
| Obsidian Ritual | Free | Colonnade of Braziers, Aegean Deep Trench, Night Sanctuary | Monolith Clock | Obsidian Bronze | 2/3 masters approved |
| Spartan Dawn | Free | The Spartan Sunrise, Thermopylae Dusk, Embers at Dawn | Creed Ticker | Dawn Brass | 2/3 masters approved |
| Corinthian Arsenal | Vanguard | Corinthian Bronze Helm, Bronze Sentinel, Black Standard | Shield Battery | Forged Helm | 1/3 masters approved |
| Crimson Phalanx | Vanguard | The Crimson Phalanx, Crimson Gate, Last Stand | War Calendar | Crimson Standard | 1/3 masters approved |

## Rules

- `inspiration/` informs only mood, material, contrast, and atmosphere. It is never a source image or composition template.
- Masters are original, text-free, without devices, frames, launcher icons, app UI, movie marks, or third-party logos.
- Every master is 1440x3200 portrait WebP, has a 320px top-safe area, and measures at least 95% pixels with RGB <= 12.
- Every catalog item has a provenance record, lean thumbnail/card derivative, and a manifest QA state before it can appear in the app.
- Icon exports are labelled launcher-dependent; no UI claims universal one-tap application.

## Delivery checklist

- [x] Catalog target, entitlement, delivery model, and provenance policy agreed.
- [x] Six first-wave original masters and compact visual previews exist.
- [x] Manifest validation exists for original six masters.
- [ ] Twelve-master manifest and all six new master files.
- [ ] Card/thumbnail derivatives for all twelve masters.
- [ ] Four native widget providers and studio cards.
- [x] 96-glyph source/export manifest with original master-board starter.
- [ ] Individually illustrated SVG/PNG exports and icon export UX.
- [ ] Designed catalog, preview, download/cache, paywall, and widget states in `screen-spec.md`.
- [ ] Play Store artwork, device screenshots, and legal/provenance sheet.
- [ ] Asset-catalog verification, visual QA, physical-device smoke test.

## Release gates

1. Red tests are written before catalog code or new metadata.
2. Asset manifest, master dimensions, true-black histogram, and derivatives pass automated verification.
3. All catalogue states render in screenshot tests and all widgets can be pinned on device.
4. APK preview budget remains below 6 MiB for `drawable-nodpi`; master packs remain outside the APK.
