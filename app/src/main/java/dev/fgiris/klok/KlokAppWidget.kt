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
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class KlokAppWidget : GlanceAppWidget() {
    @Composable
    override fun Content() {
        LaunchedEffect(Unit) {
            withContext(NonCancellable) {
                delay(1000L)
                println("I've just delayed for 1 sec because I'm non-cancellable")
            }
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