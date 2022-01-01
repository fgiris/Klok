package dev.fgiris.klok.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import androidx.glance.state.GlanceState
import androidx.glance.state.PreferencesGlanceStateDefinition
import dev.fgiris.klok.KlokAppWidget
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class TextUpdateService : Service() {
    private val scope = CoroutineScope(newSingleThreadContext("Klok"))

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Service started and updating widgets in 2 sec", Toast.LENGTH_SHORT).show()

        scope.launch {
            delay(2000)
            setTextAndUpdateWidget()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private suspend fun setTextAndUpdateWidget() {
        // Update the data store
        GlanceState.updateValue(
            this,
            PreferencesGlanceStateDefinition,
            KlokAppWidget.KLOK_FILE_KEY
        ) { preferences ->
            val mutablePreferences = preferences
                .toMutablePreferences()

            mutablePreferences[KlokAppWidget.KLOK_TEXT_KEY] =
                "The text has been set from the service"

            mutablePreferences.toPreferences()
        }

        // Update the widget
        KlokAppWidget.updateWidget(this@TextUpdateService)
    }
}