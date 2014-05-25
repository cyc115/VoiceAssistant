/**
 * 
 */
package com.example.command;

import android.view.View.OnClickListener;

/**
 * a controlized controller that passes the to be processed string
 * to  each of the CommandAxtions for process
 * @author yuechuan
 *
 */
public interface CommandParser {
	/**
	 * add command to the list of listener command 
	 * @param command
	 * @param start mark command as running in contrast to paused which still
	 * lives in the execution queue but does not get executed.
	 */
	public void addToCommandList(CommandActions command );
	/**
	 * pause a command from execution but still maintain it in the execution queue
	 * the {@link OnPause} method in {@link CommandActions} should be called
	 * @param command
	 */
	public void pause(CommandActions command);
	/**
	 * resume a command from execution, the onResume method should be called in 
	 * command
	 * @param command
	 */
	public void resume(CommandActions command);
	/**
	 * remove a command from execution queue, the onKill method should be called 
	 * from the command 
	 * @param command
	 */
	public void removeFromCommandList(CommandActions command);
	
	public boolean process(String inputString);
}