# 300 — Visual Design System, Brand Identity & Stitch Registry

> **Document Version**: 2.0.0 (Phase 1 Approved Baseline)  
> **Status**: APPROVED  
> **Stitch Project ID**: `14386677080154071069`  
> **Stitch Design System Asset ID**: `assets/3071428720144166618`

---

## 1. Visual Language & Inspiration Synthesis

From our analysis of the 20 inspiration references in `inspiration/`, we extracted core visual principles:
1. **Monolithic Dark Canvases**: Pure pitch OLED blacks (`#080809`, `#000000`) serving as negative space, eliminating pixel battery draw.
2. **Directional Specular Lighting**: Golden rim-lighting, embers drifting upwards, backlit silhouettes of Corinthian helmets, heavy hoplite shields, and spear tips.
3. **Restrained Materiality**: Weathered bronze (`#C29B38`) with hairline patina borders, chiseled obsidian stone (`#111215`), and focused Spartan crimson (`#8A1C14`).
4. **Architectural Stoic Typography**: Chiseled uppercase serif and condensed letterforms with wide letter-spacing (`tracking-widest`).
5. **Zero Gimmicks**: Strictly NO generic neon glows, rainbow gradients, bubbly 24dp corners, or blurry iOS-style glassmorphism.

---

## 2. Brand Identity & Logo Direction

### Winner: The Spartan "Tri-Blade" Lambda ($\Lambda$) Sigil
* **Concept**: Three chiseled, vertical bronze blades converging into an austere Spartan chevron ($\Lambda$), forming an abstract '3' and the historical emblem painted onto Spartan shields at Thermopylae (*Lacedaemon*).
* **Rationale**:
  - 100% original geometry; zero copyright infringement with the Warner Bros. movie wordmark or Gerard Butler imagery.
  - Scales flawlessly from a 48x48dp app icon down to a 16x16dp status glyph and expands into high-impact splash screen branding.
  - Symmetrical, balanced, and instantly recognizable as a masculine mark of strength and discipline.

---

## 3. Core Design Tokens

### 3.1 Color Palette
* **Canvas Pitch Black**: `#080809` (99.4% OLED True Black)
* **Surface Obsidian Dark**: `#111215` (Elevated cards and sheets)
* **Surface Iron Slate**: `#1B1D22` (Secondary containers and borders)
* **Border Hairline Bronze**: `rgba(194, 155, 56, 0.30)`
* **Primary Spartan Bronze**: `#C29B38` (Specular Gold: `#E2BF65`, Dark Bronze: `#8C6E21`)
* **Accent Spartan Crimson**: `#8A1C14` (Vibrant Crimson: `#B82318`, Container: `rgba(138, 28, 20, 0.16)`)
* **Text High-Contrast Bone**: `#F5F5F7`
* **Text Ash Silver (Muted)**: `#A0A3AB`

### 3.2 Typography Tokens
* **Display & Brand Headers**: *Cinzel* / *Oswald* (Bold, uppercase, `letter-spacing: 0.15em–0.25em`)
* **Body & Telemetry**: *Inter* / *Plus Jakarta Sans* (Weights: 400, 500, 700)
* **Monospace / Metric Chips**: *Space Mono* / *JetBrains Mono* (Weights: 500)

### 3.3 Geometry & Elevation
* **Corner Roundness**: `ROUND_EIGHT` (8dp for standard cards, 12dp for modal bottom sheets, 4dp for micro-badges, 999dp for pill selectors).
* **Borders**: 1dp solid `#C29B38/30` or `#1B1D22`.

---

## 4. Stitch MCP Screen Registry

All 10 MVP screens have been designed, generated, and verified in Stitch Project `14386677080154071069`:

