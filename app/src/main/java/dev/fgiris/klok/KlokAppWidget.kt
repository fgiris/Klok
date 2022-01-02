package dev.fgiris.klok

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
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

        // Responsive sizes
        val SMALL_BOX = DpSize(100.dp, 100.dp)
        val LARGE_BOX = DpSize(200.dp, 200.dp)

        private val Context.klokDataStore by preferencesDataStore(KLOK_FILE_KEY)

        suspend fun updateWidget(context: Context) = KlokAppWidget().updateAll(context)
    }

    override val sizeMode: SizeMode
        get() = SizeMode.Responsive(
            setOf(SMALL_BOX, LARGE_BOX)
        )

    @Composable
    override fun Content() {
        when (LocalSize.current) {
            SMALL_BOX -> SmallBox()
            LARGE_BOX -> LargeBox()
            else -> throw IllegalArgumentException("Invalid size")
        }
    }

    @Composable
    private fun SmallBox() {
        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "🙈")
        }
    }

    @Composable
    private fun LargeBox() {
        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color.Red)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = GlanceModifier.clickable(
                    actionStartService<TextUpdateService>()
                ),
                text = "SizeMode.Responsive\n\n" +
                        "🙊\n\n" +
                        "${LocalSize.current}",
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