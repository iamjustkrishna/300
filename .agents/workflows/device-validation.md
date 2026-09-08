# Workflow: Device Validation

## Objective
Verify the responsive layout, visual integrity, touch targets, and performance of 300 across diverse screen form factors, densities, and orientations.

## Leading Agent
`device-agent.md`

## Execution Protocol

### Step 1: Device Configuration Matrix
Run verification across the canonical test configurations:
1. **Compact Phone** (e.g., 360x640dp, mdpi / xhdpi):
   - Check text wrapping, button height, scrollability without clipping.
2. **Standard Modern Phone** (e.g., 412x915dp, xxhdpi, 20:9 aspect ratio):
   - Check hero image alignment, status bar / gesture navigation bar insets.
3. **Foldable (Folded)**:
   - Ultra-tall narrow aspect ratio (~21:9 or ~23:9).
4. **Foldable (Unfolded)** & **Tablet** (600dp - 840dp+):
   - Verify adaptive multi-column grid expansion (2 columns -> 3 or 4 columns for wallpapers).
   - Verify bottom navigation bar transitions or adapts to navigation rail if applicable.
5. **Landscape Orientation**:
   - Verify screen is scrollable and critical buttons (e.g. "Apply Wallpaper", "Commit to Challenge") remain accessible.

### Step 2: System Navigation Mode Verification
- Test with 3-button navigation (legacy back, home, recents bar).
- Test with gesture navigation (edge swipe back insets).

### Step 3: Density Bucket Check
- Check assets across `hdpi`, `xhdpi`, `xxhdpi`, `xxxhdpi` for sharpness and correct memory allocation.

### Step 4: Documentation
Log device validation results in `.agents/states/devices.md`.
