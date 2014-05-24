/**
 * 
 */
package com.example.backend;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

/**
 * service responsible to listen to the media button press even when 
 * the main app is destoried 
 * @author yuechuan
 *
 */
public class MediaButtonService extends Service {

	public static final String MEDIA_BUTTON_FILTER = "android.intent.action.MEDIA_BUTTON";
	public static final String MEDIA_BUTTON_SERVICE_FILTER = "com.example.backend.MediaButtonService";
	protected static final String TAG = MediaButtonService.class.getName();
	private Activity parentActivity ;
	private BroadcastReceiver receiver;
	final Handler mHandler = new Handler();
	private final Runnable task = new Runnable() {
		@Override
		public void run() {
			Log.d(TAG,"hello");
			mHandler.postDelayed(this, 1000);
		}
	};
	
	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate(){
		super.onCreate();
		
		Log.d(TAG,"media button Listener service created");
		
		//set up media button receiver
		receiver = new MediaButtonReceiver();
		
		//register receiver 
		IntentFilter filter = new IntentFilter();
		filter.addAction(MEDIA_BUTTON_FILTER);
		registerReceiver(receiver, filter);
		((AudioManager)getSystemService(AUDIO_SERVICE))
				.registerMediaButtonEventReceiver(
						new ComponentName(this,MediaButtonReceiver.class));
		

		task.run();
		
		
		
		
	}
	@Override 
	public int onStartCommand(Intent intent , int i , int i2 ){
		int a = super.onStartCommand(intent, i, i2);
		Log.d(TAG,"service onStartCommand ");
		return a ;
	}

	@Override 
	public void onDestroy(){
		super.onDestroy();
		unregisterReceiver(receiver);
		mHandler.removeCallbacks(task);
	}
}
