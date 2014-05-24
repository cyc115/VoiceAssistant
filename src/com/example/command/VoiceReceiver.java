/**
 * 
 */
package com.example.command;

import android.view.View.OnClickListener;

/**
 * 
 * @author yuechuan
 *
 */
public interface VoiceReceiver {
	/**
	 * add command to the list of listener command 
	 * @param command
	 * @param start mark command as running in contrast to paused which still
	 * lives in the execution queue but does not get executed.
	 */
	public void addToCommandList(VoiceCommandListener command , boolean start);
	/**
	 * pause a command from execution but still maintain it in the execution queue
	 * the {@link OnPause} method in {@link VoiceCommandListener} should be called
	 * @param command
	 */
	public void pause(VoiceCommandListener command);
	/**
	 * resume a command from execution, the onResume method should be called in 
	 * command
	 * @param command
	 */
	public void resume(VoiceCommandListener command);
	/**
	 * remove a command from execution queue, the onKill method should be called 
	 * from the command 
	 * @param command
	 */
	public void removeFromCommandList(VoiceCommandListener command);	
}