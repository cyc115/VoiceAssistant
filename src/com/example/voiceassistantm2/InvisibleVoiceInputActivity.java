package com.example.voiceassistantm2;

import java.util.ArrayList;

import com.example.backend.Constants;

import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class InvisibleVoiceInputActivity extends Activity {

	private static final String TAG = InvisibleVoiceInputActivity.class.getName();

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

	private void processCommands(String string) {
		// TODO Auto-generated method stub
		
	}
}
