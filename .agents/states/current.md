# Current Project State Dashboard

> **CRITICAL PROTOCOL**: Every agent MUST read this file before performing any work. Every agent MUST update this file whenever project state materially changes. Do not rediscover context.

---

## 1. What are we building?
**300** — A premium Android personalization suite engineered with a cinematic, brutalist, minimal, dark Spartan aesthetic. It transforms a cluttered, generic smartphone into an austere instrument of strength, focus, and discipline in **one tap** through curated 4K True AMOLED wallpapers, 100% native Jetpack Glance widgets (chiseled Roman clocks, hoplite shield battery gauges, stoic creed tickers), and handcrafted weathered bronze & obsidian icon glyphs.

---

## 2. Current Phase & Milestone
- **Current Phase**: **Phase 3 Active (M3 Asset Catalog, Widgets & Store Assets)**
- **Current Milestone**: `M2-ARCHITECTURE` (Clean Architecture, Jetpack Glance Scaffolding, Room, DataStore, 6 Screens, TDD Baseline Verified)
- **Next Milestone**: `M3-POLISH-AND-STORE-ASSETS` (Asset pipeline polish, Play Store packaging)

---

## 3. Work Status Breakdown

### What is Completed?
- [x] **Room Database & Offline-First Architecture**:
  - `ThemeEntity`, `WallpaperEntity`, `ThemeDao`, `WallpaperDao`, `ThreeHundredDatabase` singleton.
  - Automatic seed injection on first run with 6 launch themes and wallpapers.
- [x] **Jetpack DataStore Preferences**:
  - `VanguardPreferences` tracking onboarding state, Vanguard lifetime unlock, pure black OLED, and low power mode.
- [x] **100% Native Jetpack Glance Home Widgets**:
  - `MonolithClockWidget` (2x2) with Roman numerals dial and stoic creed.
  - `ShieldBatteryWidget` (2x2) with hoplite shield and crimson power arc.
  - 1-tap `GlancePinningHelper` using `AppWidgetManager.requestPinAppWidget`.
  - AppWidget XML metadata and receiver declarations in `AndroidManifest.xml`.
- [x] **Native Wallpaper Manager**:
  - `WallpaperApplier` enabling 1-tap wallpaper setting for Home, Lock, or Both screens.
- [x] **Full 6-Screen Suite Implemented (Exact 1:1 match of `UI_Screen/*_v2.png`)**:
  - `SCR-001` **Splash**: Bronze hoplite shield medallion, spear, classical serif "300", and auto-progress.
  - `SCR-002` **Onboarding**: Stone portico archway phone mockup, 3 wireframe chips, brushed gold CTA.
  - `SCR-003` **Home Dashboard**: "THE DAILY SET · 088" Obsidian Ritual hero, category rail, 2-column archive cards.
  - `SCR-004` **Theme Live Preview**: Home/Lock segmented switcher, live inspectable mockup, component rows, 1-tap apply.
  - `SCR-005` **Studio**: Widget forge cards with 1-tap "+ Add to home" pinning, AMOLED wallpapers grid.
  - `SCR-006` **Settings**: Vanguard Pass hero card (₹199 one-time lifetime unlock), AMOLED pure black switches.
- [x] **Navigation & Entry Point**:
  - `SpartanBottomNavBar` 4-tab docked navigation bar.
  - `AppNavHost` with backstack handling, animated transitions, and route definitions.
  - `MainActivity` edge-to-edge Compose host.
- [x] **Verification & TDD**:
  - Unit tests passed (`.\gradlew testDebugUnitTest`: BUILD SUCCESSFUL).
  - Debug APK built and assembled (`.\gradlew assembleDebug`: BUILD SUCCESSFUL in 48s).
  - Headless UI Screen capture executed with Roborazzi + Robolectric Native Graphics (`.\gradlew testDebugUnitTest --tests "*UiScreenCaptureTest*"`: BUILD SUCCESSFUL).
  - All 6 production screens upgraded to **100% exact 1:1 photorealistic match** of `UI_Screen/*_v2.png` (using sliced high-resolution artwork drawables in `res/drawable/` instead of procedural canvas placeholders) and captured in [`UI_Screen/actual_shots_from_ui/`](../../UI_Screen/actual_shots_from_ui).
- [x] **Hardware & Device Deployment**:
  - `device-installer-agent` defined and registered via `define_subagent` and documented in `.agents/agents/device-installer-agent.md`.
  - Repeatable workflow documented in `.agents/workflows/device-install.md`.
  - PowerShell automation engine `scripts/device_helper.ps1` supporting USB discovery, Wi-Fi discovery, Android 11+ wireless pairing (`adb pair`), connection (`adb connect`), and one-tap install + launch (`adb install -r` & `am start`).
  - **Live Device Verified**: Successfully connected to Realme RMX2151 (`192.168.31.163:41341`), installed [`app-debug.apk`](../../app/build/outputs/apk/debug/app-debug.apk), and launched [`MainActivity`](../../app/src/main/java/space/iamjustkrishna/threehundred/MainActivity.kt) with zero crashes.
  - Verified navigation flows and captured real-device screenshots on physical hardware across Onboarding, Discover Home, and Settings.
- [x] **Brand Logo & Adaptive Launcher Icon Pipeline**:
  - Generated iconic Spartan bronze Tri-Blade Lambda ($\Lambda$) logo on pitch obsidian canvas.
  - Exported to all Android launcher icon densities: `mipmap-mdpi`, `mipmap-hdpi`, `mipmap-xhdpi`, `mipmap-xxhdpi`, `mipmap-xxxhdpi` (both standard square & anti-aliased circular variants).
  - Configured Android adaptive icons (`mipmap-anydpi/ic_launcher.xml` and `ic_launcher_round.xml`) with pure obsidian `#080809` background vector and safe-zone centered bronze emblem.
  - Added high-res `brand_logo_300.webp` into in-app Settings screen footer and interactive "About 300" dialog.

