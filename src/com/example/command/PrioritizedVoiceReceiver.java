/**
 * 
 */
package com.example.command;

/**
 * @author yuechuan
 *
 */
public interface PrioritizedVoiceReceiver extends VoiceReceiver {
	public final int PRIORITY_HIGH = 1;
	public final int PRIORITY_MIDIUM= 2;
	public final int PRIORITY_LOW= 3;
	/**
	 * set the priority level of a command (that exist already in 
	 * the execution queue) to the priorityLevel defined by 
	 * priorityLevel.
	 * @param command
	 * @param priorityLevel
	 */
	public void setpriority(VoiceCommandListener command , int priorityLevel);
	/**
	 * get the priority level of a command
	 * @param command
	 */
	public void getpriority(VoiceCommandListener command );
}
