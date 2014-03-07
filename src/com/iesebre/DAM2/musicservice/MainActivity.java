package com.iesebre.DAM2.musicservice;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private boolean mIsBound = false;
	private MusicService mServ;
	private ServiceConnection Scon =new ServiceConnection(){

		public void onServiceConnected(ComponentName name, IBinder
	     service) {
			mServ = ((MusicService.ServiceBinder)service).getService();
			//mServ = binder.getService();
		}

		public void onServiceDisconnected(ComponentName name) {
			mServ = null;
		}
		};

		void doBindService(){
	 		bindService(new Intent(this,MusicService.class),
					Scon,Context.BIND_AUTO_CREATE);
			mIsBound = true;
		}

		void doUnbindService()
		{
			if(mIsBound)
			{
				unbindService(Scon);
	      		mIsBound = false;
			}
		}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		doBindService();
		Intent music = new Intent();
    	music.setClass(MainActivity.this,MusicService.class);
    	startService(music);
		mServ = new MusicService();
		Button button1 = (Button) findViewById(R.id.button1);
		Button button2 = (Button) findViewById(R.id.button2);
		Button button3 = (Button) findViewById(R.id.button3);
		
		
    	
    	//mServ.startMusic();
		button1.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View arg0)
		    {
		    }
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View arg0)
		    {
		    	Intent music = new Intent();
		    	music.setClass(MainActivity.this,MusicService.class);
		    	mServ.stopMusic();
		    }
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View arg0)
		    {
		    	Intent music = new Intent();
		    	music.setClass(MainActivity.this,MusicService.class);
		    	mServ.pauseMusic();
		    }
		});
		
		
	}
	
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
