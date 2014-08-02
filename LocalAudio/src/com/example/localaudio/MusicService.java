package com.example.localaudio;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MusicService extends Service {
	private MediaPlayer mp;
	Boolean musicPlaying = false;
	public static String START_PLAY= "START_PLAY";
	

	public int onStartCommand(Intent intent, int flags, int startId)
	{
		if (intent.getBooleanExtra(START_PLAY, false)) 
			if (!musicPlaying)
			{
				int sum=intent.getExtras().getInt("sum");
				Toast.makeText(this.getApplicationContext(), "The sum is "+sum, Toast.LENGTH_LONG).show();
				if(sum%2==0)
					mp=MediaPlayer.create(getApplicationContext(),R.raw.give_life_back_to_music);
				else
					mp=MediaPlayer.create(getApplicationContext(),R.raw.lose_yourself_to_dance);
				mp.setLooping(true);
				mp.start();
				musicPlaying=true;
			}
		return START_STICKY;
	}
	
	public void onDestroy()
	{
		if(musicPlaying)
		{
			mp.release();
			mp=null;
			musicPlaying=false;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
