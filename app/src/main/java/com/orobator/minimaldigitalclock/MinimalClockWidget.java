package com.orobator.minimaldigitalclock;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link MinimalClockWidgetConfigureActivity MinimalClockWidgetConfigureActivity}
 */
public class MinimalClockWidget extends AppWidgetProvider {

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId) {
//        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, getUpdatedView(context));
    }

    public static RemoteViews getUpdatedView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.minimal_clock_widget);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        views.setTextViewText(R.id.time_TextView, timeFormat.format(cal.getTime()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM d");
        views.setTextViewText(R.id.date_TextView, dateFormat.format(cal.getTime()));

        return views;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
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

