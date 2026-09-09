# 300 — Design System Specification: "Classical Stoic Luxury"

> **Document Version**: 4.0.0 (Classical Stoic Luxury Baseline)  
> **Status**: APPROVED  
> **Stitch Design System Asset ID**: `assets/13394188174485581719` ("300 Classical Stoic Luxury")  
> **Aesthetic Philosophy**: Classical Stoic Luxury meets modern minimalism (Nothing OS, Linear, Bang & Olufsen). Tactile basalt stone, antique brushed satin gold, restrained spartan crimson accents, chiseled architectural compositions, and museum-grade typography (*EB Garamond* + *Plus Jakarta Sans*).

---

## 1. Color Palette Tokens

```
┌─────────────────────────────────────────────────────────────┐
│                      CANVAS & SURFACES                      │
├─────────────────────────┬───────────────────────────────────┤
│ Canvas Deep Basalt Void │ #08080A (100% OLED True Black)    │
│ Canvas Obsidian Dark    │ #0B0B0C (Secondary canvas depth)  │
│ Surface Basalt Stone    │ #121216 (Sculpted cards & sheets) │
│ Surface Frosted Basalt  │ rgba(18, 18, 22, 0.85)            │
│ Surface Muted Iron      │ #1A1A22                           │
│ Subtle Hairline Border  │ rgba(197, 168, 105, 0.12)         │
└─────────────────────────┴───────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│            PRIMARY ACCENT: BRUSHED SATIN GOLD               │
├─────────────────────────┬───────────────────────────────────┤
│ Satin Gold Primary      │ #C5A869 (Warm antique gold)       │
│ Satin Gold Light Gleam  │ #E2CA8F (Specular light edge)     │
│ Satin Gold Deep Bronze  │ #9E8045 (Metallic gradient base)  │
│ Satin Gold Ambient Glow │ rgba(197, 168, 105, 0.10)         │
│ Satin Gold Hairline Rim │ rgba(197, 168, 105, 0.35)         │
└─────────────────────────┴───────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                 SECONDARY ACCENT: SPARTAN OXBLOOD           │
├─────────────────────────┬───────────────────────────────────┤
│ Spartan Oxblood Crimson │ #8A1C14 (Deep ceremonial red)     │
│ Crimson Power Arc       │ #A62820 (Gauge & active accents)  │
│ Crimson Muted Badge     │ rgba(138, 28, 20, 0.20)           │
└─────────────────────────┴───────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                     TYPOGRAPHY & CONTRAST                   │
├─────────────────────────┬───────────────────────────────────┤
│ Text High Contrast Bone │ #F5F3EF (Warm off-white)          │
│ Text Warm Silver Muted  │ #8E8E98 (65% opacity body text)   │
│ Text Charcoal Dimmed    │ #585862 (40% micro captions)      │
│ Text Accent Gold        │ #C5A869 (Serif headers & CTAs)    │
└─────────────────────────┴───────────────────────────────────┘
```

---

## 2. Typography Scale

* **Headlines / Display**: *EB Garamond* (Classical Serif)
  * **Display Large (`display-lg`)**: 28sp / 34sp, Regular/Medium, `letter-spacing: +0.02em` (Majestic Roman serif)
  * **Headline Medium (`headline-md`)**: 20sp / 26sp, Regular/Medium, `letter-spacing: +0.01em`
  * **Brand Monogram (`brand-monogram`)**: 36sp, Chiseled classical numeral "300"
* **Body / UI Labels**: *Plus Jakarta Sans* (Modern Clean Sans)
  * **Body Medium (`body-md`)**: 14sp / 20sp, Regular (400), `letter-spacing: 0.0em`, color `#8E8E98`
  * **Button Labels (`label-md`)**: 14sp / 18sp, SemiBold (600), `letter-spacing: +0.03em`
  * **Micro-badges (`label-sm`)**: 10sp / 14sp, Medium (500), `letter-spacing: +0.08em` uppercase tracking

---

## 3. Geometry & Roundness Scale

* **Cards & Containers**: `16dp` to `18dp` rounded corners (`ROUND_TWELVE`).
* **Buttons**: `999dp` full pill buttons (`ROUND_FULL`) with brushed metallic satin gold gradient fill.
* **Pill Switchers & Filter Rails**: `999dp` (`ROUND_FULL`).
* **Borders**: Ultra-fine `1dp` or `0.5dp` hairlines (`rgba(197, 168, 105, 0.12)` or `rgba(255, 255, 255, 0.06)`).

