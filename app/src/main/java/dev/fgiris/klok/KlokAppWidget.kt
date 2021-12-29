package dev.fgiris.klok

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.glance.GlanceModifier
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import dev.fgiris.klok.action.LogTimeActionCallback

class KlokAppWidget : GlanceAppWidget() {
    @Composable
    override fun Content() {
        Text(
            modifier = GlanceModifier.clickable(
                actionRunCallback<LogTimeActionCallback>()
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