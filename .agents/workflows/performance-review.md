# Workflow: Performance Review

## Objective
Profile, measure, and optimize cold start latency, scrolling frame stability, memory footprint, and battery consumption across 300.

## Leading Agent
`performance-agent.md`

## Execution Protocol

### Step 1: Startup Profiling
1. Measure cold start with Android Profiler / Macrobenchmark:
   - Target: First frame displayed in < 500ms; Time to Interactive (TTI) < 800ms.
2. Verify deferred initialization:
   - Heavy SDKs or database pre-warming must never block the Application `onCreate()` or Activity startup.

### Step 2: Scrolling & Recomposition Audit
1. Run Compose layout inspector with recomposition counts enabled:
   - Verify that scrolling lists (`LazyColumn`, `LazyVerticalGrid`) do not trigger unneeded recompositions of offscreen or stable list items.
2. Verify list item keys:
   - Ensure `items(items = list, key = { it.id })` is used universally.
3. Check frame rendering:
   - Zero frozen frames (>700ms), dropped frames < 1% during standard flings.

### Step 3: Memory & Bitmap Profiling
1. Monitor memory during continuous wallpaper browsing:
   - Heap must remain bounded under 80MB-120MB.
   - Verify Coil bitmap downsampling to exact viewport dimensions.
   - Trigger Garbage Collection and verify zero leaked Activities or ViewModels.

### Step 4: Battery & Background Audit
1. Verify WorkManager background jobs:
   - Periodic daily challenges must not trigger high-frequency alarms.
   - Restrict execution to idle or unmetered network conditions where appropriate.
2. AppWidgets (Glance):
   - Widget updates must be event-driven (upon challenge completion) or throttled. Zero polling loops.
