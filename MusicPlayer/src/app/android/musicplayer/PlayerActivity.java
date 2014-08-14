package app.android.musicplayer;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import app.android.musicserver.IMusicService;

public class PlayerActivity extends Activity implements OnClickListener{
	private String TAG = "PFG";
	private ImageButton btn_favorite,btn_pre,btn_play,btn_next,btn_repeate;
	private boolean isFavorite = false;
	private boolean isPlaying = false;
	private static int preNum = 0;
	private static int nextNum = 2;
	private IMusicService music_service;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);
		btn_favorite = (ImageButton)findViewById(R.id.btn_favorite);
		btn_pre = (ImageButton)findViewById(R.id.btn_pre);
		btn_play = (ImageButton)findViewById(R.id.btn_play);
		btn_next = (ImageButton)findViewById(R.id.btn_next);
		btn_repeate = (ImageButton)findViewById(R.id.btn_repeate);
		btn_favorite.setOnClickListener(this);
		btn_pre.setOnClickListener(this);
		btn_play.setOnClickListener(this);
		btn_next.setOnClickListener(this);
		btn_repeate.setOnClickListener(this);
		
		String action = "app.android.action.musicplay";
		Intent intent = new Intent(action);
		
		boolean result = bindService(intent, con, Context.BIND_AUTO_CREATE);
		Log.v(TAG, "bindService=======result:"+result);
		this.startService(intent);
	}
	
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		try {
			music_service.stop();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		unbindService(con);
		String action = "app.android.action.musicplay";
		Intent intent = new Intent(action);
		stopService(intent);
	}



	ServiceConnection con = new ServiceConnection()
	{

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			Log.v(TAG, "onServiceConnected=======ComponentName:"+name+" Binder:"+binder);
			music_service = IMusicService.Stub.asInterface(binder);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.v(TAG, "onServiceDisconnected=======ComponentName:"+name);
			music_service = null;
		}
		
	};
	
	@Override
	public void onClick(View view) {
		switch(view.getId())
		{
			case R.id.btn_favorite:
			{
				Drawable favorite;
				if(isFavorite)
				{
					isFavorite = false;
					favorite = getResources().getDrawable(R.drawable.favorite_grey);
				}
				else
				{
					isFavorite = true;
					favorite = getResources().getDrawable(R.drawable.favorite_red);
				}
				btn_favorite.setImageDrawable(favorite);
			}
			break;
			case R.id.btn_pre:
			{
				
			}
			break;
			case R.id.btn_play:
			{
				Drawable play;
				if(!isPlaying)
				{
					play = getResources().getDrawable(R.drawable.pause);
					try {
						music_service.play();
					} catch (RemoteException e) {
						
						e.printStackTrace();
					}
					
					isPlaying = true;
				}
				else
				{
					play = getResources().getDrawable(R.drawable.play);
					try {
						music_service.pause();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					isPlaying = false;
				}
				btn_play.setImageDrawable(play);
			}
			break;
			default:
				break;
		}
	}
}
