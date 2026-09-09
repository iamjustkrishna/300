# 300 — Navigation & User Flow Specification

> **Document Version**: 2.0.0 (Phase 1 Approved Baseline)  
> **Status**: APPROVED  
> **Framework**: Jetpack Navigation Compose (Phase 2 Target)

---

## 1. Top-Level Information Architecture (IA)

```
[ App Launch ]
      │
      ▼
[ SCR-001: Splash Screen ]
      │
      ├────────────────────────────┐
      ▼ (First Launch)             ▼ (Returning User)
[ SCR-002: Onboarding ]            │
      │                            │
      └────────────┬───────────────┘
                   ▼
     ┌─────────────────────────────┐
     │   Main Bottom Navigation    │
     │      (5 Root Destinations)  │
     └─────────────┬───────────────┘
                   │
    ┌──────────────┼──────────────┬──────────────┬──────────────┐
    ▼              ▼              ▼              ▼              ▼
[ Tab 1 ]      [ Tab 2 ]      [ Tab 3 ]      [ Tab 4 ]      [ Tab 5 ]
Themes         Wallpapers     Widgets        Icons          Settings
(SCR-003)      (SCR-005)      (SCR-007)      (SCR-009)      (SCR-011)
    │              │              │              │              │
    ▼              ▼              │              │              │
SCR-004        SCR-006            │              │              │
Theme Detail   Wallpaper Preview  │              │              │
    │              │              │              │              │
    ▼              ▼              │              │              │
SCR-010        System Apply       │              │              │
Apply Modal    Dialog             │              │              │
    │                             │              │              │
    └─────────────────────────────┴──────────────┴──────────────┤
                                                                ▼
                                                            SCR-008
                                                            Vanguard Paywall
```

---

## 2. Navigation Routes & Deep Link Matrix

| Route URI | Canonical Screen | Description | Deep Link Pattern |
| :--- | :--- | :--- | :--- |
| `threehundred://splash` | `SCR-001` | Splash brand entry | `android.intent.action.MAIN` |
| `threehundred://onboarding` | `SCR-002` | First-time transformation reveal | `threehundred://welcome` |
| `threehundred://home` | `SCR-003` | The Phalanx Home Dashboard | `threehundred://themes` |
| `threehundred://theme/{themeId}` | `SCR-004` | Theme Detail & Live Preview | `threehundred://theme/{themeId}` |
| `threehundred://wallpapers` | `SCR-005` | Wallpaper Vault Gallery | `threehundred://wallpapers` |
| `threehundred://wallpaper/{wallpaperId}` | `SCR-006` | Fullscreen Wallpaper Preview | `threehundred://wallpaper/{wallpaperId}` |
| `threehundred://widgets` | `SCR-007` | Spartan Widgets Forge | `threehundred://widgets` |
| `threehundred://vanguard` | `SCR-008` | Vanguard Pass Paywall | `threehundred://upgrade` |
| `threehundred://icons` | `SCR-009` | Icon Pack Forge & Styler | `threehundred://icons` |
| `threehundred://settings` | `SCR-011` | Settings & Vault Management | `threehundred://settings` |

---

## 3. Back Stack & State Preservation Rules

1. **Root Tabs (Bottom Navigation)**:
   - Tapping any of the 5 bottom tabs pops to the root destination of that tab with `saveState = true` and `restoreState = true`.
   - Pressing system Back on any secondary tab (Wallpapers, Widgets, Icons, Settings) routes to **The Phalanx (Home Dashboard)** before exiting the application.
2. **Detail Screens (`SCR-004`, `SCR-006`)**:
   - Pushed onto the current tab's back stack. System Back pops back to the originating gallery screen without re-triggering network or database reload.
3. **Paywall (`SCR-008`) & Modals (`SCR-010`)**:
   - Displayed as slide-up overlay sheets. Pressing Back or tapping the close button dismisses the sheet and restores the underlying screen state with zero loss of scroll position.
