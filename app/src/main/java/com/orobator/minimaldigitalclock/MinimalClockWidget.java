package com.orobator.minimaldigitalclock;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link MinimalClockWidgetConfigureActivity MinimalClockWidgetConfigureActivity}
 */
public class MinimalClockWidget extends AppWidgetProvider {
    private static PendingIntent updatePendingIntent;

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId) {

        Intent updateIntent = new Intent(context, WidgetUpdaterService.class);
        if (updatePendingIntent == null) {
            updatePendingIntent = PendingIntent.getService(context, 0, updateIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        }

//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC, Calendar.getInstance().getTimeInMillis(), A, updatePendingIntent);
//
//        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, getUpdatedView(context));
    }

    public static RemoteViews getUpdatedView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.minimal_clock_widget);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        views.setTextViewText(R.id.time_TextView, timeFormat.format(cal.getTime()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM d");
        views.setTextViewText(R.id.date_TextView, dateFormat.format(cal.getTime()));

        Intent updateIntent = new Intent(context, WidgetUpdaterService.class);
        if (updatePendingIntent == null) {
            updatePendingIntent = PendingIntent.getService(context, 0, updateIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        }

        views.setOnClickPendingIntent(R.id.widget, updatePendingIntent);

        return views;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

