# 300 — Product Specification (Phase 1 Baseline)

> **Document Version**: 2.0.0 (Phase 1 Validated Baseline)  
> **Status**: APPROVED  
> **Package**: `space.iamjustkrishna.threehundred`  
> **Target Android Versions**: Android 10+ (API 29 to API 35)

---

## 1. Product Vision & Core Promise

**300** is an elite Android personalization suite engineered with a cinematic, brutalist, minimal, dark Spartan aesthetic.

### Core Promise
> **"Transform your phone into a cohesive cinematic warrior environment."**

The product transforms a cluttered, generic smartphone into an austere instrument of strength, focus, and discipline in **one tap**. It is not a wallpaper downloader; it is a **complete identity and aesthetic overhaul**.

---

## 2. Product Hypothesis & Scope Evolution

### 2.1 The Pivot: Aesthetic Transformation Over Habit Tracking
* **Original Hypothesis**: An app combining wallpapers, widgets, daily discipline challenges, and streak tracking.
* **Validated Phase 1 Decision (ADR-005)**: Habit tracking and streak counters are **strictly excluded from the MVP**. Empirical research demonstrated that users seeking home-screen personalization resent productivity guilt trips and multi-purpose apps with identity crises.
* **Refocused Value**: Pure **Identity + Aesthetics + Phone Personalization** executed with uncompromising craft.

---

## 3. Target Audience Rankings

1. **Primary: Masculine Aesthetic Seekers & Fitness/Gym Community (Age 18–34)**
   - Prioritize stoicism, dark minimalism, and physical/mental discipline.
   - Lock screen is viewed 80–120 times daily; serves as a psychological focus cue.
   - High willingness to pay for personal identity and aesthetic prestige.
2. **Secondary: Android Customization Power Users (r/androidthemes, Nova/Smart Launcher)**
   - Fatigued by configuring 4 separate apps (KWGT + launcher + icon packs + Backdrops).
   - High willingness to buy one-time lifetime pro utilities.
3. **Tertiary: Dark/Brutalist Gamers & Film Admirers (Elden Ring, God of War, 300)**
   - Seeking pure OLED black themes without neon gamer clutter.

---

## 4. Monetization Strategy

* **Model**: Freemium with **One-Time Lifetime Unlock ("Vanguard Pass")**.
* **Launch Price**: **₹199 INR (~$2.99 USD)** (Anchor price: ₹399 / $4.99).
* **Payment Mechanism**: Google Play In-App Billing (Non-consumable).
* **Why Subscriptions Were Rejected**: The collapse and shutdown of MKBHD's "Panels" wallpaper app ($50/year subscription) on December 31, 2025 proved that subscriptions provoke severe backlash for visual digital assets. A fair, one-time lifetime price produces high conversion, 5-star ratings, and zero refund friction.

---

## 5. MVP Feature Specifications

### 5.1 Curated 4K Wallpaper Engine
- Minimum 95% True Black (`#080809` or `#000000`) on OLED panels to eliminate sub-pixel power draw.
- Categories: *True AMOLED (95%+), Warriors, Chiseled Armor, Stoic Creed*.
- Fullscreen interactive preview with UI overlay toggles (simulated clock and app dock) to test readability before applying.
- 1-Tap apply to Home Screen, Lock Screen, or Both via Android `WallpaperManager`.
- Full 4K uncompressed raw download to gallery.

### 5.2 Complete Phalanx Theme Bundles
- Pre-packaged aesthetic sets containing:
  1. 4K Wallpaper.
  2. Matching Jetpack Glance Home-Screen Widget.
  3. Weathered Bronze Icon Glyphs.
  4. Spartan Soundpack & System Accent.
- 1-Tap Complete Apply flow that executes the transformation in under 2 seconds.

### 5.3 Native Jetpack Glance Home-Screen Widgets
- 100% native Android widgets built with `androidx.glance:glance-appwidget`. Zero KWGT required.
- Direct 1-tap "Add to Home Screen" pinning via Android `AppWidgetManager.requestPinAppWidget`.
- 4 Core Widget Presets:
  1. **The Stoic Monolith Clock (2x2)**: Chiseled Roman numerals, Spartan date, battery arc gauge.
  2. **The Hoplite Shield Gauge (2x2)**: Circular weathered bronze shield with dynamic battery perimeter meter.
  3. **War Creed & Maxim Ticker (4x1)**: Rotating austere quotes ("Win in silence", "Return with your shield or on it").
  4. **Thermopylae War Calendar (4x2)**: 7-day tactical battle calendar with crimson wax seal.

### 5.4 Weathered Bronze Icon Pack & Quick Styler
- 400+ vector glyphs styled in antique weathered bronze, obsidian slate, and crimson accents.
- Direct compatibility with Nova, Smart, Niagara, Samsung One UI, and stock Pixel Launcher (via shortcuts).

### 5.5 Vanguard Pass (Paywall & Lifetime Membership)
- Unlocks 15+ Vault Themes, 300+ 4K Wallpapers, all 6 Glance widgets, all 400+ icons, and the Hoplite soundpack.
- Lifetime purchase verification and "Restore Purchases" flow.

### 5.6 Settings, Battery Optimization & Vault Storage
- AMOLED battery-saving armor mode.
- 3D Gyro Parallax tilt toggle with auto-pause below 20% battery.
- Local offline cache manager (cleans temporary wallpaper downloads).

---

## 6. Deliberately Rejected Features (Explicit Exclusions)

1. **Habit & Streak Trackers**: Strictly out of scope.
2. **Subscriptions**: Strictly out of scope.
3. **Banner Ads & Video Interstitials**: Strictly out of scope.
4. **Mandatory User Accounts / Social Logins**: Strictly out of scope.
5. **AI Image Generation in MVP**: Post-MVP exploration only ("Turn Yourself Into Sparta").
