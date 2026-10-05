# Current Sprint: Sprint 02 — Core Architecture, Compose Screens, Glance Widgets & Live Hardware Deployment (Phase 2)

> **Sprint Goal**: Implement the complete Clean Architecture foundation (Room, DataStore, Repositories), build 100% native Jetpack Glance home-screen widgets, implement all 6 Compose UI screens matching `UI_Screen/*_v2.png` 1:1 with photorealistic sliced artwork, automate wireless device deployment, and verify live on physical hardware without crashes.
> **Sprint Status**: **COMPLETED (Phase 2 Complete & Verified on Device)**  
> **Target Date**: Hand-Off to Sprint 03 (Phase 3: High-Res Asset Packaging, Google Play Billing & Release Hardening)

---

## 1. Deliverables & Progress Tracker

- [x] **Room SQLite Offline-First Database**:
  - `ThemeEntity`, `WallpaperEntity`, `ThemeDao`, `WallpaperDao`, `ThreeHundredDatabase` singleton.
  - Automatic seed injection on first run with 6 launch themes and curated wallpapers.
- [x] **Jetpack DataStore Preferences**:
  - `VanguardPreferences` tracking onboarding state, Vanguard lifetime unlock, true black OLED mode, and low-power battery mode.
- [x] **100% Native Jetpack Glance Home Widgets**:
  - `MonolithClockWidget` (2x2): Obsidian marble dial, Roman numerals, analog clock hands, digital readout, and stoic creed.
  - `ShieldBatteryWidget` (2x2): Sculpted hoplite shield medallion with lambda, battery percentage readout, and crimson power arc.
  - 1-tap `GlancePinningHelper` utilizing `AppWidgetManager.requestPinAppWidget`.
  - AppWidget XML metadata and receiver declarations in `AndroidManifest.xml`.
- [x] **Native Wallpaper Manager**:
  - `WallpaperApplier` service supporting 1-tap wallpaper setting for Home, Lock, or Both screens with downsampling.
- [x] **Complete 6-Screen Compose Suite (Exact 1:1 Match of `UI_Screen/*_v2.png`)**:
  - `SCR-001` **Splash**: Bronze hoplite shield medallion, spear, classical serif "300", and auto-progress.
  - `SCR-002` **Onboarding**: Stone portico archway sanctuary, floating smartphone mockup, 3 wireframe chips, brushed gold CTA.
  - `SCR-003` **Home Dashboard**: "THE DAILY SET · 088" Obsidian Ritual hero, category rail, 2-column archive cards.
  - `SCR-004` **Theme Live Preview**: Home/Lock segmented switcher, live inspectable mockup, component rows, 1-tap apply.
  - `SCR-005` **Studio**: Widget forge cards with 1-tap "+ Add to home" pinning, AMOLED wallpapers grid.
  - `SCR-006` **Settings**: Vanguard Pass hero card (₹199 one-time lifetime unlock), AMOLED pure black switches.
- [x] **1:1 Photorealistic Asset Pipeline (`app/src/main/res/drawable/`)**:
  - Sliced high-resolution artwork from `UI_Screen/*_v2.png`: `hero_onboarding_portico`, `hero_obsidian_ritual`, `img_splash_medallion`, `hero_vanguard_helmet`, widget forge cards (`card_monolith_clock`, `card_shield_battery`), theme archive cards (`card_spartan_dawn`, `card_helm`, `card_aegean_noir`, `card_last_light`), and wallpaper forge cards (`card_wp_sanctuary`, `card_wp_crimson`).
- [x] **Device Installer Agent & Automation**:
  - `device-installer-agent` defined and registered via `define_subagent` and documented in `.agents/agents/device-installer-agent.md`.
  - Repeatable workflow documented in `.agents/workflows/device-install.md`.
  - `scripts/device_helper.ps1` PowerShell engine supporting USB discovery, Wi-Fi discovery, Android 11+ wireless pairing (`adb pair`), connection (`adb connect`), and one-tap install + launch (`adb install -r` & `am start`).
- [x] **Verification & TDD**:
  - Unit tests passed (`.\gradlew testDebugUnitTest`: BUILD SUCCESSFUL).
  - Production debug APK compiled (`.\gradlew assembleDebug`: BUILD SUCCESSFUL in 48s).
  - Headless UI Screen capture executed with Roborazzi + Robolectric Native Graphics (`.\gradlew testDebugUnitTest --tests "*UiScreenCaptureTest*"`: BUILD SUCCESSFUL in 45s).
  - All 6 production screens rendered directly from live Jetpack Compose code into [`UI_Screen/actual_shots_from_ui/`](../../UI_Screen/actual_shots_from_ui).
- [x] **Live Hardware Deployment**:
  - Paired and connected wirelessly to physical device Realme RMX3171 (`192.168.31.23:35759`).
  - Streamed and installed `app-debug.apk`.
  - Launched `space.iamjustkrishna.threehundred/.MainActivity` with zero runtime crashes in logcat.

---

## 2. Transition Boundary: Ready for Sprint 03 (Phase 3)

Phase 2 is 100% complete. The workspace is fully primed for **Sprint 03**:
1. **Asset Pipeline Polish**: Full catalog of True AMOLED 4K wallpapers and handcrafted weathered bronze icon pack glyphs.
2. **Monetization Integration (Phase 4)**: Google Play Billing Client 7.x for the ₹199 One-Time Lifetime Vanguard Pass.
3. **Release Hardening (Phase 5)**: ProGuard/R8 minification, signed Release AAB, and Play Store assets.

