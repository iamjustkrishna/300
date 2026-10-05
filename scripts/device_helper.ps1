# 300 - ADB Device Management & Installation Script
# Usage:
#   .\scripts\device_helper.ps1 -Action discover
#   .\scripts\device_helper.ps1 -Action pair -Ip 192.168.1.50 -PairPort 37821 -PairCode 123456
#   .\scripts\device_helper.ps1 -Action connect -Ip 192.168.1.50 -ConnectPort 41293
#   .\scripts\device_helper.ps1 -Action install -DeviceId "192.168.1.50:41293"

param (
    [Parameter(Mandatory=$true)]
    [ValidateSet("discover", "pair", "connect", "install")]
    [string]$Action,

    [string]$DeviceId = "",
    [string]$Ip = "",
    [int]$PairPort = 0,
    [string]$PairCode = "",
    [int]$ConnectPort = 0,
    [string]$ApkPath = "app/build/outputs/apk/debug/app-debug.apk",
    [switch]$Launch = $true
)

$ErrorActionPreference = "Stop"

function Check-Adb {
    $adbPath = (Get-Command adb -ErrorAction SilentlyContinue)
    if (-not $adbPath) {
        Write-Error "ERROR: 'adb' was not found on PATH. Please ensure Android SDK platform-tools is installed and added to PATH."
        exit 1
    }
}

function Get-Devices {
    Check-Adb
    $raw = adb devices -l
    $lines = $raw -split "`r?`n"
    
    $usbDevices = @()
    $wifiDevices = @()
    $emulators = @()
    $unauthorizedDevices = @()

    foreach ($line in $lines) {
        $trimmed = $line.Trim()
        if ([string]::IsNullOrWhiteSpace($trimmed) -or $trimmed.StartsWith("List of devices attached") -or $trimmed.StartsWith("*")) {
            continue
        }

        # Example format:
        # 192.168.1.50:5555  device product:oriole model:Pixel_6 device:oriole transport_id:1
        # ce05171542a2b02302  device product:star2qltezc model:SM_G9650 device:star2qlte transport_id:2
        # emulator-5554       device product:sdk_gphone64_arm64 model:sdk_gphone64_arm64 device:emu64a transport_id:3
        # 192.168.1.55:38291  unauthorized transport_id:4
        
        $tokens = $trimmed -split '\s+'
        if ($tokens.Count -ge 2) {
            $id = $tokens[0]
            $state = $tokens[1]
            
            $model = ""
            $product = ""
            foreach ($tok in $tokens) {
                if ($tok -like "model:*") { $model = $tok.Substring(6) }
                if ($tok -like "product:*") { $product = $tok.Substring(8) }
            }

            $deviceObj = [PSCustomObject]@{
                Id = $id
                State = $state
                Model = if ($model) { $model } else { $product }
                Raw = $trimmed
            }

            if ($state -eq "unauthorized") {
                $unauthorizedDevices += $deviceObj
            } elseif ($id -match '^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}:[0-9]+$') {
                $wifiDevices += $deviceObj
            } elseif ($id -like "emulator-*") {
                $emulators += $deviceObj
            } else {
                $usbDevices += $deviceObj
            }
        }
    }

    return [PSCustomObject]@{
        Usb = $usbDevices
        Wifi = $wifiDevices
        Emulators = $emulators
        Unauthorized = $unauthorizedDevices
    }
}

