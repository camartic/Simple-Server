package com.camartic.api;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

public class NetflixAPI extends DefaultApi10a{

	private static final String REQUEST_TOKEN_URL = "http://api.netflix.com/oauth/request_token";
	private static final String ACCESS_TOKEN_URL = "http://api.netflix.com/oauth/access_token";
	private static final String AUTHORIZE_URL = "https://api-user.netflix.com/oauth/login?application_name=%s&oauth_callback=%s&oauth_consumer_key=%s&oauth_token=%s";
	  
	@Override
	public String getAccessTokenEndpoint() {
		// TODO Auto-generated method stub
		return ACCESS_TOKEN_URL;
	}
	
	@Override
	public String getAuthorizationUrl(Token requestToken) {
		// TODO Auto-generated method stub
		return "";
	}
	public static String getAuthorizationUrlNew(Token requestToken, String appName, String callBack, String key) {
		return String.format(AUTHORIZE_URL, appName, callBack, key, requestToken.getToken());
	}
	
	@Override
	public OAuthService createService(OAuthConfig config) {
		// TODO Auto-generated method stub
		return super.createService(config);
	}

	@Override
	public String getRequestTokenEndpoint() {
		// TODO Auto-generated method stub
		return REQUEST_TOKEN_URL;
	}

}
