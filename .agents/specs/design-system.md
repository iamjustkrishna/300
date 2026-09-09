# 300 — Design System Specification: "Luxury Modern Minimalist"

> **Document Version**: 3.0.0 (Luxury Modern Minimalist Baseline)  
> **Status**: APPROVED  
> **Stitch Design System Asset ID**: `assets/1647928783357377156`  
> **Aesthetic Philosophy**: Nothing OS / Linear / Bang & Olufsen modern luxury minimalism. Smooth champagne gradients, deep smoky obsidian surfaces, spacious breathing room, zero clutter.

---

## 1. Color Palette Tokens

```
┌─────────────────────────────────────────────────────────────┐
│                      CANVAS & SURFACES                      │
├─────────────────────────┬───────────────────────────────────┤
│ Canvas Deep Void Black  │ #0A0A0C (99.4% OLED True Black)   │
│ Surface Smoky Obsidian  │ #141418 (Elevated cards & sheets) │
│ Surface Dark Glass      │ rgba(20, 20, 24, 0.75)            │
│ Surface Muted Iron      │ #1F1F26                           │
│ Subtle Hairline Border  │ rgba(255, 255, 255, 0.06)         │
└─────────────────────────┴───────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│           PRIMARY ACCENT: SATIN CHAMPAGNE BRONZE            │
├─────────────────────────┬───────────────────────────────────┤
│ Champagne Primary       │ #D8B26E (Warm satin gold/bronze)  │
│ Champagne Light Gleam   │ #F0CF8E (Specular light edge)     │
│ Champagne Deep Tone     │ #B88E4A (Gradient base)           │
│ Champagne Soft Halo     │ rgba(216, 178, 110, 0.08)         │
│ Champagne Border Hair   │ rgba(216, 178, 110, 0.35)         │
└─────────────────────────┴───────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                 SECONDARY ACCENT: JEWEL CRIMSON             │
├─────────────────────────┬───────────────────────────────────┤
│ Crimson Muted Jewel     │ #9E2A2B                           │
│ Crimson Dot / Badge     │ rgba(158, 42, 43, 0.20)           │
└─────────────────────────┴───────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                     TYPOGRAPHY & CONTRAST                   │
├─────────────────────────┬───────────────────────────────────┤
│ Text High Contrast Bone │ #F3F3F6 (100% white)              │
│ Text Warm Silver Muted  │ #8E8E98 (65% opacity)             │
│ Text Charcoal Dimmed    │ #585862 (40% opacity)             │
│ Text Accent Gold        │ #D8B26E                           │
└─────────────────────────┴───────────────────────────────────┘
```

---

## 2. Typography Scale

* **Primary Font Family**: *Plus Jakarta Sans* & *Inter*
* **Display Titles (`display-lg`)**: 28sp / 34sp, SemiBold (600), `letter-spacing: -0.02em`
* **Headlines (`headline-md`)**: 20sp / 26sp, Medium (500), `letter-spacing: -0.01em`
* **Body (`body-md`)**: 14sp / 20sp, Regular (400), `letter-spacing: 0.0em`, color `#8E8E98`
* **Labels / Button Text (`label-md`)**: 13sp / 16sp, SemiBold (600), `letter-spacing: +0.02em`
* **Micro-badges (`label-sm`)**: 10sp / 14sp, Medium (500), `letter-spacing: +0.04em`

---

## 3. Geometry & Roundness Scale

* **Cards & Containers**: `16dp` to `20dp` rounded corners (`ROUND_TWELVE`).
* **Buttons**: `14dp` rounded corners with soft gradient surfaces.
* **Pill Switchers & Badges**: `999dp` (`ROUND_FULL`).
* **Borders**: Strictly ultra-thin `1dp` or `0.5dp` hairlines (`rgba(255, 255, 255, 0.06)` or `rgba(216, 178, 110, 0.30)`).
