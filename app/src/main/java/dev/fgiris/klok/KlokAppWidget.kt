package dev.fgiris.klok

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

class KlokAppWidget : GlanceAppWidget() {
    @Composable
    override fun Content() {
        //        val colorTarget = remember { mutableStateOf(Color.Blue) }
//        val animatedColor = animateColorAsState(targetValue = colorTarget.value)
//
//        LaunchedEffect(Unit) {
//            colorTarget.value = Color.Red
//        }
        Text(
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