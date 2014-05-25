package com.example.command;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.example.backend.CommonTools;
import com.example.backend.Constants;
import com.example.backend.VoiceRecogString;

public class SendTextMessageCommand implements CommandActions{

	public static String TAG = SendTextMessageCommand.class.getName();
	
	public static SendTextMessageCommand instance ;
	
	private static String currentContact = null;
	private static String currentContactNumber = null;
	private static String currentMessage = null;
	
	public static SendTextMessageCommand getInstance(){
		if (instance == null){
			instance = new SendTextMessageCommand();
		}
		return instance ;
	}
	
	@Override
	public void onStart(String... information) {
		
	}
	
	public static String [] defineContact = {
		"message",
		"send message to",
		"send text message to",
		"text message to"
	};
	public static String [] composeMessage = {
		"compose",
		"compose message",
		"write",
		"text"
	};

	@Override
	public boolean onListen(Context applicationContext, String... information) {
		boolean handled = false; 
		Pair<Boolean, String> result = 
				VoiceRecogString.contains(information[0], true,VoiceRecogString.SEARCH_METHOD_PREFIX, defineContact);
		
		if (result.first == true && handled == false){ //search contact 
			handled = true;
			String numb = CommonTools.getInstance().getPhoneNumber(result.second);
			currentContact = result.second;
			currentContactNumber = numb;
			String voiceResponse = "contact cannot be found please try again";
			if (currentContactNumber == null ){
				CommonTools.getInstance().toSpeech(voiceResponse, false);
				handled = false;
			}
			else {
				CommonTools.getInstance().toSpeech("contact "+ currentContact +" found", false);
				Log.i(TAG,"contace found for " + result.second +  " : " + numb );
			}
		}
		//compose message can be called again if message is incorrect 
		if (handled == false && currentContact != null ){
			result = VoiceRecogString.contains(information[0], true,VoiceRecogString.SEARCH_METHOD_PREFIX, composeMessage);
			if (result.first ){
				handled = true;
				String confirmMsg = "send text message to " + currentContact + ": " + "with message : " + result.second;
				Log.i(TAG,confirmMsg);
				currentMessage = result.second;	//save the message for next confirmation
				CommonTools.getInstance().toSpeech(confirmMsg + " is this correct ? ", true); //confirm with user 
			}
		}
		//confirm to send 
		if (handled == false && currentContact != null && currentMessage != null) {
			Log.i(TAG,"else entered ");
			
			for (String s : Constants.positiveConfirmation){
				Log.i(TAG,"positive : " + s);
			}
			
			result = VoiceRecogString.contains(information[0], false, VoiceRecogString.SEARCH_METHOD_CONTAINS, Constants.positiveConfirmation);
			if (result.first){	//if it's a yes then send 
				handled = true;
				CommonTools.sendSMS(currentContactNumber,currentMessage);
				CommonTools.getInstance().toSpeech("message sent ", false);
				Log.i(TAG,"message sent to " + currentContact + " with number : " + currentContactNumber + " with message : " + currentMessage);
			}
			//if a no then clear message
			else {
				Log.i(TAG,"not a positive command");
				if (VoiceRecogString.contains(
						information[0],
						false,
						VoiceRecogString.SEARCH_METHOD_CONTAINS,
						Constants.negativeConfirmation).first
						){
					handled = true;
					CommonTools.getInstance().toSpeech("message cleared ", true);
					Log.i(TAG,"user stoped sending message: message is cleared");
				}

			}
			currentMessage = null ;	//clear the sent message 
		}

		return handled;
	}

	@Override
	public void onPause(String... information) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResume(String... information) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKill(String... information) {
		// TODO Auto-generated method stub
		
	}
	public String toString(){
		return TAG;
	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

}
