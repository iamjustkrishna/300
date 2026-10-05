# 300 — Screen Specification Inventory (Classical Stoic Luxury Redesign)

## M3 Asset Catalog Screens & Canonical States

All M3 surfaces inherit v2 tokens: obsidian canvas `#08080A`, basalt cards `#121216`, satin bronze `#C5A869`, oxblood `#8A1C14`, Roman serif display, clean sans utility copy, 18dp cards, and no glass/neon.

### SCR-007: Wallpaper Vault

* **Route**: `vault`.
* **Purpose**: Browse the 12-item downloadable master catalog by bundle and entitlement.
* **Layout/actions**: v2 dock and header, category rail, portrait cards with true-black badge, cache state, lock treatment; preview, filter, and Vanguard actions.

### SCR-008: Wallpaper Preview & Apply

* **Route**: `wallpaper/{wallpaperId}`.
* **Purpose**: Inspect text-free masters with clock/dock readability overlays before download or apply to Home, Lock, or Both.
* **Layout/actions**: edge-to-edge art and a black gradient control shelf; download/cache, apply, favourite, and context-preserving paywall actions. Controls are never baked into artwork.

### SCR-009: Icon Forge & Export

* **Route**: `icons`.
* **Purpose**: Browse six 16-glyph bronze families and export a selected family for compatible launchers.
* **Layout/actions**: basalt family selector, 4-column glyph grid, compatibility notice, and gold `Export icons` CTA. It must say “launcher-dependent export”, never universal installation.

### SCR-010: Widget Forge Expansion

* **Surface**: SCR-005 Widgets tab.
* **Purpose**: Add Creed Ticker (4x1) and War Calendar (4x2) beside the approved two 2x2 widgets.
* **Layout/actions**: same Studio cards; previews are native-looking components and dynamic values render in Glance. `Add to home` uses Android pinning and falls back to system-placement guidance.

### Eight canonical asset-catalog states

| State | Visible behavior | Required action |
| --- | --- | --- |
| Loading | Basalt skeleton cards preserve crop ratio. | None; no empty flash. |
| Populated | Thumbnail, title, black percentage, entitlement, cache state. | Preview/filter/export. |
| Locked | Oxblood scrim and bronze lock. | Open Vanguard pass. |
| Offline cache hit | `AVAILABLE OFFLINE` bronze label. | Apply without network. |
| Downloading | Per-card progress/cancel; thumbnail stays visible. | Cancel/retry. |
| Download error | Concise non-destructive error below card. | Retry. |
| Empty/filter no match | Creed and reset control. | Clear filters. |
| Apply/export success | Short bronze confirmation sheet. | Dismiss or continue browsing. |

> **Document Version**: 4.0.0 (Classical Stoic Luxury Baseline)  
> **Status**: APPROVED  
> **Stitch Project ID**: `14386677080154071069`  
> **Stitch Design System**: `assets/13394188174485581719` ("300 Classical Stoic Luxury")  
> **Stitch Model**: `GEMINI_3_8_FLASH`

---

## 1. Streamlined Screen Inventory (6-Screen Core Suite)

