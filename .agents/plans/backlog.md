# 300 Product & Engineering Backlog

## Epic 1: Core Design System & Theming
- [ ] Implement `Theme300` Compose theme (Obsidian Black, Spartan Crimson, Weathered Bronze).
- [ ] Define typography scales (Cinzel display serif + Inter body).
- [ ] Implement custom components: `SpartanButton`, `ShieldCard`, `CrestHeader`, `BronzeDivider`.
- [ ] Implement haptic feedback utility wrapper.

## Epic 2: Onboarding & Identity Selection
- [ ] Build 3-step onboarding flow: Identity Selection -> Discipline Focus -> Day 1 Personalization.
- [ ] DataStore persistence for onboarding completion flag.
- [ ] Transition smoothly into Home screen with predictive back disabled during onboarding.

## Epic 3: Spartan Wallpaper & Personalization Engine
- [ ] Seed offline asset pack: 15 high-definition dark Spartan wallpapers.
- [ ] Build adaptive 2-column (phone) / 3-4 column (tablet) wallpaper catalog grid.
- [ ] Build immersive full-screen wallpaper preview with mock clock & app grid toggle.
- [ ] Implement `WallpaperManager` integration (Home, Lock, Both) with downsampling.

## Epic 4: Daily Spartan Discipline Challenge & Streak Engine
- [ ] Seed 30 days of stoic Spartan challenges in local Room database.
- [ ] Implement challenge card on Home screen with 1-tap "I Have Conquered" completion.
- [ ] Implement consecutive streak calculation engine with grace periods.
- [ ] Challenge history log and milestone badges (Day 3, 7, 14, 30, 100, 300).

## Epic 5: Glance Home-Screen Widgets
- [ ] Build Minimal Stoic Quote Glance Widget (2x1, 3x2).
- [ ] Build Spartan Shield & Streak Glance Widget (2x2, 4x2).
- [ ] Implement periodic and event-driven widget update triggers via WorkManager.

## Epic 6: Premium Entitlements & Monetization
- [ ] Define In-App Purchase / One-Time Unlock architecture for "The 300 Vanguard Pass".
- [ ] Build Spartan Paywall Sheet with high-contrast benefits breakdown.
- [ ] Offline fallback entitlement state verification.

## Epic 7: Future Expansion (Post-MVP)
- [ ] Live animated video wallpapers with low-overhead SurfaceView rendering.
- [ ] Additional themed content packs (e.g., "The Immortals", "Thermopylae Night", "Ancient Bronze").
- [ ] Soundscapes / Ambient battlefield focus audio.
