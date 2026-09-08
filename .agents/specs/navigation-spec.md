# Navigation & Information Architecture Specification

---

## 1. Information Architecture (IA) Hierarchy

```
[ App Launch ]
       │
       ▼ (Check DataStore: isOnboardingCompleted)
       ├── FALSE ──► [ Onboarding Graph ]
       │                   ├── Step 1: Oath / Identity Selection
       │                   ├── Step 2: Armory / Day 1 Wallpaper Preview & Apply
       │                   └── Step 3: First Challenge Commitment ──► [ Main Graph ]
       │
       └── TRUE ───► [ Main Graph (Bottom Navigation / NavRail) ]
                           │
                           ├── Tab 1: HOME (SCREEN-HOME-001)
                           │     ├── Daily Challenge Card ──► [ Challenge Detail Sheet ]
                           │     ├── Active Streak Banner ──► [ Streak Milestones ]
                           │     ├── Today's Featured Wallpaper ──► [ Wallpaper Preview ]
                           │     └── Quick Widget Pin Action ──► [ Widget Setup Dialog ]
                           │
                           ├── Tab 2: ARMORY / WALLPAPERS (SCREEN-WALLPAPER-001)
                           │     ├── Wallpaper Grid (Filter by: All, Shields, Ruin, Bronze, Steel)
                           │     └── Wallpaper Item Tap ──► [ Fullscreen Preview (SCREEN-WALLPAPER-002) ]
                           │                                   ├── Toggle App Grid / Clock Mock
                           │                                   ├── 1-Tap Apply (Home, Lock, Both)
                           │                                   └── Locked Item ──► [ Paywall Sheet ]
                           │
                           ├── Tab 3: DISCIPLINE (SCREEN-CHALLENGE-001)
                           │     ├── Today's Challenge (Active / Completed)
                           │     ├── 300 Day Streak Progress Bar & Milestones
                           │     └── Past Completed Days History Log
                           │
                           └── Tab 4: SETTINGS (SCREEN-SETTINGS-001)
                                 ├── Notification Schedule (Time Picker)
                                 ├── Widget Configuration
                                 ├── Data & Storage (Clear Cache)
                                 ├── Restore Purchases
                                 ├── Privacy Policy & Legal
                                 └── About 300
```

---

## 2. Jetpack Compose Navigation Graph Contracts

### Type-Safe Routes & Destinations

```kotlin
sealed interface ScreenRoute {
    @Serializable
    data object Onboarding : ScreenRoute

    @Serializable
    data object Home : ScreenRoute

    @Serializable
    data object Armory : ScreenRoute

    @Serializable
    data class WallpaperPreview(val wallpaperId: String) : ScreenRoute

    @Serializable
    data object Discipline : ScreenRoute

    @Serializable
    data class ChallengeDetail(val dayNumber: Int) : ScreenRoute

    @Serializable
    data object Settings : ScreenRoute

    @Serializable
    data class Paywall(val sourceTrigger: String) : ScreenRoute
}
```

---

## 3. Deep Link & AppWidget Navigation Actions

| Source | Trigger / Intent | Destination Route | Behavior |
|---|---|---|---|
| Glance Quote Widget | Tap Widget Body | `ScreenRoute.Home` | Opens app, highlights today's quote |
| Glance Streak Widget | Tap "Conquer" Button | `ScreenRoute.Discipline` | Opens app directly to Daily Challenge completion view |
| Daily Notification | Notification Click | `ScreenRoute.Discipline` | Opens Day's active challenge sheet directly |
| Deep Link / External | `300://wallpaper/{id}` | `ScreenRoute.WallpaperPreview(id)` | Resolves asset and opens full-screen preview |

---

## 4. Back Stack & Exit Lifecycle Rules
1. **Onboarding Back Stack**: Once Onboarding Step 3 is completed and Home is launched, the Onboarding Graph is popped with `popUpTo(0) { inclusive = true }`. Pressing system back on Home exits the app to the device launcher.
2. **Bottom Navigation Switching**: Switching between Home, Armory, Discipline, and Settings saves and restores back-stack state (`restoreState = true`, `launchSingleTop = true`).
3. **Modal Sheets**: Bottom sheets and Paywall overlays dismiss gracefully with standard Android back gesture without exiting the host screen.
4. **Predictive Back Navigation**: Fully compatible with Android 14+ predictive back gestures with smooth scrim fading and edge transitions.
