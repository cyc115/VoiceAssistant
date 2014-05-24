/**
 * 
 */
package com.example.backend;

import com.example.voiceassistantm2.InvisibleVoiceInputActivity;
import com.example.voiceassistantm2.MainActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.util.Log;

/**
 * @author yuechuan
 *
 */
public class MediaButtonReceiver extends BroadcastReceiver {

	private static final String TAG = MediaButtonReceiver.class.getName();
	/**
	 * this is used as a walk around the problem : receiving two
	 * identical broadcast
	 */
	private static Long previousReceivedTime = System.currentTimeMillis();
	private static Long currentTime = System.currentTimeMillis();
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if (broadCastIsNotAEcho()){
			String action = intent.getAction();
			if (action.equals(FloatingVoiceInputIconService.MEDIA_BUTTON_FILTER)){
				//TODO do action when received press
				Log.d(TAG,"media button pressed:" + intent.getStringExtra("message"));
				//TODO start the invisible activity to listen 
				Intent i = new Intent(context,InvisibleVoiceInputActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(i);
				
//				//bring app to forground
//				Intent i =  new Intent(context,MainActivity.class);
//				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
//				context.startActivity(i);
			}
			else {
				//when action not recognized
				//don't need to implement 
				
			}
		}

		
	}
	/**
	 * my phone receives two messages per button press
	 * for some reason. do this to filter out extra messages
	 * @return
	 */
	private boolean broadCastIsNotAEcho() {
		currentTime = System.currentTimeMillis();
		if (currentTime - previousReceivedTime >= 100){
			Log.i(TAG,"current:"+ currentTime + "previ:" + previousReceivedTime +"diff:" + (currentTime - previousReceivedTime));
			
			previousReceivedTime = currentTime ;
			
			return true ;
		}
		else {
			Log.i(TAG,"current:"+ currentTime + "previ:" + previousReceivedTime +"diff:"+ (currentTime - previousReceivedTime));
			previousReceivedTime = currentTime ;
			return false ;
		}
			
	}

	

}
