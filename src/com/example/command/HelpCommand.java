package com.example.command;

import android.content.Context;

/**
 * TODO user can say help and this should generate a 
 * tts help menu that can be navigated.the menu will 
 * help the user to learn commands and general usecases
 * @author yuechuan
 * TODO unimplemented 
 *
 */
public class HelpCommand implements CommandActions {

	@Override
	public void onStart(String... information) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onListen(Context applicationContext, String... information) {
		// TODO Auto-generated method stub
		return false;
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
