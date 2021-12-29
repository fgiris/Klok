package dev.fgiris.klok.action

import android.content.Context
import android.util.Log
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback

/**
 * A simple callback to log the current time.
 */
class LogTimeActionCallback : ActionCallback {
    override suspend fun onRun(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        Log.v("Klok", System.currentTimeMillis().toString())
    }
}