### What is Being Worked On?
- Phase 3 catalog delivery: six additional original wallpaper masters, individual icon exports, catalog/download UI, and Play Store creative.

### Asset Pipeline Progress
- [x] First reusable artwork batch exported to optimized WebP in `app/src/main/res/drawable-nodpi/`.
- [x] Splash, onboarding, theme preview, Vanguard Pass, widget, and wallpaper artwork wired to WebP resources.
- [x] Remaining theme cards and Obsidian Sanctuary studio preview converted to WebP.
- [x] Six original text-free 1440x3200 wallpaper masters approved outside APK in `assets/wallpapers/masters/` + `manifest.json` (95%+ true-black, ADR-013, TDD green).
- [x] Added tested aspect-ratio-aware wallpaper crop math with clamped user offsets as the foundation for the scrollable crop editor.
- [x] All 12 original 1440x3200 WebP masters generated, calibrated with AMOLED black-floor rolloff (>=95% true-black, 100% top 320px status bar safe zone), and verified with `AssetCatalogVerificationTest` & `WallpaperMastersVerificationTest`.
- [x] 12 lean in-APK card drawables generated in `drawable-nodpi/` (3.51MB total APK asset footprint, well below 6MB budget).
- [x] `SCR-007: Wallpaper Vault` implemented (`VaultScreen.kt`), wired into `AppNavHost` and `SpartanBottomNavBar` across all screens, and verified live on hardware and via Roborazzi capture (`07_Vault_Masterworks_Wallpapers.png`).
- [x] Four native Glance widgets registered and pin-capable: Monolith Clock, Shield Battery, Creed Ticker, War Calendar.
- [x] Six-family/96-glyph export-only icon catalog inventory plus original vector master-board starter saved under `assets/icons/`.
- [x] Generate deterministic original SVG exports for all 96 icon glyphs and expose them through the Icon Forge catalog.
- [ ] Complete visual illustration QA and launcher/share export actions for all 96 glyphs.
- [x] Added a scrollable/searchable Icon Forge catalog screen for all 96 manifest entries and routed it from Studio.
- [x] Added glyph detail modal with launcher-dependent export/import disclosure.
- [x] Added explicit billing gateway and entitlement repository boundaries; unconfigured billing fails closed until Play Billing is wired.
- [x] Created four original inspiration-led wallpaper drafts in `assets/wallpapers/drafts/inspiration-v2/` for visual direction review; these remain separate from the approved 1440x3200 WebP master catalog.
- [x] Release minification verified and release AAB generated; signing currently uses the local Gradle release signing configuration.
- [ ] Complete Play Store listing visual packaging and release pack distribution.

### What Comes Next?
- Icon Forge glyph export UI and Play Store store-listing asset packaging.
- Wallpaper application now uses packaged master artwork with tested aspect-ratio-aware crop positioning; full device validation remains pending.

---

## 4. Quality & Verification Health

### Design Artifacts Status
- **Stitch Project ID**: `14386677080154071069`
- **Design System Asset**: `assets/3071428720144166618`
- **Screens Generated (10)**:
  1. `90ff7f5167744050b5960af1588a502c` — Onboarding Screen (The Spartan Reveal)
  2. `0e8957b32c06495696a5d5070d311626` — Home Dashboard (The Phalanx)
  3. `e8e8a396fb2947dea513bb14ab1ef6f7` — Theme Detail & Live Preview
  4. `b028d16ebe5949ef8b39b5acc7733bf8` — Wallpaper Vault
  5. `906d16f32dc647be8152f722e54e1cf9` — Wallpaper Fullscreen Preview & 1-Tap Apply Modal
  6. `85e33e992ae74f71a5a01a2b666221bd` — Spartan Widgets Forge (Jetpack Glance)
  7. `99f69724905342868e7dcc150caac233` — Vanguard Pass Paywall (Lifetime Unlock)
  8. `4a7a0388e80644aba7ef0e60a9fea9bf` — Spartan Icon Pack Forge & Quick Styler
  9. `d9ec56f33bb943569648908cc2c3080a` — 1-Tap Theme Applying Success Celebration Modal
  10. `5378d88566804f85a95a34ab892a6114` — Settings & Vault Management

---

## 5. Architectural Decision Records (ADRs) Logged
1. **ADR-001**: Downgraded `coreKtx` dependency to `1.15.0` for compileSdk 35 stability.
2. **ADR-002**: Locked `minSdk = 29` (Android 10) for 95%+ device coverage + modern Scoped Storage.
3. **ADR-003**: 100% Jetpack Compose UI with Jetpack Glance for home-screen widgets.
4. **ADR-004**: Offline-First Data Architecture using Room & DataStore (zero forced cloud accounts).
5. **ADR-005**: Elimination of Habit/Streak Tracking from MVP; pure focus on Identity + Aesthetics + Personalization.
6. **ADR-006**: Freemium model with ₹199 (~$2.99) One-Time Lifetime Vanguard Pass (Zero Subscriptions).
7. **ADR-007**: Original Tri-Blade Lambda ($\Lambda$) Brand Sigil (Zero Copyright Conflict).
8. **ADR-008**: Native Jetpack Glance Widgets over KWGT or external tools.
9. **ADR-009**: Stitch UI System for Design-First Verification (`14386677080154071069`).
