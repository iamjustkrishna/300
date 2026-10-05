# 300 Master Execution Plan

---

## Phase 0: Repository & Tooling Setup
- **Objective**: Establish build health, dependency catalogs, Android SDK targets, and agent persistent memory.
- **Inputs**: Workspace files, Gradle wrapper, Android Studio project skeleton.
- **Outputs**: Verified buildable project, resolved dependency versions, `.agents/` structure.
- **Acceptance Criteria**: `./gradlew assembleDebug` and `./gradlew testDebugUnitTest` exit with code 0.
- **Dependencies**: None.
- **Tests**: Gradle build invocation, daemon health verification.
- **Completion Status**: **COMPLETED (Phase 0)**.

---

## Phase 1: Product Definition
- **Objective**: Formulate the product specification, user personas, MVP boundary, and core emotional engagement loops.
- **Inputs**: Master prompt vision, Spartan ethos, discipline & personalization requirements.
- **Outputs**: `.agents/specs/product-spec.md` with explicit MVP scope, free vs. premium tiers, and retention loops.
- **Acceptance Criteria**: Clear definition of what is in vs. out of MVP; signed off by `product-agent.md`.
- **Dependencies**: Phase 0.
- **Tests**: Scope validation against MVP criteria.
- **Completion Status**: **COMPLETED (Phase 1)**.

---

## Phase 2: Competitive & User Research
- **Objective**: Analyze competitor shortcomings on Google Play Store (intrusive ads, broken widgets, cluttered UI) to secure 300's market edge.
- **Inputs**: Play Store market review data, habit tracking and personalization competitor benchmarks.
- **Outputs**: Competitive analysis section in product spec, pricing and differentiation strategy.
- **Acceptance Criteria**: Identification of 3 core competitor failure modes and 3 distinct 300 differentiators.
- **Dependencies**: Phase 1.
- **Tests**: Value proposition verification.
- **Completion Status**: **COMPLETED (Phase 2)**.

---

## Phase 3: Information Architecture & Navigation
- **Objective**: Establish complete navigation graph, routes, argument passing, and back stack behaviors.
- **Inputs**: Product specification and user flows.
- **Outputs**: `.agents/specs/navigation-spec.md`.
- **Acceptance Criteria**: All screen transitions, modal sheets, and predictive back gestures mapped with zero trapped states.
- **Dependencies**: Phase 1, Phase 2.
- **Tests**: Navigation graph contract validation.
- **Completion Status**: **COMPLETED (Phase 3)**.

---

## Phase 4: Design System
- **Objective**: Create the Spartan brutalist design system (tokens, typography, colors, surfaces, haptics, motion).
- **Inputs**: Cinematic dark Spartan aesthetic guidelines.
- **Outputs**: `.agents/specs/design-system.md` with complete token tables.
- **Acceptance Criteria**: WCAG AA contrast verified, 8dp grid, no generic neon/glassmorphism effects.
- **Dependencies**: Phase 3.
- **Tests**: Contrast ratio checks, typography scale audits.
- **Completion Status**: **COMPLETED (Phase 4)**.

---

## Phase 5: Complete Screen Design & Inventory
- **Objective**: Explicitly design every application screen across all 8 canonical states.
- **Inputs**: Navigation spec, Design system tokens.
- **Outputs**: `.agents/specs/screen-spec.md` detailing every screen, hierarchy, components, and states.
- **Acceptance Criteria**: Every screen defined for first launch, normal, loading, offline, empty, error, returning user, and premium state.
- **Dependencies**: Phase 4.
- **Tests**: State coverage inspection.
- **Completion Status**: **COMPLETED (Phase 5)**.

---

## Phase 6: System Architecture & Data Contracts
- **Objective**: Define state models, Room schema, DataStore preferences, repository interfaces, and Glance widget contracts.
- **Inputs**: Screen specifications, Product spec.
- **Outputs**: `.agents/specs/state-spec.md`, `.agents/states/architecture.md`.
- **Acceptance Criteria**: UDF/MVI state models, immutable data classes, and Room entities documented.
- **Dependencies**: Phase 5.
- **Tests**: Contract and schema reviews.
- **Completion Status**: **COMPLETED (Phase 6)**.

---

