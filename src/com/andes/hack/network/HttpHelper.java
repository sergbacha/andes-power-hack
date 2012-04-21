/**
 * 
 */
package com.andes.hack.network;

public interface HttpHelper {
	
	/** Header properties */
	static final String USER_AGENT_HEADER = "User-Agent";
	static final String USER_AGENT = "quickvote";
	static final String CONTENT_TYPE_HEADER = "Content-Type";
	static final String APPLICATION_JSON = "application/x-www-form-urlencoded";
	static final String AUTHORIZATION_HEADER = "Authorization";
	static final String CONNECTION_HEADER = "Connection";
	
	static final String PROTOCOL = "https";
	static final String HOST = "localhost";
	static final String FORWARD_SLASH = "/";
	static final String REST_VERSION = "/v1";
	
	// THE HTTP methods
	static final String HTTP_GET = "GET";
	static final String HTTP_POST = "POST";
	static final String HTTP_PUT = "PUT";
	static final String HTTP_DELETE = "DELETE";
}
