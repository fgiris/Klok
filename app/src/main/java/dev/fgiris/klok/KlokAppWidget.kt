package dev.fgiris.klok

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.glance.GlanceModifier
import androidx.glance.LocalSize
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionStartService
import androidx.glance.appwidget.updateAll
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import dev.fgiris.klok.service.TextUpdateService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class KlokAppWidget : GlanceAppWidget() {
    companion object {
        val KLOK_FILE_KEY = "KLOK_FILE_KEY"
        val KLOK_TEXT_KEY = stringPreferencesKey("KLOK_TEXT_KEY")

        private val Context.klokDataStore by preferencesDataStore(KLOK_FILE_KEY)

        suspend fun updateWidget(context: Context) = KlokAppWidget().updateAll(context)
    }

    override val sizeMode: SizeMode
        get() = SizeMode.Exact

    @Composable
    override fun Content() {
        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color.Red)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            val text = if (LocalSize.current.width > 150.dp) {
                "Width is greater than 150 dp"
            } else "Width is smaller than 150 dp"

            Text(
                modifier = GlanceModifier.clickable(
                    actionStartService<TextUpdateService>()
                ),
                text = "SizeMode.Exact\n\n" +
                        "Content function is called for each size \n\n" +
                        "$text\n\n${LocalSize.current}",
                style = TextStyle(
                    color = ColorProvider(Color.White)
                )
            )
        }
    }

    private fun getText(context: Context): String {
        return runBlocking {
            val preferences = context.klokDataStore.data.first()
            preferences[KLOK_TEXT_KEY] ?: "No text was set"
        }
    }
}

class KlokAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = KlokAppWidget()
}