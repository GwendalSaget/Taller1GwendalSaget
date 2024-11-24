import com.example.proyecto1_pantalla.R

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class BookListWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val views = RemoteViews(context.packageName, R.layout.widget_book_list)
        val sharedPreferences = context.getSharedPreferences("PersonPrefs", Context.MODE_PRIVATE)
        val personCount = sharedPreferences.getInt("person_count", 0)
        views.setTextViewText(R.id.person_count_text, "Numero de personas: $personCount")
        val intent = Intent(context, BookListWidgetProvider::class.java)
        intent.action = "com.example.proyecto1_pantalla.UPDATE_WIDGET"
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        views.setOnClickPendingIntent(R.id.update_button, pendingIntent)
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action == "com.example.proyecto1_pantalla.UPDATE_WIDGET") {
            val sharedPreferences = context.getSharedPreferences("PersonPrefs", Context.MODE_PRIVATE)
            var personCount = sharedPreferences.getInt("person_count", 0)
            personCount++
            with(sharedPreferences.edit()) {
                putInt("person_count", personCount)
                apply()
            }
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val componentName = ComponentName(context, BookListWidgetProvider::class.java)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(componentName)
            for (appWidgetId in appWidgetIds) {
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.person_count_text)
            }
        }
    }
}

