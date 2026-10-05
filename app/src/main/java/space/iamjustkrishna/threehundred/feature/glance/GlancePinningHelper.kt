package space.iamjustkrishna.threehundred.feature.glance

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.widget.Toast

/**
 * Helper to pin native Jetpack Glance / AppWidget components to the Android home screen.
 */
object GlancePinningHelper {

    fun pinMonolithClock(context: Context) {
        pinWidget(
            context = context,
            receiverClass = MonolithClockReceiver::class.java,
            widgetName = "Monolith Clock"
        )
    }

    fun pinShieldBattery(context: Context) {
        pinWidget(
            context = context,
            receiverClass = ShieldBatteryReceiver::class.java,
            widgetName = "Shield Battery"
        )
    }

    fun pinCreedTicker(context: Context) {
        pinWidget(context, CreedTickerReceiver::class.java, "Creed Ticker")
    }

    fun pinWarCalendar(context: Context) {
        pinWidget(context, WarCalendarReceiver::class.java, "War Calendar")
    }

    private fun pinWidget(
        context: Context,
        receiverClass: Class<*>,
        widgetName: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val appWidgetManager = context.getSystemService(AppWidgetManager::class.java)
            val componentName = ComponentName(context, receiverClass)
            if (appWidgetManager != null && appWidgetManager.isRequestPinAppWidgetSupported) {
                try {
                    appWidgetManager.requestPinAppWidget(componentName, null, null)
                    Toast.makeText(context, "Pinning $widgetName to Home...", Toast.LENGTH_SHORT).show()
                    return
                } catch (e: Exception) {
                    // Fall back to toast instruction below
                }
            }
        }
        Toast.makeText(
            context,
            "$widgetName configured. Long-press your home screen to place widget.",
            Toast.LENGTH_LONG
        ).show()
    }
}
