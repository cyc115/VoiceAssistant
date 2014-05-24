package com.example.voiceassistantm2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.backend.MediaButtonService;

public class MainActivity extends ActionBarActivity {

    protected static final String TAG = MainActivity.class.getName();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //important do not remove
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }
    @Override
    protected void onStart(){
    	super.onStart();
    	//add button listeners 
    	Button start = (Button) findViewById(R.id.frag_main_start_button);
    	Button stop = (Button) findViewById(R.id.frag_main_stop_button);
    	start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//start service 
				Intent intent = new Intent(MediaButtonService.MEDIA_BUTTON_SERVICE_FILTER);
				startService(intent);
			}
		});
    	
    	stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MediaButtonService.MEDIA_BUTTON_SERVICE_FILTER);
				boolean i = stopService(intent);
				Log.d(TAG,"service stoped " + i);
			}
		});
    }
     
	TextView tv ;
    public void display(){
    	tv = (TextView) findViewById(R.id.frag_main_text_view);
    	int i = Integer.parseInt(tv.getText().toString());
    	tv.setText(++i);   	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
