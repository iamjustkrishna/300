# Architecture State

## Technology Stack Baseline
- **Language**: Kotlin 2.0.21
- **UI Framework**: Jetpack Compose with Material 3 Foundation
- **Target SDK**: 35 (Android 15)
- **Minimum SDK**: 29 (Android 10)
- **Build System**: Gradle 8.11.1 / Android Gradle Plugin 8.10.0
- **Asynchronous Engine**: Kotlin Coroutines & Flow
- **Dependency Injection**: Modern manual DI / lightweight service locator or Hilt/Koin (evaluated per MVP simplicity)
- **Local Persistence**:
  - Room 2.6+ (Entities: `DailyChallengeEntity`, `StreakRecordEntity`, `WallpaperMetadataEntity`)
  - Jetpack DataStore (Preferences DataStore for user flags, current theme, notification preferences)
- **Image Pipeline**: Coil 3 (Memory cache + Disk cache + hardware bitmaps)
- **Home-Screen Widgets**: Jetpack Glance (Compose runtime for AppWidgets)
- **Background Tasks**: AndroidX WorkManager (Daily challenge rotation, widget sync)
- **Wallpaper Engine**: Android `WallpaperManager` API with display dimension downsampling

## Architecture Diagram (Unidirectional Data Flow)

```
+-------------------------------------------------------------+
|                      UI Layer (Compose)                     |
|  Screens -> Observes UiState -> Dispatches UiIntent (Events)|
+------------------------------+------------------------------+
                               |
                               v
+-------------------------------------------------------------+
|                    Presentation Layer                       |
|           Jetpack ViewModel (StateFlow<UiState>)            |
+------------------------------+------------------------------+
                               |
                               v
+-------------------------------------------------------------+
|                       Domain Layer                          |
|         UseCases (Pure Kotlin business logic & rules)       |
+------------------------------+------------------------------+
                               |
                               v
+-------------------------------------------------------------+
|                        Data Layer                           |
|       Repository Pattern (Single Source of Truth)           |
|      +------------------------+------------------------+    |
|      |    Room Database       |   Jetpack DataStore    |    |
|      | (Challenges, Streaks)  |  (Preferences, State)  |    |
|      +------------------------+------------------------+    |
+-------------------------------------------------------------+
```
