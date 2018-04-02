package moe.studio.pixelbar

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews


/**
 * Implementation of App Widget functionality.
 */
class PixelBarMark1 : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.search_bar_mark_1)

            // Google Now Activity
            val now = context.packageManager
                    .getLaunchIntentForPackage("com.google.android.googlequicksearchbox")
            now.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            views.setOnClickPendingIntent(
                    R.id.appwidget_left,
                    PendingIntent.getActivity(context, 0, now, 0)
            )
            // Search
            val search = Intent("android.intent.action.VOICE_ASSIST")
            search.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            search.setClassName(
                    "com.google.android.googlequicksearchbox",
                    "com.google.android.googlequicksearchbox.SearchActivity"
            )
            views.setOnClickPendingIntent(
                    R.id.appwidget_bg,
                    PendingIntent.getActivity(context, 0, search, 0)
            )
            // Voice
            val voice = Intent("android.intent.action.VOICE_ASSIST")
            voice.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            views.setOnClickPendingIntent(
                    R.id.appwidget_right,
                    PendingIntent.getActivity(context, 0, voice, 0)
            )

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

