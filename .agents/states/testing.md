# Testing State

## Current Verification Metrics
- **Build Verification**: `./gradlew assembleDebug` PASSED (**BUILD SUCCESSFUL in 48s**).
- **Unit & Headless UI Tests**: `./gradlew testDebugUnitTest` PASSED (**BUILD SUCCESSFUL in 45s**).
- **Headless Screenshot Suite**: `UiScreenCaptureTest` running via **Roborazzi 1.40.1 + Robolectric 4.14.1 Native Graphics**.
- **Physical Device Smoke-Testing**: Live deployment on Realme RMX3171 via ADB over Wi-Fi (`192.168.31.23:35759`).
- **Flaky Tests**: 0.
- **Logcat Crashes**: 0.
- **Wallpaper crop tests**: `WallpaperCropTest` passes for aspect-ratio crop sizing and offset clamping.

## Verified Test Suites

### 1. Roborazzi Headless UI Capture Suite (`UiScreenCaptureTest.kt`)
Captures all 6 production screens directly from Jetpack Compose on the JVM without emulators:
- `SCR-001` **Splash Screen**: [`UI_Screen/actual_shots_from_ui/01_Splash_Brand_Genesis.png`](../../UI_Screen/actual_shots_from_ui/01_Splash_Brand_Genesis.png) (1.17 MB)
- `SCR-002` **Onboarding Screen**: [`UI_Screen/actual_shots_from_ui/02_Onboarding_The_Reveal.png`](../../UI_Screen/actual_shots_from_ui/02_Onboarding_The_Reveal.png) (1.53 MB)
- `SCR-003` **Home Dashboard**: [`UI_Screen/actual_shots_from_ui/03_Home_Curated_Themes.png`](../../UI_Screen/actual_shots_from_ui/03_Home_Curated_Themes.png) (1.93 MB)
- `SCR-004` **Theme Live Preview**: [`UI_Screen/actual_shots_from_ui/04_Theme_Live_Preview_Apply.png`](../../UI_Screen/actual_shots_from_ui/04_Theme_Live_Preview_Apply.png) (1.06 MB)
- `SCR-005` **Studio Widgets & Wallpapers**: [`UI_Screen/actual_shots_from_ui/05_Studio_Widgets_Wallpapers.png`](../../UI_Screen/actual_shots_from_ui/05_Studio_Widgets_Wallpapers.png) (1.21 MB)
- `SCR-006` **Settings & Vanguard Pass**: [`UI_Screen/actual_shots_from_ui/06_Settings_Vanguard_Pass.png`](../../UI_Screen/actual_shots_from_ui/06_Settings_Vanguard_Pass.png) (224 KB)

### 2. Live Hardware Verification (Realme RMX3171)
- APK streaming install verified.
- `MainActivity` intent launch verified.
- ViewModel reflection initialization confirmed with zero `NoSuchMethodException`.
