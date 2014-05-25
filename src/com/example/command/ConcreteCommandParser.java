package com.example.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.example.backend.CommonTools;

import android.content.Context;
import android.util.Log;

/**
 * the concrete implementatiom of the 
 * command parser 
 * @author yuechuan
 *
 */
public class ConcreteCommandParser implements CommandParser{

	private static String TAG = ConcreteCommandParser.class.getName();
	private Context applicationContext ;
	/**
	 * memorize the previous command so that it is preoritized
	 * this enables multi step commands 
	 */
	private CommandActions previousCommand ;
	
	public ConcreteCommandParser(Context c) {
		applicationContext = c;
	}
	
	/*
	 * contains the command and the started or paused 
	 */
		private Map<CommandActions, Boolean> commandMap = new HashMap<CommandActions, Boolean>(); 
	@Override
	public void addToCommandList(CommandActions command) {
		commandMap.put(command, true);
		command.onStart(null);
	}

	@Override
	public void pause(CommandActions command) {
		commandMap.put(command, false);
		command.onPause(null);
	}

	@Override
	public void resume(CommandActions command) {
		commandMap.put(command, true);
		command.onResume(null);
	}

	@Override
	public void removeFromCommandList(CommandActions command) {
		commandMap.remove(command);	
		command.onKill(null);
	}

	/**
	 * TODO make this more efficient
	 * @return true if the string has been processed false otherwise 
	 * @Override
	 */
	public boolean process(String inputString ) {
		Long t1 = System.currentTimeMillis();
		Iterator<Entry<CommandActions, Boolean>> commandEntryIterator = commandMap.entrySet().iterator();
		Entry<CommandActions, Boolean> next ;
		CommonTools.getInstance().toSpeech("you have said" + inputString, true);  //repeat
		boolean handled = (previousCommand == null ?
				false :	previousCommand.onListen(applicationContext, inputString) );
		//first try passing the message to previousCommand and if that returns false then try other commands 
		if (handled == false){
			while (commandEntryIterator.hasNext()){
				next = commandEntryIterator.next();
				//only execute when the command is running 
				if (next.getValue() == true ){
					//if the user input is handled 
					if (next.getKey().onListen(applicationContext , inputString))	{
						previousCommand = next.getKey();
						Log.d(TAG,"memorized previous command:" + previousCommand.toString());
						handled = true;
						break;
					}
				}
				else {
					
				}
			}
		}
		Log.i(TAG,"command processing time(Ms) :" + (System.currentTimeMillis() - t1));
		return handled;

	}

}
