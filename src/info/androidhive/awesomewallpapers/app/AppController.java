package info.androidhive.awesomewallpapers.app;

import info.androidhive.awesomewallpapers.util.LruBitmapCache;
import info.androidhive.awesomewallpapers.util.PrefManager;
import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.myapp.awesomewallpaper.MainActivity;
import com.parse.Parse;
import com.parse.PushService;

public class AppController extends Application {

	public static final String TAG = AppController.class.getSimpleName();

	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;
	LruBitmapCache mLruBitmapCache;

	private static AppController mInstance;
	private PrefManager pref;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		pref = new PrefManager(this);
		Parse.initialize(this, "buVmiyAzQVjfoeVhaSqC79CIVtzc0jX6vSjtlj1N", "rb82dlO7FBTh4rp5MUpCDinaHniw3IHkIKKxBq65");
		PushService.setDefaultPushCallback(this,MainActivity.class);
	}

	public static synchronized AppController getInstance() {
		return mInstance;
	}

	public PrefManager getPrefManger() {
		if (pref == null) {
			pref = new PrefManager(this);
		}

		return pref;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}

	public ImageLoader getImageLoader() {
		getRequestQueue();
		if (mImageLoader == null) {
			getLruBitmapCache();
			mImageLoader = new ImageLoader(this.mRequestQueue, mLruBitmapCache);
		}

		return this.mImageLoader;
	}

	public LruBitmapCache getLruBitmapCache() {
		if (mLruBitmapCache == null)
			mLruBitmapCache = new LruBitmapCache();
		return this.mLruBitmapCache;
	}

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
}
