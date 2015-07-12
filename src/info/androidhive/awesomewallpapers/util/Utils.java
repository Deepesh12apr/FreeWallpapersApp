package info.androidhive.awesomewallpapers.util;

import com.myapp.awesomewallpapers.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

public class Utils {
	private String TAG = Utils.class.getSimpleName();
	private Context _context;
	private PrefManager pref;

	// constructor
	public Utils(Context context) {
		this._context = context;
		pref = new PrefManager(_context);
	}

	/*
	 * getting screen width
	 */
	@SuppressWarnings("deprecation")
	public int getScreenWidth() {
		int columnWidth;
		WindowManager wm = (WindowManager) _context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();

		final Point point = new Point();
		try {
			display.getSize(point);
		} catch (java.lang.NoSuchMethodError ignore) {
			// Older device
			point.x = display.getWidth();
			point.y = display.getHeight();
		}
		columnWidth = point.x;
		return columnWidth;
	}

	public void saveImageToSDCard(Bitmap bitmap) {
		File myDi = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				pref.getGalleryName());
      File myDir = new File(Environment.getExternalStorageDirectory(), pref.getGalleryName());
		myDir.mkdirs();
		Random generator = new Random();
		int n = 10000;
		n = generator.nextInt(n);
		String fname = "Wallpaper-" + n + ".jpg";
		File file = new File(myDir, fname);
		if (file.exists())
			file.delete();
		try {
			FileOutputStream out = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			Toast.makeText(
					_context,
					_context.getString(R.string.toast_saved).replace("#",
							"\"" + pref.getGalleryName() + "\""),
					Toast.LENGTH_SHORT).show();
			Log.d(TAG, "Wallpaper saved to: " + file.getAbsolutePath());

		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(_context,
					_context.getString(R.string.toast_saved_failed),
					Toast.LENGTH_SHORT).show();
		}
	}

	public void setAsWallpaper(Bitmap bitmap) {
		try {
			WallpaperManager wm = WallpaperManager.getInstance(_context);

			wm.setBitmap(bitmap);
			Toast.makeText(_context,
					_context.getString(R.string.toast_wallpaper_set),
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(_context,
					_context.getString(R.string.toast_wallpaper_set_failed),
					Toast.LENGTH_SHORT).show();
		}
	}
	public void share()
	{
		/*Uri uri = Uri.fromFile(file);
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("image/*");

		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
		intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
		intent.putExtra(Intent.EXTRA_STREAM, uri);
		startActivity(Intent.createChooser(intent, "Share Cover Image"));*/
	}
}