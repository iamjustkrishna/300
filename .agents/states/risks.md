# Risk Management Register

## Active Risks & Mitigations

| Risk ID | Description | Severity | Probability | Mitigation Strategy |
|---|---|---|---|---|
| RISK-01 | Memory bloat / OOM during high-resolution wallpaper decoding | High | Medium | Use Coil with automatic target size downsampling, hardware bitmaps (`Bitmap.Config.HARDWARE`), and strict LRU cache quotas. |
| RISK-02 | Wallpaper cropping/distortion across varying screen aspect ratios | High | High | Implement focal center crop math and interactive drag-to-crop preview before setting wallpaper. |
| RISK-03 | Glance widget update latency / battery throttling by OEM battery managers | Medium | Medium | Use event-driven widget updates via WorkManager / GlanceAppWidgetManager rather than aggressive polling loops. |
| RISK-04 | User streak lost due to timezone change or system clock alteration | Medium | Low | Store UTC timestamps alongside local calendar dates in Room; compute streaks using day intervals rather than raw hour math. |
| RISK-05 | Inadvertent copyright infringement of movie assets | Critical | Low | Strictly prohibit using movie screenshots, Gerard Butler imagery, official logos, or movie quotes. Rely strictly on classical Spartan history and original stylized artwork. |
