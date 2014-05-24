
/**
 * 
 */
package com.example.command;

import java.util.Locale;

import com.example.backend.CommonTools;

import android.content.Context;
import android.speech.tts.TextToSpeech;

/**
 * @author yuechuan
 *
 */
public class WhatCommand implements CommandActions{
	
	private static WhatCommand instance ;
	
	private TextToSpeech tts ;
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
	public void onListen(Context applicationContext, String... information) {
		tools = CommonTools.getInstance(applicationContext);
		tools.toSpeech(information[0]);

		
	}

	@Override
	public void onPause(String... information) {
		if (tts != null){
			tts.stop();
		}
	}

	@Override
	public void onResume(String... information) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKill(String... information) {
		//release resources
		tts.shutdown();
		tts = null;
		
	}

}
