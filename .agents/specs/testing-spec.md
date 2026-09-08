# Testing Strategy & Verification Specification

---

## 1. Testing Philosophy: Test-First Discipline (TDD)

Testing in **300** is a first-class engineering contract. No feature, ViewModel, UseCase, or Repository implementation is complete without preceding tests.

### The Red-Green-Refactor Protocol
1. **Red**: Write a focused test in the corresponding `*Test.kt` file. Confirm test failure (`.\gradlew testDebugUnitTest`).
2. **Green**: Implement the minimal, clean production code to satisfy the test assertion.
3. **Refactor**: Enhance readability, eliminate duplications, and verify green status persists.
4. **Guard**: Never disable or comment out a failing test without an explicit ADR in `.agents/states/decisions.md`.

---

## 2. Test Pyramid & Distribution

```
           / \
          /   \
         /  UI \        10% (Critical Journeys & Compose Tests)
        / Tests \
       /---------\
      /Integrat.  \     20% (Room DAOs, DataStore, WorkManager)
     /   Tests     \
    /---------------\
   /   Unit Tests    \  70% (ViewModels, UseCases, Repositories, Reducers)
  /-------------------\
```

---

## 3. Unit Test Suite (70%)

### 3.1. Domain & UseCase Tests
- `GetTodayChallengeUseCaseTest`:
  - Returns Day 1 challenge on initial launch.
  - Correctly maps day number when calendar day advances.
  - Handles day 300 milestone correctly.
- `CompleteDailyChallengeUseCaseTest`:
  - Writes completion timestamp to repository.
  - Triggers streak recalculation.
  - Prevents duplicate completion on the same calendar day.
- `CalculateStreakUseCaseTest`:
  - Increments streak by 1 if previous day was completed.
  - Preserves streak during configured grace period.
  - Resets streak to 1 if more than 48 hours have elapsed without completion.
- `ApplyWallpaperUseCaseTest`:
  - Successfully decodes and prepares bitmap for `WallpaperManager`.
  - Propagates descriptive typed error on out-of-memory or system restriction.

### 3.2. Presentation & State Management Tests
- `HomeViewModelTest`:
  - Emits `HomeUiState(isLoading = true)` on startup, transitioning to loaded challenge and streak state.
  - Tapping "I Have Conquered" dispatches `CompleteTodayChallenge` intent and emits `HomeEffect.TriggerHeavyHaptic`.
  - Handles repository failure gracefully by falling back to cached or default challenge.
- `OnboardingViewModelTest`:
  - Updates selected identity path.
  - Persists `isOnboardingCompleted = true` on oath completion.
- `WallpaperCatalogViewModelTest`:
  - Filters wallpapers by category without re-querying disk.
  - Marks premium items with locked status for free users.

---

## 4. Integration Test Suite (20%)

### 4.1. Room Database Tests
- `ChallengeDaoTest`:
  - Insert, update, and query challenges by day number.
  - Test SQLite index performance on completion queries.
  - Test database migration from v1 to subsequent versions without data loss.

### 4.2. Jetpack DataStore Tests
- `UserPreferencesRepositoryTest`:
  - Read/write onboarding completion flag.
  - Read/write notification hour and toggle.
  - Atomic update validation across concurrent coroutines.

### 4.3. AppWidget Synchronization Tests
- `GlanceWidgetSyncTest`:
  - Confirms widget state updates when in-app challenge state changes.

---

## 5. UI & Journey Test Suite (10%)

UI tests run using `createComposeRule()` and headless Robolectric Native Graphics without requiring a slow physical emulator.

| Journey ID | Test Class | Verification Points |
|---|---|---|
| `UI-JOURNEY-01` | `OnboardingFlowTest` | Walks through Oath -> Armory -> First Challenge -> Confirms Home screen reached. |
| `UI-JOURNEY-02` | `DailyChallengeFlowTest` | From Home screen, taps "I Have Conquered" -> Confirms button state updates to "Conquered" and streak increments. |
| `UI-JOURNEY-03` | `WallpaperApplyFlowTest` | Selects wallpaper -> Opens preview -> Taps "Apply" -> Confirms success toast. |
| `UI-JOURNEY-04` | `PaywallFlowTest` | Free user taps locked wallpaper -> Paywall opens -> Dismisses paywall -> Returns cleanly to catalog. |
| `UI-JOURNEY-05` | `SettingsFlowTest` | Toggles notifications -> Changes reminder time -> Clears cache -> Confirms state persisted. |

---

## 6. Regression Testing Policy

Every bug discovered during development or QA must follow this workflow:
1. Create a regression test reproducing the exact defect in the appropriate `*Test.kt` file.
2. Confirm the test fails (RED).
3. Apply the fix in production code until the test turns green (GREEN).
4. Keep the regression test permanently in the CI test suite to prevent recurrence.

---

## 7. Execution Commands

```bash
# Execute all unit and integration tests on JVM:
.\gradlew testDebugUnitTest

# Execute Android Lint:
.\gradlew lintDebug

# Execute full verification:
.\gradlew check
```
