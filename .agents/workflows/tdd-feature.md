# Workflow: TDD Feature

## Objective
Enforce the strict Test-Driven Development (TDD) lifecycle for every new feature, ViewModel, UseCase, or Repository.

## Leading Agent
`testing-agent.md` & `android-implementation-agent.md`

## Execution Protocol

### Step 1: Acceptance Criteria Review
1. Read the feature specification from `.agents/specs/`.
2. Extract all testable assertions and edge cases (e.g., "Completing daily challenge increases streak by 1 and persists state to Room").

### Step 2: Write Failing Test (RED)
1. Create or open test class (e.g., `CompleteDailyChallengeUseCaseTest.kt` or `HomeViewModelTest.kt`).
2. Write unit tests targeting the exact acceptance criteria.
3. Run the test suite:
   `.\gradlew testDebugUnitTest`
4. Confirm test fails as expected (compilation error or assertion failure).

### Step 3: Implement Minimum Code (GREEN)
1. Write the minimal production code necessary to satisfy the test contract.
2. Avoid premature optimization or writing code beyond what the test requires.
3. Run test suite:
   `.\gradlew testDebugUnitTest`
4. Confirm all tests pass green.

### Step 4: Refactor (REFACTOR)
1. Clean up duplicated logic, refine variable names, apply Compose/Kotlin idiomatic improvements.
2. Run tests again to verify no regression occurred.

### Step 5: Integration & UI Verification
1. Run integration tests (Room/DataStore).
2. Run Compose UI tests to verify component interactions and accessibility labels.
