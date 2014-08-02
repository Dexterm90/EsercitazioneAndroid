package com.example.localaudio;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
	//Boolean musicPlaying = false;
	GPSTracker gpsTracker;
	//MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        Button startButton= (Button) findViewById(R.id.startMusic);
        startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gpsTracker = new GPSTracker(MainActivity.this);
				playMusic();
				
			}
		});
        
        Button stopButton= (Button) findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stopMusic();				
			}
		});
    }

    private void playMusic() {
    	Double longitude =	gpsTracker.getLongitude();
		Double latitude  =  gpsTracker.getLatitude();
		int sum= (int) ((longitude+latitude)*1000);
		
		Intent i=new Intent(this.getApplicationContext(),MusicService.class);
		i.putExtra("START_PLAY", true);
		i.putExtra("sum", sum);
		startService(i);
		/*try{
			if(sum%2==0)
				mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.give_life_back_to_music);
			else
				mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.lose_yourself_to_dance);
			mediaPlayer.setOnCompletionListener( new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.release();
					
				}
			});
			mediaPlayer.prepare(); // might take long! (for buffering, etc)
			
			mediaPlayer.start();
		}catch(Exception e)
		{}
		musicPlaying=true;*/
	}
    
    public void stopMusic()
    {
 //   	if(musicPlaying)
    	
    		stopService(new Intent(this.getApplicationContext(),MusicService.class));
    }
/*    
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
    	super.onSaveInstanceState(savedInstanceState);
    	savedInstanceState.putBoolean("musicPlaying", musicPlaying);
    }
    
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
    	super.onRestoreInstanceState(savedInstanceState);
    	if(savedInstanceState!=null)
    		musicPlaying=savedInstanceState.getBoolean("musicPlaying");
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