switch ($Action) {
    "discover" {
        $result = Get-Devices
        
        Write-Host "`n=== [300] ADB DEVICE DISCOVERY ===" -ForegroundColor Cyan
        
        Write-Host "`n[+] USB Connected Devices ($($result.Usb.Count)):" -ForegroundColor Yellow
        if ($result.Usb.Count -eq 0) {
            Write-Host "    (None detected)" -ForegroundColor DarkGray
        } else {
            foreach ($dev in $result.Usb) {
                Write-Host "    -> ID: $($dev.Id) | Model: $($dev.Model) | State: $($dev.State)" -ForegroundColor Green
            }
        }

        Write-Host "`n[+] Wi-Fi Connected Devices ($($result.Wifi.Count)):" -ForegroundColor Yellow
        if ($result.Wifi.Count -eq 0) {
            Write-Host "    (None detected)" -ForegroundColor DarkGray
        } else {
            foreach ($dev in $result.Wifi) {
                Write-Host "    -> Address: $($dev.Id) | Model: $($dev.Model) | State: $($dev.State)" -ForegroundColor Green
            }
        }

        if ($result.Emulators.Count -gt 0) {
            Write-Host "`n[+] Running Emulators ($($result.Emulators.Count)):" -ForegroundColor Yellow
            foreach ($dev in $result.Emulators) {
                Write-Host "    -> ID: $($dev.Id) | Model: $($dev.Model) | State: $($dev.State)" -ForegroundColor Cyan
            }
        }

        if ($result.Unauthorized.Count -gt 0) {
            Write-Host "`n[!] Unauthorized Devices ($($result.Unauthorized.Count)):" -ForegroundColor Red
            foreach ($dev in $result.Unauthorized) {
                Write-Host "    -> ID: $($dev.Id) (Check device screen to allow USB/Wireless debugging RSA fingerprint)" -ForegroundColor Magenta
            }
        }

        Write-Host "`n=================================`n" -ForegroundColor Cyan
        
        # Output JSON for programmatic parsing
        $json = $result | ConvertTo-Json -Depth 3
        Write-Output "JSON_DISCOVERY_RESULT_START"
        Write-Output $json
        Write-Output "JSON_DISCOVERY_RESULT_END"
    }

    "pair" {
        Check-Adb
        if (-not $Ip -or $PairPort -le 0 -or -not $PairCode) {
            Write-Error "Action 'pair' requires -Ip, -PairPort, and -PairCode."
            exit 1
        }
        $endpoint = "${Ip}:${PairPort}"
        Write-Host "[*] Pairing to $endpoint with code $PairCode..." -ForegroundColor Cyan
        $out = adb pair $endpoint $PairCode 2>&1
        Write-Host $out
        if ($out -like "*Successfully paired*") {
            Write-Host "[+] SUCCESS: Device paired successfully!" -ForegroundColor Green
        } else {
            Write-Host "[-] WARNING: Pairing response was unexpected. Review output above." -ForegroundColor Yellow
        }
    }

    "connect" {
        Check-Adb
        if (-not $Ip -or $ConnectPort -le 0) {
            Write-Error "Action 'connect' requires -Ip and -ConnectPort."
            exit 1
        }
        $endpoint = "${Ip}:${ConnectPort}"
        Write-Host "[*] Connecting to $endpoint..." -ForegroundColor Cyan
        $out = adb connect $endpoint 2>&1
        Write-Host $out
        if ($out -like "*connected to*" -or $out -like "*already connected*") {
            Write-Host "[+] SUCCESS: Connected to $endpoint!" -ForegroundColor Green
        } else {
            Write-Host "[-] WARNING: Connect response was unexpected. Review output above." -ForegroundColor Yellow
        }
    }

    "install" {
        Check-Adb
        if (-not (Test-Path $ApkPath)) {
            Write-Host "[*] APK not found at $ApkPath. Building assembleDebug..." -ForegroundColor Yellow
            .\gradlew assembleDebug
            if ($LASTEXITCODE -ne 0) {
                Write-Error "Gradle build failed."
                exit 1
            }
        }

        if (-not $DeviceId) {
            $devs = Get-Devices
            $allValid = @($devs.Usb) + @($devs.Wifi) + @($devs.Emulators)
            if ($allValid.Count -eq 0) {
                Write-Error "No active devices or emulators found to install APK. Please connect a device first."
                exit 1
            } elseif ($allValid.Count -eq 1) {
                $DeviceId = $allValid[0].Id
                Write-Host "[*] Automatically targeting single active device: $DeviceId ($($allValid[0].Model))" -ForegroundColor Cyan
            } else {
                Write-Error "Multiple devices detected. Please specify -DeviceId <id>."
                exit 1
            }
        }

        # If targeting a Wi-Fi device, ensure active connection in the current session
        if ($DeviceId -match '^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}:[0-9]+$') {
            Write-Host "[*] Ensuring active Wi-Fi connection to $DeviceId..." -ForegroundColor Cyan
            adb connect $DeviceId
        }

        Write-Host "[*] Installing $ApkPath onto device $DeviceId..." -ForegroundColor Cyan
        $installOut = adb -s $DeviceId install -r $ApkPath 2>&1
        Write-Host $installOut
        if ($installOut -like "*Success*") {
            Write-Host "[+] SUCCESS: App installed successfully on $DeviceId!" -ForegroundColor Green
            if ($Launch) {
                Write-Host "[*] Launching space.iamjustkrishna.threehundred/.MainActivity..." -ForegroundColor Cyan
                adb -s $DeviceId shell am start -n space.iamjustkrishna.threehundred/.MainActivity
                Write-Host "[+] App launched successfully on $DeviceId!" -ForegroundColor Green
            }
        } else {
            Write-Error "Installation failed: $installOut"
            exit 1
        }
    }
}
