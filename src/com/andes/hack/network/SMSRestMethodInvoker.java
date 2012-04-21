/**
 * 
 */
package com.andes.hack.network;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import android.content.Context;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

public class SMSRestMethodInvoker implements HttpHelper{
	private static final String TAG = "RestMethodInvoker";
	private static final boolean DEBUG = true;
	
	HttpsURLConnection mHttpsURLConnection;
	Context mContext;

	private URL mUrl;
	private String mResource;
	 
	public SMSRestMethodInvoker(Context context) {
		mContext = context;
//		https://api.oneapi4sms.com/v1/smsmessaging/outbound/13344241065/requests
		// Create the URL
		try {
			if (DEBUG){
//				mUrl = new URL(PROTOCOL, "169.254.94.152", 3000, resource);
//				mUrl = new URL(PROTOCOL, "api.oneapi4sms.com/v1/smsmessaging/outbound/13344241065/requests", 3000, resource);
				
				mUrl = new URL("https://api.oneapi4sms.com/v1/smsmessaging/outbound/13344241065/requests");
			}
			else
				mUrl = new URL(PROTOCOL, "www.google.com", "");
		} catch (MalformedURLException e) {
						
			e.printStackTrace();
		}

	}

	public static SMSRestMethodInvoker buildRestMethodInvoker(Context context ) {		
		return new SMSRestMethodInvoker(context);
	}

