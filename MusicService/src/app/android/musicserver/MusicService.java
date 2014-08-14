package app.android.musicserver;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


public class MusicService extends Service {

	private static String TAG = "PFG";
	private MediaPlayer player;
	class MyStubService extends IMusicService.Stub
	{
		@Override
		public void play() throws RemoteException {
			Log.v(TAG, "==================play()");
			player.start();
			
		}

		@Override
		public void pause() throws RemoteException {
			Log.v(TAG, "==================pause()");
			player.pause();
		}

		@Override
		public void stop() throws RemoteException {
			Log.v(TAG, "==================stop()");
			player.stop();
		}

		@Override
		public int getDuration() throws RemoteException {
			Log.v(TAG, "==================getDuration():"+getDuration());
			return player.getDuration();
		}

		@Override
		public int getCurrentPosition() throws RemoteException {
			Log.v(TAG, "==================getCurrentPosition():"+getCurrentPosition());
			return player.getCurrentPosition();
		}

		@Override
		public void seekTo(int current) throws RemoteException {
			Log.v(TAG, "==================seekTo(current):"+current);
			player.seekTo(current);
			
		}

		@Override
		public boolean setLoop(boolean loop) throws RemoteException {
			Log.v(TAG, "==================seekTo(setLoop):"+loop);
			player.setLooping(loop);
			return false;
		}
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.v(TAG, "==================onCreate");
		player = MediaPlayer.create(getApplicationContext(), Util.getPath());
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.v(TAG, "==================onUnbind(intent):"+intent);
		super.onDestroy();
		return super.onUnbind(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.v(TAG, "==================onBind(intent):"+intent);
		return new MyStubService();
	}

}
