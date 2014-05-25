package com.example.backend;

import android.util.Log;
import android.util.Pair;

public class VoiceRecogString {
	public final static int SEARCH_METHOD_PREFIX = 1;  
	public final static int SEARCH_METHOD_CONTAINS= 2;
	private static final String TAG = VoiceRecogString.class.getName();  
	/**
	 * check if text contains key 
	 * @param text
	 * @param key
	 * @param removeStringAfterRecog
	 * @param searchMethod {@code SEARCH_METHOD_CONTAINS}  or {@code SEARCH_METHOD_PREFIX}
	 * @return return a pair containing boolean (contains or not ) and a string (
	 * manipulated text
	 */
	public static Pair<Boolean, String > contains
		(String text,boolean removeStringAfterRecog,int searchMethod,String ... key  ){
		Boolean result = false ;
		String replaceString = null ;
		String resultString = "";
		text = text.toLowerCase().trim();
		if (searchMethod == SEARCH_METHOD_PREFIX){
			for (String k : key){
				result = text.startsWith(k.toLowerCase().trim());
				if (result == true) {
					replaceString = k;
					break;
				}
			}
		}
		else if (searchMethod == SEARCH_METHOD_CONTAINS){
			for (String k : key){
				result = text.contains(k.toLowerCase().trim());
				if (result == true) {
					replaceString = k;
					break;
				}
			}
		}
		else {
			result = null;
			resultString = null;
		}
		//remove string or not 
		if (removeStringAfterRecog && result == true ){
			if (searchMethod == SEARCH_METHOD_PREFIX){
				resultString = text.replace(replaceString, "").trim();
			}
			else if (searchMethod == SEARCH_METHOD_CONTAINS){
				//remove up to keyword
				resultString = truncateStringUptoKeyword(text,replaceString);
			}
		}
		else if (result == true ) {
			//if don't truncate string 
			resultString = text ;
		}
		Log.d(TAG,"look for " +replaceString + " in "+ text + ",searchMethod: " 
		+ searchMethod +" removeString afterRecog: " + removeStringAfterRecog);
		Log.d(TAG,"contains keyword: " + result+ " returned string: " + resultString);
		
		return new Pair<Boolean,String> (result,resultString);
	}
	/**
	 * truncate given text after the first occurance of the keyword
	 * @param text
	 * @param prefix
	 * @return
	 */
	private static String truncateStringUptoKeyword(String text, String prefix) {
		int firstIdxOfKeyword = text.indexOf(prefix);
		return text.substring(firstIdxOfKeyword+prefix.length());
	}
	
	
}
