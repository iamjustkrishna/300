# 300 — Edge Cases, Permissions & System Fallbacks

> **Document Version**: 2.0.0 (Phase 1 Approved Baseline)  
> **Status**: APPROVED

---

## 1. System Permissions Matrix & Denial Handling

| Capability | Android API | Permission Key | Denial UX Fallback |
| :--- | :--- | :--- | :--- |
| **Set Wallpaper** | Android 10–15 | `android.permission.SET_WALLPAPER` (Normal) | Auto-granted by OS. If OEM security prevents direct apply, triggers fallback `ACTION_ATTACH_DATA` intent to let system crop picker handle it. |
| **Download Raw 4K to Gallery** | Android 10+ (API 29+) | Scoped Storage (`MediaStore.Images.Media`) | Zero runtime storage permissions needed on API 29+! Files are inserted directly into `Pictures/300_Spartan`. |
| **Pin Glance Widget** | Android 10+ | `AppWidgetManager.requestPinAppWidget` | If launcher rejects pin intent (e.g. strict enterprise launcher), app shows a clean instructional dialog: *"Long-press your home screen → Widgets → Select 300: Spartan Widgets"*. |
| **Haptic Feedback** | All APIs | `VIBRATOR_SERVICE` | Gracefully ignored on devices without linear resonant actuators (no crashes). |

---

## 2. Battery & Power Conservation Edge Cases

### 2.1 Low Battery Armor Protocol (< 20% Battery Level)
* When device battery level drops below 20%, the app automatically:
  1. Pauses the 3D Gyroscope sensor listener to eliminate CPU/sensor wake locks.
  2. Disables animated ember particle effects.
  3. Enforces static pure OLED black background (`#080809`).
  4. Drops Glance widget refresh rate from 15 minutes to 60 minutes.

### 2.2 AMOLED True Black Verification
* All static wallpapers undergo automated pixel histogram verification: at least 95% of pixels must have RGB values `<= 12` (sub-pixel shutoff threshold for Samsung/BOE OLED panels).

---

## 3. Network & Offline Edge Cases

1. **Total Airplane Mode / Offline Launch**:
   - The app reads directly from the local Room database and DataStore.
   - 3 Starter Themes and 18 Wallpapers are bundled as compressed vector/WebP assets locally in the APK assets; the app is 100% functional on cold start without an internet connection.
2. **Network Drops Mid-Download**:
   - Downloads use OkHttp with `Range` resume support and MD5 hash checksum verification. Broken files are pruned automatically; partial corrupted images are never written to disk.

---

## 4. Google Play Billing Edge Cases

1. **User Purchases Vanguard Pass on Phone A, Installs on Phone B**:
   - Tapping "RESTORE" queries `BillingClient.queryPurchasesAsync()`. If a valid `vanguard_lifetime_pass` token is found, all features unlock instantly.
2. **Payment Cancelled / Pending (e.g., UPI / Family Approval Pending)**:
   - UI displays a subtle "Payment Pending" banner without granting access until Google Play confirms `PurchaseState.PURCHASED`.
3. **Refund Processed via Google Play**:
   - On next launch, `queryPurchasesAsync()` detects token revocation and gracefully reverts user to the Free tier without crashing or wiping local cache.
