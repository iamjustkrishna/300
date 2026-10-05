# 300 — Agent System Architecture & Workspace Guide

Welcome to the **300** autonomous agent system and persistent engineering workspace.

`300` is a premium Android personalization and discipline experience engineered for maximum focus, strength, and transformation. The visual identity is cinematic, brutalist, minimal, dark, powerful, and deeply polished—inspired by Spartan/warrior discipline without infringing on copyrighted movie assets.

---

## Directory Layout

```
.agents/
├── README.md                          # Workspace guide & agent system governance (this file)
├── agents/                            # Specialized agent personas, mandates, and guardrails
│   ├── product-agent.md               # Product requirements, MVP scope, core loops
│   ├── research-agent.md              # Competitor analysis, Play Store market signals
│   ├── ux-agent.md                    # Information architecture, user flows, friction audits
│   ├── visual-design-agent.md         # Cinematic Spartan aesthetic, tokens, layouts
│   ├── android-architect-agent.md     # Compose/MVI/Clean architecture, modularity, Room
│   ├── android-implementation-agent.md# Production Kotlin implementation (strictly post-design)
│   ├── testing-agent.md               # TDD enforcement, unit/Robolectric/UI test coverage
│   ├── device-agent.md                # Device matrix, foldables, tablets, density buckets
│   ├── accessibility-agent.md         # TalkBack, 48dp touch targets, font scaling (200%), contrast
│   ├── performance-agent.md           # Cold startup (<500ms), 60/120fps scrolling, low memory
│   ├── security-agent.md              # Zero leaks, minimal permissions, safe storage
│   ├── release-agent.md               # Play Store compliance, ProGuard/R8, signing, bundles
│   ├── reviewer-agent.md              # Independent quality gate & cross-domain auditor
│   └── device-installer-agent.md      # ADB discovery (USB/Wi-Fi), wireless pairing, APK deploy & launch
├── workflows/                         # Explicit, multi-step repeatable protocols
│   ├── bootstrap.md                   # Environment & tooling readiness
│   ├── product-definition.md          # PRD drafting & scope definition
│   ├── research.md                    # Market research & teardown protocols
│   ├── design-first.md                # Screen-first visual & state design
│   ├── screen-specification.md        # Screen contract & token mapping
│   ├── architecture.md                # System design & ADR approval
│   ├── tdd-feature.md                 # Red-Green-Refactor implementation protocol
│   ├── implementation.md              # Production code writing rules
│   ├── device-validation.md           # Multi-device & density test matrix
│   ├── device-install.md              # Live hardware ADB discovery, wireless pairing & deploy
│   ├── accessibility-review.md        # Comprehensive a11y audit
│   ├── performance-review.md          # Frame drop, memory & battery profiling
│   ├── release.md                     # Build, sign, and store deployment pipeline
│   ├── regression.md                  # Defect reproduction & regression guard
│   ├── bug-fix.md                     # Root cause analysis & fix workflow
│   └── final-audit.md                 # 360-degree pre-launch verification
├── states/                            # Persistent, single-source-of-truth project state
│   ├── current.md                     # Real-time project dashboard (MUST READ FIRST)
│   ├── product.md                     # Current product scope & feature flags
│   ├── architecture.md                # Tech stack, layers & subsystem specs
│   ├── design.md                      # Design tokens, theme status & asset inventory
│   ├── implementation.md              # Codebase modules, packages & implementation status
│   ├── testing.md                     # Test suites, coverage metrics & results
│   ├── devices.md                     # Verified device profiles & matrix status
│   ├── release.md                     # Release milestones, version codes & store checklist
│   ├── decisions.md                   # Architectural Decision Records (ADRs)
│   └── risks.md                       # Active risk register & mitigation plans
├── plans/                             # Structured execution roadmaps
│   ├── master-plan.md                 # 14-phase master delivery plan (Phase 0 to 13)
│   ├── current-sprint.md              # Current iteration objectives and tasks
│   └── backlog.md                     # Prioritized feature and improvement queue
├── specs/                             # Canonical system specifications
│   ├── product-spec.md                # Core product specification & MVP bounds
│   ├── screen-spec.md                 # Screen inventory & exhaustive UI state matrix
│   ├── navigation-spec.md             # Navigation graph, arguments & deep-link mapping
│   ├── state-spec.md                  # State machines, UI models & persistence contracts
│   ├── device-spec.md                 # Responsive breakpoints, foldables, density rules
│   ├── edge-cases.md                  # Exhaustive edge case handling matrix
│   ├── design-system.md               # Spartan design tokens, typography, haptics, motion
│   └── testing-spec.md                # Test pyramid, fixtures, and verification strategy
└── templates/                         # Standardized document templates
    ├── feature.md                     # Feature specification template
    ├── bug.md                         # Defect report & regression test template
    ├── decision.md                    # Architecture Decision Record (ADR) template
    ├── screen.md                      # Screen design specification template
    └── test-plan.md                   # Test execution plan template
```

---

## The Golden Rules of the 300 Agent System

1. **Persistent State First (`.agents/states/current.md`)**:
   Every agent MUST read `.agents/states/current.md` before doing any work. Every agent MUST update it when project state changes.
2. **Design Before Implementation**:
   No feature code is written until its screens are specified with all states (first launch, normal, loading, offline, empty, error, returning user, premium vs. free user).
3. **Test-Driven Development (TDD)**:
   Acceptance criteria and automated tests precede application code.
4. **Zero Cross-Domain Trespassing**:
   Visual Design does not silently change architecture. Architect does not silently alter product scope. Cross-domain adjustments require ADRs logged in `.agents/states/decisions.md`.
5. **Brutalist, Cinematic Excellence**:
   No generic templates, excessive glowing neon, or cartoonish UI. Spartan bronze, charcoal obsidian, crimson accents, bold typography, intentional whitespace.
