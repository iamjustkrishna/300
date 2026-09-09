# 300 — Screen Specification Inventory (Phase 1 Validated)

> **Document Version**: 2.0.0 (Phase 1 Validated Baseline)  
> **Status**: APPROVED  
> **Stitch Project ID**: `14386677080154071069`  
> **Stitch Design System**: `assets/3071428720144166618`

---

## Screen Inventory Overview

| Screen ID | Canonical Name | Category | Stitch Screen Reference |
| :--- | :--- | :--- | :--- |
| `SCR-001` | Splash & Brand Genesis | Entry | Brand Moment / Genesis Sigil |
| `SCR-002` | Onboarding (The Spartan Reveal) | Entry | `90ff7f5167744050b5960af1588a502c` |
| `SCR-003` | Home Dashboard (The Phalanx) | Discovery | `0e8957b32c06495696a5d5070d311626` |
| `SCR-004` | Theme Detail & Live Preview | Themes | `e8e8a396fb2947dea513bb14ab1ef6f7` |
| `SCR-005` | Wallpaper Vault (Discovery) | Wallpapers | `b028d16ebe5949ef8b39b5acc7733bf8` |
| `SCR-006` | Wallpaper Fullscreen Preview & Apply | Wallpapers | `906d16f32dc647be8152f722e54e1cf9` |
| `SCR-007` | Spartan Widgets Forge | Widgets | `85e33e992ae74f71a5a01a2b666221bd` |
| `SCR-008` | Vanguard Pass Paywall | Monetization | `99f69724905342868e7dcc150caac233` |
| `SCR-009` | Icon Pack Forge & Quick Styler | Customization | `4a7a0388e80644aba7ef0e60a9fea9bf` |
| `SCR-010` | 1-Tap Apply Success Modal | Transition | `d9ec56f33bb943569648908cc2c3080a` |
| `SCR-011` | Settings & Vault Management | System | `5378d88566804f85a95a34ab892a6114` |

---

## Detailed Specifications per Screen

### SCR-001: Splash & Brand Genesis
* **ID**: `SCR-001`
* **Name**: Splash & Brand Genesis
* **Purpose**: Establish immediate high-craft immersion and initialize local Room/DataStore cache within 800ms.
* **Target User**: All users on app launch.
* **Entry Points**: App icon launch, OS cold start.
* **Exit Points**: Routes to `SCR-002` (if first launch) or `SCR-003` (returning user).
* **Primary Action**: Automatic transition after 800ms fade-in.
* **Secondary Actions**: Tap anywhere to bypass animation.
* **Components**: Centered bronze Tri-Blade Lambda ($\Lambda$) sigil with glowing amber rim, '300' chiseled wordmark, subtle ash particle effect on `#080809` OLED canvas.
* **States**: Normal (0.8s), Loading (cache warmup), Error (fallback to home).
* **Accessibility**: Screen reader announces "300: Spartan Theme".
* **Analytics Events**: `app_cold_start`, `splash_dismissed`.

---

### SCR-002: Onboarding (The Spartan Reveal)
* **ID**: `SCR-002`
* **Name**: Onboarding (The Spartan Reveal)
* **Purpose**: Visually demonstrate the transformation from generic stock Android to disciplined Spartan setup via an interactive before/after split slider.
* **Target User**: First-time users seeking aesthetic upgrade.
* **Entry Points**: `SCR-001` on first install.
* **Exit Points**: `SCR-003` (Home Dashboard).
* **Primary Action**: "START TRANSFORMATION" (solid bronze button).
* **Secondary Actions**: Touch/drag interactive slider dividing Stock Android vs Spartan setup.
* **Components**: Top Lambda crest, interactive split-screen phone mockup, 3 feature highlight cards (1-Tap Complete Themes, 4K True AMOLED, Native Glance Widgets), primary CTA button.
* **States**: First-time, Slider dragging, Interactive haptic tap.
* **Responsive Behavior**: Phone: Vertical split mockup; Tablet: Side-by-side device comparison.
* **Stitch Reference**: `90ff7f5167744050b5960af1588a502c`.
* **Analytics Events**: `onboarding_viewed`, `onboarding_slider_dragged`, `onboarding_completed`.

