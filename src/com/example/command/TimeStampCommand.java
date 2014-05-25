/**
 * 
 */
package com.example.command;

import android.content.Context;

/**
 * @author yuechuan
 *
 */
public class TimeStampCommand implements CommandActions {

	@Override
	public void onStart(String... information) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onListen(Context applicationContext, String... information) {
		
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
