# 300 — Screen Specification Inventory (Luxury Modern Redesign)

> **Document Version**: 3.0.0 (Luxury Minimalist Baseline)  
> **Status**: APPROVED  
> **Stitch Project ID**: `14386677080154071069`  
> **Stitch Design System**: `assets/1647928783357377156` (300 Luxury Modern Minimalist)

---

## 1. Streamlined Screen Inventory (6-Screen Core Suite)

| Screen ID | File Name in `UI_Screen/` | Canonical Screen Name | Stitch Screen Reference |
| :--- | :--- | :--- | :--- |
| `SCR-001` | [`01_Splash_Brand_Genesis.png`](01_Splash_Brand_Genesis.png) | Splash (Brand Genesis) | `e3d427d64ec6462e908d1545336688bc` |
| `SCR-002` | [`02_Onboarding_The_Reveal.png`](02_Onboarding_The_Reveal.png) | Onboarding (The Reveal) | `5ac59be843e34168b0eabbb99833d310` |
| `SCR-003` | [`03_Home_Curated_Themes.png`](03_Home_Curated_Themes.png) | Home Dashboard (Curated Themes) | `c08ebfb932b64f8cb6bf1c1209fbc60c` |
| `SCR-004` | [`04_Theme_Live_Preview_Apply.png`](04_Theme_Live_Preview_Apply.png) | Theme Live Preview & 1-Tap Apply | `80f50b5653794c54b2cdb3dbe720f8b4` |
| `SCR-005` | [`05_Studio_Widgets_Wallpapers.png`](05_Studio_Widgets_Wallpapers.png) | Studio (Widgets & Wallpapers) | `94c449da388041a786211215fe24d977` |
| `SCR-006` | [`06_Settings_Vanguard_Pass.png`](06_Settings_Vanguard_Pass.png) | Settings & Vanguard Lifetime Pass | `3b15df9333e24bca8d072572dd39a4e3` |

---

## 2. Detailed Specifications per Screen

### SCR-001: Splash (Brand Genesis)
* **ID**: `SCR-001`
* **File**: `UI_Screen/01_Splash_Brand_Genesis.png`
* **Purpose**: Instant, elegant brand introduction with cache initialization within 800ms.
* **Aesthetic**: Deep pitch-black background (`#0A0A0C`) with a soft warm ambient radial glow (`#D8B26E` at 4% opacity).
* **Components**: Centered geometric Spartan Lambda (Λ) monogram in satin champagne-bronze, clean modern '300' typography (*Plus Jakarta Sans*), subtitle "Transform your phone", and a razor-thin 1px metallic loading bar.

---

### SCR-002: Onboarding (The Reveal)
* **ID**: `SCR-002`
* **File**: `UI_Screen/02_Onboarding_The_Reveal.png`
* **Purpose**: Clearly demonstrate the value proposition: transforming the user's phone in a single tap.
* **Components**: 
  - Top minimal Lambda mark.
  - Floating bezel-less smartphone mockup showing applied dark aesthetic, clock widget, and 4 satin bronze dock glyphs.
  - Headline: *"Your phone. Reimagined."*
  - Subtitle: *"Curated 4K AMOLED themes, native widgets, and bespoke icons in a single tap."*
  - 3 clean feature rows (True AMOLED, Native Glance Widgets, One-Tap Cohesion).
  - Primary CTA: Smooth satin champagne-bronze gradient button *"Get Started →"*.

---

### SCR-003: Home Dashboard (Curated Themes)
* **ID**: `SCR-003`
* **File**: `UI_Screen/03_Home_Curated_Themes.png`
* **Purpose**: Main discovery feed for curated complete themes and the Daily Drop.
* **Components**:
  - Top bar with subtle Lambda mark, search icon, and Vanguard pass status pill.
  - Hero Card: *"Obsidian Dawn — Theme of the Day"*, cinematic 4K artwork with silky gradient scrim and floating *"1-Tap Apply"* button.
  - Horizontal filter rail (*Featured, True AMOLED, Minimal, Armor, Creed*).
  - Curated 2-column theme archive (*Ares Dark, Leonidas Dawn, Phalanx Noir, Stoic Creed*).
  - Floating dark glass bottom navigation bar.

---

### SCR-004: Theme Live Preview & 1-Tap Apply
* **ID**: `SCR-004`
* **File**: `UI_Screen/04_Theme_Live_Preview_Apply.png`
* **Purpose**: Realistic interactive smartphone preview allowing the user to inspect the full transformation before applying.
* **Components**:
  - Top bar with back button, theme title, favorite heart, and share icons.
  - Segmented control toggling between *"Home Screen"* and *"Lock Screen"*.
  - Smartphone preview mockup showing wallpaper, Glance clock, and dock icons.
  - Minimal technical specs pills (*99.4% True Black, Native Glance, Zero Battery Waste*).
  - 3 component cards (*Wallpaper, Widget, Icons*).
  - Floating bottom bar with dominant master CTA: *"Apply Complete Theme (1-Tap)"*.

---

### SCR-005: Studio (Widgets & Wallpapers)
* **ID**: `SCR-005`
* **File**: `UI_Screen/05_Studio_Widgets_Wallpapers.png`
* **Purpose**: Modular customization hub for standalone native Glance widgets and 4K AMOLED wallpapers.
* **Components**:
  - Dual pill switcher between *"Widgets"* and *"Wallpapers"*.
  - Native Glance Widget cards:
    1. *Stoic Monolith Clock (2x2)* with direct *"+ Add to Home Screen"* button.
    2. *Shield Battery Gauge (2x2)* with perimeter battery ring.
    3. *War Maxim Ticker (4x1)* with stoic quotes.
  - Curated 4K Canvases teaser grid (*The Iron King, Phalanx Shield*).
  - Floating bottom navigation bar.

---

### SCR-006: Settings & Vanguard Lifetime Pass
* **ID**: `SCR-006`
* **File**: `UI_Screen/06_Settings_Vanguard_Pass.png`
* **Purpose**: Lifetime membership upgrade and system settings.
* **Components**:
  - Vanguard Pass Hero Card: Satin champagne gradient border, gold shield emblem, *"₹199 ($2.99) One-Time • Zero Subscriptions"*, and *"Claim Lifetime Access (₹199)"* button.
  - Grouped preference cards:
    * *Display & Power*: True AMOLED Pure Black toggle, Gyroscope 3D Parallax toggle, Battery Armor Mode toggle.
    * *System & Widgets*: Native Glance 15-min sync, Hoplite Haptics toggle.
    * *Storage & Honor*: Offline Wallpaper Cache manager (24.6 MB used with "Clear" button), Restore Google Play Purchases, The Spartan Manifesto.
  - Floating bottom navigation bar.
