# 300 — Product Strategy, Target Users & MVP Scope

> **Document Version**: 2.0.0 (Phase 1 Approved Baseline)  
> **Status**: APPROVED  
> **Core Promise**: *"Transform your phone into a cohesive cinematic warrior environment."*

---

## 1. Product Positioning & Value Proposition

**300** is a premium Android personalization suite engineered with a cinematic, brutalist, minimal, dark Spartan aesthetic. 

Unlike fragmented customization tools (KWGT, standalone icon packs, generic wallpaper downloaders) or bloated ad-infested "theme" apps, 300 delivers **instant phone transformation in 1 tap**:
* Curated **4K True AMOLED** obsidian canvases that save up to 68% display power.
* **100% Native Jetpack Glance widgets** (chiseled Roman clocks, hoplite shield battery gauges, stoic creed tickers) that pin directly to any Android launcher without third-party tools.
* Handcrafted **weathered bronze & obsidian vector icon glyphs**.
* A unified, elite Spartan identity designed for focus, strength, and visual austerity.

---

## 2. Target Audience Analysis & Ranking

### Rank 1: Primary Target — Masculine Aesthetic Seekers & Fitness/Gym Community (Age 18–34)
* **Demographics**: Young men, college students, lifters, combat sports enthusiasts, self-improvement seekers.
* **Psychographics**: Values discipline, strength, stoicism, focus, and minimalism. Looks at their phone lock screen 80–120 times per day.
* **Pain Point**: Their phone looks childish, cluttered, or identical to everyone else's. They desire a lock screen and home screen that feels like a battle shield reminding them to stay focused.
* **Willingness to Pay**: High for status, identity, and personal aesthetic (spends on gym memberships, supplements, clean apparel).
* **Social Virality**: Extremely high ("Rate my home-screen setup" on TikTok, Instagram Reels, and YouTube Shorts).
* **Fit with 300**: **10/10**.

### Rank 2: Secondary Target — Android Customization Power Users & Launcher Enthusiasts
* **Demographics**: Tech-savvy Android users, subscribers to r/androidthemes, r/Android, users of Nova/Smart/Niagara launchers.
* **Pain Point**: Exhausted by the 6-step multi-app setup process and disillusioned by AI-generated wallpaper dumps on Backdrops.
* **Willingness to Pay**: High for clean, one-time lifetime utilities (standard KWGT Pro purchasers).
* **Fit with 300**: **9/10**.

### Rank 3: Tertiary Target — Dark Mode / Brutalist Gamers & Film Aesthetic Admirers
* **Demographics**: Fans of cinematic epics (*300*, *Gladiator*), dark souls-like games (*Elden Ring*, *God of War*).
* **Pain Point**: Want true pitch-black AMOLED visual themes without neon RGB gamer clutter.
* **Willingness to Pay**: Moderate-High.
* **Fit with 300**: **8.5/10**.

---

## 3. MVP Feature Classification (MoSCoW Matrix)

### MUST HAVE (Core MVP — In Scope)
1. **Curated 4K Wallpaper Vault**:
   - High-resolution, uncompressed static AMOLED wallpapers (minimum 95% true black pixels).
   - Categories: *True AMOLED, Warriors, Chiseled Armor, Stoic Creed*.
   - *Rationale*: Core visual foundation of phone transformation.
2. **Complete Phalanx Themes (Bundles)**:
   - Pre-matched combinations of Wallpaper + Widget Preset + Icon Color + System Accent.
   - 1-Tap "Apply Complete Theme" flow.
   - *Rationale*: Solves the multi-app fragmentation pain point.
3. **Native Jetpack Glance Home-Screen Widgets**:
   - 4 standalone native widgets: *Stoic Monolith Clock (2x2), Hoplite Shield Gauge (2x2), War Creed Ticker (4x1), Thermopylae Calendar (4x2)*.
   - 1-tap "Add to Home Screen" via Android's native `AppWidgetManager`.
   - *Rationale*: Zero third-party apps (KWGT) required.
4. **Interactive Device Preview Mockup**:
   - Realistic phone mockup showing home screen and lock screen overlays (clock, icons, wallpaper) *before* applying.
   - *Rationale*: Eliminates blind guessing and bad cropping complaints.
5. **Weathered Bronze & Obsidian Icon Pack**:
   - Vector-based icon glyphs for 48 essential system and popular applications with 1-tap launcher apply and shortcut builder.
   - *Rationale*: Completes the visual transformation.
6. **Vanguard Pass (One-Time In-App Purchase)**:
   - Google Play Billing integration unlocking lifetime access for ₹199 (~$2.99).
   - "Restore Purchases" functionality.
   - *Rationale*: Commercial validation without subscription toxicity.

### SHOULD HAVE (Phase 1.5 / Fast Follow)
1. **3D Gyroscope Parallax Wallpaper Engine**: Real-time tilt depth effect with auto-pause battery-saving mode below 20% battery.
2. **Hoplite Acoustic Engine**: Custom high-fidelity Spartan UI audio feedback (shield equip clangs, blade lock sounds).
3. **Launcher Assistant Wizard**: Automated shortcut creation for Pixel Launcher and Samsung One UI.

### LATER (Post-MVP / Future Expansions)
1. **"Turn Yourself Into Sparta" (AI Photo Transformation)**:
   - Upload a selfie/portrait and generate a personalized Spartan warrior avatar/wallpaper in original cinematic style.
   - Sold as a premium standalone credit pack ($0.99 per 5 portraits).
2. **Dynamic Live Charging Animation**:
   - Spartan ember forge pulse animation triggered when device is plugged into fast charging.
3. **Community Spartan Setups**:
   - User-shared widget/wallpaper screenshots with 1-tap clone links.

### REJECTED (Deliberately Out of Scope)
1. **Habit & Streak Tracking**:
   - *Rejected Rationale*: Dilutes focus from aesthetic transformation; users dislike apps with identity crises (part wallpaper, part to-do list).
2. **Subscriptions / Monthly Fees**:
   - *Rejected Rationale*: Proved commercially fatal by Panels in 2024–2025.
3. **Banner Ads & Interstitial Ads**:
   - *Rejected Rationale*: Incompatible with brutalist, premium, disciplined Spartan immersion.
4. **Cloud User Accounts / Forced Login**:
   - *Rejected Rationale*: Creates onboarding friction; all state and purchases are local and tied to Google Play.

---

## 4. The Core User Journey (First 60 Seconds)

```
[ Install & Launch ]
       │
       ▼
[ SCR-001: Splash & Brand Genesis ] ── (0.8s: Shimmering bronze Lambda sigil)
       │
       ▼
[ SCR-002: The Spartan Reveal (Onboarding) ] ── (Interactive before/after slider: Stock vs Spartan)
       │
       ▼
[ SCR-003: The Phalanx (Home Dashboard) ] ── (Featured Daily Drop: "THERMOPYLAE OBSIDIAN")
       │
       ▼
[ SCR-004: Theme Detail & Live Preview ] ── (Inspect phone mockup: Wallpaper + Glance Clock + Glyphs)
       │
       ▼
[ 1-Tap "Apply Complete Theme" ]
       │
       ▼
[ SCR-009: 1-Tap Apply Success Sheet ] ── (1.4s Forge Sequence: Wallpaper set, Widget pinned, Icons ready)
       │
       ▼
[ User Returns to Android Launcher ] ── ("My phone feels like a different device now.")
       │
       ▼
[ SCR-007: Vanguard Pass Paywall ] ── (Triggered when browsing locked vault items: ₹199 Lifetime Unlock)
```
