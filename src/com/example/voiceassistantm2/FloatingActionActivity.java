package com.example.voiceassistantm2;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.os.Build;
/**
 * this is the floating button
 * @author yuechuan
 *
 */
public class FloatingActionActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.alpha = 1.0f;
		params.dimAmount = 0f;
		getWindow().setAttributes(params);
		//floating 
		getWindow().setLayout(300, 300);
		
		setContentView(R.layout.activity_floating_action);

	}

}
