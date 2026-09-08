# Screen Specification: [SCREEN-ID]

## 1. Metadata
- **Screen ID**: `SCREEN-XXX-001`
- **Name**: `[Screen Name]`
- **Route**: `[ScreenRoute object/string]`
- **Purpose**: `[Brief purpose]`
- **User Goal**: `[What does the user achieve?]`
- **Navigation Source**: `[Inbound route]`
- **Navigation Destinations**: `[Outbound routes]`

## 2. Visual Hierarchy & Components
1. **Container / Surface**: `[Surface0 / Surface1 / Surface2]`
2. **Header / AppBar**: `[Title token, action buttons]`
3. **Primary Content**: `[Cards, lists, grids, or media preview]`
4. **Action Element**: `[SpartanButton / Floating Action]`

## 3. The 8 Canonical UI States
1. **First Launch**: `[Description]`
2. **Normal**: `[Description]`
3. **Loading**: `[Shimmer / skeleton description]`
4. **Offline**: `[Offline behavior]`
5. **Empty / Unavailable**: `[Empty state copy & fallback]`
6. **Error**: `[Error message & recovery action]`
7. **Returning User**: `[Highlight returning progress]`
8. **Premium User vs. Free User**: `[Lock/unlock distinction]`

## 4. Responsive & Device Behavior
- **Compact Phone (<600dp)**: `[Layout behavior]`
- **Foldable (Unfolded)**: `[Multi-column or dual-pane behavior]`
- **Tablet (>840dp)**: `[Max-width or rail behavior]`
- **Orientation**: `[Landscape scrolling behavior]`

## 5. Accessibility & Semantics
- **Touch Targets**: `[All clickables >= 48dp]`
- **TalkBack Announcements**: `[Semantics tree & descriptions]`
- **Font Scaling**: `[Wrap behavior at 200% font scale]`

## 6. Analytics Events
- `[screen_viewed_event]`
- `[primary_action_event]`

## 7. Verification Tests
- `[ScreenTest class & assertions]`
