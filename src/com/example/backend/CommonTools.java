package com.example.backend;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.util.Log;

import com.example.voiceassistantm2.InvisibleVoiceInputActivity;
/**
 * contains text to speech utils, wake screen  
 * and other common tools
 * @author yuechuan
 *
 */
public class CommonTools {
	private static final String TAG = CommonTools.class.getName();
	Context context ;
	private PowerManager pm;
	private KeyguardManager km;
	private TextToSpeech tts;
	static CommonTools instance ;

	private static SmsManager sms = SmsManager.getDefault();
	
	
	
	public static CommonTools getInstance(Context c){
		if (instance == null){
			instance = new CommonTools( c);
			
			//initialize tts engine here to allow some rest time 
			initTTS(c);
		}
		return instance;
	}
	/**
	 * method used to initialize tts engine
	 * @param c
	 */
	private static void initTTS(Context c) {
		instance.tts = new TextToSpeech(c, new TextToSpeech.OnInitListener() {
			
			@Override
			public void onInit(int status) {
				if(status != TextToSpeech.ERROR){
		             instance.tts.setLanguage(Locale.UK);
		        }				
			}
		});
	}
	public static CommonTools getInstance(){
		return instance;
	}
	
	
	private CommonTools (Context applicationContext){
		context = applicationContext;
	}
	
	public void toSpeech(String s,boolean queueFlush){
		if (tts == null){
			initTTS(context);
		}
		
		int command = (queueFlush ? TextToSpeech.QUEUE_FLUSH : TextToSpeech.QUEUE_ADD);
		tts.speak(s, command, null);
	}
	
	public void wakeScreen() {
		//open screen 
		if (pm == null)
			 pm = (PowerManager) context.getSystemService(Activity.POWER_SERVICE);
		if (pm.isScreenOn()== false){
			Log.i(TAG,"screen is off , wake screen ");
			WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP,"MyLock" );
			wl.acquire(10000);
		}
		
		//unlock screen 
		if (km == null){
			km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE); 
		}
		final KeyguardManager.KeyguardLock kl = km .newKeyguardLock("MyKeyguardLock"); 
		kl.disableKeyguard();
	}
	/**
	 * return phone numbe or null if unfound 
	 * @param name
	 * @return
	 */
	public String getPhoneNumber(String name) {
		String ret = null;
		String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" like'%" + name +"%'";
		String[] projection = new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER};
		Cursor c = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
		        projection, selection, null, null);
		if (c.moveToFirst()) {
		    ret = c.getString(0);
		} 
		c.close();
		return ret;
	} 
	/**
	 * return contact name or null
	 * @param phoneNumber
	 * @return
	 */
	public String getContactName(String phoneNumber) {
	    ContentResolver cr = context.getContentResolver();
	    Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
	    Cursor cursor = cr.query(uri, new String[]{PhoneLookup.DISPLAY_NAME}, null, null, null);
	    if (cursor == null) {
	        return null; 
	    } 
	    String contactName = null;
	    if(cursor.moveToFirst()) {
	        contactName = cursor.getString(cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME));
	    } 
	 
	    if(cursor != null && !cursor.isClosed()) {
	        cursor.close();
	    } 
	    return contactName;
	}
	
	/**
	 * true if msg sent successfully 
	 * false other wise 
	 * @param number
	 * @param message
	 * @return
	 */
	public static boolean sendSMS(String number , String message){
		if (message.length()> 0 ){
			sms.sendTextMessage(number, null, message, null, null);
			Log.d(TAG , "message sent successfully:" + message);
			return true ;
		}
		else {
			Log.d(TAG , "message body has length 0 or less. do not send msg");
			return false ;
		}

				
	}
	
	public static void startVoiceCommand(Context context) {
		Intent i = new Intent(context,InvisibleVoiceInputActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}

	//TODO how to enable the file is open when I need to and close when finish without holding 
	// resources for too long ? 
	//TODO is this thread safe ?
	/**
	 * 
	 * @param fileName see the list of filenames in {@link Constants} 
	 * @param text text tot be written 
	 * @param WriteMode append ,overwrite , private and stuff 
	 * see {@link Constants} FLAG_FILE_IO_* for more info
	 * @throws IOException \
	 */
	public void writeToFile(String fileName ,String text, int WriteMode )
			throws IOException{
		FileOutputStream fos = context.openFileOutput(fileName, WriteMode);
		fos.write(text.getBytes());
		fos.close();
	}
	

}