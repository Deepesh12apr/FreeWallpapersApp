package com.myapp.awesomewallpaper;

import com.myapp.awesomewallpapers.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider{

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		for(int i = 0;i<appWidgetIds.length;i++)
		{
		 int appwidgetid = appWidgetIds[i];
		 
		 Intent intent = new Intent(context, SplashActivity.class);
		 
		 PendingIntent pending = PendingIntent.getService(context, 0, intent, 0);
		 
		 RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widget);
		 views.setOnClickPendingIntent(R.id.imageButton1, pending);
		 
		 appWidgetManager.updateAppWidget(appwidgetid, views);
		 
		}
	}
}
