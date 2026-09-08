# Device & Responsive Behavior Specification

---

## 1. Supported Ecosystem Matrix

Targeting **Android 10 (API 29) and above**.

| Category | Physical Diagonal | Typical Resolution | WindowWidthSizeClass | Adaptive Behavior |
|---|---|---|---|---|
| **Compact Phone** | 4.7" - 5.5" | 360 x 640 - 360 x 780 dp | `Compact` (<600dp) | Single column, compressed vertical spacing (12dp), compact headers, scrollable containers. |
| **Standard Phone** | 5.8" - 6.4" | 393 x 851 - 412 x 915 dp | `Compact` (<600dp) | Baseline 8dp grid, standard bottom navigation, 2-column wallpaper catalog. |
| **Large / Tall Phone** | 6.7" - 6.9"+ | 412 x 960 dp (~21:9) | `Compact` (<600dp) | Hero imagery takes advantage of vertical height; bottom controls pinned within thumb reach. |
| **Foldable (Folded)** | ~6.2" cover | 320 - 360 x 840 dp (~23:9)| `Compact` (<600dp) | Narrow single column; buttons wrap text cleanly without truncation. |
| **Foldable (Unfolded)**| ~7.6" inner | 674 x 840 dp (~1:1) | `Medium` / `Expanded` | Dual-pane home dashboard; 3-4 column wallpaper catalog; navigation rail. |
| **Tablet (Portrait)** | 10" - 12" | 800 x 1280 dp (16:10) | `Medium` / `Expanded` | Multi-column grid, max-width constraints on readable text (max 640dp). |
| **Tablet (Landscape)**| 10" - 12" | 1280 x 800 dp (16:10) | `Expanded` (>840dp) | Master-detail split layout; permanent navigation rail; 4-column wallpaper catalog. |

---

## 2. Density Bucket Handling

| Density Bucket | Scale Factor | Strategy for 300 |
|---|---|---|
| `mdpi` (160 dpi) | 1.0x | Vector Drawables for all icons; WebP assets sampled appropriately. |
| `hdpi` (240 dpi) | 1.5x | Vector Drawables; Coil memory cache sized proportionally. |
| `xhdpi` (320 dpi) | 2.0x | High-res vector render; 720p thumbnail downsampling. |
| `xxhdpi` (480 dpi) | 3.0x | Standard modern phone baseline; 1080p full-res wallpaper decoding. |
| `xxxhdpi` (640 dpi)| 4.0x | 1440p / 4K asset resolution pipeline with target size decoders. |

---

## 3. System Navigation & Inset Handling

### 3.1. Gesture Navigation Mode (Edge-to-Edge)
- Window sets `enableEdgeToEdge()`.
- Top AppBars pad for `WindowInsets.statusBars`.
- Bottom Navigation and sticky action buttons pad for `WindowInsets.navigationBars`.
- Interactive swipe-to-preview surfaces respect system back gesture edge exclusion zones.

### 3.2. 3-Button Navigation Mode
- Bottom navigation bar properly positions above the system button bar (typically 48dp - 56dp height).
- Zero content overlapping or occlusion behind system back/home/recents buttons.

---

## 4. Component-Level Responsive Contracts

### 4.1. SpartanButton
- **Minimum Width**: 120dp.
- **Maximum Useful Width**: 480dp (on tablets/unfolded foldables, buttons never stretch full-screen across 1000dp+; they center with `Modifier.widthIn(max = 480.dp)`).
- **Text Wrapping**: If system font scale is 200%, the button expands its vertical height from 56dp to 72dp+ to accommodate two lines of text without clipping.

### 4.2. Wallpaper Grid (`LazyVerticalGrid`)
- **Compact (<600dp)**: `GridCells.Fixed(2)`.
- **Medium (600dp - 840dp)**: `GridCells.Fixed(3)` or `GridCells.Adaptive(minSize = 160.dp)`.
- **Expanded (>840dp)**: `GridCells.Fixed(4)` or `GridCells.Adaptive(minSize = 180.dp)`.
- **Aspect Ratio**: Each thumbnail maintains a strict 9:16 aspect ratio box with `ContentScale.Crop`.

### 4.3. Daily Challenge Card
- **Small Screens**: Single column with vertical stacking (Title -> Description -> Action Button).
- **Tablets / Landscape**: Horizontal flex (Title & Description on left 60%, Action button & Streak badge on right 40%).

### 4.4. Fullscreen Wallpaper Preview & Cropping
- **Image Focal Point**: Center-weighted.
- **Aspect Ratio Adaptability**: On ultra-tall (21:9) or wide (1:1 / 16:10) screens, the wallpaper scaler crops evenly from outer edges while preserving the central warrior focal point.
- **Preview Canvas**: The interactive wallpaper preview container always matches the exact hardware aspect ratio of the host display.

---

## 5. System Accessibility & Settings Adaptation

| System Setting | App Behavior |
|---|---|
| **System Dark Mode** | Default application visual identity is natively dark obsidian (`#0B0B0C`). |
| **System Light Mode** | App remains in its signature brutalist dark Spartan identity (personalization & theme apps maintain brand fidelity; dark mode preserves OLED power and cinematic immersion). |
| **Font Scaling (up to 200%)** | All typography uses `sp`. Containers use flexible heights (`wrapContentHeight()`) rather than fixed `dp` heights. Text wraps smoothly. |
| **Display Scaling (Density shift)** | Density changes adjust dp calculations automatically without broken layout bounds. |
| **Reduced Motion** | In-app animations (shimmers, state flips, dialog reveals) are disabled or swapped for 0ms transitions. |
| **High Contrast Text** | Border outlines (`BorderSubtle`) scale up contrast; text glyphs retain crisp edge separation. |
| **Screen Reader (TalkBack)** | Full semantics tree verified; touch targets enforce 48dp x 48dp minimums. |
