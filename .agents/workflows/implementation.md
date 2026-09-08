# Workflow: Implementation

## Objective
Standardize the coding, refactoring, and code hygiene practices for all Kotlin and Jetpack Compose implementations.

## Leading Agent
`android-implementation-agent.md`

## Execution Protocol

### Step 1: Pre-Implementation Checklist
Before writing a single line of code:
- [ ] Feature is defined in `.agents/plans/current-sprint.md`.
- [ ] Screen is specified in `.agents/specs/screen-spec.md`.
- [ ] Architecture and data models are defined in `.agents/specs/state-spec.md`.
- [ ] Failing tests exist per `workflows/tdd-feature.md`.

### Step 2: Implementation Guidelines
- **Compose UI**:
  - Always pass `Modifier` as the first optional parameter to composables.
  - Hoist state to ViewModels; keep composables pure and stateless where possible.
  - Use `remember` with appropriate keys to avoid re-allocations during recomposition.
  - Implement `@Preview` composables with both light/dark theme and multi-device preview parameters.
- **Coroutines & Flow**:
  - Never use `GlobalScope`.
  - Use `viewModelScope` for presentation coroutines.
  - Collect flows in UI using `collectAsStateWithLifecycle()`.
- **String Resources**:
  - Every user-facing string must reside in `res/values/strings.xml`. No hardcoded strings in Compose files.

### Step 3: Local Verification
1. Run `./gradlew compileDebugKotlin` to verify compilation.
2. Run `./gradlew testDebugUnitTest` to verify test suite.
3. Run `./gradlew lintDebug` to verify no lint errors.
