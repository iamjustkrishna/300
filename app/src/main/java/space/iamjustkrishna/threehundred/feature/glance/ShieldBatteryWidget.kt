package space.iamjustkrishna.threehundred.feature.glance

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.LinearProgressIndicator
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

class ShieldBatteryWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val intent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        val batteryPct = if (level >= 0 && scale > 0) (level * 100 / scale) else 100
        val status = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
        val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL

        provideContent {
            ShieldBatteryContent(
                batteryPct = batteryPct,
                isCharging = isCharging
            )
        }
    }
}

@Composable
fun ShieldBatteryContent(
    batteryPct: Int,
    isCharging: Boolean
) {
    val accentColor = if (isCharging) {
        Color(0xFFC5A869) // Satin gold
    } else if (batteryPct <= 20) {
        Color(0xFFA62820) // Crimson power arc for low battery
    } else {
        Color(0xFFC5A869) // Satin gold
    }

    val statusLabel = if (isCharging) {
        "CHARGING · VANGUARD"
    } else if (batteryPct <= 20) {
        "CRITICAL · DEFEND SHIELD"
    } else {
        "SHIELD POWER · NOMINAL"
    }

    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .cornerRadius(18.dp)
            .background(Color(0xFF121216))
            .padding(14.dp)
    ) {
        Column(
            modifier = GlanceModifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header: Spartan Lambda Badge
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Λ · PHALANX",
                    style = TextStyle(
                        color = ColorProvider(accentColor),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Spacer(modifier = GlanceModifier.height(4.dp))

            // Prominent Battery Percentage
            Text(
                text = "$batteryPct%",
                style = TextStyle(
                    color = ColorProvider(Color(0xFFF5F3EF)),
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = GlanceModifier.height(2.dp))

            // Status label
            Text(
                text = statusLabel,
                style = TextStyle(
                    color = ColorProvider(accentColor),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = GlanceModifier.height(8.dp))

            // Battery Gauge Bar
            LinearProgressIndicator(
                progress = batteryPct / 100f,
                modifier = GlanceModifier.fillMaxWidth().height(6.dp),
                color = ColorProvider(accentColor),
                backgroundColor = ColorProvider(Color(0xFF1A1A22))
            )

            Spacer(modifier = GlanceModifier.height(8.dp))

            // Bottom Stoic Quote
            Text(
                text = "POWER FUELS A QUIETER PATH.",
                style = TextStyle(
                    color = ColorProvider(Color(0xFF8E8E98)),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}
