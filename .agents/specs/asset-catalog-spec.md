# 300 Asset Catalog Specification

## Contract

`assets/wallpapers/manifest.json` is the durable catalog contract. Each master entry must declare:

- `id`, `title`, `themeId`, `entitlement`, and category.
- Relative `file` path, `width`, `height`, `blackPercentMeasured`, and `trueBlackTargetPercent`.
- `textFree`, `noPhoneFrame`, `topSafeAreaPx`, `provenance`, and `qaStatus`.
- `thumbnail` and `card` derivative identifiers, created before app wiring.

The app only ships low-resolution display derivatives. A download/cache layer resolves the master file by catalog ID and may apply it only after availability and entitlement checks.

## Canonical UI states

The catalog screens use the existing v2 visual language and specify loading, populated, locked, offline-cache-hit, download-progress, download-error, empty, and success states. No new UI is implemented until these states are represented in `screen-spec.md` and screenshot coverage.

## Icon export contract

`assets/icons/manifest.json` will list 96 original glyph IDs in six 16-icon families. Each glyph has an SVG master, monochrome bronze PNG exports, attribution/provenance, and a user-facing launcher-compatibility note. Export/share is supported; launcher-wide installation is never promised for unsupported launchers.

## Widget contract

The native providers are Monolith Clock (2x2), Shield Battery (2x2), Creed Ticker (4x1), and War Calendar (4x2). Preview cards are marketing art only; time, battery, date, and creed content must be rendered by Glance.
