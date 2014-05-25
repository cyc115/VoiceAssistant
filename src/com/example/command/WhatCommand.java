
/**
 * 
 */
package com.example.command;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.Pair;

import com.example.backend.CommonTools;
import com.example.backend.VoiceRecogString;

/**
 * @author yuechuan
 *
 */
public class WhatCommand implements CommandActions{
	
	private static final String TAG = WhatCommand.class.getName();

	private static WhatCommand instance ;
	
	private CommonTools tools ;


	private WhatCommand(){
		
	}
	
	public static WhatCommand getInstance(){
		if (instance == null){
			instance = new WhatCommand();
		}
		return instance;
	}

	@Override
	public void onStart(String... information) {
	}

	@Override
	public boolean onListen(Context applicationContext, String... information) {
		boolean handled = false;
		String whatTime = "what time";
		String whatTime2 = "what's the time";
		String whatTime3 = "what's a time";
		String whatDate = "what date";
		String whatDay = "what day";
		
		tools = CommonTools.getInstance(applicationContext);
		//what time 
		
		Pair<Boolean, String> result = VoiceRecogString
				.contains(information[0],  false, VoiceRecogString.SEARCH_METHOD_PREFIX,whatTime,whatTime2,whatTime3);
		if (result.first == true && !handled){
			DateFormat df = new SimpleDateFormat("h:mm a");
			String date = df.format(Calendar.getInstance().getTime());
			Log.i(TAG,"date obtained" + date);
			tools.toSpeech("current time is " + date, false);
			 handled = true;
		}
		//what date
		result = VoiceRecogString
				.contains(information[0],false, VoiceRecogString.SEARCH_METHOD_PREFIX,whatDate,whatDay);
		
		if (result.first == true && !handled){
			DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
			String date = df.format(Calendar.getInstance().getTime());
			Log.i(TAG,"date obtained" + date);
			tools.toSpeech("today is" + date, false);
			handled = true;
		}
		return handled;	
	}

	@Override
	public void onPause(String... information) {

	}

	@Override
	public void onResume(String... information) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKill(String... information) {
		
	}

	@Override
	public String toString() {
		return TAG;
	}
}
