package dev.fgiris.klok

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

class KlokAppWidget : GlanceAppWidget() {
    @Composable
    override fun Content() {
        LaunchedEffect(Unit) {
            println("Hello from LaunchEffect")
        }

        Text(
            modifier = GlanceModifier.clickable(
                actionStartActivity<GlanceActionCallbackActivity>()
            ),
            text = "Hello from a Glance widget!",
            style = TextStyle(
                color = ColorProvider(Color.Blue)
            )
        )
    }
}

class KlokAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = KlokAppWidget()
}