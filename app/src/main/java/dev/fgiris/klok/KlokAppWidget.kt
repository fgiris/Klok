package dev.fgiris.klok

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.actionStartService
import androidx.glance.appwidget.updateAll
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import dev.fgiris.klok.service.TextUpdateService

class KlokAppWidget : GlanceAppWidget() {
    companion object {
        val KLOK_SHARED_PREF = "KLOK_SHARED_PREF"
        val KLOK_TEXT_KEY = "KLOK_TEXT_KEY"

        suspend fun updateWidget(context: Context) = KlokAppWidget().updateAll(context)
    }

    @Composable
    override fun Content() {
        Text(
            modifier = GlanceModifier.clickable(
                actionStartService<TextUpdateService>()
            ),
            text = getText(LocalContext.current),
            style = TextStyle(
                color = ColorProvider(Color.Blue)
            )
        )
    }

    private fun getText(context: Context): String {
        val sharedPref = context.getSharedPreferences(KLOK_SHARED_PREF, Context.MODE_PRIVATE)
        return sharedPref.getString(KLOK_TEXT_KEY, "No text was set") ?: "No text was set"
    }
}

class KlokAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = KlokAppWidget()
}