# End-to-End Edge Cases Specification

This document details the exact system behavior, failure mitigation, and verification protocol for every conceivable edge case in **300**.

---

## 1. Lifecycle & Process States

| Edge Case ID | Scenario | System Behavior & Mitigation | Test Verification |
|---|---|---|---|
| `EDGE-LIFE-01` | First launch | Check DataStore `isOnboardingCompleted == false`. Render `SCREEN-ONBOARD-001` with zero flicker. Seed initial Room database records synchronously or with high priority. | `OnboardingFlowTest.firstLaunchOpensOath` |
| `EDGE-LIFE-02` | Second launch | Check DataStore `isOnboardingCompleted == true`. Immediately navigate to `SCREEN-HOME-001`. Preload active streak and today's challenge from Room. | `HomeFlowTest.secondLaunchDirectToHome` |
| `EDGE-LIFE-03` | Interrupted onboarding (App killed mid-flow) | Onboarding state (step 1, 2, or 3) is saved in DataStore. On relaunch, user resumes at their last completed step. If killed before step 3, user is never dropped into an unconfigured Home screen. | `OnboardingFlowTest.resumesAtLastStep` |
| `EDGE-LIFE-04` | Process death & recreation | ViewModels implement `SavedStateHandle`. Active scroll positions in Armory and selected filters survive OS process kill and restore cleanly. | `ViewModelSavedStateTest.stateRestored` |
| `EDGE-LIFE-05` | Screen rotation / Configuration change | UI state is decoupled in Jetpack `ViewModel`. Zero re-fetching of database or re-decoding of bitmaps on rotation. | `ComposeConfigurationChangeTest` |
| `EDGE-LIFE-06` | Background / Foreground transition | App pauses non-critical coroutines on `onStop()`. On `onStart()`, re-checks current calendar date to see if day rolled over (new challenge required). | `LifecycleObserverTest.dateRollChecked` |
| `EDGE-LIFE-07` | Device restart | Glance widgets register `ACTION_BOOT_COMPLETED` receiver (if permitted) or refresh via WorkManager to ensure streak display remains accurate post-reboot. | `BootReceiverTest.widgetsRefreshed` |

---

## 2. Network & Connectivity

| Edge Case ID | Scenario | System Behavior & Mitigation | Test Verification |
|---|---|---|---|
| `EDGE-NET-01` | Zero Internet / Airplane Mode | **100% Offline-First Guarantee**. All core wallpapers, all 30 initial challenges, and streak history are pre-bundled locally. Zero blocking network spinners. Full app functionality retained. | `OfflineIntegrationTest.fullAppAccessible` |
| `EDGE-NET-02` | Slow / High-Latency Network | Network requests (if fetching remote content packs in future) run asynchronously with 10s timeouts and instant fallback to local cache. UI never hangs. | `RepositoryTimeoutTest.fallbackToLocal` |
| `EDGE-NET-03` | Interrupted asset download | Downloads stream to a temporary `.tmp` file and rename atomically upon SHA-256 verification. Corrupted or partial files are discarded automatically. | `AssetDownloadTest.atomicRename` |

---

## 3. Permissions & System Hardware

| Edge Case ID | Scenario | System Behavior & Mitigation | Test Verification |
|---|---|---|---|
| `EDGE-PERM-01` | Notification permission denied (Android 13+) | App functions completely without notifications. Settings screen shows reminder toggle as disabled with a subtle prompt: *"Enable in system settings to receive your daily Spartan call."* Never harass user. | `NotificationPermissionTest.gracefulDenial` |
| `EDGE-PERM-02` | WallpaperManager system restriction / Knox / Enterprise policy | Some enterprise or managed devices restrict wallpaper changes. Catch `SecurityException` / `IOException` from `WallpaperManager.setBitmap()`. Display dignified dialog: *"Your device system restricts automated wallpaper application. Tap to save image to Pictures."* | `WallpaperManagerErrorTest.catchesException` |
| `EDGE-PERM-03` | Insufficient disk storage (< 20MB free) | Catch `IOException` on caching. Evict oldest LRU cached images. If still insufficient, render direct fallback from APK assets. | `DiskCacheTest.lruEvictionTriggered` |
| `EDGE-PERM-04` | Low RAM / Memory pressure (`onTrimMemory`) | Coil image loader automatically trims memory cache on `TRIM_MEMORY_RUNNING_CRITICAL`. All non-visible wallpaper bitmaps are recycled immediately. | `MemoryTrimTest.cachesEvicted` |
| `EDGE-PERM-05` | Battery Saver Mode active | Disable background shimmers and animations. Glance widget update frequency throttled to strictly user-interactive events. | `BatterySaverTest.animationsDisabled` |

