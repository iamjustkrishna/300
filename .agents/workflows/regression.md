# Workflow: Regression

## Objective
Prevent regressions by automating test suites and systematically guarding critical user journeys whenever changes occur.

## Leading Agent
`testing-agent.md`

## Execution Protocol

### Step 1: Baseline Regression Suite
Run full test suite before initiating major refactors or new features:
`.\gradlew testDebugUnitTest`

### Step 2: Critical Journey Automated Coverage
Ensure automated tests guard the core loops:
1. **Onboarding completion**: Identity selected -> Flag written to DataStore -> Home route rendered.
2. **Challenge cycle**: Daily challenge completed -> Streak incremented -> Date recorded -> Re-completion blocked for same day.
3. **Wallpaper application**: Asset resolved -> Scaled to display dimensions -> `WallpaperManager` called.
4. **Widget synchronization**: DataStore/Room update -> Glance AppWidget triggers recomposition.

### Step 3: Post-Change Verification
After any code change:
1. Execute `./gradlew testDebugUnitTest`.
2. Verify all existing tests remain green.
3. If a test fails, do NOT alter the test to fit broken behavior without an explicit architectural decision. Fix the regression in production code.
