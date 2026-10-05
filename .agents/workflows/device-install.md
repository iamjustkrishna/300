# Workflow: Device Install & Live Hardware Deployment

## Objective
Seamlessly discover connected Android devices (USB physical, Wi-Fi wireless, and emulators), facilitate wireless pairing and connection over Wi-Fi on Android 11+, install the compiled 300 APK, and launch the application.

## Leading Agent
`device-installer-agent.md`

## Execution Protocol

### Step 1: Device Discovery
Run the discovery command:
```powershell
powershell -ExecutionPolicy Bypass -File .\scripts\device_helper.ps1 -Action discover
```
Or directly:
```bash
adb devices -l
```
Inspect the output and categorize devices:
1. **USB Connected Devices**: Hardware serial without IP format, not emulator (e.g. `ce05171542a2b02302`).
2. **Wi-Fi Connected Devices**: IP:port format (e.g. `192.168.1.50:5555`).
3. **Emulators**: Identifier starts with `emulator-` (e.g. `emulator-5554`).
4. **Unauthorized Devices**: Status indicates `unauthorized`. Prompt user to grant host RSA key approval on the phone's screen.

### Step 2: Target Selection or New Wireless Connection
- If devices are discovered:
  - Present the list to the user and prompt them to select the target device, OR choose to connect a new Wi-Fi device.
- If no devices are discovered:
  - Prompt the user to plug in via USB (with USB Debugging enabled) OR connect via Wi-Fi (Wireless Debugging).

### Step 3: Wi-Fi Pairing & Connection (If user chooses Wireless)
Guide the user to:
1. Open **Settings** -> **Developer options** -> **Wireless debugging** -> **Enable**.
2. Tap **"Pair device with pairing code"**.
3. Collect:
   - IP address and pairing port (e.g., `192.168.1.50:37821`)
   - 6-digit Wi-Fi pairing code (e.g., `123456`)
   - Primary connect port (shown on main Wireless debugging screen, if different)
4. Execute pairing:
```powershell
powershell -ExecutionPolicy Bypass -File .\scripts\device_helper.ps1 -Action pair -Ip <IP> -PairPort <PAIR_PORT> -PairCode <PAIR_CODE>
```
5. Execute connection:
```powershell
powershell -ExecutionPolicy Bypass -File .\scripts\device_helper.ps1 -Action connect -Ip <IP> -ConnectPort <CONNECT_PORT>
```
6. Verify status and confirm connection to the user.

### Step 4: Build & Deploy APK
Ensure the debug APK is built:
```powershell
if (!(Test-Path "app/build/outputs/apk/debug/app-debug.apk")) {
    .\gradlew assembleDebug
}
```
Install onto the chosen target device:
```powershell
powershell -ExecutionPolicy Bypass -File .\scripts\device_helper.ps1 -Action install -DeviceId "<DEVICE_ID>"
```
Or directly:
```bash
adb -s <DEVICE_ID> install -r app/build/outputs/apk/debug/app-debug.apk
```

### Step 5: Launch & Verification
Launch the main activity:
```bash
adb -s <DEVICE_ID> shell am start -n space.iamjustkrishna.threehundred/.MainActivity
```
Verify that the activity launched with `Status: ok` and app is live on screen.
