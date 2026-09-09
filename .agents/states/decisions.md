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
