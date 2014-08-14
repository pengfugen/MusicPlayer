package app.android.musicserver;

import android.net.Uri;
import android.util.Log;

public class Util {
	static public Uri getPath()
	{
		String path = "/storage/sdcard0/Music/never grow old.mp3";
		Uri uri = Uri.parse("file://" + path);
		Log.i("PFG", "uri ==> " + uri.toString());
		return uri;
	}
}
