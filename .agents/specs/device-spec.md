# 300 — Device & Responsive Specification

> **Document Version**: 2.0.0 (Phase 1 Approved Baseline)  
> **Status**: APPROVED  
> **Target OS**: Android 10+ (API 29 to API 35)

---

## 1. Supported Form Factors & Adaptive Layout Rules

Rather than designing for a single fixed 1080x2400 resolution, 300 defines adaptive layout rules across 4 device classes using Material 3 Window Size Classes (`Compact`, `Medium`, `Expanded`).

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                    DEVICE CLASS RESPONSIVE BEHAVIOR                         │
├─────────────────┬───────────────────┬───────────────┬───────────────────────┤
│ Device Class    │ Screen Width (dp) │ Grid Columns  │ Layout Architecture   │
├─────────────────┼───────────────────┼───────────────┼───────────────────────┤
│ Compact Phone   │ < 600dp           │ 2 Columns     │ Single-column feed,   │
│                 │ (e.g. Pixel 8)    │               │ docked bottom nav     │
├─────────────────┼───────────────────┼───────────────┼───────────────────────┤
│ Medium Foldable │ 600dp – 840dp     │ 3 Columns     │ Adaptive feed,        │
│ (Folded/Unfold) │ (e.g. Fold 6)     │               │ dual-pane detail      │
├─────────────────┼───────────────────┼───────────────┼───────────────────────┤
│ Expanded Tablet │ > 840dp           │ 4 Columns     │ Permanent nav rail    │
│                 │ (e.g. Pixel Tab)  │               │ on left, split preview│
└─────────────────┴───────────────────┴───────────────┴───────────────────────┘
```

---

## 2. Navigation & System Bar Handling

### 2.1 Edge-to-Edge System Bars (Android 10–15)
* 300 operates **100% edge-to-edge** (`enableEdgeToEdge()`):
  - Status bar: Transparent with light icons (high contrast over dark obsidian canvas).
  - Navigation bar: Transparent with gesture bar padding applied via `WindowInsets.navigationBars`.
* Content never clips beneath cutouts, camera punch-holes, or curved display edges.

### 2.2 Gesture Navigation vs. 3-Button Navigation
* **Gesture Navigation**: Floating bottom navigation bar floats 16dp above the home gesture pill with zero touch overlap.
* **3-Button Navigation**: Navigation bar automatically respects system inset height, adding bottom padding so CTAs are never obscured by the system Back, Home, or Recents buttons.

---

## 3. Foldables & Dual-Screen Experience

* **Folded Mode (Cover Screen, ~380dp)**: Standard compact phone layout.
* **Unfolded Mode (Inner Screen, ~760dp - 840dp)**:
  - Theme Detail (`SCR-004`): Transitions from vertical scrolling into a **Dual-Pane Battle Layout**:
    * Left Pane (45%): Realistic phone preview mockup with Home/Lock toggles.
    * Right Pane (55%): Included components blueprint, specs, and master "Apply" action dock.
* **Posture Awareness**: Hinge/fold line is detected via Jetpack WindowManager; content avoids the crease.

---

## 4. Tablets (10"–12" Landscape & Portrait)

* **Navigation**: Bottom navigation dock transforms into an anchored **Left Navigation Rail (72dp width)** with vertical Spartan Lambda logo and bronze tab icons.
* **Wallpaper Vault (`SCR-005`)**: 4-column responsive grid with staggered aspect ratios.
* **Theme Preview**: Split canvas allowing side-by-side inspection of both Home Screen and Lock Screen setups simultaneously.
