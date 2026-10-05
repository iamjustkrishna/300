package space.iamjustkrishna.threehundred.feature.glance

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/** Seven-day tactical calendar with no dependency on an external calendar account. */
class WarCalendarWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val today = Calendar.getInstance()
        val dayFormat = SimpleDateFormat("EE", Locale.getDefault())
        val labels = (-3..3).map { offset ->
            (today.clone() as Calendar).apply { add(Calendar.DAY_OF_YEAR, offset) }
                .let { dayFormat.format(it.time).uppercase(Locale.getDefault()) to it.get(Calendar.DAY_OF_MONTH).toString() }
        }
        provideContent { WarCalendarContent(labels) }
    }
}

@Composable
fun WarCalendarContent(days: List<Pair<String, String>>) {
    Column(
        modifier = GlanceModifier.fillMaxSize().cornerRadius(18.dp)
            .background(Color(0xFF121216)).padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("\u039B  ·  TACTICAL WEEK", style = TextStyle(
            color = ColorProvider(Color(0xFFC5A869)), fontSize = 10.sp,
            fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
        ))
        Spacer(GlanceModifier.height(10.dp))
        Row(GlanceModifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            days.forEachIndexed { index, day ->
                Column(
                    modifier = GlanceModifier.defaultWeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(day.first, style = TextStyle(
                        color = ColorProvider(Color(0xFF8E8E98)), fontSize = 8.sp,
                        textAlign = TextAlign.Center
                    ))
                    Text(day.second, style = TextStyle(
                        color = ColorProvider(if (index == 3) Color(0xFFC5A869) else Color(0xFFF5F3EF)),
                        fontSize = 16.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
                    ))
                }
            }
        }
        Spacer(GlanceModifier.height(8.dp))
        Text("HOLD THE LINE.", style = TextStyle(
            color = ColorProvider(Color(0xFF8E8E98)), fontSize = 9.sp,
            textAlign = TextAlign.Center
        ))
    }
}