---

### SCR-003: Home Dashboard (The Phalanx)
* **ID**: `SCR-003`
* **Name**: Home Dashboard (The Phalanx)
* **Purpose**: Central hub for daily theme drops, curated category browsing, and instant 1-tap setup previews.
* **Target User**: Daily active users and aesthetic seekers.
* **Entry Points**: `SCR-002` completion, bottom nav "Themes" tab, app resume.
* **Exit Points**: `SCR-004` (Theme Detail), `SCR-005` (Wallpaper Vault), `SCR-007` (Widgets), `SCR-008` (Vanguard Pass).
* **Primary Action**: "1-TAP APPLY" on Daily Featured Hero Card.
* **Secondary Actions**: Filter by category chips (`ALL`, `AMOLED BLACK`, `BRONZE ARMOR`, `WARRIORS`), tap theme card in 2-column grid.
* **Components**: Top bar with Lambda sigil and Vanguard badge, Daily Drop Hero Card with live timer and phone setup preview, horizontal scrolling category filter rail, 2-column theme grid, fixed 5-tab docked bottom nav.
* **States**: First-time, Normal, Loading (shimmer), Empty, Offline (cached cards with offline indicator), Premium locked.
* **Stitch Reference**: `0e8957b32c06495696a5d5070d311626`.
* **Analytics Events**: `home_viewed`, `daily_drop_clicked`, `category_filtered`, `theme_card_clicked`.

---

### SCR-004: Theme Detail & Live Setup Preview
* **ID**: `SCR-004`
* **Name**: Theme Detail & Live Setup Preview
* **Purpose**: Full interactive inspection of a complete theme (Wallpaper + Glance Widget + Icon Pack) on a simulated Android device before applying.
* **Target User**: Users evaluating a specific theme.
* **Entry Points**: Tapping any theme card from `SCR-003`.
* **Exit Points**: Back to `SCR-003`, `SCR-010` (Apply Success Modal), `SCR-008` (if locked).
* **Primary Action**: "APPLY COMPLETE THEME (1-TAP)" solid bronze CTA.
* **Secondary Actions**: Toggle "HOME SCREEN" vs "LOCK SCREEN" tabs, "CUSTOMIZE COMPONENTS", toggle favorite heart, share theme.
* **Components**: Back button, theme title and edition tag, segmented Home/Lock switcher, realistic smartphone preview mockup with wallpaper/widget/dock overlay, 4 technical spec chips (99.4% AMOLED, 4K UHD, Glance, -0.3% drain), Spartan Blueprint component cards, sticky bottom action bar.
* **States**: Normal, Applying (disabled CTA with spinner), Applied successfully, Vanguard locked (CTA becomes "UNLOCK VANGUARD PASS").
* **Stitch Reference**: `e8e8a396fb2947dea513bb14ab1ef6f7`.
* **Analytics Events**: `theme_detail_viewed`, `preview_mode_toggled`, `apply_theme_clicked`.

---

### SCR-005: Wallpaper Vault (Discovery & Grid)
* **ID**: `SCR-005`
* **Name**: Wallpaper Vault
* **Purpose**: Browse, filter, and discover 300+ 4K True AMOLED and 3D Parallax canvases.
* **Target User**: Users seeking standalone wallpapers.
* **Entry Points**: Bottom nav "Wallpapers" tab, search action.
* **Exit Points**: `SCR-006` (Fullscreen Preview), `SCR-008` (Vanguard Pass).
* **Primary Action**: Tap wallpaper thumbnail to open fullscreen preview modal.
* **Secondary Actions**: Filter by `AMOLED 95%+`, `PARALLAX 3D`, `WARRIORS`, `STOIC CREED`; favorite heart toggle.
* **Components**: Top header with search bar and 4K Ultra chip, horizontal filter chips, stats ticker banner ("300+ EXCLUSIVE 4K CANVASES • ZERO ADS • ZERO COMPRESSION"), 2-column 9:16 vertical card grid with badges, bottom nav.
* **States**: Normal, Loading, Empty, Offline (cached wallpapers with offline indicator).
* **Stitch Reference**: `b028d16ebe5949ef8b39b5acc7733bf8`.
* **Analytics Events**: `wallpaper_vault_viewed`, `wallpaper_filter_selected`, `wallpaper_card_clicked`.

