package com.myapp.awesomewallpaper;

import info.androidhive.awesomewallpapers.app.AppController;
import info.androidhive.awesomewallpapers.util.LruBitmapCache;

import java.io.IOException;

import com.myapp.awesomewallpapers.R;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;

public class Walser extends Service{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	   
	     
		//Bitmap b = getBitmap();
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
