package com.orobator.minimaldigitalclock;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class WidgetUpdaterService extends Service {
    public WidgetUpdaterService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        RemoteViews widgetRemoteViews = MinimalClockWidget.getUpdatedView(this);
        ComponentName widgetComponentName = new ComponentName(this, MinimalClockWidget.class);
        AppWidgetManager widgetManager = AppWidgetManager.getInstance(this);
        widgetManager.updateAppWidget(widgetComponentName, widgetRemoteViews);

        return super.onStartCommand(intent, flags, startId);
    }
}
