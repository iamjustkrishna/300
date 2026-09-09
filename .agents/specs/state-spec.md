# 300 — Screen State Specification (8 Canonical UI States)

> **Document Version**: 2.0.0 (Phase 1 Validated Baseline)  
> **Status**: APPROVED  
> **Principle**: No screen may be implemented in Phase 2 without handling all 8 canonical states.

---

## 1. The 8 Canonical UI States

Every primary screen in 300 (`SCR-003`, `SCR-004`, `SCR-005`, `SCR-006`, `SCR-007`, `SCR-009`) implements the following 8 canonical states:

```
                  ┌───────────────┐
                  │ 1. First-Time │
                  └───────┬───────┘
                          │ (Complete Onboarding)
                          ▼
                  ┌───────────────┐
            ┌────►│   2. Normal   │◄────┐
            │     └───────┬───────┘     │
 (Retry OK) │             │ (Fetch)     │ (Resolved)
            │             ▼             │
     ┌──────┴──────┐┌───────────┐┌──────┴──────┐
     │ 5. Offline  ││3. Loading ││  6. Error   │
     └─────────────┘└─────┬─────┘└─────────────┘
                          │ (Zero Data)
                          ▼
                   ┌─────────────┐
                   │  4. Empty   │
                   └─────────────┘

       ┌──────────────────────────┬──────────────────────────┐
       │ 7. Vanguard Pass Locked  │ 8. Applying In-Progress  │
       │    vs. Unlocked State    │    vs. Completed State   │
       └──────────────────────────┴──────────────────────────┘
```

---

## 2. State Definitions & Behavioral Rules

### State 1: First-Time / New User
* **Visuals**: Displays the Spartan Reveal onboarding flow (`SCR-002`) with interactive before/after transformation slider.
* **Data State**: DataStore preference `has_completed_onboarding = false`.
* **Action**: Primary CTA "START TRANSFORMATION" writes `true` to DataStore and seamlessly routes to Home (`SCR-003`).

### State 2: Normal / Content Loaded
* **Visuals**: Full high-contrast dark obsidian canvas with loaded 4K thumbnails, category chips, live countdown timer on Daily Drop, and active bottom navigation.
* **Performance**: Thumbnails loaded via Coil with hardware bitmap decoding and disk caching.

### State 3: Loading / Synchronizing
* **Visuals**: Zero generic circular spinners. Screens use a **Chiseled Obsidian Shimmer** (subtle bronze/iron gradient sweep across card skeletons) that matches card dimensions exactly.
* **Duration**: Bounded by a 2.5s network timeout before falling back to local Room cache.

### State 4: Empty State
* **Visuals**: Displayed when category filters or search queries return zero results.
* **Artwork**: Centered weathered bronze Spartan helmet watermark with text: *"NO WEAPONS FOUND IN THIS ARSENAL"*.
* **Action**: Single outline button: *"RESET ARSENAL FILTERS"*.

### State 5: Offline Mode
* **Visuals**: Persistent slim top notification strip: *"OFFLINE ARSENAL • ACCESSING LOCALLY STORED CANVASES"*.
* **Data State**: Network disconnected; queries local Room cache for all previously downloaded 4K wallpapers and cached theme blueprints.
* **Action**: User can still set cached wallpapers and pin Glance widgets. A *"RETRY BATTLE LINK"* chip triggers network re-check.

### State 6: Error / Failure
* **Visuals**: Chiseled obsidian alert card with crimson border (`#8A1C14`).
* **Message**: Explicit explanation of what failed (e.g., *"GOOGLE PLAY BILLING TIMEOUT"* or *"STORAGE PERMISSION REQUIRED"*).
* **Action**: Primary *"RETRY OPERATION"* button and secondary *"RETURN TO PHALANX"*.

### State 7: Vanguard Locked vs. Unlocked
* **Locked Visuals**: Golden padlock badge over card corner, blurred 4K preview watermark, and button reading *"UNLOCK VANGUARD PASS"*.
* **Unlocked Visuals**: Padlock replaced by glowing bronze star badge; button reads *"APPLY THEME"* or *"DOWNLOAD 4K RAW"*.

### State 8: Applying In-Progress vs. Success
* **In-Progress**: Bottom sheet modal (`SCR-010`) displaying active checklist steps with indeterminate bronze progress bar.
* **Success**: Checklist items turn emerald-bronze with checkmarks, haptic confirmation fires, and master CTA *"VIEW MY NEW HOME SCREEN"* appears.
