package app.android.musicplayer;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class CoverActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cover);
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run() {
				Intent intent = new Intent(CoverActivity.this, PlayerActivity.class);
				startActivity(intent);
				finish();
			}
			
		}, 2000);
	}
}
