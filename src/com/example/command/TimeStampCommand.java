/**
 * 
 */
package com.example.command;

import java.io.IOException;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.example.backend.CommonTools;
import com.example.backend.Constants;
import com.example.backend.VoiceRecogString;

/**
 * @author yuechuan
 *
 */
public class TimeStampCommand implements CommandActions {

	public static CommandActions getInstance() {
		if (instance == null){
			instance = new TimeStampCommand();
		}
		return instance;
	}

	private static TimeStampCommand instance ; 
	private String[]timeStampCommand = {
			"timestamp",
			"add timestamp",
			"record time"
	};
	private String TAG = TimeStampCommand.class.getName();

	@Override
	public void onStart(String... information) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * write the time stamp to csv formate
	 * @Override
	 */
	public boolean onListen(Context applicationContext, String... information) {
		CommonTools ct = CommonTools.getInstance();
		boolean handled = false ;
		Pair<Boolean, String> result = VoiceRecogString.contains(
						information[0],
						true,
						VoiceRecogString.SEARCH_METHOD_PREFIX,
						timeStampCommand);
		//if user say "timestamp" or "add timestamp"
		if (result.first){
			handled = true ;
			String currentTime = ct.getCurrentTime();
			String currentDate = ct.getDate();
			ct.toSpeech("added timestamp on " + currentTime , false);
			//csv entry write to the file : cDate ,cTime , Note
			String text = currentDate + "," + currentTime + "," + result.second;
			try {
				ct.writeToFile(
						Constants.FILE_NAME_TIMESTAMP,
						text,
						Constants.FLAGE_FILE_IO_APPEND);
				Log.i(TAG, "time stamp successfully appended " + text);
			} catch (IOException e) {
				Log.e(TAG ,"error writing to file : " + e.getMessage());
			}
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

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}


}