## Phase 7: TDD Foundation & Harness
- **Objective**: Setup Compose UI test fixtures, Coroutine test dispatchers, fake repositories, and automated test runners.
- **Inputs**: Architecture specifications, test pyramid requirements.
- **Outputs**: Test dependencies configured in `libs.versions.toml`, Roborazzi 1.40.1, Robolectric 4.14.1, `UiScreenCaptureTest.kt`.
- **Acceptance Criteria**: Red-Green-Refactor test cycle verified on JVM.
- **Dependencies**: Phase 6.
- **Tests**: Automated unit and headless UI tests passing cleanly (`testDebugUnitTest`).
- **Completion Status**: **COMPLETED (Phase 7)**.

---

## Phase 8: Implementation
- **Objective**: Implement production Compose UI, ViewModels, UseCases, Room DAOs, DataStore, and Glance AppWidgets.
- **Inputs**: Approved screen designs (`UI_Screen/*_v2.png`), state models, and green tests.
- **Outputs**: Complete production application code across Room, DataStore, Glance, and 6 Compose screens with photorealistic sliced artwork drawables.
- **Acceptance Criteria**: All acceptance criteria satisfied, zero compiler warnings, zero lint errors, 1:1 photorealistic fidelity.
- **Dependencies**: Phase 7.
- **Tests**: Unit tests, integration tests, Roborazzi screenshots, assembleDebug passing.
- **Completion Status**: **COMPLETED (Phase 8)**.

---

## Phase 9: Device Validation & Hardware Deployment
- **Objective**: Validate responsive layouts across form factors and enable automated physical/wireless hardware deployment.
- **Inputs**: Built application, device configuration matrix, ADB tools.
- **Outputs**: `.agents/states/devices.md`, `device-installer-agent`, `scripts/device_helper.ps1`, live hardware verification on Realme RMX3171.
- **Acceptance Criteria**: Zero crashes, wireless pairing (`adb pair`), connection (`adb connect`), and streamed APK install verified on real hardware.
- **Dependencies**: Phase 8.
- **Tests**: Headless Robolectric Native Graphics captures + live physical phone deployment.
- **Completion Status**: **COMPLETED (Phase 9)**.

---

## Phase 10: Performance & Accessibility Audit
- **Objective**: Guarantee <500ms cold boot, 60/120fps scrolling, low RAM usage, and 100% TalkBack/font scaling support.
- **Inputs**: Implemented application.
- **Outputs**: Performance and accessibility audit reports.
- **Acceptance Criteria**: 48dp touch targets, TalkBack readable, 200% font scaling passes, zero memory leaks.
- **Dependencies**: Phase 9.
- **Tests**: Compose recomposition audits, TalkBack semantic checks.
- **Completion Status**: Pending Phase 9.

---

## Phase 11: Monetization Integration
- **Objective**: Implement Google Play Billing or offline unlock architecture for premium Spartan content packs.
- **Inputs**: Monetization specifications, paywall screen designs.
- **Outputs**: Entitlement repository, purchase state handling, paywall UI.
- **Acceptance Criteria**: Free tier fully functional; premium tier unlocks gracefully; restored purchases handled.
- **Dependencies**: Phase 10.
- **Tests**: Purchase flow unit & integration tests with fake billing clients.
- **Completion Status**: Pending Phase 10.

---

## Phase 12: Release Preparation
- **Objective**: Configure ProGuard/R8 minification, generate signed release AAB, and prepare Google Play Store assets.
- **Inputs**: Production-ready codebase, App store visual assets.
- **Outputs**: Release bundle, mapping.txt, Play Store listing metadata.
- **Acceptance Criteria**: Clean R8 obfuscation, bundle size < 25MB, all Play Store checklists signed off.
- **Dependencies**: Phase 11.
- **Tests**: Release build verification on physical/emulated devices.
- **Completion Status**: Pending Phase 11.

---

## Phase 13: Production Validation & Retrospective
- **Objective**: Final 360-degree audit, user feedback verification, and post-launch roadmap planning.
- **Inputs**: Released v1.0.0 build.
- **Outputs**: Post-launch retrospective, backlog reprioritization.
- **Acceptance Criteria**: Zero critical crashes; user review sentiment positive; discipline loop validated.
- **Dependencies**: Phase 12.
- **Tests**: Production crash reporting & analytics pipeline.
- **Completion Status**: Pending Phase 12.