| Screen ID | File Name in `UI_Screen/` | Canonical Screen Name | Stitch Screen ID | High-Res Cloud Preview |
| :--- | :--- | :--- | :--- | :--- |
| `SCR-001` | [`01_Splash_Brand_Genesis.png`](../../UI_Screen/01_Splash_Brand_Genesis.png) | Splash (Brand Genesis) | `b39aa6f3fbe840a890a29ae154e856e2` | [Stitch Image](https://lh3.googleusercontent.com/aida/AEtjO1UiPE59Biz3csSdGn0HE3XvXsReG7qxiAm3zBeiQ-zVckJ1zJTTStPwARbFZNNr1aJpDyk9OWgn7NZsjyaiqVWqTK0_gzegVMX9_tnBil8Bio6a4sfrxK6CC5phkl2cSPf-kVk0zUMDBc_W5pZtjcMU1fsR1t0EV-WJ4wFqDKX-PitGykr-Je8KuwbkwzLb-QwY9ZfGwQzblBrVPdtyLX1UEr0ErDRZ2q19ev7FhNnvg5R6NQG9dDhPyjQZ) |
| `SCR-002` | [`02_Onboarding_The_Reveal.png`](../../UI_Screen/02_Onboarding_The_Reveal.png) | Onboarding (The Reveal) | `8a2e750b1c9d417c8165106b47d14a99` | [Stitch Image](https://lh3.googleusercontent.com/aida/AEtjO1VoZ_6OAF9SiQ2NcTs7Dy6f9jcrPiiozK_crIwKUPvswc8ayS_RElbMOuGPvKWW1bOkccHYpPja6l41a5REGoEwmi1DWbx-taoVd7G4y1He93oTLR4wIP9TxPET7xKgY6455toMqvAkq9CluCScRYnF9CMyiRytapWOTcPkfgrsAXYFz4_KhBiGRFkUhGfYUp34NIuxwpNAdhFodWnoDn_Ot4lzZK6hN7LJi_DXL_YBuMbQfdGzJr9wVAo1) |
| `SCR-003` | [`03_Home_Curated_Themes.png`](../../UI_Screen/03_Home_Curated_Themes.png) | Home Dashboard (Curated Themes) | `8ffdab0be6ff4641a69e99573b27ff81` | [Stitch Image](https://lh3.googleusercontent.com/aida/AEtjO1UkcyZXGnVa051oUjEcbUZSHApyiSVuLhoULUQ1W75dY0LmKJJsFcgLJ93g0-xEB10qw0oArptULlKWNOf0LsVm60uMV4ef1NkCQ6C9gww3lhc42Y0quyYr3FGhvUVlyRl0mKeVISZewPZudUuGySx8fgTFqoQcy2TJFv7SMqaa_lAOUwMqzs65g-LQ9wqvmbrH4Q2Z8vj1RA3vFcC6FWJqbJFQZX344wxPygHBAeqpL5n4GDJyHvLEpdii) |
| `SCR-004` | [`04_Theme_Live_Preview_Apply.png`](../../UI_Screen/04_Theme_Live_Preview_Apply.png) | Theme Live Preview & 1-Tap Apply | `1fedc67d269a457db11fc5db2bea3d2a` | [Stitch Image](https://lh3.googleusercontent.com/aida/AEtjO1XFk8H1Sk9CLKA-ldWERYuwig6xF8YEWd22b7hzxJskKqyy969n77yFf4AOF-nWiXCcQSff0ouzaAi_EG5XCBJ8BisEecvdkf2866-_bJMBOT-yxnINGJdDXmih4CeZZRX4fsjZWnD8B0HueOYekyltC1LjNRjyBIAnJiz8RKuutH3VuHqnNdYxgacD-Ycu87O4kag0cSbeNr3Hrej4E9YRzcVGmSlJvbQlonPJItaYfqepChmB8NcV3Yw) |
| `SCR-005` | [`05_Studio_Widgets_Wallpapers.png`](../../UI_Screen/05_Studio_Widgets_Wallpapers.png) | Studio (Widgets & Wallpapers) | `6251170fce5d4c118e011786d7682447` | [Stitch Image](https://lh3.googleusercontent.com/aida/AEtjO1Vz0bYyAb7OlULwT35P7WTHsqRK1dJWQRVpMCzcLyMN37xcQ6y6QAWA87WLX-f12EWrBcVDHolE5lXldquRh4nnbx5Eo71WBMavlSLMfqkvwsBmqE4LPFjd0IkwmK5qg-or2m7pFIjkQcE9MXjEDlUiHC4ewqCCaAXIYyTVTok6sIFklydyUfZ5h_DDmt9qvrdtkSPUcEdsHxYbAB-mGx5VTPI3JG5Pmck8eIcFH1F0aGgZXuhIQfl1cbcc) |
| `SCR-006` | [`06_Settings_Vanguard_Pass.png`](../../UI_Screen/06_Settings_Vanguard_Pass.png) | Settings & Vanguard Lifetime Pass | `5af911867fad49bbb5d9206a67da386d` | [Stitch Image](https://lh3.googleusercontent.com/aida/AEtjO1Vow4WNabpkH13Lx7D_axCS8U4UrL1bjEXmJrNla6_fu6sIawgJKhkl41gIh4jMfAzb7TYRce7ZAqESYo--L4h1DDPwFgGdSCKVyYiODIVj-9agR91GaMnoUvOa1GZCkn0QFu8HRX7awTrgyaxokyFyKaXvhCdBB55ITYZyW_Ak3vhiNbxQHFDvVAxVoq1la236TkiHPMt6FlR7zzMPLHPJSgQTiCbJafEB-JCDBNKfx0Lmg90393JmdD8_) |

---

## 2. Detailed Specifications per Screen

### SCR-001: Splash (Brand Genesis)
* **ID**: `SCR-001`
* **File**: `UI_Screen/01_Splash_Brand_Genesis.png`
* **Purpose**: Ceremonial brand ritual and initial cache warm-up (< 800ms).
* **Aesthetic**: Deep basalt stone canvas (`#08080A`), dark ambient lighting, drifting ember particles.
* **Components**:
  - Circular antique bronze hoplite shield medallion with vertical spear piercing and embossed Spartan $\Lambda$ (Lambda) chevron.
  - Centered commanding `300` in classical luxury serif (*EB Garamond* style).
  - Spaced `SPARTA THEME` with fine gold hairline divider.
  - Stoic micro-motto *"RETURN WITH YOUR SHIELD, OR ON IT."*
  - Dual-tone progress bar (brushed satin gold to dark bronze) with tracking-widest micro-caption `PREPARING YOUR SET · V1.0 × PHALANX`.

---

### SCR-002: Onboarding (The Reveal)
* **ID**: `SCR-002`
* **File**: `UI_Screen/02_Onboarding_The_Reveal.png`
* **Purpose**: Make the transformation concrete before user entry.
* **Components**: 
  - Centered top pill badge: `300 · SPARTA`.
  - Floating flagship smartphone in an ancient monumental stone portico with archways, tranquil reflective water pool, and glowing flame brazier.
  - Phone screen interior: minimal lock screen (*"Mon, Apr 28 · A CALMER BRIGHTER YOU"*), minimal weather, search pill, and 4 bronze glyph dock icons.
  - Classical serif headline: *"Make your phone feel intentional."*
  - Subtitle: *"Themes, widgets, and icons — curated as one set."*
  - 3 Circular wireframe gold chips:
    1. `True black` (crescent moon)
    2. `Native widgets` (4-square grid)
    3. `One-tap sets` (layered stack)
  - Primary CTA: Brushed satin-gold pill button *"Start exploring →"*.
  - Micro-footer: `100% NATIVE ANDROID · ZERO ADS`.

---

### SCR-003: Home Dashboard (Curated Themes)
* **ID**: `SCR-003`
* **File**: `UI_Screen/03_Home_Curated_Themes.png`
* **Purpose**: Editorial daily theme discovery and curated theme archive.
* **Components**:
  - Top bar with Spartan Lambda gold badge, `300 CURATED THEMES`, search glass pill, and profile indicator.
  - Hero Card: *"Obsidian Ritual — THE DAILY SET"*, depicting a monumental stone colonnade with flame braziers and antique bronze hoplite shield. Includes `Preview theme >` button and `99.2% Black · 4K UHD` badge.
  - Category Filter Rail: Horizontal pill switcher: `Featured` (active solid gold), `AMOLED`, `Armor`, `Minimal`.
  - Curated Archive: 2-column masonry grid cards:
    1. *Spartan Dawn* ("Light finds a way.")
    2. *Helm* ("Silence looks stronger.")
    3. *Aegean Noir* ("Depth over noise.")
    4. *Last Light* ("Stillness remains.")
  - Stoic Philosophy banner: *"DISCIPLINE IS FREEDOM · CURATED SPARTAN ECOSYSTEM · ZERO BLOATWARE"*.
  - Docked 4-tab navigation bar: `Discover` (active), `Studio`, `Vault`, `Settings`.

---

### SCR-004: Theme Live Preview & 1-Tap Apply
* **ID**: `SCR-004`
* **File**: `UI_Screen/04_Theme_Live_Preview_Apply.png`
* **Purpose**: Interactive inspection flow displaying the complete applied theme before 1-tap activation.
* **Components**:
  - Top bar with back chevron `<`, title `Obsidian Ritual (CURATED THEME · 088)`, and favorite heart.
  - Segmented Mode Switcher: `Home screen` (active brushed gold) vs `Lock screen` (dark basalt).
  - Center Hero Smartphone Mockup: Shows the exact live theme with basalt portal wallpaper, minimal clock widget (`09:41 · Tue, Apr 22`), and dock of 5 bronze glyph icons.
  - Feature Badges: `TRUE BLACK`, `NATIVE WIDGET`, `BRASS GLYPHS`.
  - Component List: Live inspectable rows for Wallpaper, Clock widget, and Icon pack with chevrons.
  - Master CTA: Large brushed satin-gold pill button: `Apply this set →` with micro-caption `1-TAP ANDROID SYSTEM APPLIER · ZERO ROOT REQUIRED`.

---

### SCR-005: Studio (Widgets & Wallpapers)
* **ID**: `SCR-005`
* **File**: `UI_Screen/05_Studio_Widgets_Wallpapers.png`
* **Purpose**: Native Jetpack Glance widget customization and standalone 4K AMOLED wallpaper gallery.
* **Components**:
  - Header: Classical serif `Studio / SHAPES A CALMER TOMORROW` with gold laurel badge `300 SET ACTIVE`.
  - Dual pill category switcher: `Widgets` (active) vs `Wallpapers`.
  - Featured Widget Forge Cards:
    1. *Monolith Clock (2x2)*: Obsidian marble widget with Roman numerals (`XII`, `III`, `VI`, `IX`), analog hands, digital readout, stoic quote *"Time keeps better company here."*, and `+ Add to home` gold button.
    2. *Shield Battery (2x2)*: Sculpted bronze hoplite shield with Lambda, 78% battery readout, crimson power arc, quote *"Power fuels a quieter path."*, and `+ Add to home` gold button.
  - Wallpaper Sets: 2-column grid featuring *Obsidian Sanctuary* and *Crimson Silence* (Vanguard Pass exclusive with lock badge).
  - Docked 4-tab bar: `Discover`, `Studio` (active Doric temple glowing gold), `Vault`, `Settings`.

---

### SCR-006: Settings & Vanguard Lifetime Pass
* **ID**: `SCR-006`
* **File**: `UI_Screen/06_Settings_Vanguard_Pass.png`
* **Purpose**: Transparent lifetime membership upgrade and core app preferences.
* **Components**:
  - Top bar: `Settings` in classical serif with `v1.0.0` metallic badge.
  - Vanguard Pass Hero Card: High-contrast basalt card featuring the ancient weathered bronze Corinthian helmet with crimson crest, laurel wreath badge `LIFETIME ACCESS`, title `Vanguard Pass`, subtitle *"A complete collection. Yours for life."*, price `₹199 one-time` (zero recurring subscriptions), and `Unlock the full vault >` CTA button.
  - Display Section: Pure black canvas (gold switch ON) and Low-power mode (switch OFF).
  - Library Section: Offline cache, Restore purchases, and About 300 rows.
  - Stoic footer: *"Discipline in every detail. Zero bloatware."*
  - Docked 4-tab bar: `Discover`, `Studio`, `Vault`, `Settings` (active gear glowing gold).
