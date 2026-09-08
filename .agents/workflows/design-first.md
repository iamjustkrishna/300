# Workflow: Design-First

## Objective
Enforce the ironclad law of 300: **No feature or screen shall be implemented before its complete visual hierarchy, tokens, responsive behavior, and 8 canonical UI states are designed and approved.**

## Leading Agent
`visual-design-agent.md` (in coordination with `ux-agent.md` and `accessibility-agent.md`)

## Execution Protocol

### Step 1: Screen Inventory Entry
Before any mockups or code are created, assign the screen a unique Screen ID (e.g., `SCREEN-HOME-001`, `SCREEN-WALLPAPER-002`) and enter it into `.agents/specs/screen-spec.md`.

### Step 2: Information Architecture & Wireframe Spec
1. Define Purpose and User Goal.
2. Define Navigation Inbound / Outbound paths.
3. Establish Visual Hierarchy (Focal element -> Secondary action -> Tertiary controls).
4. Verify primary action is identifiable in < 2 seconds.

### Step 3: Design 8 Canonical UI States
Every screen specification must define exact visual behaviors for:
1. **First Launch**: Clean, inspiring zero-data state with onboarding hints.
2. **Normal State**: Standard loaded content, active streak, crisp thumbnails.
3. **Loading State**: Subtle bronze-tinted skeleton shimmer (no spinning spinners).
4. **Offline State**: Uninterrupted offline content display with subtle non-blocking status banner.
5. **Empty State**: Meaningful Spartan quote or call to action when no items exist.
6. **Error State**: Clear, dignified recovery message with a 1-tap "Retry" or fallback action.
7. **Returning User State**: Highlighting current streak, days disciplined, and fresh daily challenge.
8. **Premium vs. Free State**: Clear visual distinction for locked items (bronze crest/lock icon) without cluttering.

### Step 4: Responsive & Accessibility Validation
- Verify layout behavior across Small Phone (360dp), Standard Phone (412dp), Foldable Unfolded (600dp+), and Tablet (840dp+).
- Verify 48dp minimum touch targets.
- Verify color contrast (minimum 4.5:1 text, 3:1 display).
- Test layout with 200% font scaling.

### Step 5: Approval Gate
Submit the complete specification to `reviewer-agent.md`. Only when status is **APPROVED** may the screen proceed to testing and implementation.
