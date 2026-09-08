# Workflow: Release

## Objective
Standardize the production build, signing, obfuscation verification, and Google Play Store submission package.

## Leading Agent
`release-agent.md`

## Execution Protocol

### Step 1: Release Pre-Checks
- [ ] Master plan milestones completed and verified.
- [ ] Unit test suite passing (`.\gradlew testDebugUnitTest`).
- [ ] Android Lint clean with zero fatal/critical errors (`.\gradlew lintRelease`).
- [ ] Device validation signed off in `.agents/states/devices.md`.
- [ ] Accessibility review signed off.
- [ ] Version code and version name bumped in `app/build.gradle.kts`.

### Step 2: Production Bundle Generation
1. Build release Android App Bundle (AAB):
   `.\gradlew bundleRelease`
2. Verify R8 / ProGuard minification:
   - Ensure classes and symbols are obfuscated.
   - Archive `mapping.txt` for Crashlytics / Google Play Console symbol mapping.
3. Check final bundle size: Target < 25MB.

### Step 3: Store Listing Asset Verification
- [ ] App Icon (512x512 PNG, 32-bit color).
- [ ] Feature Graphic (1024x500 PNG/JPEG, no transparency).
- [ ] Phone Screenshots (minimum 4 cinematic Spartan previews, 1080x2400 or 1440x3120).
- [ ] Tablet Screenshots (7" and 10" layouts).
- [ ] Release Notes / Changelog documented.

### Step 4: State Update
Update `.agents/states/release.md` with release artifact hashes, version codes, and submission date.
