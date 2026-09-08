# Workflow: Screen Specification

## Objective
Convert conceptual screen layouts into detailed, technical engineering contracts in `.agents/specs/screen-spec.md` with full token bindings, Compose component breakdowns, and state machines.

## Leading Agent
`ux-agent.md` & `visual-design-agent.md`

## Execution Protocol

### Step 1: Structural Breakdown
For each screen:
1. **Screen ID**: Canonical identifier (e.g., `SCREEN-HOME-001`).
2. **Title**: Human-readable name.
3. **Route**: Jetpack Compose navigation route string or type-safe destination object.
4. **Arguments**: Required and optional route arguments.

### Step 2: Component Breakdown
Map every UI element to its Design System Token:
- Background: Surface level (`Surface0`, `Surface1`, `Surface2`).
- Headers: Typography token (`DisplayLarge`, `TitleMedium`), Color token (`OnSurface`, `AccentBronze`).
- Cards / Containers: Shape token (`ShapeSmall`, `ShapeMedium`), Border token (`BorderSubtle`, `BorderAccent`).
- Buttons / Clickables: Height token (min 48dp), Padding token (`SpacingNormal`), Haptic token (`Click`, `HeavyThud`).

### Step 3: Event & State Modeling
- Define the UI State `data class` with all fields and loading flags.
- Define sealed interface `UiIntent` for all user actions (clicks, swipes, pull-to-refresh).
- Define sealed interface `UiEffect` for one-time side effects (navigation, snackbar, haptics).

### Step 4: Documentation
Record the full specification in `.agents/specs/screen-spec.md`.
