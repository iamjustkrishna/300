# Testing State

## Current Verification Metrics
- **Build Verification**: `./gradlew assembleDebug` PASSED.
- **Unit Test Execution**: `./gradlew testDebugUnitTest` verified.
- **Test Coverage**: 0% (Application code not yet started; TDD tests will precede implementation).
- **Flaky Tests**: 0.

## Target Test Suites for MVP
1. `ChallengeRepositoryTest`: Room persistence, offline retrieval, daily challenge rotation.
2. `CalculateStreakUseCaseTest`: Consecutive day calculation, missed day reset, grace period logic.
3. `ApplyWallpaperUseCaseTest`: Bitmap downsampling, WallpaperManager interaction, error recovery.
4. `HomeViewModelTest`: State emissions, intent handling, offline state transitions.
5. `OnboardingViewModelTest`: Selection persistence, navigation effect emission.
6. `DeviceMatrixUiTest`: Multi-screen layout assertions across Compact, Standard, Foldable, Tablet.
