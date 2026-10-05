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
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import java.util.Calendar

/** A compact 4x1 native Glance maxim. The message changes once per day. */
class CreedTickerWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val creed = CREEDS[Calendar.getInstance().get(Calendar.DAY_OF_YEAR) % CREEDS.size]
        provideContent { CreedTickerContent(creed) }
    }

    companion object {
        internal val CREEDS = listOf(
            "WIN IN SILENCE.",
            "DISCIPLINE IS FREEDOM.",
            "STAND WHERE YOU ARE.",
            "DEPTH OVER NOISE.",
            "KEEP YOUR SHIELD."
        )
    }
}

@Composable
fun CreedTickerContent(creed: String) {
    Column(
        modifier = GlanceModifier.fillMaxSize().cornerRadius(18.dp)
            .background(Color(0xFF121216)).padding(horizontal = 18.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("\u039B  ·  DAILY CREED  ·  300", style = TextStyle(
            color = ColorProvider(Color(0xFFC5A869)), fontSize = 9.sp,
            fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
        ))
        Text(creed, style = TextStyle(
            color = ColorProvider(Color(0xFFF5F3EF)), fontSize = 16.sp,
            fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
        ))
    }
}
