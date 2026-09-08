# 300 Spartan Brutalist Design System

---

## 1. Aesthetic Philosophy: Spartan Brutalism

The design system of **300** embodies ancient Spartan discipline, martial honor, and austere physical grit translated into a cutting-edge Android mobile interface.

### Core Aesthetic Principles
1. **Cinematic Restraint**: Obsidian backgrounds, deep charcoal surfaces, and razor-sharp contrast. Every element serves a purpose; zero unnecessary decorations.
2. **Brutalist Honesty**: Chiseled edges, clear borders, tactile weights, and geometric confidence. No fluffy cartoon shapes or playful bubbles.
3. **Severe Color Hierarchy**: Spartan Crimson (`#8A1C14`) and Weathered Bronze (`#C29B38`) are sacred accents reserved for focus, streaks, and calls to action. Never flood surfaces with aggressive color.
4. **Tactile Physicality**: Buttons have weight; haptics replicate heavy metallic strikes; transitions are swift, stiff springs without comical bouncy physics.
5. **Anti-Generative Guardrails**: Explicitly prohibited: rainbow gradient meshes, milky glassmorphism blurs, floating neon badges, stock corporate vectors, and ungrounded AI glow effects.

---

## 2. Color System Tokens

All colors are designed for deep OLED power savings and maximum text readability.

| Token Name | Hex Code | Purpose & Usage | Contrast vs Background |
|---|---|---|---|
| `Color.Background` | `#0B0B0C` | Primary app canvas (Deep Obsidian Black) | Baseline (1:1) |
| `Color.Surface0` | `#0B0B0C` | Root window surface | Baseline |
| `Color.Surface1` | `#131416` | Card background, list items, search bars | 1.15:1 subtle lift |
| `Color.Surface2` | `#1C1D20` | Elevated cards, dialog backgrounds, bottom sheets | 1.3:1 surface lift |
| `Color.Surface3` | `#25272B` | Hover, selected states, chip backgrounds | 1.5:1 |
| `Color.AccentBronze` | `#C29B38` | Spartan crests, primary buttons, active highlights | 6.8:1 (AAA large) |
| `Color.AccentBronzeDim` | `#7A6020` | Inactive bronze borders, subtle icons | 3.5:1 |
| `Color.AccentCrimson` | `#8A1C14` | Streak fire, day badge, critical alert accent | 4.8:1 (AA) |
| `Color.AccentCrimsonDim` | `#4A100C` | Crimson card tint, subtle danger surfaces | 2.1:1 |
| `Color.TextPrimary` | `#E5E5E7` | Primary headers, card titles (Ash White) | 17.2:1 (AAA) |
| `Color.TextSecondary` | `#8F9094` | Body copy, secondary descriptions, timestamps | 6.5:1 (AA) |
| `Color.TextMuted` | `#5C5D61` | Inactive captions, disabled button text | 3.2:1 |
| `Color.BorderSubtle` | `#26272B` | 1dp chiseled card borders | N/A |
| `Color.BorderBronze` | `#C29B38` | 1.5dp selected item active border | N/A |
| `Color.Success` | `#2E7D32` | Challenge completed status indicator | 5.2:1 (AA) |

---

## 3. Typography Scale Tokens

The typography pairs a commanding, chiseled serif font for display/hero headers with an ultra-clean, legible sans-serif for body text and telemetry data.

- **Display Serif**: Cinzel / Oswald (Capitalized, authoritative, Spartan)
- **Body Sans**: Inter / Roboto (Neutral, crisp, high legibility)

| Token Name | Font Family | Weight | Size (sp) | Line Height (sp) | Tracking / Letter Spacing |
|---|---|---|---|---|---|
| `Typography.DisplayLarge` | Cinzel | Bold (700) | 32sp | 40sp | 0.05em (Expanded) |
| `Typography.DisplayMedium`| Cinzel | Bold (700) | 26sp | 32sp | 0.04em |
| `Typography.DisplaySmall` | Cinzel | SemiBold (600)| 22sp | 28sp | 0.03em |
| `Typography.HeadlineLarge` | Cinzel | Medium (500)| 20sp | 26sp | 0.02em |
| `Typography.HeadlineMedium`| Inter | SemiBold (600)| 18sp | 24sp | 0.01em |
| `Typography.TitleLarge` | Inter | SemiBold (600)| 16sp | 22sp | Normal |
| `Typography.TitleMedium` | Inter | Medium (500)| 14sp | 20sp | Normal |
| `Typography.BodyLarge` | Inter | Normal (400)| 16sp | 24sp | Normal |
| `Typography.BodyMedium` | Inter | Normal (400)| 14sp | 20sp | Normal |
| `Typography.BodySmall` | Inter | Normal (400)| 12sp | 16sp | Normal |
| `Typography.LabelLarge` | Cinzel | Bold (700) | 14sp | 18sp | 0.08em (Uppercase Buttons) |
| `Typography.LabelSmall` | Inter | Medium (500)| 11sp | 14sp | 0.04em (Badges) |

