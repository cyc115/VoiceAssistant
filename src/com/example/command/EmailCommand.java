/**
 * 
 */
package com.example.command;

import android.content.Context;

/**
 * @author yuechuan
 *	TODO
 */
public class EmailCommand implements CommandActions{

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
