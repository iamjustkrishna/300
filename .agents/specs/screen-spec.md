# Complete Screen Inventory & Specification

This document is the canonical specification for every screen in **300**. No screen may be implemented without strictly complying with its specification here.

---

## SCREEN-ONBOARD-001: The Spartan Oath (Identity)

### Metadata
- **Screen ID**: `SCREEN-ONBOARD-001`
- **Name**: Spartan Oath & Identity Selection
- **Purpose**: Welcomes the user with a solemn, brutalist Spartan visual statement and establishes their personal focus.
- **User Goal**: Choose their discipline path and commit to transforming their phone.
- **Navigation Source**: Cold launch when `isOnboardingCompleted == false`.
- **Navigation Destinations**: Forward to `SCREEN-ONBOARD-002`. System back exits app.

### Visual Hierarchy & Components
1. **Background**: Obsidian dark canvas (`#0B0B0C`) with subtle dark crimson vignette at screen edges.
2. **Spartan Crest Icon**: Stylized bronze warrior helmet emblem at top center (72dp x 72dp).
3. **Headline**: DisplayLarge Cinzel text: *"WHO COMMANDS THIS DEVICE?"* in Ash White (`#E5E5E7`).
4. **Subhead**: BodyMedium: *"A weapon or a distraction? Choose your path of iron."* in Muted Gray (`#8F9094`).
5. **Path Selection Cards** (Radio style cards with 1dp bronze border):
   - **Path 1: Iron Body**: Physical grit, daily strength, cold exposure.
   - **Path 2: Cold Mind**: Deep focus, zero mindless scrolling, mental discipline.
   - **Path 3: Sovereign Will**: Complete mastery of time, habits, and stoic resolve.
6. **Primary Action Button**: Full-width `SpartanButton` (Height 56dp): *"TAKE THE OATH"*, bronze background, black bold text.

### States
- **First Launch**: Default state; first card selected by default.
- **Normal**: Smooth selection change; selected card glows with razor-thin 1.5dp Spartan bronze border.
- **Loading**: Instant render (no network dependencies; pre-bundled resources).
- **Offline**: Fully functional.
- **Content Unavailable**: N/A (static embedded config).
- **Returning User**: Skipped directly to Home.
- **Premium User / Free User**: Identical in onboarding.

### Responsive Behavior
- **Compact Phone (360dp)**: Spacing compressed to 12dp between cards; subhead reduced to 14sp; scrollable if vertical height < 600dp.
- **Standard Phone (412dp)**: Default layout.
- **Foldable (Unfolded) & Tablet (600dp+)**: Content width constrained to 520dp centered with generous obsidian margins.

### Accessibility & Analytics
- **Touch Targets**: Cards have 72dp minimum height; Button is 56dp height.
- **TalkBack**: Cards announce: *"Path: Iron Body. Focus on physical grit. Selected/Not Selected. Double tap to select."*
- **Contrast**: Text `#E5E5E7` on `#0B0B0C` yields 17:1 contrast (WCAG AAA).
- **Analytics Event**: `onboarding_oath_viewed`, `onboarding_oath_committed(path_id)`.
- **Tests**: `OnboardingOathScreenTest` verifies path selection, DataStore state update, and navigation trigger.

---

## SCREEN-ONBOARD-002: Day 1 Armory (Personalization)

### Metadata
- **Screen ID**: `SCREEN-ONBOARD-002`
- **Name**: Day 1 Wallpaper Preview & Armor Claim
- **Purpose**: Delivers the immediate visual transformation payoff: previewing and setting the Day 1 Spartan wallpaper.
- **User Goal**: Transform their phone's lock and home screen with 1 tap.
- **Navigation Source**: `SCREEN-ONBOARD-001`.
- **Navigation Destinations**: Forward to `SCREEN-ONBOARD-003`; Back to `SCREEN-ONBOARD-001`.