| Screen ID | Screen Name | Role in MVP | Generated Artifact / Download Link |
| :--- | :--- | :--- | :--- |
| `90ff7f5167744050b5960af1588a502c` | **SCR-002: Onboarding (The Spartan Reveal)** | Interactive before/after split phone comparison (Stock vs Spartan). | [View Screenshot](https://lh3.googleusercontent.com/aida/AEtjO1UKTC3t6xIQP6K7QRgu_rQzim079UkxI37d6gnmhZ7nT5bdtyBOzPZa3uH0c2OMB3IEyRMo66tp1kVOCVAPsKiomaGqDDc8690m2NhKnblc0r3eGN_EJZhUhFw7dWiIR3Gnyoeby0895WbImFV5hWanZsLODJNgcG6H8HFXR5n9Hzkd20DtSiWlK0i2mWFvcr7UKmlLUpZTT4SlQv6DIA2lJRF60iJlUObE8UUJVfSWDlUVdHbh3yLlIpA4) |
| `0e8957b32c06495696a5d5070d311626` | **SCR-003: Home Dashboard (The Phalanx)** | Daily featured drop hero, category filters, 2-column theme grid, bottom navigation. | [View Screenshot](https://lh3.googleusercontent.com/aida/AEtjO1XfDKuOq4FG4z59NfP4wQCcs0TAZ5nMJhOmPeZowQ_DImnVsJNR0FQKsBE0rvN6gGKy3BdfppTOA0qwr5X4h32eyX6joDFAU65SkCUROF__v6Mj65FBAkP6Cyp0K_qsKWUOxm6rQnhQ2NzWJwvyfmo_KPzdA7dGjoD9dAepBykWwAeRtRQ7SFpRL5QXWybJvrvRRpZHvSH5Xd9W7q4V68loy4M2W1yA9qCRNH1n-SEdnS1rUjJko4MeAtJq) |
| `e8e8a396fb2947dea513bb14ab1ef6f7` | **SCR-004: Theme Detail & Live Preview** | Interactive device mockup with Home/Lock toggles, blueprint breakdown, 1-tap apply CTA. | [View Screenshot](https://lh3.googleusercontent.com/aida/AEtjO1VwzC9We8w2OhfaMZ2VEMXufPsHC6R3QKS5fv0DWJxCDf1rN3pRXJQ2_IYjHvSEcxLO9_ZDAiNK-FBEbh8OTC3ains4CKwdfrjx1zVWWXlAkEMW9w7DHpeB9eIYH2qsv0MSnfynZuXBlIaErQPCT1ZCfHUg5gXFLmc7dPrZ5qtLabyZnRjzMDTr0sZAVroEEtjk9OnfCSHJH5THWHyWljccI3TQBRK36RBRKHe_wgllhneHNlhS0vUcfPQ) |
| `b028d16ebe5949ef8b39b5acc7733bf8` | **SCR-005: Wallpaper Vault** | Curated 4K AMOLED gallery, gyro parallax filter chips, zero-compression certifications. | [View Screenshot](https://lh3.googleusercontent.com/aida/AEtjO1VJ3l1z5RERwFfEZdRqmDDwNIuSV-o3IaGDoyLMVSqNy8ZWIvjtBr7x-fS2EIhkU6ljlfluwYz2H9IM-aBQyunFYgIlRAvh0BQwb5imIw14NXBFPFhAC5ADkbXk0MH2zsZy1bULi4qR_KC_MZ0GZazs-JGg4Rq-qf6QHO11hIzbLuI8s4nfgdXzNg95N6VpwWwgwmdr43ywdiVV88PfmtOlRCnkeX6IbencF0Wx93ZcqL8itc-qs7K42Jjz) |
| `906d16f32dc647be8152f722e54e1cf9` | **SCR-006: Fullscreen Preview & Apply Modal** | Full-bleed 4K view, simulated UI overlay toggle (eye icon), 3-way apply drawer. | [View Screenshot](https://lh3.googleusercontent.com/aida/AEtjO1WDs25O187_yDARGer7hWySbdbz5LaOKhu4PHzVsHg8s8HLqCvBxuFyVhGB1WxsyV2Hu8CO-L98bp1nRVSLqUIP0g3f1J1fMMqQN7oOpGDD61_owEKl6i7CV4gEUIp-jIODzAN1FAuBcx6rAXhWLsVVndUSPnrQjcMF5Rtf0deC9XA_na1LiSU795G1KUoZpGzHux5fTOObecqphAsW4ZoSpIwbDwN1eXjPUfMZdyoasZ2rhQpPZpoVEHsS) |
| `85e33e992ae74f71a5a01a2b666221bd` | **SCR-007: Spartan Widgets Forge** | Jetpack Glance showcase: Stoic Clock, Shield Battery Gauge, Maxim Ticker, 1-tap pin. | [View Screenshot](https://lh3.googleusercontent.com/aida/AEtjO1VVr9_7V8qaRUA2RemDz54DmW9lWXxkuJYrEc5jJY3mR28y1MAShXjW4_-Z9wlXcxnJgYgIKW27dDtweK5qwDejwbCZwJoVVausQVqEwyuaVCaEiYV4HiqOpRoXd4V9y0QCc8qQXBa-fc-BUvVD0IomVbU6dW1cCuMVQCwHh8OnummhJdtOWodPDZprXp4_c37IyN0ZKKH5vGA5-CtMvA2SJvLPfWqp71k0-KVN7s9TmLbKnTmK98ifzE0) |
| `99f69724905342868e7dcc150caac233` | **SCR-008: Vanguard Pass Paywall** | High-converting lifetime tribute paywall (₹199 / $2.99), 6 core deliverables, Google Play trust. | [View Screenshot](https://lh3.googleusercontent.com/aida/AEtjO1WYNc-fa-V77yCbmEtUj6K5TG0j9U8dHaJ_eg7jJL6KRBRWSp1uOfMRFAE0wouazi03eP9VTDZCIVvkncdx0JyHM8NmgCfZU2PWkz38fjlC51ReM-NgymnysX62n7phPajZSjsOZI-NvWIBcgrpRRfLXjgo5IYU5ssnaX88m-AOqNBuEaGnVBhgJIi4UCJYZIPcwIDrtdYxf_5LUhq6HQW4xF1L47e3P_woC4I_RrkyGRznZdE7nkEe6Fs) |
| `4a7a0388e80644aba7ef0e60a9fea9bf` | **SCR-009: Icon Pack Forge & Styler** | 400+ vector glyphs, live dock preview, launcher compatibility chips, palette switcher. | [View Screenshot](https://lh3.googleusercontent.com/aida/AEtjO1W6lP7avbHc9o3gHeRfQSZeZFIdGqta9gaW6cCNtovkoYrdX4qfpBnRI-WcmTUtKG2sy8Ug6W8BSKyPbyRWPwGQzGI18_tfH2kLKzBOBjCG0ip4c1E7jEk-M3vaW_w9cwLxcXhRhu5OiA8rUJKB97D-OuiHD2QsVMrND9d6csrvVd9PvqY6C2iXxhKnqNsP562uz_A-iIKZUrEbn_q75H7PaQblmorABaBLppR6dBZ3UOgSI0DPIcDoC13v) |
| `d9ec56f33bb943569648908cc2c3080a` | **SCR-010: 1-Tap Apply Success Modal** | Real-time sequence feedback (Wallpaper set, Widget pinned, Icons ready), exit CTA. | [View Screenshot](https://lh3.googleusercontent.com/aida/AEtjO1UBYVJR_RY8qgBiYY-NIQDRHazRwZEhJ9aSm7nkPDh-ZKLKAvAdoZPbbEaI620aCU4rpNqtSAPnyqXJfL5s8zNNLM_K_klLOhgr5jO9bNFIsLy1NmKfLf-6WzPd6W2Kv8uDCWWO5JK38ykyY0fXmcMqtWh-isc9SslH4vw_ox1Pxl08wrCp34UzLH_8YPB5YHXTLiGueencHI7qdMc46Wlxfkpqjt0IbaRhHB2K8FvFd53jBqp2MWzL9aua) |
| `5378d88566804f85a95a34ab892a6114` | **SCR-011: Settings & Vault Management** | AMOLED battery-saving armor toggles, cache manager, restore purchase, Spartan manifesto. | [View Screenshot](https://lh3.googleusercontent.com/aida/AEtjO1U6fx2S1_3rJmjn5Wgt3IQ_iRPPKsWD6p_SOMokU86-NRj57PT9qivSLEh6tNS3mIIZpx4eYAZZ-Du62zQARgFkmhmFLr3s1IbAkm0WcWHbo8VJY9rCHG5aCsXY1xwCp9dJNFw4CUK2c2FW7reZreqhZ1bfQleX_xhMldl7SaIqPvgTBrvJsZYtE2McHjZ5lFLa7KDM5g7X48IqogJ6X9y80D1O-R4_3eJPFtRIDBC1r4ixXsgIlbMkNyHz) |
