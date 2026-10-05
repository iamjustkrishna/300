# 300 Wallpaper Asset Pipeline

This directory is reserved for high-resolution WebP wallpaper masters that are intentionally kept outside Android resources. Dedicated masters must not be copied from full-screen UI screenshots or studio cards and should not be copied into `app/src/main/res`.

## Current status

Six original text-free masters are approved in `masters/` + `manifest.json` (v2, 2026-09-14); the M3 catalog target is twelve. The six remaining IDs are intentionally listed as pending generation briefs rather than being represented by placeholder imagery.
Each is 1440x3200 WebP q85, base #08080A, top 320px safe, 95%+ true-black verified.
They are original minimal scenes (not crops of UI mockups) per policy below.

The APK continues to use compact preview derivatives from `app/src/main/res/drawable-nodpi/` for UI only.

See `generation-briefs.md` for the six approved original-art directions and `.agents/plans/asset-catalog-plan.md` for the current completion matrix.

## Acceptance criteria for future masters

- 1440x3200 portrait WebP, no baked-in text or controls.
- No phone frame, clock, app icons, lock badge, title, or navigation chrome.
- Composition leaves safe areas for Android status bars and home-screen icons.
- Artwork is an original scene or licensed source, not a crop of a UI mockup.
