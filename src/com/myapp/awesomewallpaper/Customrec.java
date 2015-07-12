package com.myapp.awesomewallpaper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.myapp.awesomewallpapers.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class Customrec extends BroadcastReceiver{

	public static final String ACTION = "com.myapp.awesomewallpapers.custom";
	Context mContext;
	public String myURL = null;
	public String top = null;
	public String content = null;
	@Override
	public void onReceive(final Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "custom", Toast.LENGTH_LONG).show();
		
		JSONObject json = null;
		try {
			json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String myURL = null;
		try {
			myURL = json.getString("url");
			top = json.getString("top");
			content = json.getString("content");
			Toast.makeText(context,myURL, Toast.LENGTH_LONG).show();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread() {
			public void run() {
				showNotification(context);
				/*
				Notification notif = new Notification.Builder(context)
				 .setContentTitle("New photo from ")
				 .setContentText("hello")
				 .setSmallIcon(R.drawable.ic_launcher)
				 .setStyle(new Notification.BigPictureStyle()
				     .bigPicture(getBitmapFromURL(myURL)))
				 .build();*/
			}
		}.start();
		
		
	}
	private void showNotification(Context context) {
	    PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
	            new Intent(context, MainActivity.class), 0);

	    NotificationCompat.Builder mBuilder =
	            new NotificationCompat.Builder(context)
	            .setSmallIcon(R.drawable.ic_launcher)
	            .setContentTitle(top)
	            .setContentText(content)
	            ;
	    NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle();
	    s.bigPicture(getBitmapFromURL(myURL));
	    s.setSummaryText(content);
	   
	    mBuilder.setStyle(s);
	    
	    mBuilder.setContentIntent(contentIntent);
	    mBuilder.setDefaults(Notification.DEFAULT_SOUND);
	    mBuilder.setAutoCancel(true);
	    NotificationManager mNotificationManager =
	        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	    mNotificationManager.notify(1, mBuilder.build());

	}  
	
	public static Bitmap getBitmapFromURL(String src) {
	    try {
	        URL url = new URL(src);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
	        return myBitmap;
	    } catch (IOException e) {
	        // Log exception
	        return null;
	    }
	}
}
