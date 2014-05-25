package com.example.command;

/**
 * this interface defines Location based actions that should be executed 
 * when the phone is at a certain location. a use case for that is when 
 * you are at the supermarket and the phone will remind you what you need 
 * to buy when you are around the area. 
 * TODO need to think over the implementations  
 * @author yuechuan
 *
 */
public interface LocationEvent {
	/**
	 * actions to take while at the location 
	 * @param strings
	 */
	public void onLocation(String ...strings );
	/**
	 * generate a string of help manual, this will 
	 * either be displayed or dictated to the user 
	 * 
	 * @return
	 */
	public String help();
	/**
	 * generate a string description of current class
	 * this should be brief since {@link help()} 
	 * can generate more detailed help string
	 * @return
	 */
	public String toString();

}
