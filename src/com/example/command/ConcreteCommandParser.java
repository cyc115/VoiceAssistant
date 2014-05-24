package com.example.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
	 * @Override
	 */
	public void process(String inputString ) {
		Long t1 = System.currentTimeMillis();
		Iterator<Entry<CommandActions, Boolean>> commandEntryIterator = commandMap.entrySet().iterator();
		Entry<CommandActions, Boolean> next ;
		while (commandEntryIterator.hasNext()){
			next = commandEntryIterator.next();
			//only execute when the command is running 
			if (next.getValue() == true ){
				next.getKey().onListen(applicationContext , inputString);
			}
			else {} //skip this entry
		}
		Log.i(TAG,"command processing time(Ms) :" + (System.currentTimeMillis() - t1));
	}

}
