# 300 — Design System Specification: "Spartan Obsidian"

> **Document Version**: 2.0.0 (Phase 1 Approved Baseline)  
> **Status**: APPROVED  
> **Stitch Design System Asset ID**: `assets/3071428720144166618`  
> **Aesthetic Philosophy**: Brutalist, Minimal, Dark Obsidian, Weathered Bronze, Disciplined Masculine Power.

---

## 1. Color Palette Tokens

```
┌─────────────────────────────────────────────────────────────┐
│                      CANVAS & SURFACES                      │
├─────────────────────────┬───────────────────────────────────┤
│ Canvas Pitch Black      │ #080809 (99.4% OLED True Black)   │
│ Surface Obsidian Dark   │ #111215 (Elevated cards & sheets) │
│ Surface Iron Slate      │ #1B1D22 (Secondary containers)    │
│ Surface Charcoal        │ #24272E (Dividers & borders)      │
└─────────────────────────┴───────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                 PRIMARY: SPARTAN METALLIC                   │
├─────────────────────────┬───────────────────────────────────┤
│ Bronze Primary          │ #C29B38 (Antique weathered gold)  │
│ Bronze Highlight        │ #E2BF65 (Specular gleam)          │
│ Bronze Dark Shadow      │ #8C6E21 (Bevel & pressed state)   │
│ Bronze Container Fill   │ rgba(194, 155, 56, 0.12)          │
│ Bronze Hairline Border  │ rgba(194, 155, 56, 0.30)          │
└─────────────────────────┴───────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                 SECONDARY: SPARTAN CRIMSON                  │
├─────────────────────────┬───────────────────────────────────┤
│ Crimson Primary         │ #8A1C14 (Deep cape red)           │
│ Crimson Vibrant         │ #B82318 (Active indicator & live) │
│ Crimson Container Fill  │ rgba(138, 28, 20, 0.16)           │
│ Crimson Border          │ rgba(184, 35, 24, 0.40)           │
└─────────────────────────┴───────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                     TYPOGRAPHIC CONTRAST                    │
├─────────────────────────┬───────────────────────────────────┤
│ Text Primary (Bone)     │ #F5F5F7 (High-contrast 100%)      │
│ Text Secondary (Ash)    │ #A0A3AB (Muted 60%)               │
│ Text Tertiary (Iron)    │ #646770 (Disabled 40%)            │
│ Text Bronze Accent      │ #C29B38                           │
└─────────────────────────┴───────────────────────────────────┘
```

---

## 2. Typography Scale

| Token Name | Font Family | Size / Line Height | Weight | Tracking (Letter Spacing) | Usage |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `display-lg` | Cinzel / Oswald | 32sp / 38sp | Bold (700) | `+0.20em` | Splash header, Hero titles |
| `display-md` | Cinzel / Oswald | 24sp / 30sp | Bold (700) | `+0.15em` | Screen titles (`THE PHALANX`) |
| `headline-sm` | Cinzel / Oswald | 18sp / 24sp | SemiBold (600) | `+0.12em` | Card headers, Widget titles |
| `body-lg` | Inter | 16sp / 22sp | Regular (400) | `0.0em` | Primary narrative & descriptions |
| `body-md` | Inter | 14sp / 20sp | Regular (400) | `0.0em` | Secondary text, specifications |
| `label-md` | Inter / Mono | 12sp / 16sp | SemiBold (600) | `+0.08em` | Button text, Category chips |
| `label-sm` | Space Mono | 10sp / 14sp | Medium (500) | `+0.10em` | Telemetry chips, AMOLED % badges |

---

## 3. Elevation, Spacing & Corner Radii

* **Corner Radii (`roundness`)**:
  - `ROUND_FOUR` (`4dp`): Micro-badges, technical stat chips, tab indicators.
  - `ROUND_EIGHT` (`8dp`): Standard cards, list items, widget preview containers.
  - `ROUND_TWELVE` (`12dp`): Modal bottom sheets, hero cards.
  - `ROUND_FULL` (`999dp`): Category pills, circular floating action buttons.
* **Borders**:
  - Strictly `1dp` solid hairline strokes (`border-[#C29B38]/30` or `border-[#1B1D22]`).
* **Spacing Scale**:
  - `xxs = 2dp`, `xs = 4dp`, `sm = 8dp`, `md = 16dp`, `lg = 24dp`, `xl = 32dp`.

---

## 4. Component Library Rules

### 4.1 Buttons
* **Primary Master CTA (Solid Spartan Bronze)**:
  - Background: Linear gradient from `#D4AC54` through `#C29B38` to `#9E7B22`.
  - Text: `#080809` Pitch Black, `label-md` bold, uppercase, `tracking-widest`.
  - Elevation: Soft bronze glow shadow `rgba(194, 155, 56, 0.35)`.
  - Active Press: Scale down to `0.98` with medium tactile haptic feedback.
* **Secondary Action Button (Chiseled Obsidian Outline)**:
  - Background: `#17181C` at 70% opacity.
  - Border: `1dp` solid `#C29B38/35`.
  - Text: `#E3C279` light bronze.

### 4.2 Cards
* **The Spartan Slate Card**:
  - Background: `#111215`.
  - Border: `1dp` solid `#1B1D22` (default) or `#C29B38/30` (active/featured).
  - Internal Padding: `16dp`.

### 4.3 Category Filter Chips
* Horizontal scroll rail with `no-scrollbar`.
* Active: Solid `#C29B38` background, `#080809` text, bold.
* Inactive: `#111215` background with `1dp` `#1B1D22` border, `#A0A3AB` text.

### 4.4 Haptics & Acoustic Feedback
* Heavy click (`HapticFeedbackConstants.CONFIRM`): Applying theme, unlocking Vanguard pass.
* Light click (`HapticFeedbackConstants.CLOCK_TICK`): Scrolling wallpaper carousel, toggling overlay.
* Acoustic Feedback: Custom short wav assets (`equip_shield.wav`, `blade_lock.wav`) playing via `SoundPool`.
