# Asset Pipeline State

## First Production Batch

The first reusable artwork batch is available in `app/src/main/res/drawable-nodpi/` as WebP:

| Asset | Source role | Dimensions | Approx. size |
|---|---|---:|---:|
| `brand_splash_medallion.webp` | Splash medallion | 512x512 | 55 KB |
| `asset_onboarding_portico.webp` | Onboarding hero | 1080x1204 | 59 KB |
| `asset_obsidian_ritual.webp` | Theme preview artwork | 1440x897 | 138 KB |
| `asset_vanguard_helmet.webp` | Vanguard Pass hero | 1024x685 | 56 KB |
| `widget_monolith_clock.webp` | Monolith Clock widget art | 800x357 | 31 KB |
| `widget_shield_battery.webp` | Shield Battery widget art | 800x357 | 27 KB |
| `wallpaper_obsidian_sanctuary.webp` | Obsidian Sanctuary wallpaper preview | 1080x1834 | 113 KB |
| `wallpaper_crimson_silence.webp` | Crimson Silence wallpaper preview | 1080x1421 | 47 KB |
| `card_spartan_dawn.webp` | Spartan Dawn theme card | 370x410 | 22 KB |
| `card_helm.webp` | Helm theme card | 371x410 | 18 KB |
| `card_aegean_noir.webp` | Aegean Noir theme card | 370x410 | 27 KB |
| `card_last_light.webp` | Last Light theme card | 371x410 | 21 KB |
| `card_wp_sanctuary.webp` | Obsidian Sanctuary studio preview | 380x500 | 16 KB |

These are optimized production derivatives of the approved v2 artwork already in the repository. Full-resolution masters and future generated wallpaper packs should remain outside the APK and be delivered through the asset pipeline.

## Format Policy

- WebP lossy at quality 84–88 for photographic artwork and wallpapers.
- VectorDrawable or Compose drawing for simple marks, gradients, labels, and controls.
- `drawable-nodpi` for artwork whose dimensions must not be density-scaled.
- Keep text and interactive controls native so the UI remains accessible and responsive.
- Avoid AVIF for the MVP because WebP has safer API 29 compatibility.

The remaining theme cards and studio preview are now also WebP resources. Their existing Compose resource IDs were preserved, so no UI behavior changes were required.

## Sprint 03 Wallpaper Masters (v1, 2026-09-11)

Six original text-free 1440x3200 WebP q85 masters in `assets/wallpapers/masters/` + `manifest.json`:
`wp_obsidian_ritual` 98.4%, `wp_spartan_dawn` 98.3%, `wp_corinthian_helm` 98.3%, `wp_aegean_noir` 100.0%, `wp_last_light` 98.3%, `wp_phalanx_crimson` 95.5% true-black (RGB<=12, full-res sampled).
Base #08080A, top 320px safe, 8-12KB each, outside APK. In-APK `drawable-nodpi` stays lean (~3MB total, <6MB budget) for <25MB guardrail.
Guarded by `WallpaperMastersVerificationTest` (manifest + lean thumbnails).