---

## 4. Time, Calendar & Clock Manipulation

| Edge Case ID | Scenario | System Behavior & Mitigation | Test Verification |
|---|---|---|---|
| `EDGE-TIME-01` | Midnight / Day rollover while app is open | Day rollover listener updates `todayChallenge` automatically when local time passes 00:00 without requiring app restart. | `TimeRolloverTest.updatesAtMidnight` |
| `EDGE-TIME-02` | Timezone change (e.g., User flies London -> Tokyo) | Streak calculations use normalized UTC timestamps alongside local calendar dates. Grace period prevents unfair streak loss if clock shifts backward or forward within 24 hours. | `StreakCalculatorTest.timezoneShiftHandled` |
| `EDGE-TIME-03` | Clock manipulation / Cheating | User changes phone clock forward 10 days to unlock streaks. System detects monotonic uptime mismatch or past completion dates. Prevents future challenge completion before valid elapsed time. | `StreakSecurityTest.detectsClockDrift` |
| `EDGE-TIME-04` | Daylight Saving Time (DST) transitions | DST shifts (23-hour or 25-hour days) do not break streak logic or daily notification alarms (uses `ZoneId.systemDefault()`). | `DstTransitionTest.calculatesCorrectly` |

---

## 5. Monetization & Purchases

| Edge Case ID | Scenario | System Behavior & Mitigation | Test Verification |
|---|---|---|---|
| `EDGE-MON-01` | Failed / Cancelled In-App Purchase | Reset paywall button state from "Processing" to normal. Show brief non-accusatory message: *"Purchase was not completed."* User returns to previous screen without being blocked. | `BillingTest.handlesCancellation` |
| `EDGE-MON-02` | Restore purchases on new device / reinstall | Settings -> "Restore Purchases" queries Google Play Billing cache and unlocks Vanguard Pass immediately if active entitlement found. | `BillingTest.restoresPurchases` |
| `EDGE-MON-03` | Free user attempts to tap locked wallpaper | Never crash or lock UI. Open `SCREEN-PAYWALL-001` with clear dismiss option. Free wallpapers remain fully accessible. | `PaywallTriggerTest.opensPaywall` |

---

## 6. Form Factor & Accessibility Variations

| Edge Case ID | Scenario | System Behavior & Mitigation | Test Verification |
|---|---|---|---|
| `EDGE-FORM-01` | Foldable unfolded mid-operation | Dynamic configuration change triggers recomposition. Wallpaper grid adjusts columns from 2 to 3/4 instantly without reloading data or losing scroll offset. | `FoldableFoldUnfoldTest.retainsScrollOffset` |
| `EDGE-FORM-02` | Multi-Window / Split-screen mode | App window resizes to half-screen. Layout adapts as a `Compact` size class. Floating elements remain within visible bounds. | `SplitScreenTest.layoutAdapts` |
| `EDGE-FORM-03` | 200% System Font Scaling | Container cards expand vertically. No text is clipped or ellipsized out of legibility. Buttons remain clickable and accessible. | `FontScaleTest.noTextClippingAt200Percent` |
| `EDGE-FORM-04` | TalkBack Screen Reader active | Every clickable element has semantic click action and clear label. Non-decorative images have informative content descriptions; decorative graphics set to `null`. | `AccessibilitySemanticsTest.allClickablesLabeled` |
| `EDGE-FORM-05` | Reduced Motion setting enabled | Compose animations respect system setting and transition with 0ms duration. | `ReducedMotionTest.animationsDisabled` |
