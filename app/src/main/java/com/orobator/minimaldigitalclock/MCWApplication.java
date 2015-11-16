package com.orobator.minimaldigitalclock;

import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.RemoteViews;

/**
 * Created by AndrewOrobator on 11/16/15.
 */
public class MCWApplication extends Application {

    private final BroadcastReceiver timeBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            RemoteViews widgetRemoteViews = MinimalClockWidget.getUpdatedView(context);
            ComponentName widgetComponentName = new ComponentName(context, MinimalClockWidget.class);
            AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
            widgetManager.updateAppWidget(widgetComponentName, widgetRemoteViews);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter timeChangeIntentFilter = new IntentFilter();
        timeChangeIntentFilter.addAction(Intent.ACTION_TIME_TICK);
        timeChangeIntentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        timeChangeIntentFilter.addAction(Intent.ACTION_TIME_CHANGED);

        registerReceiver(timeBroadCastReceiver, timeChangeIntentFilter);
    }
}