---

## 4. Spacing & Spatial Grid
All layouts strictly adhere to an **8dp grid** with **4dp micro-increments**.

| Token Name | Value | Usage |
|---|---|---|
| `Spacing.Micro` | 4dp | Icon-to-text gap, badge internal padding |
| `Spacing.Small` | 8dp | Compact margins, chip spacing |
| `Spacing.Medium` | 16dp | Standard screen margin, card inner padding |
| `Spacing.Large` | 24dp | Section separation, header margin |
| `Spacing.ExtraLarge`| 32dp | Hero component separation |
| `Spacing.Section` | 48dp | Major screen section breaks |

---

## 5. Shape & Corner Radii

In keeping with brutalist minimalism, corners are tightly controlled rather than bubbly or pill-shaped.

| Token Name | Radius | Usage |
|---|---|---|
| `Shape.None` | 0dp | Full-bleed media, header bars |
| `Shape.Small` | 4dp | Small chips, selection indicators |
| `Shape.Medium` | 8dp | Standard action buttons, input fields |
| `Shape.Card` | 12dp | Content cards, wallpaper tiles, challenge cards |
| `Shape.Sheet` | Top 16dp | Bottom sheets, modal dialogs |

---

## 6. Elevation, Borders & Shadows

- **Elevation Strategy**: 300 avoids soft, diffuse drop-shadows. Depth is conveyed through **surface tonal lifting** (`Surface0` -> `Surface1` -> `Surface2`) accompanied by razor-sharp **1dp borders**.
- **Border Tokens**:
  - `Border.Subtle`: `BorderStroke(1.dp, Color.BorderSubtle)`
  - `Border.Bronze`: `BorderStroke(1.5.dp, Color.AccentBronze)`
  - `Border.Crimson`: `BorderStroke(1.5.dp, Color.AccentCrimson)`

---

## 7. Component Specifications

### 7.1. SpartanButton (Primary)
- Height: 56dp (meets 48dp touch requirement with generous target).
- Background: `Color.AccentBronze` (`#C29B38`).
- Content Color: `#0B0B0C` (Solid black text, maximum punch).
- Typography: `Typography.LabelLarge` (Uppercase Cinzel Bold, tracking 0.08em).
- Shape: `Shape.Medium` (8dp).
- Haptics: `HapticFeedbackConstants.HEAVY_CLICK` on press.
- Disabled State: Background `#25272B`, Content Color `#5C5D61`.

### 7.2. ShieldCard (Containers)
- Background: `Color.Surface1` (`#131416`).
- Border: `Border.Subtle` (`#26272B`).
- Shape: `Shape.Card` (12dp).
- Inner Padding: 16dp.

### 7.3. SpartanFilterChip
- Height: 36dp (48dp touch target via `Modifier.sizeIn`).
- Inactive: Background `#131416`, Border `#26272B`, Text `#8F9094`.
- Active: Background `#25272B`, Border `Color.AccentBronze`, Text `#E5E5E7`.

### 7.4. Bottom Navigation Bar / Rail
- Background: `Color.Surface0` with top 1dp border `#26272B`.
- Active Indicator: Bronze crest icon with subtle crimson dot beneath.
- Inactive Icon: Muted Shield Gray (`#8F9094`).

---

## 8. Haptic Feedback Tokens

| Haptic Event | Android System Constant | Emotional Sensation |
|---|---|---|
| `Haptic.ChallengeDone` | `HEAVY_CLICK` (or double tick) | Heavy Spartan iron hammer falling |
| `Haptic.WallpaperApply` | `CONFIRM` | Solid lock-in click |
| `Haptic.TabSwitch` | `CLOCK_TICK` | Crisp mechanical rotation |
| `Haptic.Selection` | `KEYBOARD_TAP` | Swift tactile acknowledgement |

---

## 9. Motion & Animation Physics

All animations are swift, decisive, and martial:
- **Duration**: Fast (150ms - 250ms). No slow, lazy floats.
- **Spring Specification**: Stiff spring (`dampingRatio = 0.8f`, `stiffness = 500f`).
- **Reduced Motion**: If system setting `TRANSITION_ANIMATION_SCALE == 0`, animations collapse immediately to 0ms with instant state swaps.
