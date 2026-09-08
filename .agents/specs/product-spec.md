# Product Specification: 300

---

## 1. Product Summary & Identity
- **Product Name**: 300
- **Tagline**: Forge Your Device. Master Your Discipline.
- **Application ID**: `space.iamjustkrishna.threehundred`
- **Core Emotional Hook**: *"My phone feels like a different device now."*
- **Visual & Atmospheric Vibe**: Cinematic, brutalist, minimal, dark, powerful, and deeply polished. Inspired by Spartan warrior ethos, iron resolve, weathered bronze, crimson cloaks, and stoic focus. Absolutely zero cartoonish mascots, glowing rainbow neon, or generic wallpaper dumps.

---

## 2. Target Audience & User Problems

### Primary Audiences
1. **The Modern Stoic & Discipline Seeker**: Individuals striving to build physical grit, mental endurance, and daily focus. They want their smartphone—often a source of distraction—to become a solemn reminder of their goals.
2. **The High-End Android Minimalist**: Discerning Android power users who curate dark, clean, brutalist home screens, value high-resolution art, and appreciate flawless native performance.

### Core Problems Solved
- **Digital Dissipation**: Standard phones are designed for dopamine addiction and mindless scrolling.
- **Cheap, Intrusive Personalization Apps**: Wallpaper and customization apps in the Play Store are universally ruined by loud video ads, low-quality internet rips, and broken widgets.
- **Fluffy, Annoying Habit Trackers**: Existing habit apps feel like children's games with colorful cartoons, nagging notifications, and expensive recurring subscriptions.

---

## 3. Product Promise & Core Loops

### The Product Promise
Within 60 seconds of installing 300:
1. The user's home screen and lockscreen are visually transformed with austere, cinematic Spartan artwork.
2. A brutalist Glance widget is placed on their home screen displaying their active streak and daily stoic challenge.
3. The user commits to Day 1 of the 300 Discipline Journey.

### The Daily Core Loop
```
[ Morning Glance ]
Widget displays Day's Spartan Maxim & Active Streak.
       │
       ▼
[ Action / Execution ]
User confronts their real-world discipline task (e.g. cold plunge, hard workout, deep focus sprint).
       │
       ▼
[ In-App Commitment ]
Open 300 (< 500ms cold boot) -> Tap "I Have Conquered" -> Feel heavy haptic thud -> Streak increments.
       │
       ▼
[ Evolution & Reward ]
Unlock or rotate to a new daily Spartan wallpaper and widget theme reflecting their growing streak.
```

---

## 4. Onboarding & First-Session Experience (FSE)
The first session must take under 45 seconds from launch to phone transformation:
1. **Step 1: The Oath (Identity)**:
   - Full-screen obsidian background with subtle crimson vignette.
   - Headline: *"WHO COMMANDS THIS DEVICE?"*
   - Selection: Choose Primary Path (Iron Body, Cold Mind, Sovereign Focus).
2. **Step 2: The Armory (Day 1 Personalization)**:
   - Screen renders preview of "The Vanguard" wallpaper with real-time lockscreen clock overlay.
   - Primary button: *"Claim Armor (Apply Wallpaper)"*.
   - 1-tap invokes Android `WallpaperManager` to set Home and Lock screen.
3. **Step 3: The First Challenge**:
   - Screen presents Day 1 Challenge: *"Conquer the first hour without distraction. No social media. Complete 50 pushups or 20 minutes of deliberate reading."*
   - User commits: *"I Will Stand"* -> Onboarding complete -> Enters Home Screen.

---

## 5. Explicit MVP Scope Boundary

### In MVP (v1.0.0)
- **Spartan Wallpaper Engine**:
  - 15 handcrafted, ultra-high-resolution dark Spartan wallpapers bundled locally.
  - Interactive full-screen preview with lockscreen clock and home app grid toggle.
  - 1-tap system wallpaper setter (Home screen, Lock screen, or Both).
- **Daily Discipline & Streak System**:
  - 30 curated daily stoic challenges stored in local Room database.
  - 1-tap completion with satisfying heavy metallic haptic feedback.
  - Consecutive streak counter with automated daily validation and grace periods.
  - Milestone unlock badges (Day 3: Bronze, Day 7: Iron, Day 14: Steel, Day 30: Vanguard).
- **Jetpack Glance Home-Screen Widgets**:
  - Minimal Spartan Maxim Widget (2x1 / 3x2).
  - Shield & Streak Glance Widget (2x2 / 4x2).
  - Instant synchronization when in-app challenge is checked off.
- **100% Offline-First**:
  - Zero mandatory login or account creation.
  - Complete functionality in airplane mode.
- **Freemium Entitlement Architecture**:
  - Core 10 wallpapers and all 30 daily challenges 100% free.
  - 5 exclusive elite wallpapers and premium widget variants marked as "Vanguard Tier" with preview paywall.

### Out of MVP (Post-Launch Roadmap)
- Live video wallpapers (SurfaceView engine scheduled for Phase 14).
- Cloud sync / social leaderboards (deliberately avoided to prevent distractions and server overhead).
- Third-party launcher icon packs.

---

## 6. Retention & Notification Protocol
- **Single Daily Notification**: Sent at user-selected hour (default: 08:00 AM) with brief, commanding text (e.g., *"Spartan, Day 12 awaits. Do not let your shield fall."*).
- Never send more than 1 notification per day.
- Notifications completely disabled if user turns them off in Settings.

---

## 7. Privacy & Security Expectations
- Zero tracking of personal identity, contacts, location, or browsing habits.
- Local data stored securely via Room and DataStore on device.
- Minimal permissions: `SET_WALLPAPER`, `POST_NOTIFICATIONS` (Android 13+ contextual opt-in).
