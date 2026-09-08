# Workflow: Accessibility Review

## Objective
Audit and verify that every screen, dialog, and component in 300 meets strict WCAG 2.1 AA guidelines and Android Accessibility standards.

## Leading Agent
`accessibility-agent.md`

## Execution Protocol

### Step 1: Touch Target Verification
- Verify that every interactive element has a minimum touch target of 48dp x 48dp.
- If visual icon is 24dp, verify `Modifier.sizeIn(minWidth = 48.dp, minHeight = 48.dp)` or `IconButton` wrapper is used.

### Step 2: Screen Reader (TalkBack) Audit
- Navigate the screen sequentially using TalkBack gestures.
- Verify focus order is logical and intuitive.
- Verify non-text content (icons, images) has descriptive `contentDescription` text.
- Verify decorative visual dividers, backdrops, and gradient overlays have `contentDescription = null`.
- Verify announcements for dynamic state changes (e.g., "Challenge completed! 14 day streak unlocked").

### Step 3: Contrast Ratio Verification
- Test all text colors against their parent background surfaces using WCAG contrast calculators:
  - Normal text (< 18sp / < 14sp bold): Minimum 4.5:1.
  - Large text (>= 18sp / >= 14sp bold): Minimum 3:1.
  - Interactive icons and boundaries: Minimum 3:1.

### Step 4: Dynamic Font Scaling & Display Size
- Set Android system font scaling to 130%, 150%, and 200%.
- Verify that text reflows or wraps without truncation or overlapping.
- Verify buttons expand vertically if necessary rather than clipping text.

### Step 5: Motion Sensitivity
- Verify that with system animator duration scale set to 0 or "Remove Animations" enabled:
  - Wallpaper transitions do not glitch.
  - Screens load immediately without hanging.
