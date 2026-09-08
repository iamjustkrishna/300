# Design State

## Visual Direction: Spartan Brutalism
- **Philosophy**: Austere, imposing, cinematic, dark, disciplined.
- **Palette**:
  - `BackgroundDark`: `#0B0B0C` (Deep obsidian black)
  - `SurfaceLevel1`: `#131416` (Smoky iron card surface)
  - `SurfaceLevel2`: `#1C1D20` (Elevated weapon rack surface)
  - `AccentBronze`: `#C29B38` (Weathered Spartan bronze crests, active states)
  - `AccentCrimson`: `#8A1C14` (Spartan battle cape crimson, streak fire, alerts)
  - `TextPrimary`: `#E5E5E7` (Ash white, high contrast)
  - `TextSecondary`: `#8F9094` (Muted shield gray)
  - `BorderSubtle`: `#26272B` (Chiseled stone border)
- **Typography**:
  - Headers: Cinzel / Spartan Display Serif (Imposing, classical, stoic)
  - Body & Numbers: Inter / Oswald Sans-Serif (Clean, legible, zero clutter)
- **Haptic Feedback**:
  - Challenge Done: `HapticFeedbackConstants.HEAVY_CLICK` (Heavy metallic thud)
  - Navigation: `HapticFeedbackConstants.CLOCK_TICK` (Subtle mechanical tick)
- **Motion & Physics**:
  - Stiff, rapid springs (damping ratio 0.8, stiffness 500f). No bouncy cartoon wobbles.
