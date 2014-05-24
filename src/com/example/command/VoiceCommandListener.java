/**
 * 
 */
package com.example.command;

/**
 * @author yuechuan
 *
 */
public interface VoiceCommandListener {
	/**
	 * all {@link VoiceCommandListener}s should be singleton 
	 * and implements {@code getInstance} 
	 * @return an running instance of the listener
	 */
	public VoiceCommandListener getInstance();
	/**
	 * initializes the {@link VoiceCommandListener}
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
	public void onListen(String ...information);
	/**
	 * called when the VoiceReceiver decides to pause 
	 * the {@link VoiceCommandListener}, this can be used to 
	 * store unstore data.
	 * @param information
	 */
	public void onPause(String ...information);
	/**
	 * when resuming from a pause, this should restores all values 
	 * and event that was turned off by the onPause 
	 * @param information
	 */
	public void onResume (String ... information);
	/**
	 * before completely shutting off a {@link VoiceCommandListener}
	 * and removing it from the execution line permanently  
	 * @param information
	 */
	public void onKill(String ... information);
}
