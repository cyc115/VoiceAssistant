/**
 * 
 */
package com.example.backend;

import android.content.Context;

/**
 * @author yuechuan
 *	contains global constants that I need 
 */
public class Constants {

	public static final int MEDIA_BUTTON_REQUEST_CODE = 1000;
	
	/**
	 * used for speech recognition 
	 */
	public static final String [] positiveConfirmation = {
		"yes",
		"positive",
		"right"
	};
	/**
	 * used for speech recognition 
	 */
	public static final String [] negativeConfirmation = {
		"no",
		"nop",
		"negative",
		"wrong"
	};

	/**
	 * below are constants fro file io used in {@link CommonTools}
	 * writeToFile()
	 * 
	 */
	public static final int FLAGE_FILE_IO_PRIVATE = Context.MODE_PRIVATE;
	public static final int FLAGE_FILE_IO_APPEND = Context.MODE_APPEND;
	
	
	/**
	 * below are file names used by file io for some of the commands 
	 */
}
