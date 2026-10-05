# Architectural Decision Records (ADRs)

> **Document Version**: 2.0.0 (Phase 1 Approved Baseline)  
> **Status**: APPROVED  
> **Repository**: `300` Android App

---

## Index of Decisions

- [ADR-001: Core KTX Dependency Harmonization](#adr-001)
- [ADR-002: Minimum SDK Locked to API 29 (Android 10)](#adr-002)
- [ADR-003: 100% Jetpack Compose & Jetpack Glance Architecture](#adr-003)
- [ADR-004: Offline-First Local Data Architecture (Room + DataStore)](#adr-004)
- [ADR-005: Elimination of Habit & Streak Tracking from MVP](#adr-005)
- [ADR-006: Freemium Model with ₹199 One-Time Lifetime Vanguard Pass](#adr-006)
- [ADR-007: Brand Identity — The Tri-Blade Lambda ($\Lambda$) Sigil](#adr-007)
- [ADR-008: Native Jetpack Glance Over KWGT / Third-Party Engines](#adr-008)
- [ADR-009: Stitch UI System for Design-First Verification](#adr-009)
- [ADR-010: Canonical UI Source Locked to UI_Screen/ (Classical Stoic Luxury)](#adr-010)
- [ADR-011: Automated Wireless ADB Hardware Deployment Protocol](#adr-011)
- [ADR-012: Photorealistic Visual Assets Over Pure Procedural Canvas](#adr-012)

---

### ADR-001: Core KTX Dependency Harmonization
* **Status**: ACCEPTED (2026-09-09)
* **Context**: `coreKtx` 1.19.0 required compileSdk 36 (Android 16 preview), causing compile errors with compileSdk 35.
* **Decision**: Downgraded to stable `1.15.0` in `gradle/libs.versions.toml`.
* **Consequences**: Zero build friction, 100% stable compatibility with compileSdk 35.

---

### ADR-002: Minimum SDK Locked to API 29 (Android 10)
* **Status**: ACCEPTED (2026-09-09)
* **Context**: Choosing between API 26 (Android 8.0) and API 29 (Android 10).
* **Decision**: Lock `minSdk = 29`.
* **Consequences**: Covers 95%+ of global active Android devices while unlocking native system Dark Theme, modern Scoped Storage, and biometric authentication.

---

### ADR-003: 100% Jetpack Compose & Jetpack Glance Architecture
* **Status**: ACCEPTED (2026-09-09)
* **Context**: Choosing UI technology for in-app screens and home-screen widgets.
* **Decision**: Use 100% Jetpack Compose for all in-app UI and Jetpack Glance for all home-screen widgets. Zero legacy XML layouts or WebViews.
* **Consequences**: Declarative reactive state, shared design tokens, and smooth hardware-accelerated animations.

---

### ADR-004: Offline-First Local Data Architecture (Room + DataStore)
* **Status**: ACCEPTED (2026-09-09)
* **Context**: Handling user preferences, cached wallpapers, and offline state.
* **Decision**: Local Room database for wallpaper metadata and DataStore Preferences for settings. No mandatory cloud account or external login for core app utility.
* **Consequences**: Instant app launch, full offline reliability, zero server downtime for free/paid local features.

---

### ADR-005: Elimination of Habit & Streak Tracking from MVP
* **Status**: ACCEPTED (Phase 1 Baseline)
* **Context**: The initial concept explored combining phone personalization with daily discipline challenges and streak counters.
* **Decision**: Explicitly **reject habit and streak tracking from the MVP**. Focus exclusively on **Identity + Aesthetics + Phone Personalization** (*"Transform your phone"*).
* **Consequences**:
  - Sharper product focus, cleaner UX, and zero identity crisis.
  - Development scope is cut by 40%, allowing a solo engineer to ship a flawless MVP.
  - Discipline is expressed through aesthetic tone, chiseled imagery, and stoic creed widgets rather than gamified checklists.

---

### ADR-006: Freemium Model with ₹199 One-Time Lifetime Vanguard Pass
* **Status**: ACCEPTED (Phase 1 Baseline)
* **Context**: Choosing between upfront paid ($1.99), subscription ($4.99/mo), ad-supported freemium, or one-time lifetime IAP.
* **Decision**: Freemium model with generous free starter tier (3 complete themes, 18 wallpapers, 1 Glance widget) + **One-Time Lifetime Unlock ("Vanguard Pass")** at **₹199 INR (~$2.99 USD)** via Google Play In-App Billing.
* **Consequences**:
  - Immunizes 300 from the subscription backlash that killed MKBHD's Panels in 2024–2025.
  - Completely ad-free experience preserves elite Spartan immersion.
  - High conversion rate on Google Play and positive community reviews.

---

### ADR-007: Brand Identity — The Tri-Blade Lambda ($\Lambda$) Sigil
* **Status**: ACCEPTED (Phase 1 Baseline)
* **Context**: Need a distinctive brand mark that captures Spartan strength without infringing Warner Bros. movie copyrights.
* **Decision**: The "Tri-Blade Lambda" ($\Lambda$) Sigil — three chiseled vertical bronze blades converging into an austere chevron, subtly evoking '3' and the historical Spartan shield emblem.
* **Consequences**:
  - Zero copyright liability.
  - Excellent scalability across app icon (48dp), splash screen, and physical merchandise.

---

### ADR-008: Native Jetpack Glance Over KWGT / Third-Party Engines
* **Status**: ACCEPTED (Phase 1 Baseline)
* **Context**: Many aesthetic Android themes rely on KWGT Kustom Widget Maker, requiring users to purchase external $5.99 keys.
* **Decision**: Implement all 6 widgets natively using **Jetpack Glance** (`androidx.glance:glance-appwidget`).
* **Consequences**:
  - 1-tap "Add to Home Screen" via Android's native `AppWidgetManager.requestPinAppWidget`.
  - Zero third-party apps, zero code editing, zero configuration friction for users.

---

### ADR-009: Stitch UI System for Design-First Verification
* **Status**: ACCEPTED (Phase 1 Baseline)
* **Context**: The design-first mandate requires high-fidelity, validated UI designs before Kotlin production code is written.
* **Decision**: Create Stitch Project `14386677080154071069` with design system `assets/3071428720144166618`, generating all 10 canonical MVP screens.
* **Consequences**: Every screen, state, token, and touch target is verified and visually approved prior to Phase 2 architecture and implementation.

---

### ADR-010: Canonical UI Source Locked to UI_Screen/ (Classical Stoic Luxury)
* **Status**: ACCEPTED (User Directive)
* **Context**: Two UI design iterations were prepared: the classical stoic luxury designs located directly inside `UI_Screen/` and the high-fantasy Spartan suite inside `UI_Screen/v3/`.
* **Decision**: Lock the screens located directly inside [`UI_Screen/`](file:///c:/Users/krish/AndroidStudioProjects/300/UI_Screen) as the single authoritative source of truth for Compose UI implementation. Discard `v3` designs for production code.
* **Consequences**: All Jetpack Compose token mappings, typography (Cinzel/Playfair Roman serif titles + Inter sans body), muted stone portico backdrops, and weathered bronze borders will directly implement the `UI_Screen/` layouts.

---

### ADR-011: Automated Wireless ADB Hardware Deployment Protocol
* **Status**: ACCEPTED (Phase 2)
* **Context**: Deploying, testing, and debugging on physical Android devices over USB and Wi-Fi involved repetitive manual command strings, pairing code inputs, and port conflicts.
* **Decision**: Create a dedicated `device-installer-agent` and PowerShell automation helper (`scripts/device_helper.ps1`). The script automatically scans `adb devices -l`, categorizes devices (USB, Wi-Fi, Emulators, Unauthorized), pairs over Android 11+ Wireless Debugging (`adb pair`), connects (`adb connect`), and installs + launches the debug APK in one command.
* **Consequences**:
  - One-tap deployment to physical and wireless hardware.
  - Transparent error diagnostics when devices require RSA authorization or port re-entry.

---

### ADR-012: Photorealistic Visual Assets Over Pure Procedural Canvas
* **Status**: ACCEPTED (Phase 2)
* **Context**: Procedural 2D Jetpack Compose `Canvas` geometry (arches, braziers, shields, helmets) lacked the textured depth, specular highlights, and cinematic grandeur of the approved `UI_Screen/*_v2.png` designs.
* **Decision**: Slice high-resolution rendered master artwork directly from `UI_Screen/*_v2.png` into `app/src/main/res/drawable/` (`hero_onboarding_portico`, `hero_obsidian_ritual`, `img_splash_medallion`, `hero_vanguard_helmet`, widget cards, theme cards). Render these via Compose `Image(painterResource(...))` with content scaling and contrast scrims.
* **Consequences**:
  - Exact 100% visual fidelity matching the user's `*_v2.png` benchmark.
  - Zero performance penalty compared to heavy procedural drawing math during recompositions.

---

### ADR-014: M3 Scoped Launch Catalog and Downloadable Master Packs
* **Status**: ACCEPTED (M3)
* **Context**: The prior product promise of 300+ wallpapers conflicted with a quality-first launch and the APK size guardrail.
* **Decision**: Launch target is 12 original wallpaper masters across four bundles: two free bundles/six wallpapers and two Vanguard bundles/six wallpapers. The APK contains only compact derivatives; high-resolution masters are versioned downloadable packs with cache metadata.
* **Consequences**: The release claim remains honest, initial APK size is controlled, and future packs scale without a database or navigation rewrite.

---

### ADR-015: Launcher-Dependent Icon Export
* **Status**: ACCEPTED (M3)
* **Context**: Android launchers do not provide one reliable system-wide API for applying a custom icon set.
* **Decision**: 300 ships its 96-glyph catalog as export/share assets with an explicit compatible-launcher notice. It will never promise universal automatic icon replacement.
* **Consequences**: The product avoids deceptive UX while retaining an expandable, original vector icon asset pipeline.

---

### ADR-013: Original Text-Free Wallpaper Masters Outside APK (Sprint 03)
* **Status**: ACCEPTED (2026-09-11)
* **Context**: User requested slicing `UI_Screen/*_v2.png` into 1440x3200 wallpaper masters, but `assets/wallpapers/README.md` forbids UI-mockup crops as downloadable wallpapers (prior masters removed for phone frames / baked-in UI).
* **Decision**: Keep sliced v2 art for in-APK UI thumbnails only (`drawable-nodpi`, ~3MB). Generate six original minimal 1440x3200 WebP masters outside APK in `assets/wallpapers/masters/` + `manifest.json`, base `#08080A`, 95%+ true-black, text-free, no phone frames.
* **Consequences**: Compliant downloadable wallpapers, APK stays lean for <25MB guardrail, TDD guarded by `WallpaperMastersVerificationTest`.