---

### SCR-006: Wallpaper Fullscreen Preview & 1-Tap Apply Modal
* **ID**: `SCR-006`
* **Name**: Wallpaper Fullscreen Preview & 1-Tap Apply Modal
* **Purpose**: Full-bleed immersive 4K preview with simulated system UI overlay and 1-tap wallpaper application.
* **Target User**: Users ready to apply a specific wallpaper.
* **Entry Points**: Tapping any wallpaper from `SCR-005`.
* **Exit Points**: Back to `SCR-005`, system home screen on apply.
* **Primary Action**: "SET BOTH HOME & LOCK SCREEN" master CTA.
* **Secondary Actions**: "SET HOME SCREEN", "SET LOCK SCREEN", UI Overlay toggle (eye icon), 3D Parallax gyro toggle, "DOWNLOAD 4K RAW", "ADJUST CROP".
* **Components**: Fullscreen image bleed, floating top controls (back, overlay toggle, gyro toggle), slide-up obsidian apply sheet, AMOLED zero-draw certification badge.
* **States**: Normal, Applying, Applied, Crop mode active, Gyro active.
* **Stitch Reference**: `906d16f32dc647be8152f722e54e1cf9`.
* **Analytics Events**: `wallpaper_preview_opened`, `overlay_toggled`, `parallax_toggled`, `wallpaper_applied`.

---

### SCR-007: Spartan Widgets Forge
* **ID**: `SCR-007`
* **Name**: Spartan Widgets Forge
* **Purpose**: Showcase, customize, and directly pin native Jetpack Glance widgets to the user's home screen in 1 tap.
* **Target User**: Users wanting functional, chiseled stoic widgets.
* **Entry Points**: Bottom nav "Widgets" tab.
* **Exit Points**: Home screen on pin, `SCR-008` (Vanguard Pass).
* **Primary Action**: "ADD TO HOME SCREEN" button on any widget card.
* **Secondary Actions**: Accent selector (Bronze Metallic, Crimson Embers, Obsidian Mono), opacity slider (0% to 100%).
* **Components**: Top header with "100% Native Glance" badge, sticky forge customizer bar, 4 live widget preview cards (Stoic Monolith Clock 2x2, Hoplite Shield Gauge 2x2, War Creed Ticker 4x1, Thermopylae Calendar 4x2), bottom nav.
* **States**: Normal, Pinning requested, Pin granted, Permission denied.
* **Stitch Reference**: `85e33e992ae74f71a5a01a2b666221bd`.
* **Analytics Events**: `widgets_forge_viewed`, `widget_customized`, `widget_pin_requested`.

---

### SCR-008: Vanguard Pass Paywall (Lifetime Unlock)
* **ID**: `SCR-008`
* **Name**: Vanguard Pass Paywall
* **Purpose**: Convert free users into lifetime members via a high-converting, honest, subscription-free tribute page.
* **Target User**: Users tapping locked vault themes, 4K raw downloads, or pro widgets.
* **Entry Points**: Tapping locked items in `SCR-003`, `SCR-004`, `SCR-005`, `SCR-007`, or "Vanguard" badge in top bar.
* **Exit Points**: Dismiss 'X' back to previous screen, purchase success state.
* **Primary Action**: "UNLOCK VANGUARD PASS — ₹199" solid bronze button.
* **Secondary Actions**: "RESTORE" purchases, "Terms of Honor", "Privacy Policy".
* **Components**: Close button, embossed bronze helmet hero medallion, "BECOME A VANGUARD" header, 6-item Vanguard Arsenal feature card, limited launch 50% off pricing box (₹199 / $2.99 lifetime), Google Play security & family library trust badges.
* **States**: Normal, Processing purchase, Purchase succeeded, Purchase failed, Network error.
* **Stitch Reference**: `99f69724905342868e7dcc150caac233`.
* **Analytics Events**: `paywall_viewed`, `paywall_source_tracked`, `checkout_initiated`, `purchase_completed`, `restore_clicked`.

