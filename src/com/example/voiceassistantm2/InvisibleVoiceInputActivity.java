package com.example.voiceassistantm2;

import java.util.ArrayList;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.speech.RecognizerIntent;
import android.util.Log;

import com.example.backend.CommonTools;
import com.example.backend.Constants;
import com.example.command.ConcreteCommandParser;
import com.example.command.WhatCommand;

public class InvisibleVoiceInputActivity extends Activity {

	private static final String TAG = InvisibleVoiceInputActivity.class.getName();
	private ConcreteCommandParser commandParser ;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invisible_voice_input);
		Log.i(TAG,"transparent activity started");
		
		//start to listen for input once this transparent activity is created 
		Intent intent = new Intent (RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		Log.d(TAG,"intent is not null");
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "speak...");
		startActivityForResult(intent, Constants.MEDIA_BUTTON_REQUEST_CODE);
		
		//init commandParser 
		commandParser = new ConcreteCommandParser(getApplicationContext());
		//add commands to the commandParser
		commandParser.addToCommandList(WhatCommand.getInstance());
	}

	@Override 
	public void onResume(){
		super.onResume();
		Log.i(TAG,"onResume reached");
		CommonTools.getInstance(getApplicationContext()).wakeScreen();
	}



	
	@Override 
	public void onActivityResult(int requestCode , int resultCode , Intent data){
		if (resultCode == RESULT_OK){
			//handles the user voice text 
			if (requestCode == Constants.MEDIA_BUTTON_REQUEST_CODE){
				ArrayList<String> texts = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS); 
				Log.d(TAG,"voice recognized:" + texts.get(0));
				//set recognized text to the debugTv
				if (!texts.isEmpty()){
					processCommands(texts.get(0));
				}
			}
		}
		//quit the invisible activity and give back control to the user
		finish();
	}
	
	@Override
	public void finish(){
		super.finish();
	}
	private void processCommands(String string) {
		// TODO Auto-generated method stub
		commandParser.process(string);
		
	}
}
