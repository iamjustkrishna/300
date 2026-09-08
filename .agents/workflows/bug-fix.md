# Workflow: Bug Fix

## Objective
Enforce a disciplined, test-backed protocol for triaging, reproducing, and resolving software defects.

## Leading Agent
`testing-agent.md` & `android-implementation-agent.md`

## Execution Protocol

### Step 1: Reproduction & Triage
1. Document the bug in `.agents/states/current.md` under Known Bugs.
2. Formulate steps to reproduce, expected behavior, and observed failure.

### Step 2: Write Failing Regression Test First
1. Never fix a bug directly in production code without a reproducing test.
2. Write a unit or integration test reproducing the exact defect conditions.
3. Run the test and confirm failure (RED).

### Step 3: Implement Root Cause Fix
1. Apply the minimal architectural or logic correction.
2. Verify the failing test now passes (GREEN).
3. Ensure no secondary regressions are introduced.

### Step 4: Verification & Closeout
1. Run `./gradlew testDebugUnitTest`.
2. Remove the bug entry from Known Bugs in `.agents/states/current.md`.
3. Document resolution in Git commit or release notes.
