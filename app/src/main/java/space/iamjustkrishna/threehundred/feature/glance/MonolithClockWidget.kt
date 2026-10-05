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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MonolithClockWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val now = Date()
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dateFormat = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
        val time = timeFormat.format(now)
        val date = dateFormat.format(now).uppercase(Locale.getDefault())

        provideContent {
            MonolithClockContent(
                time = time,
                date = date
            )
        }
    }
}

@Composable
fun MonolithClockContent(
    time: String,
    date: String
) {
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
            // Roman Numeral Top Marker
            Text(
                text = "XII",
                style = TextStyle(
                    color = ColorProvider(Color(0xFFC5A869)),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = GlanceModifier.height(4.dp))

            // Main Digital Readout
            Text(
                text = time,
                style = TextStyle(
                    color = ColorProvider(Color(0xFFF5F3EF)),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = GlanceModifier.height(2.dp))

            // Date Readout
            Text(
                text = date,
                style = TextStyle(
                    color = ColorProvider(Color(0xFFC5A869)),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = GlanceModifier.height(6.dp))

            // Roman Numeral Horizontal Track
            Row(
                modifier = GlanceModifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "IX",
                    style = TextStyle(
                        color = ColorProvider(Color(0xFF9E8045)),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = GlanceModifier.defaultWeight())
                Text(
                    text = "· 300 ·",
                    style = TextStyle(
                        color = ColorProvider(Color(0xFF585862)),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = GlanceModifier.defaultWeight())
                Text(
                    text = "III",
                    style = TextStyle(
                        color = ColorProvider(Color(0xFF9E8045)),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }

            Spacer(modifier = GlanceModifier.height(6.dp))

            // Bottom Stoic Creed
            Text(
                text = "TIME KEEPS BETTER COMPANY HERE.",
                style = TextStyle(
                    color = ColorProvider(Color(0xFF8E8E98)),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = GlanceModifier.height(2.dp))

            // Roman Numeral Bottom Marker
            Text(
                text = "VI",
                style = TextStyle(
                    color = ColorProvider(Color(0xFFC5A869)),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}
