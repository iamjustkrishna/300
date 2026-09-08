# Architecture Decision Records (ADR)

## ADR-001: Resolve Version Catalog Incompatibility for core-ktx
- **Status**: Accepted
- **Date**: 2026-09-09
- **Context**: The template `libs.versions.toml` specified `coreKtx = "1.19.0"`, which triggered a build failure under AGP 8.10.0 and compileSdk 35 (requiring AGP 9.1.0 and API 37).
- **Decision**: Downgraded `coreKtx` to `1.15.0`.
- **Consequences**: Enables immediate clean build under AGP 8.10.0 and compileSdk 35 with rock-solid stability.

## ADR-002: Minimum Android Target SDK 29 (Android 10)
- **Status**: Accepted
- **Date**: 2026-09-09
- **Context**: Project requires modern Android features (dark theme, Scoped Storage, gesture navigation) while supporting broad user base.
- **Decision**: MinSdk set to 29; TargetSdk set to 35.
- **Consequences**: Drops pre-Android 10 legacy devices, drastically simplifying storage, theme, and wallpaper code while ensuring 95%+ device reach.

## ADR-003: Pure Jetpack Compose with Jetpack Glance for AppWidgets
- **Status**: Accepted
- **Date**: 2026-09-09
- **Context**: Personalization requires home-screen widgets and cohesive UI styling.
- **Decision**: Use 100% Jetpack Compose for in-app screens and Jetpack Glance for home-screen widgets.
- **Consequences**: Single declarative UI paradigm across both in-app and home screen; eliminates XML layout fragmentation.

## ADR-004: Offline-First Architecture with Local Preloaded Content Pack
- **Status**: Accepted
- **Date**: 2026-09-09
- **Context**: Spartan discipline requires zero friction, zero sign-in walls, and instant offline availability.
- **Decision**: Bundle initial Spartan wallpaper pack and first 30 days of discipline challenges directly in app assets/Room database, functioning 100% offline.
- **Consequences**: Instant first-session delight; no server dependency for MVP.