### Visual Hierarchy & Components
1. **Interactive Device Frame**: Centered 9:16 phone mockup displaying "The Vanguard" dark bronze Spartan wallpaper with mock lockscreen clock (12:00 / Today's Date).
2. **Transformation Copy**: TitleMedium: *"CLAIM YOUR SHIELD"*, BodySmall: *"Set this wallpaper as your daily reminder of strength."*
3. **Primary Action**: `SpartanButton`: *"SET WALLPAPER (HOME & LOCK)"*.
4. **Secondary Action**: TextButton: *"Skip for now"*.

### States
- **Normal**: High-resolution preview rendered seamlessly from preloaded assets.
- **Loading**: Subtle bronze shimmer while bitmap prepares in memory (<100ms).
- **Offline**: Fully functional offline.
- **Error**: If `WallpaperManager` throws permission/IO error, non-blocking toast displays: *"Unable to apply automatically. You can save to gallery in Armory."*

### Responsive Behavior
- **Phones**: Device frame takes 55% of vertical viewport height.
- **Foldables / Tablets**: Device frame rendered in dual-pane layout: Left pane displays 3D-angled phone mockup; Right pane displays claim controls.

### Accessibility & Analytics
- **TalkBack**: Mockup described as: *"Preview of Spartan Vanguard dark wallpaper with Roman numerals."*
- **Analytics Event**: `onboarding_wallpaper_applied`, `onboarding_wallpaper_skipped`.
- **Tests**: `OnboardingArmoryScreenTest` verifies wallpaper application call and navigation.

---

## SCREEN-ONBOARD-003: The First Challenge

### Metadata
- **Screen ID**: `SCREEN-ONBOARD-003`
- **Name**: First Spartan Challenge Commitment
- **Purpose**: Introduces the daily discipline loop and establishes the Day 1 challenge.
- **User Goal**: Accept Day 1 challenge and enter the active app.
- **Navigation Source**: `SCREEN-ONBOARD-002`.
- **Navigation Destinations**: Forward to `SCREEN-HOME-001` (pops onboarding stack).

### Visual Hierarchy & Components
1. **Day Header**: TitleSmall: *"DAY 1 OF 300"*, Spartan Crimson color (`#8A1C14`).
2. **Challenge Title**: HeadlineMedium: *"THE FIRST HOUR"* in Ash White.
3. **Challenge Rule Card**: Charcoal card (`#131416`) detailing the task: *"Spend your first 60 minutes today without social media or passive entertainment. Fuel your body with water, move with purpose, and prepare for battle."*
4. **Primary Action Button**: `SpartanButton`: *"I ACCEPT THE CHALLENGE"* (triggers heavy haptic feedback, marks onboarding complete in DataStore, navigates to Home).

### States
- **Normal**: Card displayed with crisp typography.
- **Offline**: Fully functional.
- **Tests**: `OnboardingChallengeScreenTest` verifies onboarding completion flag set and navigation.

---

## SCREEN-HOME-001: The War Room (Home Dashboard)

### Metadata
- **Screen ID**: `SCREEN-HOME-001`
- **Name**: Home Dashboard
- **Purpose**: Central hub showing current streak, active daily discipline challenge, featured daily wallpaper, and widget shortcut.
- **User Goal**: Check off today's challenge, review streak progress, and glance at daily Spartan wisdom.
- **Navigation Source**: App launch (when onboarding done) or Bottom Nav "Home" tab.
- **Navigation Destinations**: Challenge Detail (`SCREEN-CHALLENGE-002`), Wallpaper Preview (`SCREEN-WALLPAPER-002`), Settings (`SCREEN-SETTINGS-001`).

### Visual Hierarchy & Components
1. **Top Bar**:
   - App Brand: DisplaySmall *"300"* with bronze Spartan crest.
   - Streak Pill (Top Right): Fire icon with number of consecutive days (`14 DAYS`) in Crimson/Bronze. Tap opens `SCREEN-CHALLENGE-001`.
2. **Daily Maxim Card**:
   - Quotes from Spartan generals and Stoic philosophers (e.g., *"He who sweats more in training bleeds less in war."*).
3. **Hero Challenge Card**:
   - Card containing Day number, challenge name, brief description, and large action button:
     - State A (Pending): Bronze border, button text: *"I HAVE CONQUERED"* (Tap checks off challenge, triggers heavy metallic haptic thud).
     - State B (Completed): Ash Gray/Crimson shield icon, text: *"CONQUERED FOR TODAY"*, checkmark badge.
4. **Armory Glance Strip**:
   - Horizontal carousel displaying "Today's Featured Armors" (wallpapers). Tapping opens preview.
5. **Widget Setup Banner**:
   - Dismissible prompt: *"Place the Spartan Shield on your home screen. Add Widget."*

### States (8 Canonical States)
1. **First Launch (Returning from Onboarding)**: Streak at 0 or 1, Day 1 challenge active, warm guidance banner.
2. **Normal**: Active streak, today's challenge ready for completion, featured wallpapers loaded.
3. **Loading**: Dark skeleton cards with bronze shimmer pulsation.
4. **Offline**: Fully accessible; offline banner hidden or discreet; Room cached challenges and streak rendered.
5. **Empty**: If challenge database has no active entry, fallback to evergreen Spartan challenge *"Master Your Mind"*.
6. **Error**: Retry card with dignified prompt: *"The armory connection wavered. Tap to refresh."*
7. **Returning User**: Highlight days disciplined; celebration animation if streak milestone reached.
8. **Premium User vs. Free User**: Free user sees standard badges; Premium user sees "Vanguard Member" golden emblem in top bar.

### Responsive Behavior
- **Phones**: Single vertical scrolling column.
- **Foldables & Tablets**: Dual-column dashboard: Left column contains Daily Maxim & Challenge Card; Right column contains Streak Stats & Armory carousel.

### Accessibility & Analytics
- **Touch Targets**: "I Have Conquered" button is 56dp height; streak pill is 48dp clickable area.
- **TalkBack**: Full state announcements for completed challenges.
- **Analytics Event**: `home_viewed`, `daily_challenge_completed(day_number, streak_count)`.
- **Tests**: `HomeScreenTest` verifies challenge completion, streak counter increment, and offline stability.

---

## SCREEN-WALLPAPER-001: The Armory (Wallpaper Catalog)

### Metadata
- **Screen ID**: `SCREEN-WALLPAPER-001`
- **Name**: Wallpaper Catalog Grid
- **Purpose**: Browse curated Spartan wallpapers categorized by theme.
- **User Goal**: Find and select a dark, brutalist wallpaper to personalize their phone.
- **Navigation Source**: Bottom Nav "Armory" tab.
- **Navigation Destinations**: Wallpaper Preview (`SCREEN-WALLPAPER-002`).

### Visual Hierarchy & Components
1. **Filter Chips**: Row of Spartan category chips: *"All"*, *"Shields & Crests"*, *"Ruins & Marble"*, *"Bronze & Iron"*, *"Battlefield"*.
2. **Adaptive Wallpaper Grid**:
   - 2 columns on phones (vertical aspect ratio 9:16 item cards).
   - 3-4 columns on foldables and tablets.
   - Cards display rounded 12dp corners, high-res WebP preview, subtle title, and lock badge for Vanguard tier.
3. **Item Interaction**: Tap opens full-screen preview with smooth hero transition.

### States
- **Normal**: Fast thumbnail rendering via Coil with hardware bitmaps.
- **Loading**: Grid of dark placeholder cards with subtle bronze shimmer.
- **Offline**: Fully functional with all pre-bundled wallpapers.
- **Free vs. Premium**: Locked wallpapers display a subtle bronze padlock icon in the top-right corner.

### Responsive Behavior
- Automatic column count adjustment based on `WindowWidthSizeClass`:
  - Compact (<600dp): 2 columns.
  - Medium (600dp - 840dp): 3 columns.
  - Expanded (>840dp): 4 columns.

### Accessibility & Analytics
- **TalkBack**: Announce wallpaper title, category, and lock status: *"The Spartan Phalanx wallpaper, Bronze category, Free, Double tap to preview."*
- **Analytics Event**: `wallpaper_catalog_viewed`, `wallpaper_thumbnail_clicked(wallpaper_id)`.
- **Tests**: `WallpaperCatalogScreenTest` verifies category filtering and column counts.

---

## SCREEN-WALLPAPER-002: Fullscreen Wallpaper Preview & Apply

### Metadata
- **Screen ID**: `SCREEN-WALLPAPER-002`
- **Name**: Wallpaper Preview & Apply Engine
- **Purpose**: Full-bleed immersive wallpaper inspection with mock phone UI overlays and 1-tap application.
- **User Goal**: Preview how the wallpaper looks behind app icons and lockscreen clocks, then apply it.
- **Navigation Source**: `SCREEN-WALLPAPER-001` or `SCREEN-HOME-001`.
- **Navigation Destinations**: Paywall Sheet (`SCREEN-PAYWALL-001`) if locked; Back to Armory.

### Visual Hierarchy & Components
1. **Full-Bleed Wallpaper Canvas**: High-res image filling 100% of viewport with edge-to-edge rendering.
2. **Overlay Control Bar (Top)**:
   - Back button (circle 48dp, semi-transparent obsidian).
   - Mock UI Toggle:
     - Mode 1: Clean (no overlays).
     - Mode 2: Lockscreen (shows real system time/date in clean serif font).
     - Mode 3: Homescreen (shows semi-transparent grid of app icons).
3. **Bottom Action Bar**:
   - Semi-transparent obsidian gradient scrim.
   - Title and category.
   - Primary `SpartanButton`: *"APPLY WALLPAPER"* (opens bottom sheet with: *"Home Screen"*, *"Lock Screen"*, *"Both"*).
   - If locked: Primary button is *"UNLOCK WITH VANGUARD PASS"*.

### States
- **Applying State**: Button shows subtle bronze progress spinner while `WallpaperManager` sets bitmap.
- **Success State**: Haptic confirmation + toast: *"Shield Applied to Device"*.
- **Error State**: Non-blocking alert: *"Failed to apply. Permission or memory constraint."*

### Responsive Behavior
- Wallpaper centers on focal point across all aspect ratios without stretching or distortion.
- User can drag/pan slightly on wide or tall displays to adjust crop position.

### Accessibility & Analytics
- **Analytics Event**: `wallpaper_preview_viewed`, `wallpaper_applied(wallpaper_id, target_screen)`.
- **Tests**: `WallpaperPreviewScreenTest` verifies overlay toggles and wallpaper application callbacks.

---

## SCREEN-CHALLENGE-001: The 300 Discipline Hub

### Metadata
- **Screen ID**: `SCREEN-CHALLENGE-001`
- **Name**: Discipline & Streak Hub
- **Purpose**: Tracks user's multi-day discipline journey toward the 300-day milestone.
- **User Goal**: Review streak statistics, explore past achievements, and inspect upcoming challenges.
- **Navigation Source**: Bottom Nav "Discipline" tab.
- **Navigation Destinations**: Challenge Detail (`SCREEN-CHALLENGE-002`).

### Visual Hierarchy & Components
1. **The 300 Ring / Progress Arc**:
   - Hero graphic showing current day out of 300 (e.g., `Day 14 / 300`).
   - Circular bronze progress meter with crimson fire accent.
2. **Milestone Shields**:
   - Row of unlockable badges:
     - Day 3: *The First Stand* (Bronze)
     - Day 7: *The Phalanx* (Iron)
     - Day 14: *The Shield-Wall* (Steel)
     - Day 30: *Spartan Warrior* (Crimson)
     - Day 100: *The Immortal* (Gold)
     - Day 300: *The 300 Legend* (Obsidian & Gold)
3. **Challenge Calendar / History Log**:
   - Chronological list of completed days with date, challenge title, and completion timestamp.

### States
- **Normal**: Full history rendered from Room DB.
- **Empty**: For Day 1 users, shows encouraging quote: *"A journey of 300 days begins with a single shield."*
- **Offline**: Fully accessible.

### Accessibility & Analytics
- **TalkBack**: Arc announces: *"Current progress: 14 days out of 300 completed. 4 percent total journey."*
- **Analytics Event**: `discipline_hub_viewed`, `milestone_badge_clicked(badge_id)`.
- **Tests**: `DisciplineHubScreenTest` verifies streak calculation and badge unlock states.

---

## SCREEN-SETTINGS-001: The Citadel (Settings)

### Metadata
- **Screen ID**: `SCREEN-SETTINGS-001`
- **Name**: Application Settings & Preferences
- **Purpose**: Manage notification schedules, widget styles, storage cache, and purchase restoration.
- **User Goal**: Configure daily reminder time, clear cache, or restore purchases.
- **Navigation Source**: Bottom Nav "Settings" tab.

### Visual Hierarchy & Components
1. **Preferences Group: Daily Discipline**:
   - Toggle: *Daily Spartan Reminder* (On/Off).
   - Time Picker: *Reminder Time* (Default: 08:00 AM).
2. **Preferences Group: Armory & Storage**:
   - *Clear Image Cache* (Displays current disk MB usage, 1-tap clear).
3. **Preferences Group: Membership**:
   - *Vanguard Pass Status* (Active / Inactive).
   - *Restore Purchases* button.
4. **Preferences Group: Privacy & Legal**:
   - *Privacy Policy* (Opens internal viewer or browser).
   - *Terms of Service*.
   - *App Version & Build* (`v1.0.0 (1)`).

### States
- **Normal**: Settings loaded from DataStore.
- **Offline**: Fully functional.
- **Tests**: `SettingsScreenTest` verifies DataStore updates for notifications and cache clearing.

---

## SCREEN-PAYWALL-001: The Vanguard Pass

### Metadata
- **Screen ID**: `SCREEN-PAYWALL-001`
- **Name**: Vanguard Pass Paywall Sheet
- **Purpose**: Present the premium offering cleanly with dignified Spartan value propositions and zero deceptive urgency.
- **User Goal**: Unlock exclusive wallpapers and premium widgets.
- **Navigation Source**: Tapping locked wallpaper or premium widget option.
- **Navigation Destinations**: In-app purchase sheet or dismiss back.

### Visual Hierarchy & Components
1. **Dismiss Bar**: Down-swipe handle and close "X" icon.
2. **Crest Header**: Weathered Bronze Spartan Shield emblem with crossed spears.
3. **Headline**: DisplayMedium: *"THE VANGUARD PASS"*.
4. **Benefit Bullets**:
   - *All Current & Future Premium Spartan Wallpapers*
   - *Exclusive Shield & Streak Glance Widgets*
   - *Offline Content Pack Downloads*
   - *Lifetime Access / One-Time Purchase (No recurring subscription trap)*
5. **Pricing Button**: `SpartanButton`: *"UNLOCK FOREVER — $4.99"*.
6. **Footer**: Restore Purchases link & Terms of Sale.

### States
- **Normal**: Pricing loaded.
- **Purchasing State**: Button displays spinner while Google Play Billing processes.
- **Dismissed**: Instant closure without guilt-tripping dialogs.

### Tests
- `PaywallScreenTest` verifies purchase initiation, entitlement unlocking, and dismissal.
