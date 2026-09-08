# Workflow: Architecture

## Objective
Establish, review, and maintain system modularity, layer boundaries, data flow protocols, and Architectural Decision Records (ADRs).

## Leading Agent
`android-architect-agent.md`

## Execution Protocol

### Step 1: Architectural Requirements Analysis
1. Analyze technical drivers: Offline-first, reactive Compose UI, low RAM, fast cold boot, battery-safe Glance widgets.
2. Determine module boundaries (`:core:designsystem`, `:core:data`, `:core:domain`, `:core:model`, `:feature:home`, `:feature:customize`, `:feature:challenge`, `:feature:widget`).

### Step 2: Formulate Architectural Decision Records (ADRs)
When selecting libraries, patterns, or changing data flow:
1. Create an ADR entry in `.agents/states/decisions.md`.
2. State Context, Alternatives Considered, Decision, and Tradeoffs.
3. Review with `reviewer-agent.md`.

### Step 3: Layer Separation Enforcement
- **UI Layer**: Composable functions observing `StateFlow<UiState>` and emitting `UiIntent`. Zero business logic.
- **ViewModel Layer**: Transforms Domain models into UI states, manages coroutine scopes, handles lifecycle.
- **Domain Layer**: Pure Kotlin UseCases with zero Android framework dependencies (except standard annotations).
- **Data Layer**: Repositories managing Room, DataStore, and local asset providers. Exposes Flow streams.

### Step 4: Verification
Verify through automated architecture tests or code reviews that UI never accesses DAOs or data sources directly.
