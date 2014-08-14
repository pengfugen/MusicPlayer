package app.android.musicserver;
interface IMusicService
{
	void play();
	void pause();
	void stop();
	int getDuration();
	int getCurrentPosition();
	void seekTo(int current);
	boolean setLoop(boolean loop);
}