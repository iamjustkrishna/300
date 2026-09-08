# Workflow: Bootstrap

## Objective
Establish complete tooling, dependency, Gradle build, and agent memory readiness for the 300 Android project.

## Prerequisites
- Android Studio / Android SDK installed with SDK Platform 35 and Build Tools.
- JDK 17 / JDK 21 configured.
- Gradle Wrapper operational.

## Execution Protocol

### Step 1: Repository & Environment Inspection
1. Verify Gradle wrapper executable and daemon status:
   `.\gradlew -v`
2. Verify SDK location in `local.properties`.
3. Check version catalog `gradle/libs.versions.toml` for compatibility:
   - AGP 8.10.0
   - Kotlin 2.0.21
   - compileSdk 35
   - minSdk 29
   - targetSdk 35

### Step 2: Build Sanity Verification
1. Run `./gradlew tasks` to verify project graph.
2. Execute `./gradlew assembleDebug` to confirm build succeeds without errors.
3. Execute `./gradlew testDebugUnitTest` to verify baseline testing environment.

### Step 3: Agent System Structure Verification
1. Verify `.agents/` folder structure exists:
   - `agents/`
   - `workflows/`
   - `states/`
   - `plans/`
   - `specs/`
   - `templates/`
2. Ensure `.agents/states/current.md` is populated with current baseline state.

## Output & Acceptance Criteria
- [x] Clean, passing `./gradlew assembleDebug`.
- [x] Clean, passing `./gradlew testDebugUnitTest`.
- [x] `.agents/` structure initialized with persistent state files.
- [x] Phase 0 marked complete in `.agents/plans/master-plan.md`.
