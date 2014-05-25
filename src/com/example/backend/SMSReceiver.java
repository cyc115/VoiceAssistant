/**
 * 
 */
package com.example.backend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * @author yuechuan
 * receives and speak out the text message 
 */
public class SMSReceiver extends BroadcastReceiver {

	final SmsManager sms = SmsManager.getDefault();
	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		final Bundle bundle = intent.getExtras();
		try {
			if (bundle != null){
				final Object [] pdusObj = (Object[]) bundle.get("pdus");
				for (int i = 0 ; i < pdusObj.length ; i++){
					SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
		            String phoneNumber = currentMessage.getDisplayOriginatingAddress();
		             
		            String senderNum = phoneNumber;
		            String message = currentMessage.getDisplayMessageBody();
		 
		            Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);
		            
		            String contactName = CommonTools.getInstance().getContactName(senderNum);
		            CommonTools.getInstance().toSpeech("you have received a text message from " + contactName +"with the message : " + message , false);
		           // Show alert
		            int duration = Toast.LENGTH_LONG;
		            Toast toast = Toast.makeText(context, "senderNum: "+ senderNum + ", message: " + message, duration);
		            toast.show();
				}
			}
		} catch (Exception e) {
		    Log.e("SmsReceiver", "Exception smsReceiver" +e);
		     
		}

	}

}
