package com.example.backend;

import java.util.Locale;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.speech.tts.TextToSpeech;
import android.util.Log;
/**
 * contains text to speech utils, wake screen  
 * and other common tools
 * @author yuechuan
 *
 */
public class CommonTools {
	private static final String TAG = CommonTools.class.getName();
	Context context ;
	private PowerManager pm;
	private KeyguardManager km;
	private TextToSpeech tts;
	static CommonTools instance ;
	
	public static CommonTools getInstance(Context c){
		if (instance == null){
			instance = new CommonTools( c);
		}
		return instance;
	}
	public static CommonTools getInstance(){
		return instance;
	}
	
	
	private CommonTools (Context applicationContext){
		context = applicationContext;
	}
	
	public void toSpeech(String s){
		if (tts == null){
			tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
				
				@Override
				public void onInit(int status) {
					if(status != TextToSpeech.ERROR){
			             tts.setLanguage(Locale.UK);
		            }				
				}
			});
		}
		tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
	}
	
	public void wakeScreen() {
		//open screen 
		if (pm == null)
			 pm = (PowerManager) context.getSystemService(Activity.POWER_SERVICE);
		if (pm.isScreenOn()== false){
			Log.i(TAG,"screen is off , wake screen ");
			WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP,"MyLock" );
			wl.acquire(10000);
		}
		
		//unlock screen 
		if (km == null){
			km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE); 
		}
		final KeyguardManager.KeyguardLock kl = km .newKeyguardLock("MyKeyguardLock"); 
		kl.disableKeyguard();
	}
}