	/**
	 * Send a post request to our server. It will use the URL that was created
	 * at object initialization (see buildRestMethodInvoker(...))
	 * 
	 * @param newUser the JSON data we will add to our request body
	 */
	public JsonNode post(final ObjectMapper jsonMapper, final String jsonData) {
		JsonNode postResponse = null;
		
		try {
			Log.d(TAG, "posting: "+mUrl.toString());
			
			// open the connection
			mHttpsURLConnection = (HttpsURLConnection) mUrl.openConnection();
			
			
			// specificy other settings that all request verbs have in common
			loadCommonHttpSettings();
			
			// specific settings settings of this verb
			mHttpsURLConnection.setDoOutput(true);
			mHttpsURLConnection.setDoInput(true);
			mHttpsURLConnection.setRequestMethod(HTTP_POST);
//			mHttpsURLConnection.setHostnameVerifier( new HostnameVerifier() {
//				@Override
//				public boolean verify(String hostname, SSLSession session) {
//					// TODO Auto-generated method stub
//					return true;
//				}
//			});

//			// get our ssl factory to accept any certificate if we wre in debug mode
//			if (DEBUG) {
//				SSLSocketFactory sslf = createSSLSocketFactoryNoVal();
//				// set the ssl certificate acceptance in our connection
//				mHttpsURLConnection.setSSLSocketFactory(sslf);
//			}
			
//			// add data to connection
//			final String data = jsonData.toJsonString(jsonMapper);
//			/** Performance suggestions per ANdroid javadocs HttpURLConnection*/
			mHttpsURLConnection.setFixedLengthStreamingMode(jsonData.length());
			

			// remember we set http.keepalive in IntroActivity to false
			
			// send the output // could also use DataOutputStream
			BufferedOutputStream output = new BufferedOutputStream(mHttpsURLConnection.getOutputStream());
			output.write(jsonData.getBytes());
			output.flush();
			
//			output.close();
			
			// get the input
			postResponse = jsonMapper.readTree(mHttpsURLConnection.getInputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			// ALWAYS DISCONNECT
			mHttpsURLConnection.disconnect();
		}
		
		return postResponse;
	}
	
//	/**
//	 * Invokes a PUT request to our server.
//	 * 
//	 * @param mJsonMapper
//	 * @param moveVO
//	 * @return 
//	 */
//	public JsonNode put(ObjectMapper jsonMapper, MoveVO jsonData) {
//		JsonNode putResponse = null;
//		
//		try {
//			Log.d(TAG, "putting: "+ mUrl.toString());
//			
//			// create the connection
//			mHttpsURLConnection = (HttpsURLConnection) mUrl.openConnection();
//			
//			// set common connections settings to all verbs
//			loadCommonHttpSettings();
//			
//			// options of the connection
//			mHttpsURLConnection.setDoOutput(true);
//			mHttpsURLConnection.setDoInput(true);
//			mHttpsURLConnection.setRequestMethod(HTTP_PUT);
//			mHttpsURLConnection.setHostnameVerifier( new HostnameVerifier() {
//				@Override
//				public boolean verify(String hostname, SSLSession session) {
//					// TODO Auto-generated method stub
//					return true;
//				}
//			});
//			
//			// get our ssl factory to accept any certificate if we wre in debug mode
//			if (DEBUG) {
//				SSLSocketFactory sslf = createSSLSocketFactoryNoVal();
//				// set the ssl certificate acceptance in our connection
//				mHttpsURLConnection.setSSLSocketFactory(sslf);
//			}
//			
//
//			// add data to connection
//			final String data = jsonData.toJsonString(jsonMapper);
//			/** Performance suggestions per ANdroid javadocs HttpURLConnection*/
//			mHttpsURLConnection.setFixedLengthStreamingMode(data.length());
//			
//
//			// remember we set http.keepalive in IntroActivity to false
//			
//			// send the output // coyld also use DataOutputStream
//			BufferedOutputStream output = new BufferedOutputStream(mHttpsURLConnection.getOutputStream());
//			output.write(data.getBytes());
//			output.flush();
//			
//			output.close();
//			
//			// get the input
//			putResponse = jsonMapper.readTree(mHttpsURLConnection.getInputStream());
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally {
//			// ALWAYS DISCONNECT
//			mHttpsURLConnection.disconnect();
//		}
//		
//		return putResponse;
//	}
//	
//	/**
//	 * Send a get request to our server and return
//	 * the response in json format.
//	 * Returns null if got an error or nothing
//	 * 
//	 * @param mJsonMapper 
//	 * @return 
//	 */
//	public JsonNode get(final ObjectMapper jsonMapper) {
//		JsonNode getResponse = null;
//		
//		try {
//			mHttpsURLConnection = (HttpsURLConnection) mUrl.openConnection();
//			loadCommonHttpSettings();
//			
//			/** Performance suggestions per ANdroid javadocs HttpURLConnection*/
//			// remember we set http.keepalive in IntroActivity to false
//
//			// read the input into our JSON vo wrapper
//			getResponse = jsonMapper.readTree(mHttpsURLConnection.getInputStream());
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally {
//			mHttpsURLConnection.disconnect();
//		}
//		
//		return getResponse;
//	}

	/**
	 * Common Http Setting that all calls will us.
	 * Like they all will use the same user-agent
	 * and the same enocding type.
	 * 
	 */
	private void loadCommonHttpSettings() {
		mHttpsURLConnection.addRequestProperty(USER_AGENT_HEADER, USER_AGENT + " ( " + Build.DEVICE + " " + Build.ID + ")");
		mHttpsURLConnection.setRequestProperty(CONTENT_TYPE_HEADER, APPLICATION_JSON);
		mHttpsURLConnection.addRequestProperty(AUTHORIZATION_HEADER, "Basic ODI6NGUwOWJjN2M5ZWM2ZTI2ZjJkYTkyMWVkZGMxYmU4ZWU=");// + Base64.encode("82:4e09bc7c9ec6e26f2da921eddc1be8ee".getBytes(), Base64.NO_WRAP));
		mHttpsURLConnection.addRequestProperty(CONNECTION_HEADER, "Keep-Alive");
		
	}

	/**
	 * Prepare the headers for our Http call
	 * 
	 */
	private void prepareHeaders() {
		// TODO Auto-generated method stub
		
	}
	

	/**
	 * Creates SSL Factory that accepts all certificates. This is used
	 * to debug the app. Very necessary.
	 * @return
	 */
	private SSLSocketFactory createSSLSocketFactoryNoVal() {
		SSLContext context = null;
		try{
			// create a keystore with keystore type: jks
			// find more types: http://publib.boulder.ibm.com/infocenter
			// /wasinfo/v6r1/index.jsp?topic=%2Fcom.ibm.websphere.base.
			// doc%2Finfo%2Faes%2Fae%2Fcsec_sslkeystoreconfs.html
				
			// init trust factory
			TrustManager[] trustAllCerts = new TrustManager[]{
				    new X509TrustManager() {
						
						@Override
						public X509Certificate[] getAcceptedIssuers() {
							// TODO Auto-generated method stub
							return null;
						}
						
						@Override
						public void checkServerTrusted(X509Certificate[] chain, String authType)
								throws CertificateException {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void checkClientTrusted(X509Certificate[] chain, String authType)
								throws CertificateException {
							// TODO Auto-generated method stub
							
						}
					}
				};

			   
			// context of the SSL
			context = SSLContext.getInstance("TLS");
			context.init(null, trustAllCerts, null);
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}

		if (context != null)
			return context.getSocketFactory();
		
		return null;
	}
}

