/**
 * 
 */

package com.andes.hack.network;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import com.andes.hack.Constants;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class RestService extends IntentService {
	private static final String TAG = "RestService";
	
	/** Different extras the Service Helper can send to us */
	public static final String EXTRA_SMS_INFO = "extra_sms_info";
	
	/** Different actions we will handle*/
	public static final String SERVICE_ACTION = "service_action";
	
	/** The API of our intent service. The actions we want completed */
	public static final int ACTION_SEND_SMS = 1;
	

	/** Our Jackson Json Mapper */
	//Best practices suggests to have only one Mapper
	ObjectMapper mJsonMapper;
	
	/** Our response processor */
//	ResponseProcessor mProcessor;
	
	/**
	 * @param name
	 */
	public RestService() {
		super(TAG);
	}
	
	/* (non-Javadoc)
	 * @see android.app.IntentService#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		
		mJsonMapper = new ObjectMapper();
//		mProcessor = new ResponseProcessor(getContentResolver());
	}
	
	/* (non-Javadoc)
	 * @see android.app.IntentService#onHandleIntent(android.content.Intent)
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		
		int match = intent.getIntExtra(SERVICE_ACTION, -1);
		
		switch(match) {
			case ACTION_SEND_SMS:{
				Log.d(TAG, "We have set a create user rest request");
				
				// get the values of the new user
				ContentValues values = (ContentValues) intent.getExtras().get(EXTRA_SMS_INFO);
				
			
				sendSMS(values);
				
				return;
			}

			default:
				return;
		}

	}

	/**
	 * @param values
	 */
	private void sendSMS(ContentValues values) {
		//create the root node
		JsonNode rootNode = mJsonMapper.createObjectNode();
		
		// get model number
		String phoneNumber = values.getAsString(Constants.VALUE_PHONE_NUMBER);
		
		// add the one-under user node
		
		((ObjectNode)rootNode).put(Constants.SMS_ADDRESS_FIELD, phoneNumber);
		((ObjectNode)rootNode).put(Constants.SMS_MESSAGE_FIELD, "You've been liked");
		
		
		
		String data = rootNode.toString();
		
		
		SMSRestMethodInvoker invoker = SMSRestMethodInvoker.buildRestMethodInvoker(this);
		JsonNode response = invoker.post(mJsonMapper, data);
		
		
		
	}

	

}
