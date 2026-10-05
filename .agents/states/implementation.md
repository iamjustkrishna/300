# Implementation State

## Current Codebase Status
- **Application Code**: Phase 3 catalog implementation is active across Clean Architecture, Room, DataStore, Jetpack Glance, and Jetpack Compose.
- **Compilation Health**: `./gradlew assembleDebug` is PASSING (**BUILD SUCCESSFUL in 48s**).
- **Baseline Version**: `1.0.0` (versionCode `1`).
- **Namespace**: `space.iamjustkrishna.threehundred`.
- **Target Hardware**: Verified live on physical hardware (Realme RMX3171 over Wi-Fi ADB).
- **Current polish slice**: Vault applies packaged wallpaper masters through aspect-ratio-aware crop math and bounded position controls; card thumbnails are no longer used for wallpaper application.
- **Icon catalog slice**: Added typed 96-entry catalog model and routed searchable family-filtered Icon Forge screen; illustrated per-glyph exports and launcher-share behavior remain pending.

## Implemented Architecture & Packages

### 1. Core Architecture (`space.iamjustkrishna.threehundred.core`)
- **`core.data`**:
  - `ThemeEntity`, `WallpaperEntity`: Room SQLite data entities with auto-seeding.
  - `ThemeDao`, `WallpaperDao`: Flow-based reactive DAOs.
  - `ThreeHundredDatabase`: Thread-safe Room database singleton.
  - `ThemeRepository`, `WallpaperRepository`: Offline-first repository layer with 6 launch themes and curated wallpapers.
- **`core.preferences`**:
  - `VanguardPreferences`: Jetpack DataStore tracking onboarding completion, Vanguard lifetime unlock, true black OLED, and low power mode.
- **`core.designsystem`**:
  - `Color.kt`, `Type.kt`, `Theme.kt`: Spartan design tokens (`#08080A`, `#C29B38`, `#8A1C14`).
  - `SpartanComponents.kt`: Shared brutalist UI components, cards, buttons, and badges.
  - `SpartanBottomNavBar.kt`: Docked 4-tab floating navigation bar with golden indicator.
- **`core.system`**:
  - `WallpaperApplier`: Native Android `WallpaperManager` service applier for Home, Lock, or Both screens with downsampling.
  - `GlancePinningHelper`: 1-tap `AppWidgetManager.requestPinAppWidget` launcher.

### 2. 100% Native Jetpack Glance Widgets (`space.iamjustkrishna.threehundred.widget`)
- `MonolithClockWidget` (2x2): Obsidian marble Roman numeral dial, analog clock hands, digital time readout, and stoic creed.
- `ShieldBatteryWidget` (2x2): Sculpted hoplite shield medallion with lambda, battery percentage readout, and crimson power arc.
- `MonolithClockWidgetReceiver`, `ShieldBatteryWidgetReceiver`: Glance AppWidget broadcast receivers.

### 3. Screen Suite (`space.iamjustkrishna.threehundred.feature`)
- `SCR-001` **Splash** (`feature.splash.SplashScreen`): Bronze hoplite shield medallion, spear, classical serif "300", and auto-progress.
- `SCR-002` **Onboarding** (`feature.onboarding.OnboardingScreen`): Stone portico archway phone mockup, 3 wireframe chips, brushed gold CTA.
- `SCR-003` **Home Dashboard** (`feature.home.HomeScreen`, `HomeViewModel`): "THE DAILY SET · 088" Obsidian Ritual hero, category rail, 2-column archive cards.
- `SCR-004` **Theme Live Preview** (`feature.preview.ThemePreviewScreen`, `ThemePreviewViewModel`): Home/Lock segmented switcher, live inspectable mockup, component rows, 1-tap apply.
- `SCR-005` **Studio** (`feature.studio.StudioScreen`): Widget forge cards with 1-tap "+ Add to home" pinning, AMOLED wallpapers grid.
- `SCR-006` **Settings** (`feature.settings.SettingsScreen`): Vanguard Pass hero card (₹199 one-time lifetime unlock), AMOLED pure black switches.

### 4. 1:1 Photorealistic Asset Integration (`res/drawable-nodpi/`)
- `hero_onboarding_portico.png`: 3D stone sanctuary & floating smartphone.
- `hero_obsidian_ritual.png`: Monumental colonnade with fire braziers & shield.
- `img_splash_medallion.png`: Antique bronze hoplite shield with spear piercing.
- `hero_vanguard_helmet.png`: Corinthian helmet with crimson plume.
- `card_monolith_clock.png`, `card_shield_battery.png`: Widget forge preview cards.
- `card_spartan_dawn.png`, `card_helm.png`, `card_aegean_noir.png`, `card_last_light.png`: Curated theme archive cards.
- `card_wp_sanctuary.png`, `card_wp_crimson.png`: Wallpaper forge cards.
- Twelve lean wallpaper master derivatives are packaged in `drawable-nodpi` for offline application; high-resolution source masters remain under `assets/wallpapers/masters/`.
