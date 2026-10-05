# 300 — AGENTS.md

Welcome to the **300** autonomous agent system and persistent engineering workspace.

`300` is a premium Android personalization and discipline experience engineered with a cinematic, brutalist, minimal, dark Spartan aesthetic.

---

## 1. Golden Rules for All Agents

1. **Persistent State Protocol**: Before starting any meaningful work, EVERY agent MUST read `.agents/states/current.md`. Update this file when project state materially changes.
2. **Design-First Mandate**: No feature may be implemented without its approved screen specification, token mapping, and 8 canonical UI states in `.agents/specs/screen-spec.md`.
3. **Test-Driven Development (TDD)**: Automated unit/integration tests must precede application code. Confirm red before making green.
4. **Zero Cross-Domain Trespassing**: Cross-domain decisions require an Architectural Decision Record (ADR) logged in `.agents/states/decisions.md`.
5. **Brutalist Spartan Excellence**: Dark obsidian canvas (`#0B0B0C`), Spartan bronze (`#C29B38`), crimson accents (`#8A1C14`), chiseled typography, zero generic neon or glassmorphism effects.

---

## 2. Directory Structure

- `.agents/README.md`: Workspace guide and system rules.
- `.agents/agents/`: 14 specialized agent definitions (including `device-installer-agent`).
- `.agents/workflows/`: 16 step-by-step engineering protocols (including `device-install`).
- `.agents/states/`: Persistent project state (single source of truth).
- `.agents/plans/`: 14-phase master execution plan, current sprint, backlog.
- `.agents/specs/`: Complete product, screen, navigation, state, device, edge cases, design system, and testing specifications.
- `.agents/templates/`: Standardized feature, bug, decision, screen, and test plan templates.

---

## 3. Core Commands & Tooling

```bash
# Build & verify assembleDebug:
.\gradlew assembleDebug

# Run unit and integration tests:
.\gradlew testDebugUnitTest

# Run Android Lint:
.\gradlew lintDebug

# Discover connected devices (USB / Wi-Fi) & install:
powershell -ExecutionPolicy Bypass -File .\scripts\device_helper.ps1 -Action discover
```
