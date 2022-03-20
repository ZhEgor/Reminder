package com.zhiroke.reminder.ui.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.zhiroke.reminder.R
import com.zhiroke.reminder.data.entity.Word

/**
 * Implementation of App Widget functionality.
 */
class RememberWordWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

//internal fun updateAppWidget(word: Word, context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
//    // Construct the RemoteViews object
//    val views = RemoteViews(context.packageName, R.layout.widget_remember_word)
//    views.setTextViewText(R.id.tv_spelling, word.spelling)
//    views.setTextViewText(R.id.tv_translation, word.spelling)
//    views.setTextViewText(R.id.tv_pronunciation, word.spelling)
//
//    // Instruct the widget manager to update the widget
//    appWidgetManager.updateAppWidget(appWidgetId, views)
//}