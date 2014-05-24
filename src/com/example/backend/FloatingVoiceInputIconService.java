/**
 * 
 */
package com.example.backend;

import android.R;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * service responsible to listen to the media button press even when 
 * the main app is destoried 
 * @author yuechuan
 *
 */
public class FloatingVoiceInputIconService extends Service {

	public static final String MEDIA_BUTTON_FILTER = "android.intent.action.MEDIA_BUTTON";
	public static final String MEDIA_BUTTON_SERVICE_FILTER = "com.example.backend.MediaButtonService";
	protected static final String TAG = FloatingVoiceInputIconService.class.getName();
	/**
	 * used to creat floating stuff
	 */
	private WindowManager windowManager;
	private ImageView chatHead;
	
	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate(){
		super.onCreate();
		
		Log.d(TAG,"media button Listener service created");
		//add floating button 
		windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		chatHead = new ImageView(this);
		chatHead.setImageResource(R.drawable.ic_lock_power_off);
		
		final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_PRIORITY_PHONE,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT	);
		params.gravity = Gravity.TOP | Gravity.LEFT;
		params.x = 0;
		params.y = 100;
		windowManager.addView(chatHead, params);
		//make dragable 
		try {
			chatHead.setOnTouchListener(new View.OnTouchListener() {
				private WindowManager.LayoutParams paramsF = params;
				private int initialX;
				private int initialY;
				private float initialTouchX;
				private float initialTouchY;

				@Override public boolean onTouch(View v, MotionEvent event) {
					Log.i(TAG,"on drag");
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:

						// Get current time in nano seconds.
						initialX = paramsF.x;
						initialY = paramsF.y;
						initialTouchX = event.getRawX();
						initialTouchY = event.getRawY();
						break;
					case MotionEvent.ACTION_UP:
						break;
					case MotionEvent.ACTION_MOVE:
						paramsF.x = initialX + (int) (event.getRawX() - initialTouchX);
						paramsF.y = initialY + (int) (event.getRawY() - initialTouchY);
						windowManager.updateViewLayout(chatHead, paramsF);
						break;
					}
					return false;
				}
			});
			
			chatHead.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Log.i(TAG,"icon clicked");
					//TODO start voice input 
				}
			});
		} catch (Exception e) {
		}
	}

	@Override 
	public int onStartCommand(Intent intent , int i , int i2 ){
		int a = super.onStartCommand(intent, i, i2);
		Log.d(TAG,"service onStartCommand ");
		return a ;
	}

	@Override 
	public void onDestroy(){
		super.onDestroy();
	if (chatHead != null) 
		windowManager.removeView((chatHead));
	}
}
