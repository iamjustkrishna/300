# Workflow: Final Audit

## Objective
Conduct a comprehensive 360-degree audit before public release, ensuring 300 meets the highest bar of engineering, design, accessibility, performance, and legal compliance.

## Leading Agent
`reviewer-agent.md` (in coordination with all agents)

## Execution Protocol

### Step 1: Design & Emotional Impact Audit
- [ ] Does the app feel deeply cinematic, brutalist, and premium?
- [ ] Is there zero generic or neon AI aesthetics?
- [ ] Are all 8 UI states implemented cleanly on every screen?
- [ ] Is the primary action obvious in under 2 seconds?
- [ ] Could any screen be featured directly on the Google Play Store?

### Step 2: Architecture & Code Audit
- [ ] Strict UDF / MVI data flow adhered to.
- [ ] Zero database or disk I/O on the main thread.
- [ ] Proper error handling and typed results.
- [ ] Kotlin and Compose best practices enforced.

### Step 3: Test Suite & Coverage Audit
- [ ] 100% passing test suite (`.\gradlew testDebugUnitTest`).
- [ ] Critical user journeys covered by automated tests.
- [ ] Zero flaky or disabled tests.

### Step 4: Device & Form Factor Audit
- [ ] Compact, standard, large phone layouts verified.
- [ ] Foldable folded and unfolded states verified.
- [ ] Tablet adaptive grid and navigation verified.
- [ ] 3-button and gesture navigation insets handled without overlap.

### Step 5: Accessibility & Internationalization Audit
- [ ] 100% of interactive elements meet 48dp touch target standard.
- [ ] TalkBack screen reader navigation tested and verified.
- [ ] 200% font scaling tested without text clipping.
- [ ] All strings externalized to `strings.xml`.

### Step 6: Security, Privacy & Compliance Audit
- [ ] Zero unnecessary permissions.
- [ ] Zero hardcoded secrets, API keys, or credentials.
- [ ] No copyrighted film logos, actors, character names, or direct movie footage used.
- [ ] ProGuard / R8 minification and mapping file generated.
- [ ] Data Safety and Play Store compliance confirmed.

### Step 7: Final Verdict
`reviewer-agent.md` signs off with:
- **STATUS: APPROVED FOR PRODUCTION** or **BLOCKED** with remediation list.
