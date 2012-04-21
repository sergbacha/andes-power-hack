/**
 * 
 */

package com.andes.hack.network;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

import com.andes.hack.network.RestService;


public class ServiceHelper  {
	private static final String TAG = "ServiceHelper";
	
	/**
	 * Sends a create user request to our service
	 */
	public void sendSMS(Context context, ContentValues values) {
		
		// Create our intent to send to service
		Intent i = new Intent(context, RestService.class);
		
		// put in the new user values
		i.putExtra(RestService.EXTRA_SMS_INFO, values);
		
		// intent action so that the service know what/how to process
		i.putExtra(RestService.SERVICE_ACTION, RestService.ACTION_SEND_SMS);
		
		// Launch the service
		context.startService(i);
	}

	/**
	 * @param modelInfoActivity
	 * @param values
	 */
	public void sendSMSComment(Context context,
			ContentValues values) {
		// Create our intent to send to service
		Intent i = new Intent(context, RestService.class);
		
		// put in the new user values
		i.putExtra(RestService.EXTRA_SMS_INFO, values);
		
		// intent action so that the service know what/how to process
		i.putExtra(RestService.SERVICE_ACTION, RestService.ACTION_SEND_SMS_COMMENT);
		
		// Launch the service
		context.startService(i);
		
	}

}