---

### SCR-009: Icon Pack Forge & Quick Styler
* **ID**: `SCR-009`
* **Name**: Icon Pack Forge & Quick Styler
* **Purpose**: Preview, style, and apply 400+ handcrafted weathered bronze vector glyphs to Android launchers.
* **Target User**: Users completing their visual overhaul.
* **Entry Points**: Bottom nav "Icons" tab.
* **Exit Points**: Launcher settings, app search.
* **Primary Action**: "1-TAP APPLY TO LAUNCHER" master button.
* **Secondary Actions**: Palette selector (Weathered Bronze, Obsidian Slate, Crimson Spartan), "REQUEST SPARTAN GLYPH", search app icons.
* **Components**: Top header, quick-apply banner with launcher chips (Nova, Smart, Niagara, One UI, Pixel), simulated bottom dock preview with 5 chiseled glyphs, 4x4 categorized icon grid, icon request card, bottom nav.
* **States**: Normal, Searching, Palette switched, Applying.
* **Stitch Reference**: `4a7a0388e80644aba7ef0e60a9fea9bf`.
* **Analytics Events**: `icons_forge_viewed`, `palette_switched`, `apply_icons_clicked`, `request_icon_clicked`.

---

### SCR-010: 1-Tap Apply Success Modal
* **ID**: `SCR-010`
* **Name**: 1-Tap Apply Success Modal
* **Purpose**: Provide instant, reassuring feedback on the theme application sequence and celebrate device transformation.
* **Target User**: Users who just initiated "1-Tap Apply Complete Theme".
* **Entry Points**: Triggered from `SCR-004`.
* **Exit Points**: Exit to Android Launcher ("VIEW MY NEW HOME SCREEN"), or stay in app ("FINE-TUNE WIDGETS & ICONS").
* **Primary Action**: "VIEW MY NEW HOME SCREEN →" solid bronze button.
* **Secondary Actions**: "FINE-TUNE WIDGETS & ICONS" outline button.
* **Components**: Glowing circular Spartan Lambda seal, "FORGING SPARTAN TRANSFORMATION" header, 4-step progress checklist (Wallpaper applied, Glance widget pinned, Icons ready, Soundpack active), mini device preview thumbnail, telemetry bar (Apply: 1.4s, Battery: 0.0%, Cache: 18.2 MB).
* **States**: In-progress (step-by-step ticks), Complete (100%).
* **Stitch Reference**: `d9ec56f33bb943569648908cc2c3080a`.
* **Analytics Events**: `theme_apply_started`, `theme_apply_step_completed`, `theme_apply_succeeded`, `exit_to_launcher_clicked`.

---

### SCR-011: Settings & Vault Management
* **ID**: `SCR-011`
* **Name**: Settings & Vault Management
* **Purpose**: Manage AMOLED efficiency, gyroscope battery-saving armor, offline wallpaper storage, and Google Play billing restore.
* **Target User**: Users customizing performance and system settings.
* **Entry Points**: Bottom nav "Settings" tab.
* **Exit Points**: Sub-settings, Google Play Store rating.
* **Primary Action**: Toggles for AMOLED true black and battery-saver armor.
* **Secondary Actions**: "CLEAR WALLPAPER CACHE", "Restore Google Play Purchases", "The Spartan Manifesto".
* **Components**: Top header with build tag `v1.0.0 (PHALANX)`, Vanguard Lifetime Status card, Display & AMOLED Efficiency settings group, Widgets & Launcher Integration group, Storage & Cache group with memory progress bar, Honor & Support group, bottom nav.
* **States**: Normal, Cache cleared, Purchases restored, Network error.
* **Stitch Reference**: `5378d88566804f85a95a34ab892a6114`.
* **Analytics Events**: `settings_viewed`, `amoled_toggled`, `cache_cleared`, `restore_purchases_clicked`.
