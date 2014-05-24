/**
 * 
 */
package com.example.command;

import android.content.Context;

/**
 * @author yuechuan
 *
 */
public interface CommandActions {
	/**
	 * all {@link CommandActions}s should be singleton 
	 * and implements {@code getInstance} 
	 * @return an running instance of the listener
	 */
	public CommandActions getInstance();
	/**
	 * initializes the {@link CommandActions}
	 * @param information
	 */
	public void onStart(String ...information);
	/**
	 * called when the voice input recognizes voice input 
	 * and have converted it into strings 
	 * @param information information to be passed on to the 
	 * listeners , in most cases this will be the speech 
	 * recognized
	 */
	public void onListen(Context applicationContext ,String ...information );
	/**
	 * called when the VoiceReceiver decides to pause 
	 * the {@link CommandActions}, this can be used to 
	 * store unstore data.
	 * @param information
	 * @deprecated no need to implement unless in special cases
	 */
	public void onPause(String ...information);
	/**
	 * when resuming from a pause, this should restores all values 
	 * and event that was turned off by the onPause 
	 * @param information
	 * @deprecated no need to implement unless in special cases
	 */
	public void onResume (String ... information);
	/**
	 * before completely shutting off a {@link CommandActions}
	 * and removing it from the execution line permanently  
	 * @param information
	 * @deprecated no need to implement unless in special cases
	 */
	public void onKill(String ... information);
}
