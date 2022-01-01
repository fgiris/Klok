package dev.fgiris.klok.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.widget.Toast
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
        val sharedPref = getSharedPreferences(KlokAppWidget.KLOK_SHARED_PREF, Context.MODE_PRIVATE)
        sharedPref
            .edit()
            .putString(KlokAppWidget.KLOK_TEXT_KEY, "The text has been set from the service")
            .apply()

        KlokAppWidget.updateWidget(this@TextUpdateService)
    }
}